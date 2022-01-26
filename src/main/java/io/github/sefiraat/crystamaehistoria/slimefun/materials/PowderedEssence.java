package io.github.sefiraat.crystamaehistoria.slimefun.materials;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

public class PowderedEssence extends LimitedUseItem {

    private static final NamespacedKey key = Keys.newKey("uses");

    @ParametersAreNonnullByDefault
    public PowderedEssence(ItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amount) {
        super(group, item, recipeType, recipe);
        setMaxUseCount(amount);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Optional<Block> optionalBlock = e.getClickedBlock();

            if (!optionalBlock.isPresent()) {
                return;
            }

            Block block = optionalBlock.get();

            if (block.applyBoneMeal(e.getClickedFace())) {
                damageItem(e.getPlayer(), e.getItem());
            }

        };
    }

    @Override
    protected @Nonnull
    NamespacedKey getStorageKey() {
        return key;
    }
}
