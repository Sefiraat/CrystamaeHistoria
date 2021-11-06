package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveConfigRunnable extends BukkitRunnable {

    @Override
    public void run() {
        CrystamaeHistoria.getConfigManager().saveAll();
    }
}
