package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TickingBlockNoGui extends SlimefunItem {

    protected final Map<Location, Boolean> firstTickMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    protected TickingBlockNoGui(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(blockTicker());
    }

    @ParametersAreNonnullByDefault
    protected TickingBlockNoGui(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    @ParametersAreNonnullByDefault
    protected abstract void onFirstTick(Block block, SlimefunItem slimefunItem, Config config);

    @ParametersAreNonnullByDefault
    protected abstract void onTick(Block block, SlimefunItem slimefunItem, Config config);

    @ParametersAreNonnullByDefault
    protected abstract void onPlace(BlockPlaceEvent event);

    @ParametersAreNonnullByDefault
    protected abstract void onBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list);

    protected ItemHandler[] blockTicker() {
        return new ItemHandler[]{
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return true;
                }

                @Override
                public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                    if (!firstTickMap.containsKey(block.getLocation())) {
                        onFirstTick(block, slimefunItem, config);
                        firstTickMap.put(block.getLocation(), true);
                    }
                    onTick(block, slimefunItem, config);
                }
            },
            new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                    onPlace(event);
                }
            },
            new BlockBreakHandler(false, false) {
                @Override
                public void onPlayerBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
                    onBreak(blockBreakEvent, itemStack, list);
                }
            }
        };
    }
}
