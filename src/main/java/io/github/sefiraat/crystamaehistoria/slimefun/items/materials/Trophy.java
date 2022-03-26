package io.github.sefiraat.crystamaehistoria.slimefun.items.materials;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import org.bukkit.Location;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

public class Trophy extends SlimefunItem {

    private final Consumer<Location> displayConsumer;

    @ParametersAreNonnullByDefault
    public Trophy(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Consumer<Location> displayConsumer) {
        super(itemGroup, item, recipeType, recipe);
        this.displayConsumer = displayConsumer;
    }

    @Override
    public void preRegister() {
        addItemHandler(onBlockPlace());
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                e.setCancelled(true);
            }
        };
    }

    public Consumer<Location> getDisplayConsumer() {
        return displayConsumer;
    }
}
