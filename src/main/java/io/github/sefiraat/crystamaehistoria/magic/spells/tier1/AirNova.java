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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class AirNova extends Spell {

    public AirNova() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 10, false, 10, false)
            .makeDamagingSpell(1, true, 1, false)
            .makeProjectileSpell(this::fireProjectiles, 2, false, 2, true)
            .makeProjectileVsEntitySpell(this::projectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        final double sizeEnd = getRange(castInformation);
        final int sizeCast = 2;
        final int stepSize = 3;
        final Location middle = castInformation.getCastLocation().clone().add(0, 1.5, 0);
        for (double i = 0; i < 360; i += stepSize) {
            final double angle = (i * Math.PI / 180);
            final int sx = (int) (sizeCast * Math.cos(angle));
            final int sz = (int) (sizeCast * Math.sin(angle));
            final int dx = (int) (sizeEnd * Math.cos(angle));
            final int dz = (int) (sizeEnd * Math.sin(angle));
            final Location spawn = middle.clone().add(sx, 0, sz);
            final Location destination = middle.clone().add(dx, 0, dz);
            final MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SHULKER_BULLET, spawn);
            magicProjectile.setVelocity(destination, 1);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(
                livingEntity,
                castInformation.getCaster(),
                getDamage(castInformation),
                castInformation.getProjectileLocation().clone().subtract(0, 1, 0),
                getKnockback(castInformation)
            );
            ParticleUtils.displayParticleEffect(livingEntity, Particle.SWEEP_ATTACK, 1, 5);
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "AIR_NOVA";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a massive nova of air",
            "around you to knockback foes."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.FEATHER;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.MECHANICAL,
            StoryType.ANIMAL
        );
    }
}
