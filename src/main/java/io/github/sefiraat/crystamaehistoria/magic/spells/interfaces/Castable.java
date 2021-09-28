package io.github.sefiraat.crystamaehistoria.magic.spells.interfaces;

import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.concurrent.ThreadLocalRandom;

public interface Castable {

    void cast(@NonNull SpellDefinition spellDefinition);

    /**
     * Sets the last damage cause to Magic and Caster
     * @param damagedEntity The {@link LivingEntity} that was hit by the spell
     * @param spellDefinition The {@link SpellDefinition} containing the caster
     */
    default void setLastDamageToCaster(@NonNull LivingEntity damagedEntity, @NonNull SpellDefinition spellDefinition) {
        setLastDamageToCaster(damagedEntity, spellDefinition.getCaster());
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
        displayParticleEffect(entity.getLocation(), particle, rangeRadius, 4);
    }

    default void displayParticleEffect(@NonNull Entity entity, Particle particle, double rangeRadius, int numberOfParticles) {
        displayParticleEffect(entity.getLocation(), particle, rangeRadius, numberOfParticles);
    }

    default void displayParticleEffect(@NonNull Location location, Particle particle, double rangeRadius) {
        displayParticleEffect(location, particle, rangeRadius, 4);
    }

    default void displayParticleEffect(@NonNull Location location, Particle particle,  double rangeRadius, int numberOfParticles) {
        for (int i = 0; i < numberOfParticles; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(particle, location.clone().add(x, y ,z), 1);
        }
    }

}
