package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.chroniclerpanel.ChroniclerPanel;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar.RealisationAltar;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Machines {

    private final Structure parent;
    private final CrystamaeHistoria plugin;

    public Machines(Structure s, CrystamaeHistoria p) {
        this.parent = s;
        this.plugin = p;
    }

    public void setup() {
        new ChroniclerPanel(
                parent.getCategories().MECHANISMS_T1,
                ThemeUtils.themedSlimefunItemStack(
                        "CHRONICLER_PANEL_1",
                        new ItemStack(Material.DEEPSLATE_TILE_SLAB),
                        ThemeType.MECHANISM,
                        "Chronicler Panel",
                        "The chronicler panel will draw out",
                        "the stories contained within a given",
                        "block over time."
                ),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.DEEPSLATE_BRICKS),   new ItemStack(Material.DEEPSLATE_BRICKS),   new ItemStack(Material.DEEPSLATE_BRICKS),
                        SlimefunItems.CORINTHIAN_BRONZE_INGOT,      new ItemStack(Material.AMETHYST_CLUSTER),   SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                        SlimefunItems.MAGIC_LUMP_2,                 SlimefunItems.MAGIC_LUMP_2,                 SlimefunItems.MAGIC_LUMP_2,
                }
        ).register(plugin);
        new RealisationAltar(
                parent.getCategories().MECHANISMS_T1,
                ThemeUtils.themedSlimefunItemStack(
                        "REALISATION_ALTAR_1",
                        new ItemStack(Material.CHISELED_DEEPSLATE),
                        ThemeType.MECHANISM,
                        "Realisation Altar",
                        "The realisation altar takes storied",
                        "blocks and converts their stories into",
                        "a physical form."
                ),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        null,                                       new ItemStack(Material.BOOK),               null,
                        SlimefunItems.CORINTHIAN_BRONZE_INGOT,      new ItemStack(Material.AMETHYST_CLUSTER),   SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                        SlimefunItems.MAGIC_LUMP_2,                 SlimefunItems.TALISMAN_MAGICIAN,            SlimefunItems.MAGIC_LUMP_2,
                }
        ).register(plugin);
    }

}
