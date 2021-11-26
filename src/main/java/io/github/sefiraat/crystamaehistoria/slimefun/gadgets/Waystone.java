package io.github.sefiraat.crystamaehistoria.slimefun.gadgets;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.function.Consumer;

public class Waystone extends MysteriousTicker {

    @ParametersAreNonnullByDefault
    public Waystone(ItemGroup category,
                    SlimefunItemStack item,
                    RecipeType recipeType,
                    ItemStack[] recipe,
                    Set<Material> tickingMaterials,
                    int tickFrequency
    ) {
        this(category, item, recipeType, recipe, tickingMaterials, tickFrequency, null);
    }

    @ParametersAreNonnullByDefault
    public Waystone(ItemGroup category,
                    SlimefunItemStack item,
                    RecipeType recipeType,
                    ItemStack[] recipe,
                    Set<Material> tickingMaterials,
                    int tickFrequency,
                    @Nullable Consumer<Block> consumer
    ) {
        super(category, item, recipeType, recipe, tickingMaterials, tickFrequency, consumer);
    }
}
