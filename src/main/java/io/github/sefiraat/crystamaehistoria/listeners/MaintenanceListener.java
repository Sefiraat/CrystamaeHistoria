package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CauldronLevelChangeEvent;

public class MaintenanceListener implements Listener {

    @EventHandler
    public void onRemovableBlockBreak(CauldronLevelChangeEvent event) {
        if (BlockStorage.check(event.getBlock()) instanceof LiquefactionBasin) {
            event.setCancelled(true);
        }
    }
}
