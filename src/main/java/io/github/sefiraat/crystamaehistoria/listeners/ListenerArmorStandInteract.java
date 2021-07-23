package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class ListenerArmorStandInteract implements Listener {

    @EventHandler
    public void onArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
        if (EntityUtils.isDisplayStand(e.getRightClicked())) e.setCancelled(true);
    }

}
