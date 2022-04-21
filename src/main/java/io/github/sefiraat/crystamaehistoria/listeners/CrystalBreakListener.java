package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar.RealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar.RealisationAltarCache;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class CrystalBreakListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBreakCrystal(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.LARGE_AMETHYST_BUD) {
            handleCrystal(event, block, true);
        } else if (block.getRelative(BlockFace.UP).getType() == Material.LARGE_AMETHYST_BUD) {
            handleCrystal(event, block.getRelative(BlockFace.UP), false);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBreakCrystal(BlockPistonExtendEvent event) {
        List<Block> blocks = event.getBlocks();
        for (Block block : blocks) {
            if (block.getType() == Material.LARGE_AMETHYST_BUD) {
                handleCrystal(block, false);
            } else if (block.getRelative(BlockFace.UP).getType() == Material.LARGE_AMETHYST_BUD) {
                handleCrystal(block.getRelative(BlockFace.UP), false);
            }
        }
    }

    private void handleCrystal(@Nonnull BlockBreakEvent event, @Nonnull Block block, boolean forceStopDrops) {
        // To stop annoying drops with silk touch :)
        if (handleCrystal(block, true) && forceStopDrops) {
            event.setCancelled(true);
            block.setType(Material.AIR);
        }
    }

    private boolean handleCrystal(@Nonnull Block block, boolean manual) {
        final BlockPosition blockPosition = new BlockPosition(block);
        for (RealisationAltarCache cache : RealisationAltar.getCaches().values()) {
            final RealisationAltarCache.RealisedCrystalState state = cache.getCrystalStoryMap().remove(blockPosition);
            if (state != null) {
                final StoryRarity rarity = state.getStoryRarity();
                final String id = state.getStoryId();
                final Story story = CrystamaeHistoria.getStoriesManager().getStory(id, rarity);
                story.getStoryShardProfile().dropShards(rarity, block.getLocation(), state.isGilded() && manual);
                cache.saveMap();
                return true;
            }
        }
        return false;
    }

}
