package io.github.sefiraat.crystamaehistoria.magic.spells.superclasses;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

interface Castable {

    int DEFAULT_PARTICLE_NUMBER = 4;

    void cast(@NonNull SpellCastInformation spellCastInformation);

    /**
     * Sets the last damage cause to Magic and Caster
     * @param damagedEntity The {@link LivingEntity} that was hit by the spell
     * @param spellCastInformation The {@link SpellCastInformation} containing the caster
     */
    default void setLastDamageToCaster(@NonNull LivingEntity damagedEntity, @NonNull SpellCastInformation spellCastInformation) {
        setLastDamageToCaster(damagedEntity, spellCastInformation.getCaster());
    }

    /**
     * Sets the last damage cause to Magic and Caster
     * @param damagedEntity The {@link LivingEntity} that was hit by the spell
     * @param caster The {@link LivingEntity} that cast the spell
     */
    default void setLastDamageToCaster(@NonNull LivingEntity damagedEntity, @NonNull LivingEntity caster) {
        EntityDamageByEntityEvent e = new EntityDamageByEntityEvent(caster, damagedEntity, EntityDamageEvent.DamageCause.MAGIC, 0);
        damagedEntity.setLastDamageCause(e);
    }

    default void displayParticleEffect(@NonNull Entity entity, Particle particle, double rangeRadius) {
        displayParticleEffect(entity.getLocation(), particle, rangeRadius, DEFAULT_PARTICLE_NUMBER);
    }

    default void displayParticleEffect(@NonNull Entity entity, Particle particle, double rangeRadius, int numberOfParticles) {
        displayParticleEffect(entity.getLocation(), particle, rangeRadius, numberOfParticles);
    }

    default void displayParticleEffect(@NonNull Location location, Particle particle, double rangeRadius) {
        displayParticleEffect(location, particle, rangeRadius, DEFAULT_PARTICLE_NUMBER);
    }

    default void displayParticleEffect(@NonNull Location location, Particle particle,  double rangeRadius, int numberOfParticles) {
        for (int i = 0; i < numberOfParticles; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(particle, location.clone().add(x, y ,z), 1);
        }
    }

    default void pullEntity(Location loc, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector().subtract(loc.toVector()).normalize().multiply(force);
        pushEntity(vector, pushed);
    }

    default void pushEntity(Location loc, Entity pushed, double force) {
        Vector vector = loc.toVector().subtract(pushed.getLocation().toVector()).normalize().multiply(force);
        pushEntity(vector, pushed);
    }

    default void pushEntity(Vector vector, Entity pushed) {
        vector.add(new Vector(0, 2, 0));
        pushed.setVelocity(vector);
    }

    default void damageEntity(LivingEntity livingEntity, LivingEntity caster, int damage) {
        damageEntity(livingEntity, caster, damage, null, 0);
    }

    default void damageEntity(LivingEntity livingEntity, LivingEntity caster, int damage, @Nullable Location knockbackOrigin, double knockbackForce) {
        livingEntity.damage(damage, caster);
        if (knockbackOrigin != null && knockbackForce > 0) {
            EntityUtils.push(livingEntity, knockbackOrigin, knockbackForce);
        }
    }

}
