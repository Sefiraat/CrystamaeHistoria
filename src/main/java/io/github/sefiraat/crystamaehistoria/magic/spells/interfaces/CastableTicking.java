package io.github.sefiraat.crystamaehistoria.magic.spells.interfaces;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
import io.github.sefiraat.crystamaehistoria.magic.wrappers.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTick;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.NonNull;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public interface CastableTicking extends Castable {

    int DEFAULT_TICK_AMOUNT = 5;

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     * @param spellDefinition The {@link SpellDefinition} with the stave information
     */
    default void registerTicker(@NonNull SpellDefinition spellDefinition) {
        registerTicker(spellDefinition, DEFAULT_TICK_AMOUNT);
    }

    /**
     * Used to register the projectile's events to the definition and then
     * the projectile/definition to the projectileMap. Used when detecting
     * the projectile hitting targets.
     * @param spellDefinition The {@link SpellDefinition} with the stave information
     * @param tickAmount The number of times this event should tick before stopping.
     */
    default void registerTicker(@NonNull SpellDefinition spellDefinition, int tickAmount) {
        spellDefinition.setTickEvent(this::onTick);
        spellDefinition.setAfterTicksEvent(this::afterAllTicks);
        SpellTick ticker = new SpellTick(spellDefinition, tickAmount);
        CrystamaeHistoria.getActiveStorage().getTickingCastables().put(ticker, tickAmount);
        ticker.runTaskTimer(CrystamaeHistoria.inst(), 0, Slimefun.getTickerTask().getTickRate());
    }

    /**
     * The main affect and/or damage
     * Called each Slimefun tick when {@link MagicProjectile}
     * hits after #beforeAffect or call manually in #Cast
     *
     * @param spellDefinition The {@link SpellDefinition} with the stave information
     */
    void onTick(@NonNull SpellDefinition spellDefinition);

    /**
     * The event that fires once all ticks have been processed.
     * @param spellDefinition The {@link SpellDefinition} with the stave information
     */
    default void afterAllTicks(@NonNull SpellDefinition spellDefinition) { }

}
