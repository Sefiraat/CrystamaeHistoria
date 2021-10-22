package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onRemovableBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (GeneralUtils.isRemovableBlock(event.getBlock())) {
            event.setCancelled(true);
            block.removeMetadata("ch", CrystamaeHistoria.getInstance());
            block.setType(Material.AIR);
            CrystamaeHistoria.getActiveStorage().stopBlockRemoval(block);
        }
    }

}
