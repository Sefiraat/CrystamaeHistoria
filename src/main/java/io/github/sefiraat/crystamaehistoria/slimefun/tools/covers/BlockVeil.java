package io.github.sefiraat.crystamaehistoria.slimefun.tools.covers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BlockVeil extends SlimefunItem {

    public BlockVeil(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput, Class<? extends SlimefunItem>... classToCover) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
        addItemHandler((ItemUseHandler) e -> {
            e.cancel();
            if (e.getClickedBlock().isPresent()) {
                Block block = e.getClickedBlock().get();
                SlimefunItem slimefunItem = BlockStorage.check(block);
                ItemStack offhand = e.getPlayer().getInventory().getItemInOffHand();

                if (slimefunItem == null) {
                    return;
                }

                Class<? extends SlimefunItem> itemClass = slimefunItem.getClass();

                for (Class<?> testClass : classToCover) {
                    if (testClass.isInstance(slimefunItem)) {
                        if (offhand.getType() != Material.AIR
                            && offhand.getType().isBlock()
                            && materialIsValid(offhand.getType())
                        ) {
                            block.setType(offhand.getType());
                            e.getItem().setAmount(e.getItem().getAmount() - 1);
                        }
                        return;
                    }
                }
            }
        });
    }

    public boolean materialIsValid(Material material) {
        return material != Material.SPAWNER && material.getHardness() != -1;
    }
}
