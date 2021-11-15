package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class DisplayItemListener implements Listener {

    @EventHandler
    public void onItemPickup(InventoryPickupItemEvent e) {
        if (PersistentDataAPI.hasBoolean(e.getItem(), Keys.PDC_IS_DISPLAY_ITEM)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent e) {
        if (PersistentDataAPI.hasBoolean(e.getEntity(), Keys.PDC_IS_DISPLAY_ITEM)) {
            e.setCancelled(true);
            e.getEntity().setTicksLived(1);
        }
    }
}
