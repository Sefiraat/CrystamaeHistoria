package io.github.sefiraat.crystamaehistoria.slimefun.machines;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.Structure;
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
    }

}
