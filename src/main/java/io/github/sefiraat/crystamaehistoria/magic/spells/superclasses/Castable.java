package io.github.sefiraat.crystamaehistoria.magic.spells.superclasses;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

interface Castable {

    int DEFAULT_PARTICLE_NUMBER = 4;
    Map<PotionEffectType, Pair<Integer, Integer>> positiveEffectPairMap = new HashMap<>();
    Map<PotionEffectType, Pair<Integer, Integer>> negativeEffectPairMap = new HashMap<>();

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

    /**
     *
     * @param potionEffectType The {@link PotionEffectType} to apply.
     * @param amplification The amplification of the effect. If multiple of the same effects are added, the values are combined.
     * @param duration The duration of the effect. If multiple of the same effects are added, the highest is used.
     */
    default void addPositiveEffect(PotionEffectType potionEffectType, int amplification, int duration) {
        if (positiveEffectPairMap.containsKey(potionEffectType)) {
            Pair<Integer, Integer> integerPair = positiveEffectPairMap.get(potionEffectType);
            amplification = amplification == 0 ? 1 : amplification;
            integerPair.setFirstValue(integerPair.getFirstValue() + amplification);
            integerPair.setSecondValue(Math.max(integerPair.getSecondValue(), duration));
            positiveEffectPairMap.put(potionEffectType, integerPair);
        } else {
            positiveEffectPairMap.put(potionEffectType, new Pair<>(amplification, duration));
        }
    }

    /**
     *
     * @param potionEffectType The {@link PotionEffectType} to apply.
     * @param amplification The amplification of the effect. If multiple of the same effects are added, the values are combined.
     * @param duration The duration of the effect. If multiple of the same effects are added, the highest is used.
     */
    default void addNegativeEffect(PotionEffectType potionEffectType, int amplification, int duration) {
        if (negativeEffectPairMap.containsKey(potionEffectType)) {
            Pair<Integer, Integer> integerPair = negativeEffectPairMap.get(potionEffectType);
            amplification = amplification == 0 ? 1 : amplification;
            integerPair.setFirstValue(integerPair.getFirstValue() + amplification);
            integerPair.setSecondValue(Math.max(integerPair.getSecondValue(), duration));
            negativeEffectPairMap.put(potionEffectType, integerPair);
        } else {
            negativeEffectPairMap.put(potionEffectType, new Pair<>(amplification, duration));
        }
    }

    default void applyPositiveEffects(LivingEntity livingEntity) {
        for (Map.Entry<PotionEffectType, Pair<Integer, Integer>> entry : positiveEffectPairMap.entrySet()) {
            livingEntity.addPotionEffect(new PotionEffect(entry.getKey(), entry.getValue().getFirstValue(), entry.getValue().getSecondValue()));
        }
    }

    default void applyNegativeEffects(LivingEntity livingEntity) {
        for (Map.Entry<PotionEffectType, Pair<Integer, Integer>> entry : negativeEffectPairMap.entrySet()) {
            livingEntity.addPotionEffect(new PotionEffect(entry.getKey(), entry.getValue().getFirstValue(), entry.getValue().getSecondValue()));
        }
    }

}
