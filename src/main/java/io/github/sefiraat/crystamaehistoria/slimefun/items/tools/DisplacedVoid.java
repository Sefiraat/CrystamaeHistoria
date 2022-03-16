package io.github.sefiraat.crystamaehistoria.slimefun.items.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.DoubleRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.magical.InfusedMagnet;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class DisplacedVoid extends InfusedMagnet {

    private final ItemSetting<Double> radius = new DoubleRangeSetting(this, "pickup-radius", 0.1, 10, 20);

    @ParametersAreNonnullByDefault
    public DisplacedVoid(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(radius);
    }

    @Override
    public double getRadius() {
        return radius.getValue();
    }
}
