package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.utils.KeyHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class ListenerArmorStandInteract implements Listener {

    @EventHandler
    public void onArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
        String name = e.getRightClicked().getCustomName();
        if (name != null && name.startsWith(KeyHolder.PANEL_STAND_PREFIX)) {
            e.setCancelled(true);
        }
    }

}
