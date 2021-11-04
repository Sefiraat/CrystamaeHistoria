package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.SpellMemory;
import org.bukkit.scheduler.BukkitRunnable;

public class TemporaryEffectsRunnable extends BukkitRunnable {

    @Override
    public void run() {
        final SpellMemory storage = CrystamaeHistoria.getActiveStorage();

        clearExpiredProjectiles(storage);
        clearMagicSummons(storage);
        clearTempBlocks(storage);
        removeTempFlight(storage);
    }

    public void clearMagicSummons(SpellMemory storage) {
        storage.removeEntities(false);
    }

    public void clearTempBlocks(SpellMemory storage) {
        storage.removeBlocks(false);
    }

    public void clearExpiredProjectiles(SpellMemory storage) {
        storage.removeProjectiles(false);
    }

    public void removeTempFlight(SpellMemory storage) {
        storage.removeFlight(false);
    }
}
