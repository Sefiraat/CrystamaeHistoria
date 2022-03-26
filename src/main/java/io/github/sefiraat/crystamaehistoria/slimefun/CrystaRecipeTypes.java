package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class CrystaRecipeTypes {

    private static final Map<ItemStack, ItemStack> DRAINING_RECIPES = new HashMap<>();

    public static final RecipeType LIQUEFACTION_CRAFTING = new RecipeType(Keys.LIQUEFACTION_CRAFTING_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_LIQUEFACTION_CRAFTING);
    public static final RecipeType LIQUEFACTION_SPELL = new RecipeType(Keys.LIQUEFACTION_SPELL_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_LIQUEFACTION_SPELL);
    public static final RecipeType NETHER_DRAINING = new RecipeType(Keys.NETHER_DRAINING_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_NETHER_DRAINING);
    public static final RecipeType REALISATION_ALTAR_NORMAL = new RecipeType(Keys.REALISATION_ALTAR_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_REALISATION_ALTAR_NORMAL);
    public static final RecipeType REALISATION_ALTAR_SIGIL = new RecipeType(Keys.REALISATION_ALTAR_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_REALISATION_ALTAR_SIGIL);

    public static Map<ItemStack, ItemStack> getDrainingRecipes() {
        return DRAINING_RECIPES;
    }

    public static ItemStack[] getDummyRecipe(@Nonnull ItemStack output) {
        final ItemStack[] itemStacks = new ItemStack[9];
        int i = 0;
        for (Map.Entry<StoryType, SlimefunItem> entry : Materials.getCrystalMap().get(StoryRarity.MYTHICAL).entrySet()) {
            final ItemStack itemStack = entry.getValue().getItem();
            DRAINING_RECIPES.put(itemStack, output);
            itemStacks[i] = itemStack;
            i++;
        }
        return itemStacks;
    }
}
