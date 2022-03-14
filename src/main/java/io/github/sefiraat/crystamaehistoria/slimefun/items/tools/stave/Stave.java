package io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class Stave extends SlimefunItem {

    @Getter
    private final int level;

    @ParametersAreNonnullByDefault
    public Stave(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int level) {
        super(itemGroup, item, recipeType, recipe);
        this.level = level;
    }

}
