package io.github.sefiraat.crystamaehistoria.slimefun.types;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.PatternUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

public abstract class RefillableUseItem extends LimitedUseItem {

    @ParametersAreNonnullByDefault
    protected RefillableUseItem(ItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(group, item, recipeType, recipe);
    }

    @ParametersAreNonnullByDefault
    protected void refillItem(ItemStack itemStack) {
        refillItem(itemStack, 1);
    }

    @ParametersAreNonnullByDefault
    protected void refillItem(ItemStack item, int refillAmount) {
        final ItemMeta meta = item.getItemMeta();
        final NamespacedKey key = getStorageKey();
        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        int usesLeft = pdc.getOrDefault(key, PersistentDataType.INTEGER, getMaxUseCount());

        usesLeft = Math.min(usesLeft + refillAmount, getMaxUseCount());
        pdc.set(key, PersistentDataType.INTEGER, usesLeft);
        updateLore(item, meta, usesLeft);
    }

    // Just taken from super class
    @ParametersAreNonnullByDefault
    protected void updateLore(ItemStack item, ItemMeta meta, int usesLeft) {
        List<String> lore = meta.getLore();

        String newLine = ChatColors.color(LoreBuilder.usesLeft(usesLeft));
        if (lore != null && !lore.isEmpty()) {
            // find the correct line
            for (int i = 0; i < lore.size(); i++) {
                if (PatternUtils.USES_LEFT_LORE.matcher(lore.get(i)).matches()) {
                    lore.set(i, newLine);
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    return;
                }
            }
        } else {
            meta.setLore(Collections.singletonList(newLine));
            item.setItemMeta(meta);
        }
    }

}
