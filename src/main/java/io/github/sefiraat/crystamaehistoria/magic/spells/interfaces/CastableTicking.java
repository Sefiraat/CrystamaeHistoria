package io.github.sefiraat.crystamaehistoria.magic.spells.interfaces;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastDefinition;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTick;
import lombok.NonNull;

public interface CastableTicking {

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     * @param castDefinition The {@link CastDefinition} with the stave information
     * @param tickAmount The number of times this event should tick before stopping.
     */
    default void registerTicker(@NonNull CastDefinition castDefinition, long period, int tickAmount) {
        castDefinition.setTickEvent(this::onTick);
        castDefinition.setAfterTicksEvent(this::afterAllTicks);
        SpellTick ticker = new SpellTick(castDefinition, tickAmount);
        CrystamaeHistoria.getActiveStorage().getTickingCastables().put(ticker, tickAmount);
        ticker.runTaskTimer(CrystamaeHistoria.inst(), 0, period);
    }

    /**
     * The main affect and/or damage
     * Called each Slimefun tick when {@link MagicProjectile}
     * hits after #beforeAffect or call manually in #Cast
     *
     * @param castDefinition The {@link CastDefinition} with the stave information
     */
    void onTick(@NonNull CastDefinition castDefinition);

    /**
     * The event that fires once all ticks have been processed.
     * @param castDefinition The {@link CastDefinition} with the stave information
     */
    default void afterAllTicks(@NonNull CastDefinition castDefinition) { }

}
