package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class RemovalBlocksListener implements Listener {

    @EventHandler
    public void onRemovableBlockBreak(BlockBreakEvent event) {
        event.setCancelled(processBlockRemoval(event.getBlock()));
    }

    @EventHandler
    public void onBucketUse(PlayerBucketFillEvent event) {
        event.setCancelled(processBlockRemoval(event.getBlock()));
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        event.setCancelled(processBlockRemoval(event.getBlock()));
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        event.setCancelled(processBlockRemoval(event.getBlock()));
    }

    private boolean processBlockRemoval(Block block) {
        if (GeneralUtils.isRemovableBlock(block)) {
            block.removeMetadata("ch", CrystamaeHistoria.getInstance());
            block.setType(Material.AIR);
            CrystamaeHistoria.getActiveStorage().stopBlockRemoval(block);
            return true;
        }
        return false;
    }


}
