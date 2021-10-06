package io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.StoryShardProfile;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CrystalBreakListener implements Listener {

    @EventHandler
    public void onBreakCrystal(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.LARGE_AMETHYST_BUD) {
            BlockPosition blockPosition = new BlockPosition(block);
            long l = blockPosition.getPosition();
            for (RealisationAltarCache cache : RealisationAltar.CACHE_MAP.values()) {
                if (cache.getCrystalStoryMap().containsKey(l)) {
                    Pair<StoryRarity, Integer> pair = cache.getCrystalStoryMap().get(l);
                    StoryRarity rarity = pair.getFirstValue();
                    int id = pair.getSecondValue();
                    Story story = CrystamaeHistoria.getStoriesManager().getStory(id, rarity);
                    StoryShardProfile shardProfile = story.getStoryShardProfile();
                    shardProfile.dropShards(rarity, block.getLocation());
                }
            }
        }
    }

}
