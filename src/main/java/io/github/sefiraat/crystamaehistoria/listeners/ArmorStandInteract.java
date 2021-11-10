package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.utils.ArmourStandUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class ArmorStandInteract implements Listener {

    @EventHandler
    public void onArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
        if (ArmourStandUtils.isDisplayStand(e.getRightClicked())) e.setCancelled(true);
    }

    @EventHandler
    public void onArmorDispense(BlockDispenseArmorEvent e) {
        if (e.getTargetEntity() instanceof ArmorStand
            && ArmourStandUtils.isDisplayStand((ArmorStand) e.getTargetEntity())
        ) {
            e.setCancelled(true);
        }
    }
}
