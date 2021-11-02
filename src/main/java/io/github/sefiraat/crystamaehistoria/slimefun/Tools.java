package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Tools {

    public static Stave STAVE_BASIC;
    public static Stave STAVE_ADVANCED;

    public Tools() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Basic Stave
        final ItemStack elementalCrystal = Materials.CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.ELEMENTAL).getItem();

        STAVE_BASIC = new Stave(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_STAVE_1",
                new ItemStack(Material.STICK),
                ThemeType.STAVE,
                "Basic Stave",
                "A stave with the ability to hold",
                "magically charged plates."
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, null, elementalCrystal,
                null, new ItemStack(Material.STICK), null,
                new ItemStack(Material.STICK), null, null
            },
            1
        );

        // Advanced Stave
        final ItemStack ingot = Materials.AMALGAMATE_INGOT.getItem();

        STAVE_ADVANCED = new Stave(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_STAVE_2",
                new ItemStack(Material.STICK),
                ThemeType.STAVE,
                "Advanced Stave",
                "A stave with the ability to hold",
                "magically charged plates."
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                ingot, ingot, ingot,
                ingot, STAVE_BASIC.getItem().clone(), ingot,
                ingot, ingot, ingot
            },
            2
        );

        // Slimefun Registry
        STAVE_BASIC.register(plugin);
        STAVE_ADVANCED.register(plugin);
    }
}
