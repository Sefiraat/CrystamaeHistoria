package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTick;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Spell {

    @Getter
    @Setter
    private SpellCore spellCore;

    public void castSpell(SpellCastInformation spellCastInformation) {

        if (spellCore.isInstantCast()) {
            spellCore.getInstantCastEvent().accept(spellCastInformation);
        }

        if (spellCore.isProjectileSpell()) {
            spellCore.getFireProjectileEvent().accept(spellCastInformation);
            spellCastInformation.setBeforeAffectEvent(spellCore.getBeforeProjectileHitEvent());
            spellCastInformation.setAffectEvent(spellCore.getProjectileHitEvent());
            spellCastInformation.setAfterAffectEvent(spellCore.getAfterProjectileHitEvent());
        }

        if (spellCore.isTickingSpell()) {
            registerTicker(spellCastInformation, spellCore.getTickInterval(), spellCore.getNumberOfTicks());
        }

        triggerCooldown(spellCastInformation);

    }

    protected void triggerCooldown(@NonNull SpellCastInformation spellCastInformation) {

    }

    protected double getBaseCooldown(SpellCastInformation spellCastInformation) {
        return spellCore.isCooldownMultiplied() ? spellCore.getCooldown() * (3 - spellCastInformation.getCooldownMulti()) : spellCore.getCooldown();
    }

    protected double getRange(SpellCastInformation spellCastInformation) {
        return spellCore.isRangeMultiplied() ? spellCore.getRange() * spellCastInformation.getPowerMulti() : spellCore.getRange();
    }

    protected double getDurabilityCost(SpellCastInformation spellCastInformation) {
        return spellCore.isDurabilityMultiplied() ? spellCore.getDurabilityCost() * (3 - spellCastInformation.getDurabilityMulti()) : spellCore.getDurabilityCost();
    }

    protected double getDamage(SpellCastInformation spellCastInformation) {
        return spellCore.isDamageMultiplied() ? spellCore.getDamageAmount() * spellCastInformation.getPowerMulti() : spellCore.getDamageAmount();
    }

    protected double getHealAmount(SpellCastInformation spellCastInformation) {
        return spellCore.isHealMultiplied() ? spellCore.getHealAmount() * spellCastInformation.getPowerMulti() : spellCore.getHealAmount();
    }

    protected double getKnockback(SpellCastInformation spellCastInformation) {
        return spellCore.isKnockbackMultiplied() ? spellCore.getKnockbackAmount() * spellCastInformation.getPowerMulti() : spellCore.getKnockbackAmount();
    }

    protected double getProjectileKnockback(SpellCastInformation spellCastInformation) {
        return spellCore.isProjectileKnockbackMultiplied() ? spellCore.getProjectileKnockbackAmount() * spellCastInformation.getPowerMulti() : spellCore.getProjectileKnockbackAmount();
    }

    protected double getProjectileAoe(SpellCastInformation spellCastInformation) {
        return spellCore.isProjectileAoeMultiplied() ? spellCore.getProjectileAoeRange() * spellCastInformation.getPowerMulti() : spellCore.getProjectileAoeRange();
    }

    /**
     * Sets the last damage cause to Magic and Caster
     * @param damagedEntity The {@link LivingEntity} that was hit by the spell
     * @param spellCastInformation The {@link SpellCastInformation} containing the caster
     */
    protected void setLastDamageToCaster(@NonNull LivingEntity damagedEntity, @NonNull SpellCastInformation spellCastInformation) {
        setLastDamageToCaster(damagedEntity, spellCastInformation.getCaster());
    }

    /**
     * Sets the last damage cause to Magic and Caster
     * @param damagedEntity The {@link LivingEntity} that was hit by the spell
     * @param casterUUID The {@link LivingEntity} that cast the spell
     */
    protected void setLastDamageToCaster(@NonNull LivingEntity damagedEntity, @NonNull UUID casterUUID) {
        Player player = Bukkit.getPlayer(casterUUID);
        if (player != null) {
            EntityDamageByEntityEvent e = new EntityDamageByEntityEvent(player, damagedEntity, EntityDamageEvent.DamageCause.MAGIC, 0);
            damagedEntity.setLastDamageCause(e);
        }
    }

    protected void displayParticleEffect(@NonNull Entity entity, Particle particle, double rangeRadius) {
        displayParticleEffect(entity.getLocation(), particle, rangeRadius, spellCore.getParticleNumber());
    }

    protected void displayParticleEffect(@NonNull Entity entity, Particle particle, double rangeRadius, int numberOfParticles) {
        displayParticleEffect(entity.getLocation(), particle, rangeRadius, numberOfParticles);
    }

    protected void displayParticleEffect(@NonNull Location location, Particle particle, double rangeRadius) {
        displayParticleEffect(location, particle, rangeRadius, spellCore.getParticleNumber());
    }

    protected void displayParticleEffect(@NonNull Location location, Particle particle,  double rangeRadius, int numberOfParticles) {
        for (int i = 0; i < numberOfParticles; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(particle, location.clone().add(x, y ,z), 1);
        }
    }

    protected void pullEntity(Location loc, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector().subtract(loc.toVector()).normalize().multiply(-force);
        pushEntity(vector, pushed);
    }

    protected void pushEntity(Location loc, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector().subtract(loc.toVector()).normalize().multiply(force);
        pushEntity(vector, pushed);
    }

    protected void pushEntity(Vector vector, Entity pushed) {
        pushed.setVelocity(vector);
    }

    protected void damageEntity(LivingEntity livingEntity, UUID caster, double damage) {
        damageEntity(livingEntity, caster, damage, null, 0);
    }

    protected void damageEntity(LivingEntity livingEntity, UUID caster, double damage, @Nullable Location knockbackOrigin, double knockbackForce) {
        Player player = Bukkit.getPlayer(caster);
        livingEntity.damage(damage, player);
        if (knockbackOrigin != null && knockbackForce > 0) {
            EntityUtils.push(livingEntity, knockbackOrigin, knockbackForce);
        }
    }

    /**
     * Heal the entity by the provided amount
     * @param livingEntity The {@link LivingEntity} to heal
     * @param healAmount The amount to heal by
     */
    protected void healEntity(LivingEntity livingEntity, double healAmount) {
        AttributeInstance attribute = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attribute != null) {
            livingEntity.setHealth(Math.min(attribute.getValue(), livingEntity.getHealth() + healAmount));
        }
    }

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     * @param entity The {@link Entity} being stored (projectile or LightningStrike)
     * @param spellCastInformation The {@link SpellCastInformation} with the stave information
     */
    protected void registerProjectile(@NonNull Entity entity, @NonNull SpellCastInformation spellCastInformation) {
        registerProjectile(entity, spellCastInformation, spellCore.getParticleNumber());
    }

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     * @param entity The {@link Entity} being stored (projectile or lightningstrike)
     * @param spellCastInformation The {@link SpellCastInformation} with the stave information
     */
    protected void registerProjectile(@NonNull Entity entity, @NonNull SpellCastInformation spellCastInformation, long projectileDuration) {
        spellCastInformation.setBeforeAffectEvent(spellCore.getBeforeProjectileHitEvent());
        spellCastInformation.setAffectEvent(spellCore.getProjectileHitEvent());
        spellCastInformation.setAfterAffectEvent(spellCore.getAfterProjectileHitEvent());
        Long expiry = System.currentTimeMillis() + projectileDuration;
        CrystamaeHistoria.getActiveStorage().getProjectileMap().put(entity, new Pair<>(spellCastInformation, expiry));
    }

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     * @param spellCastInformation The {@link SpellCastInformation} with the stave information
     * @param tickAmount The number of times this event should tick before stopping.
     */
    protected void registerTicker(@NonNull SpellCastInformation spellCastInformation, long period, int tickAmount) {
        spellCastInformation.setTickEvent(spellCore.getTickEvent());
        spellCastInformation.setAfterTicksEvent(spellCore.getAfterAllTicksEvent());
        SpellTick ticker = new SpellTick(spellCastInformation, tickAmount);
        CrystamaeHistoria.getActiveStorage().getTickingCastables().put(ticker, tickAmount);
        ticker.runTaskTimer(CrystamaeHistoria.inst(), 0, period);
    }

    /**
     * Applies all registered positive effects on the selected entity
     * @param livingEntity The {@link LivingEntity} to apply the effects to
     */
    protected void applyPositiveEffects(LivingEntity livingEntity) {
        for (Map.Entry<PotionEffectType, Pair<Integer, Integer>> entry : spellCore.getPositiveEffectPairMap().entrySet()) {
            livingEntity.addPotionEffect(new PotionEffect(entry.getKey(), entry.getValue().getFirstValue(), entry.getValue().getSecondValue()));
        }
    }

    /**
     * Applies all registered negative effects on the selected entity
     * @param livingEntity The {@link LivingEntity} to apply the effects to
     */
    protected void applyNegativeEffects(LivingEntity livingEntity) {
        for (Map.Entry<PotionEffectType, Pair<Integer, Integer>> entry : spellCore.getNegativeEffectPairMap().entrySet()) {
            livingEntity.addPotionEffect(new PotionEffect(entry.getKey(), entry.getValue().getFirstValue(), entry.getValue().getSecondValue()));
        }
    }

    /**
     * Gets all targets around the damageLocation.
     * Should only be used in response to projectile events.
     * Does NOT include the main target hit
     * @param spellCastInformation The {@link SpellCastInformation} containing the DamageLocation
     * @return
     */
    protected Set<LivingEntity> getTargets(SpellCastInformation spellCastInformation, double range) {
        return getTargets(spellCastInformation, range,false);
    }

    /**
     * Gets all targets around the damageLocation.
     * Should only be used in response to projectile events.
     * @param spellCastInformation The {@link SpellCastInformation} containing the DamageLocation
     * @param includeMain If the main target should be included in the return set
     * @return
     */
    protected Set<LivingEntity> getTargets(SpellCastInformation spellCastInformation, double range, boolean includeMain) {
        Set<LivingEntity> livingEntities = new HashSet<>();
        Location location = spellCastInformation.getDamageLocation();
        if (range > 0) {
            for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
                if (entity instanceof LivingEntity && entity.getUniqueId() != spellCastInformation.getCaster() && (entity != spellCastInformation.getMainTarget() || includeMain)) {
                    livingEntities.add((LivingEntity) entity);
                }
            }
        }
        return livingEntities;
    }

}
