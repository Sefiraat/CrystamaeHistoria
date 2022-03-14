package io.github.sefiraat.crystamaehistoria.slimefun.items.materials;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;
import java.util.Map;

public class Crystal extends UnplaceableBlock {

    protected static final Map<StoryRarity, Integer> RARITY_VALUE_MAP = new EnumMap<>(StoryRarity.class);

    static {
        RARITY_VALUE_MAP.put(StoryRarity.COMMON, 1);
        RARITY_VALUE_MAP.put(StoryRarity.UNCOMMON, 3);
        RARITY_VALUE_MAP.put(StoryRarity.RARE, 10);
        RARITY_VALUE_MAP.put(StoryRarity.EPIC, 25);
        RARITY_VALUE_MAP.put(StoryRarity.MYTHICAL, 50);
        RARITY_VALUE_MAP.put(StoryRarity.UNIQUE, 2);
    }

    @Getter
    private final StoryType type;
    @Getter
    private final StoryRarity rarity;

    @ParametersAreNonnullByDefault
    public Crystal(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, StoryRarity rarity, StoryType type) {
        super(itemGroup, item, recipeType, recipe);
        this.type = type;
        this.rarity = rarity;
    }

    public static Map<StoryRarity, Integer> getRarityValueMap() {
        return RARITY_VALUE_MAP;
    }

}
