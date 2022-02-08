package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.PhilosophersSpray;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasin;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.papermc.paper.event.block.BlockFailedDispenseEvent;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PhilosophersSprayListener implements Listener {

    @EventHandler
    public void onInteract(BlockFailedDispenseEvent e) {
        final Block block = e.getBlock();
        SlimefunItem item = BlockStorage.check(block);
        if (item instanceof PhilosophersSpray) {
            PhilosophersSpray.triggerChange(block);
        }

    }
}
