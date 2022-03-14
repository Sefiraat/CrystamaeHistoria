package io.github.sefiraat.crystamaehistoria.slimefun.recipetypes;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class NetherDrainingRecipeType {

    public static final SlimefunItemStack STACK = ThemeType.themedSlimefunItemStack(
        "CRY_REALISATION_ALTAR_RECIPE_TYPE",
        new ItemStack(Material.OBSIDIAN),
        ThemeType.RESEARCH,
        "Nether Draining",
        "Crysta has a hard time transferring",
        "to the Nether. If it is in it's Crystal",
        "form, all the magic will be drained."
    );

    public static final RecipeType TYPE = new RecipeType(Keys.REALISATION_ALTAR_RECIPE_TYPE, STACK);

    private static final Map<ItemStack, ItemStack> DRAINING_RECIPES = new HashMap<>();

    public static Map<ItemStack, ItemStack> getDrainingRecipes() {
        return DRAINING_RECIPES;
    }

    public static ItemStack[] generateDrainingRecipe(@Nonnull ItemStack input, @Nonnull ItemStack output) {
        DRAINING_RECIPES.put(input, output);
        return new ItemStack[] {
            null, null, null,
            null, input, null,
            null, null, null
        };
    }
}
