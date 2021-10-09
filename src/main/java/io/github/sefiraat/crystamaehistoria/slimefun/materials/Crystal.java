package io.github.sefiraat.crystamaehistoria.slimefun.materials;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class Crystal extends UnplaceableBlock {

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

}
