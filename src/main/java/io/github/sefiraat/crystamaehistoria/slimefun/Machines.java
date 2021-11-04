package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.chroniclerpanel.ChroniclerPanel;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.DummyLiquefactionBasinCrafting;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar.RealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.staveconfigurator.StaveConfigurator;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Machines {

    public static ChroniclerPanel CHRONICLER_PANEL;
    public static RealisationAltar REALISATION_ALTAR;
    public static LiquefactionBasin LIQUEFACTION_BASIN;
    public static StaveConfigurator STAVE_CONFIGURATOR;

    public Machines() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Chronicler
        CHRONICLER_PANEL = new ChroniclerPanel(
            ItemGroups.MECHANISMS,
            ThemeType.themedSlimefunItemStack(
                "CRY_CHRONICLER_PANEL_1",
                new ItemStack(Material.DEEPSLATE_TILE_SLAB),
                ThemeType.MECHANISM,
                "Chronicler Panel",
                "The chronicler panel will draw out",
                "the stories contained within a given",
                "block over time."
            ),
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.DEEPSLATE_BRICKS), new ItemStack(Material.DEEPSLATE_BRICKS), new ItemStack(Material.DEEPSLATE_BRICKS),
                SlimefunItems.CORINTHIAN_BRONZE_INGOT, new ItemStack(Material.AMETHYST_CLUSTER), SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                SlimefunItems.MAGIC_LUMP_2, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.MAGIC_LUMP_2,
            }
        );

        // Realisation
        REALISATION_ALTAR = new RealisationAltar(
            ItemGroups.MECHANISMS,
            ThemeType.themedSlimefunItemStack(
                "CRY_REALISATION_ALTAR_1",
                new ItemStack(Material.CHISELED_DEEPSLATE),
                ThemeType.MECHANISM,
                "Realisation Altar",
                "The realisation altar takes storied",
                "blocks and converts their stories into",
                "a physical form."
            ),
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, new ItemStack(Material.BOOK), null,
                SlimefunItems.CORINTHIAN_BRONZE_INGOT, new ItemStack(Material.AMETHYST_CLUSTER), SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                SlimefunItems.MAGIC_LUMP_2, SlimefunItems.TALISMAN_MAGICIAN, SlimefunItems.MAGIC_LUMP_2,
            }
        );

        // Liquefaction
        LIQUEFACTION_BASIN = new LiquefactionBasin(
            ItemGroups.MECHANISMS,
            ThemeType.themedSlimefunItemStack(
                "CRY_LIQUEFACTION_BASIN_1",
                new ItemStack(Material.CAULDRON),
                ThemeType.MECHANISM,
                "Liquefaction Basin",
                "The liquefaction basin can take",
                "Crystals and convert them into their liquid",
                "Crystamae form. Used for magical crafting"
            ),
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null,
                new ItemStack(Material.BOOK),
                null,
                SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                new ItemStack(Material.AMETHYST_CLUSTER),
                SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                Materials.AMALGAMATE_INGOT.getItem(),
                SlimefunItems.TALISMAN_MAGICIAN,
                Materials.AMALGAMATE_INGOT.getItem(),
            }
        );


        // Stave Configurator
        RecipeItem staveConfiguratorRecipe = new RecipeItem(
            new ItemStack(Material.COPPER_BLOCK),
            StoryType.ELEMENTAL, 1,
            StoryType.MECHANICAL, 1,
            StoryType.ALCHEMICAL, 1
        );
        STAVE_CONFIGURATOR = new StaveConfigurator(
            ItemGroups.MECHANISMS,
            ThemeType.themedSlimefunItemStack(
                "CRY_STAVE_CONFIGURATOR",
                new ItemStack(Material.CUT_COPPER),
                ThemeType.MECHANISM,
                "Stave Configurator",
                "The Stave Configurator allows you",
                "to add spell plates into your",
                "Staves."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            staveConfiguratorRecipe.getDisplayRecipe()
        );

        // Slimefun Registry
        CHRONICLER_PANEL.register(plugin);
        REALISATION_ALTAR.register(plugin);
        LIQUEFACTION_BASIN.register(plugin);
        STAVE_CONFIGURATOR.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(STAVE_CONFIGURATOR, staveConfiguratorRecipe);
    }

}
