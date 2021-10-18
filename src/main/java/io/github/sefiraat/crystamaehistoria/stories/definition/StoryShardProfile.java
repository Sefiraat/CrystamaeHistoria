package io.github.sefiraat.crystamaehistoria.stories.definition;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class StoryShardProfile {

    public final Map<StoryType, Integer> shardMap = new EnumMap<>(StoryType.class);

    public StoryShardProfile(List<Integer> integerList) {
        shardMap.put(StoryType.ELEMENTAL, integerList.get(StoryType.ELEMENTAL.getId() - 1));
        shardMap.put(StoryType.MECHANICAL, integerList.get(StoryType.MECHANICAL.getId() - 1));
        shardMap.put(StoryType.ALCHEMICAL, integerList.get(StoryType.ALCHEMICAL.getId() - 1));
        shardMap.put(StoryType.HISTORICAL, integerList.get(StoryType.HISTORICAL.getId() - 1));
        shardMap.put(StoryType.HUMAN, integerList.get(StoryType.HUMAN.getId() - 1));
        shardMap.put(StoryType.ANIMAL, integerList.get(StoryType.ANIMAL.getId() - 1));
        shardMap.put(StoryType.CELESTIAL, integerList.get(StoryType.CELESTIAL.getId() - 1));
        shardMap.put(StoryType.VOID, integerList.get(StoryType.VOID.getId() - 1));
        shardMap.put(StoryType.PHILOSOPHICAL, integerList.get(StoryType.PHILOSOPHICAL.getId() - 1));
    }

    public void dropShards(StoryRarity rarity, Block block) {
        dropShards(rarity, block.getLocation());
    }

    public void dropShards(StoryRarity rarity, Location location) {
        for (Map.Entry<StoryType, Integer> entry : shardMap.entrySet()) {
            StoryType storyType = entry.getKey();
            int amount = entry.getValue();
            if (amount > 0) {
                ItemStack itemStack = CrystamaeHistoria.getStructure().getMaterials().getCrystalMap().get(rarity).get(storyType).getItem().clone();
                itemStack.setAmount(amount);
                location.getWorld().dropItemNaturally(location, itemStack);
            }
        }
    }
}
