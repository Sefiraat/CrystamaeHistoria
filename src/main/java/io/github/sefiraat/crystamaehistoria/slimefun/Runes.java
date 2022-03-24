package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.PowderedEssence;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Skulls;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;

@UtilityClass
public class Runes {

    @Getter
    private static UnplaceableBlock rune_a;

    public static void setup() {

        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Rune A
        rune_a = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.RUNE_A,
            CrystaRecipeTypes.NETHER_DRAINING,
            CrystaRecipeTypes.getDummyRecipe(CrystaStacks.CRYSTAL_BLANK)
        );

        // Slimefun Registry
        rune_a.register(plugin);

    }

}
