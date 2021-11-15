package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.GolemGoal;
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

public class SummonGolem extends Spell {

    public SummonGolem() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 0, false, 50, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final UUID caster = castInformation.getCaster();
        final Location location = castInformation.getCastLocation();
        final Location spawnLocation = location.clone().add(
            ThreadLocalRandom.current().nextDouble(-3, 3),
            0,
            ThreadLocalRandom.current().nextDouble(-3, 3)
        );
        SpellUtils.summonTemporaryMob(
            EntityType.IRON_GOLEM,
            caster,
            spawnLocation,
            new GolemGoal(caster),
            200 + (100 * castInformation.getStaveLevel()),
            this::onTick
        );
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
        ParticleUtils.displayParticleEffect(magicSummon.getMob(), Particle.SLIME, 1, 3);
    }

    @Nonnull
    @Override
    public String getId() {
        return "SUMMON_GOLEM";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a golem that will follow you",
            "and protect you.",
            "Golems will follow you nearly anywhere."
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
