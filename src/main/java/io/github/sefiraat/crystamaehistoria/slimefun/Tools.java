package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.RefactingLens;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Tools {

    @Getter
    private static Stave staveBasic;
    @Getter
    private static Stave staveAdvanced;
    @Getter
    private static RefactingLens refractingLens;

    public static void setup() {
        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        final ItemStack elementalCrystal = Materials.CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.ELEMENTAL).getItem();
        final ItemStack amalgamateIngot = Materials.getAmalgamateIngotCommon().getItem();

        // Basic Stave

        staveBasic = new Stave(
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

        staveAdvanced = new Stave(
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
                amalgamateIngot, amalgamateIngot,               amalgamateIngot,
                amalgamateIngot, staveBasic.getItem().clone(),  amalgamateIngot,
                amalgamateIngot, amalgamateIngot,               amalgamateIngot
            },
            2
        );

        // Refracting Lens
        final ItemStack ingot = Materials.getAmalgamateIngotCommon().getItem();

        refractingLens = new RefactingLens(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_REFRACTING_LENS",
                new ItemStack(Material.SPYGLASS),
                ThemeType.TOOL,
                "Refracting Lens",
                "This magical lens has glass that can",
                "the lights of Crysta into it's",
                "individual elements.",
                ThemeType.CLICK_INFO.getColor() + "Right Click: " + ThemeType.PASSIVE.getColor() + "Shows the contents",
                ThemeType.PASSIVE.getColor() + "of a Liquefaction Basin."
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, Materials.getImbuedGlass().getItem(),    null,
                null, new ItemStack(Material.SPYGLASS),        null,
                null, ingot,                                   null
            }
        );

        // Slimefun Registry
        staveBasic.register(plugin);
        staveAdvanced.register(plugin);
        refractingLens.register(plugin);
    }
}
