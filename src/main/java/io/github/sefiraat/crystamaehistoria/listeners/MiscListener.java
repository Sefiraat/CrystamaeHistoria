package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.MysteriousTicker;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MiscListener implements Listener {

    @EventHandler
    public void onDontTouchMyCrap(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && block != null) {
            SlimefunItem slimefunItem = BlockStorage.check(block);
            if (slimefunItem instanceof MysteriousTicker) {
                e.setCancelled(true);
            }
        }
    }
}