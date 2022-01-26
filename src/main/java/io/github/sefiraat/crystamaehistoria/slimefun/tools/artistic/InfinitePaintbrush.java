package io.github.sefiraat.crystamaehistoria.slimefun.tools.artistic;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import javax.annotation.ParametersAreNonnullByDefault;

public class InfinitePaintbrush extends SlimefunItem implements MagicPaintbrush {

    @ParametersAreNonnullByDefault
    public InfinitePaintbrush(ItemGroup itemGroup,
                              SlimefunItemStack item,
                              RecipeType recipeType,
                              ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(onItemUse());
    }

    public ItemUseHandler onItemUse() {
        return e -> {
            ItemStack itemStack = e.getItem();
            if (itemStack.getType() != Material.AIR) {
                PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
                int currentSelection = PersistentDataAPI.getInt(potionMeta, Keys.PDC_PAINT_TYPE, 0);
                if (e.getPlayer().isSneaking()) {
                    currentSelection++;
                    if (currentSelection >= PaintProfile.getCachedValues().length) {
                        currentSelection = 0;
                    }
                    PersistentDataAPI.setInt(potionMeta, Keys.PDC_PAINT_TYPE, currentSelection);
                    PaintProfile profile = PaintProfile.getCachedValues()[currentSelection];
                    e.getPlayer().sendActionBar(
                        Component.text("Now painting in " + ThemeType.toTitleCase(profile.name()))
                            .color(profile.getTextColor())
                    );
                    potionMeta.setColor(profile.getDyeColor().getColor());
                    itemStack.setItemMeta(potionMeta);
                } else {
                    tryPaint(e, PaintProfile.getCachedValues()[currentSelection], true);
                }
            }
        };
    }
}
