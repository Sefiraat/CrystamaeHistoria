package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.chroniclerpanel.ChroniclerPanel;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar.RealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.staveconfigurator.StaveConfigurator;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class Machines {

    private final CrystamaeHistoria plugin;

    @ParametersAreNonnullByDefault
    public Machines(CrystamaeHistoria p) {
        this.plugin = p;
    }

    public void setup() {
        new ChroniclerPanel(
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
        ).register(plugin);

        new RealisationAltar(
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
        ).register(plugin);

        new LiquefactionBasin(
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
                CrystamaeHistoria.getStructure().getMaterials().amalgamateIngot.getItem(),
                SlimefunItems.TALISMAN_MAGICIAN,
                CrystamaeHistoria.getStructure().getMaterials().amalgamateIngot.getItem(),
            }
        ).register(plugin);

        new StaveConfigurator(
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
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.COPPER_INGOT,
                SlimefunItems.COPPER_INGOT,
                SlimefunItems.COPPER_INGOT,
                SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                new ItemStack(Material.AMETHYST_CLUSTER),
                SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                CrystamaeHistoria.getStructure().getMaterials().amalgamateIngot.getItem(),
                Materials.CHARGED_PLATE_T_1.getItem(),
                CrystamaeHistoria.getStructure().getMaterials().amalgamateIngot.getItem(),
            }
        ).register(plugin);
    }

}
