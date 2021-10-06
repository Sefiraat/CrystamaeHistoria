package io.github.sefiraat.crystamaehistoria.stories;

import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class StoryShardProfile {

    public final Map<StoryType, Integer> shardMap = new HashMap<>();

    public StoryShardProfile(int amountElemental, int amountMechanical, int amountAlchemical, int amountHistorical, int amountHuman, int amountAnimal, int amountCelestial, int amountVoid, int amountPhilosophical) {
        shardMap.put(StoryType.ELEMENTAL, amountElemental);
        shardMap.put(StoryType.MECHANICAL, amountMechanical);
        shardMap.put(StoryType.ALCHEMICAL, amountAlchemical);
        shardMap.put(StoryType.HISTORICAL, amountHistorical);
        shardMap.put(StoryType.HUMAN, amountHuman);
        shardMap.put(StoryType.ANIMAL, amountAnimal);
        shardMap.put(StoryType.CELESTIAL, amountCelestial);
        shardMap.put(StoryType.VOID, amountVoid);
        shardMap.put(StoryType.PHILOSOPHICAL, amountPhilosophical);
    }

    public void dropShards(StoryRarity rarity, Block block) {
        dropShards(rarity, block.getLocation());
    }

    public void dropShards(StoryRarity rarity, Location location) {
        for (Map.Entry<StoryType, Integer> entry : shardMap.entrySet()) {
            StoryType storyType = entry.getKey();
            int amount = entry.getValue();
            if (amount > 0) {
                ItemStack itemStack = Materials.CRYSTAL_MAP.get(rarity).get(storyType).getItem().clone();
                itemStack.setAmount(amount);
                location.getWorld().dropItemNaturally(location, itemStack);
            }
        }
    }
}
