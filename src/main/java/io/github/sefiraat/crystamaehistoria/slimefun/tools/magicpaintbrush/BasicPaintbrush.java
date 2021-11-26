package io.github.sefiraat.crystamaehistoria.slimefun.tools.magicpaintbrush;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BasicPaintbrush extends LimitedUseItem implements MagicPaintbrush {

    private static final NamespacedKey key = Keys.newKey("uses");
    private final PaintProfile profile;

    @ParametersAreNonnullByDefault
    public BasicPaintbrush(ItemGroup itemGroup,
                           SlimefunItemStack item,
                           RecipeType recipeType,
                           ItemStack[] recipe,
                           PaintProfile profile,
                           int amount
    ) {
        super(itemGroup, item, recipeType, recipe);
        this.profile = profile;
        setMaxUseCount(amount);
    }

    @Override
    protected @Nonnull
    NamespacedKey getStorageKey() {
        return key;
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (tryPaint(e, this.profile, false)) {
                damageItem(e.getPlayer(), e.getItem());
            }
        };
    }
}
