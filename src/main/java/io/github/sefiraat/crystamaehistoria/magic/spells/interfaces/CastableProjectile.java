package io.github.sefiraat.crystamaehistoria.magic.spells.interfaces;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastDefinition;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.NonNull;
import org.bukkit.entity.Entity;

/**
 * The castable adds methods to fire events when the projectile first hits
 * a target, when applying damage and then after damage is applied.
 * LightningStrikes are covered by this class also.
 */
public interface CastableProjectile {

    long DEFAULT_PROJECTILE_DURATION = 10000;

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     * @param entity The {@link Entity} being stored (projectile or LightningStrike)
     * @param castDefinition The {@link CastDefinition} with the stave information
     */
    default void registerProjectile(@NonNull Entity entity, @NonNull CastDefinition castDefinition) {
        registerProjectile(entity, castDefinition, DEFAULT_PROJECTILE_DURATION);
    }

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     * @param entity The {@link Entity} being stored (projectile or lightningstrike)
     * @param castDefinition The {@link CastDefinition} with the stave information
     */
    default void registerProjectile(@NonNull Entity entity, @NonNull CastDefinition castDefinition, long projectileDuration) {
        castDefinition.setBeforeAffectEvent(this::beforeAffect);
        castDefinition.setAffectEvent(this::affect);
        castDefinition.setAfterAffectEvent(this::afterAffect);
        Long expiry = System.currentTimeMillis() + projectileDuration;
        CrystamaeHistoria.getActiveStorage().getProjectileMap().put(entity, new Pair<>(castDefinition, expiry));
    }

    /**
     * Events to trigger when a mob has been hit but before damage is applied
     * (i.e. Entity is still alive at this point)
     * Called automatically when {@link io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile}
     * hits, first, or call manually in #Cast
     * @param castDefinition The {@link CastDefinition} with the stave information
     */
    default void beforeAffect(@NonNull CastDefinition castDefinition) { }

    /**
     * The main affect and/or damage
     * Called automatically when {@link MagicProjectile}
     * hits after #beforeAffect or call manually in #Cast
     *
     * @param castDefinition The {@link CastDefinition} with the stave information
     */
    void affect(@NonNull CastDefinition castDefinition);

    /**
     * Effects to apply after damage (i.e. Entity may be dead)
     * Called automatically when {@link io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile}
     * hits after #affect or call manually in #Cast
     * @param castDefinition The {@link CastDefinition} with the stave information
     */
    default void afterAffect(@NonNull CastDefinition castDefinition) { }

}
