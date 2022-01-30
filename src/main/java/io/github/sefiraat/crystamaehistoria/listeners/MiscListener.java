package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.tools.covers.BlockVeil;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MiscListener implements Listener {

    @EventHandler
    public void onPlaceStoriedBlock(BlockPlaceEvent e) {
        ItemStack itemStack = e.getItemInHand();
        if (itemStack.getType() != Material.AIR && StoryUtils.isStoried(itemStack)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlaceCover(BlockPlaceEvent e) {
        ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
        if (SlimefunItem.getByItem(itemStack) instanceof BlockVeil) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkCooldown(PlayerInteractEvent event) {
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (itemStack.getType() != Material.AIR
            && (event.getAction() == Action.RIGHT_CLICK_AIR
            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
            && GeneralUtils.isOnCooldown(itemStack)
        ) {
            event.setCancelled(true);
        }
    }
}