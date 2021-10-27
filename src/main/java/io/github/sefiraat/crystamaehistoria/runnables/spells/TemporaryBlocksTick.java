package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.ActiveStorage;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import org.bukkit.scheduler.BukkitRunnable;

public class TemporaryBlocksTick extends BukkitRunnable {

    @Override
    public void run() {
        ActiveStorage storage = CrystamaeHistoria.getActiveStorage();
        storage.removeBlocks(false);
    }
}
