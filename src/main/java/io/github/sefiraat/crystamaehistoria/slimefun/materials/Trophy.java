package io.github.sefiraat.crystamaehistoria.slimefun.materials;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

public class Trophy extends SlimefunItem {

    private final Consumer<Location> displayConsumer;

    @ParametersAreNonnullByDefault
    public Trophy(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Consumer<Location> displayConsumer) {
        super(itemGroup, item, recipeType, recipe);
        this.displayConsumer = displayConsumer;
    }

    public Consumer<Location> getDisplayConsumer() {
        return displayConsumer;
    }
}
