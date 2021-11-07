package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.realisationaltar.RealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.realisationaltar.RealisationAltarCache;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CrystalBreakListener implements Listener {

    @EventHandler
    public void onBreakCrystal(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.LARGE_AMETHYST_BUD) {
            handleCrystal(block);
        } else if (block.getRelative(BlockFace.UP).getType() == Material.LARGE_AMETHYST_BUD) {
            handleCrystal(block.getRelative(BlockFace.UP));
        }
    }

    private void handleCrystal(Block block) {
        final BlockPosition blockPosition = new BlockPosition(block);
        for (RealisationAltarCache cache : RealisationAltar.getCaches().values()) {
            final Pair<StoryRarity, String> pair = cache.getCrystalStoryMap().remove(blockPosition);
            if (pair != null) {
                final StoryRarity rarity = pair.getFirstValue();
                final String id = pair.getSecondValue();
                final Story story = CrystamaeHistoria.getStoriesManager().getStory(id, rarity);
                story.getStoryShardProfile().dropShards(rarity, block.getLocation());
            }
            cache.saveMap();
        }
    }

}
