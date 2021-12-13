package io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public abstract class ExaltedItem extends SlimefunItem {

    protected ExaltedItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public abstract void onExalt(ExaltedItem slimefunItem, Location location);

}
