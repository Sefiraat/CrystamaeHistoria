package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class Chaos extends Spell {

    public Chaos() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, true, 6, false, 50, true)
            .makeDamagingSpell(1, false, 0.2, false)
            .makeProjectileSpell(this::cast, 0, false, 0, false)
            .makeProjectileVsEntitySpell(this::onHitEntity)
            .makeProjectileVsBlockSpell(this::onHitBlock)
            .makeTickingSpell(this::cast, 10, true, 5, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final Player caster = Bukkit.getPlayer(castInformation.getCaster());
        final int shots = 10;
        final double minRadius = 1;
        final double maxRadius = getRange(castInformation);
        final Location location = caster.getLocation();
        final Vector startVector = new Vector(0, 1.5, 1);
        final double alessioMath = Math.PI / 180;

        for (double a = 0; a < Math.PI * 2; a += Math.PI / shots) {
            final double radius = ThreadLocalRandom.current().nextDouble(minRadius, maxRadius);
            final double y = radius * Math.cos(a);
            final double x = radius * Math.sin(a);
            final Vector pointVector = startVector.clone()
                .add(new Vector(x, y, 0))
                .rotateAroundY(-(location.getYaw() * alessioMath));
            final Location pointLocation = location.clone().add(pointVector);
            final Block block = pointLocation.getBlock();
            if (pointLocation.getBlock().isEmpty()) {
                final MagicProjectile projectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SPECTRAL_ARROW, pointLocation, this::onTick);
                projectile.getProjectile().setGravity(false);
                projectile.setVelocity(caster.getEyeLocation().getDirection(), 1);
            } else {
                GeneralUtils.tryBreakBlock(castInformation.getCaster(), block);
            }
        }
    }

    public void onTick(MagicProjectile magicProjectile) {
        ParticleUtils.displayParticleEffect(magicProjectile.getProjectile(), Particle.GLOW, 0.2, 1);
    }

    public void onHitEntity(CastInformation castInformation) {
        GeneralUtils.damageEntity(
            castInformation.getMainTarget(),
            castInformation.getCaster(),
            getDamage(castInformation)
        );
    }

    public void onHitBlock(CastInformation castInformation) {
        GeneralUtils.tryBreakBlock(castInformation.getCaster(), castInformation.getHitBlock());
    }

    @Nonnull
    @Override
    public String getId() {
        return "CHAOS";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Spawns a forward-firing rain of",
            "chaos breaking blocks and hurting",
            "all living things."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.SOUL_LANTERN;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.HISTORICAL,
            StoryType.VOID
        );
    }
}
