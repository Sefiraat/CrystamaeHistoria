package io.github.sefiraat.crystamaehistoria.stories.definition;

import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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

    public void dropShards(StoryRarity rarity, Block block, boolean isGilded) {
        dropShards(rarity, block.getLocation(), isGilded);
    }

    public void dropShards(StoryRarity rarity, Location location, boolean isGilded) {
        for (Map.Entry<StoryType, Integer> entry : shardMap.entrySet()) {
            final StoryType storyType = entry.getKey();

            int amount = entry.getValue() * getMultiplier(isGilded);
            if (amount > 0) {
                ItemStack itemStack = Materials.getCrystalMap().get(rarity).get(storyType).getItem().clone();
                itemStack.setAmount(amount);
                location.getWorld().dropItemNaturally(location, itemStack);
            }
        }
    }

    private int getMultiplier(boolean isGilded) {
        if (isGilded) {
            int rnd = ThreadLocalRandom.current().nextInt(1, 101);
            if (rnd < 5) {
                return 4;
            } else if (rnd < 25) {
                return 3;
            }
            return 2;
        }

        return 1;
    }
}
