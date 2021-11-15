package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.LeechGoal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class LeechBomb extends Spell {

    public LeechBomb() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 0, false, 50, true)
            .makeProjectileSpell(this::fireProjectile, 0, false, 0, false)
            .makeProjectileVsEntitySpell(this::eggHit)
            .makeProjectileVsBlockSpell(this::eggHit);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        Location aimLocation = location.clone().add(0, 1.5, 0).add(location.getDirection().multiply(1));
        MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.EGG, aimLocation);
        magicProjectile.setVelocity(location.getDirection(), 1.5);
    }

    @ParametersAreNonnullByDefault
    public void eggHit(CastInformation castInformation) {
        final UUID caster = castInformation.getCaster();
        final Location location = castInformation.getProjectileLocation();
        for (int i = 0; i < castInformation.getStaveLevel() * 2; i++) {
            final Location spawnLocation = location.clone().add(
                ThreadLocalRandom.current().nextDouble(-1, 1),
                0,
                ThreadLocalRandom.current().nextDouble(-1, 1)
            );
            SpellUtils.summonTemporaryMob(
                EntityType.SILVERFISH,
                caster,
                spawnLocation,
                new LeechGoal(caster),
                (60 * castInformation.getStaveLevel()),
                this::onTick
            );
        }
    }

    public void onTick(MagicSummon magicSummon) {
        Mob mob = magicSummon.getMob();
        Player player = magicSummon.getPlayer();
        Block block = player.getLocation().subtract(0, 0.5, 0).getBlock();
        if (!player.getWorld().equals(mob.getWorld())
            || (mob.getLocation().distance(player.getLocation()) > 50 && block.getType().isSolid())
        ) {
            mob.teleport(player.getLocation());
        }
        ParticleUtils.displayParticleEffect(magicSummon.getMob(), Particle.SPORE_BLOSSOM_AIR, 1, 1);
    }

    @Nonnull
    @Override
    public String getId() {
        return "LEECH_BOMB";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Throws an egg filled with viscous",
            "leeches that will attack your enemies."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.CARVED_PUMPKIN;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.HUMAN,
            StoryType.ANIMAL
        );
    }

}
