package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.SpellRecipe;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Hellscape extends Spell {

    public Hellscape() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 10, false, 10, false)
            .makeDamagingSpell(1, true, 1, false)
            .makeTickingSpell(this::fireProjectiles, 10, true, 5, false)
            .makeProjectileSpell(this::fireProjectiles, 1, false, 1, false)
            .makeProjectileVsEntitySpell(this::projectileHit)
            .addAfterProjectileHitEntityEvent(this::afterProjectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        final Location middle = castInformation.getCasterAsPlayer().getLocation().clone().add(0, 1, 0);

        for (double angle = 0; angle < Math.PI * 2; angle += Math.PI / 20) {
            final double rotated = angle + (castInformation.getCurrentTick() * 10);
            final double x = Math.cos(rotated) * 3;
            final double z = Math.sin(rotated) * 3;
            final double dx = Math.cos(rotated) * 4;
            final double dz = Math.sin(rotated) * 4;
            final Location spawn = middle.clone().add(x, 0, z);
            final Location destination = middle.clone().add(dx, 0, dz);
            final MagicProjectile magicProjectile = new MagicProjectile(EntityType.SNOWBALL, spawn, castInformation.getCaster());

            magicProjectile.setConsumer(this::projectileParticle);
            magicProjectile.disableGravity();
            magicProjectile.setVelocity(destination, 0.2);
            registerProjectile(magicProjectile, castInformation);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            damageEntity(
                livingEntity,
                castInformation.getCaster(),
                getDamage(castInformation),
                castInformation.getCasterAsPlayer().getLocation(),
                getKnockback(castInformation)
            );
        }
    }

    @ParametersAreNonnullByDefault
    public void afterProjectileHit(CastInformation castInformation) {
        displayParticleEffect(castInformation.getMainTarget(), Particle.VILLAGER_ANGRY, 1.0, 5);
    }

    @ParametersAreNonnullByDefault
    public void projectileParticle(MagicProjectile magicProjectile) {
        displayParticleEffect(magicProjectile.getProjectile(), Particle.SWEEP_ATTACK, 0.5, 1);
        displayParticleEffect(magicProjectile.getProjectile(), Particle.VILLAGER_ANGRY, 0.5, 1);
    }

    @Nonnull
    @Override
    public String getId() {
        return "HELLSCAPE";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a fiery spiral to ravage foes."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.FLINT_AND_STEEL;
    }

    @NotNull
    @Override
    public SpellRecipe getRecipe() {
        return new SpellRecipe(
            1,
            StoryType.ELEMENTAL,
            StoryType.ALCHEMICAL,
            StoryType.PHILOSOPHICAL
        );
    }
}
