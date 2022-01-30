package io.github.sefiraat.crystamaehistoria.slimefun.gadgets;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Consumer;

public class MysteriousTickerNoInteraction extends MysteriousTicker {

    public MysteriousTickerNoInteraction(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Set<Material> tickingMaterials, int tickFrequency) {
        super(category, item, recipeType, recipe, tickingMaterials, tickFrequency);
    }

    public MysteriousTickerNoInteraction(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Set<Material> tickingMaterials, int tickFrequency, @Nullable Consumer<Block> consumer) {
        super(category, item, recipeType, recipe, tickingMaterials, tickFrequency, consumer);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(blockUseHandler());
    }

    private BlockUseHandler blockUseHandler() {
        return PlayerRightClickEvent::cancel;
    }
}
