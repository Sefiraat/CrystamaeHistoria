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

public class BlockRemovalListener implements Listener {

    @EventHandler
    public void onRemovableBlockBreak(BlockBreakEvent event) {
        if (processBlockRemoval(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBucketUse(PlayerBucketFillEvent event) {
        if (processBlockRemoval(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        if (processBlockRemoval(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        if (processBlockRemoval(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    private boolean processBlockRemoval(Block block) {
        if (GeneralUtils.isRemovableBlock(block)) {
            block.removeMetadata("ch", CrystamaeHistoria.getInstance());
            block.setType(Material.AIR);
            CrystamaeHistoria.getSpellMemory().stopBlockRemoval(block);
            return true;
        }
        return false;
    }


}
