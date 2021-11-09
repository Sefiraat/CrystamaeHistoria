package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.RefactingLens;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.ThaumaturgicSalt;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.BlankPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Tools {

    @Getter
    private static SlimefunItem inertPlate;
    @Getter
    private static SlimefunItem chargedPlate;
    @Getter
    private static Stave staveBasic;
    @Getter
    private static Stave staveAdvanced;
    @Getter
    private static RefactingLens refractingLens;
    @Getter
    private static ThaumaturgicSalt thaumaturgicSalts;

    public static void setup() {
        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        final ItemStack elementalUniqueCrystal = Materials.CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.ELEMENTAL).getItem();
        final ItemStack commonIngot = Materials.getAmalgamateIngotCommon().getItem();
        final ItemStack commonDust = Materials.getAmalgamateDustCommon().getItem();

        // Inert Plate
        RecipeItem inertPlateRecipe = new RecipeItem(
            SlimefunItems.REINFORCED_PLATE.clone(),
            StoryType.ELEMENTAL, 10,
            StoryType.HUMAN, 10,
            StoryType.PHILOSOPHICAL, 10
        );
        inertPlate = new BlankPlate(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_SPELL_PLATE_1",
                new ItemStack(Material.PAPER),
                ThemeType.TOOL,
                "Basic Spell Plate",
                "A blank plate that has the potential to",
                "store magical energy"
            ),
            RecipeType.ORE_WASHER,
            inertPlateRecipe.getDisplayRecipe(),
            1
        );


        // Charged Plate
        chargedPlate = new ChargedPlate(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_CHARGED_PLATE_1",
                new ItemStack(Material.PAPER),
                ThemeType.TOOL,
                "Charged Basic Spell Plate",
                "A magically charged plate storing magic",
                "potential."
            ),
            RecipeType.ORE_WASHER,
            new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
            1
        );

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
                null, null, elementalUniqueCrystal,
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
                commonIngot, commonIngot, commonIngot,
                commonIngot, staveBasic.getItem().clone(), commonIngot,
                commonIngot, commonIngot, commonIngot
            },
            2
        );

        // Refracting Lens
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
                "",
                "Right click on a Crystamae Block for",
                "details if available.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Works with:",
                ChatColor.DARK_BLUE + "Liquefaction Basin",
                ChatColor.DARK_BLUE + "Exp Collector"
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, Materials.getImbuedGlass().getItem(), null,
                null, new ItemStack(Material.SPYGLASS), null,
                null, commonIngot, null
            }
        );

        // Thaumaturgic Salt
        thaumaturgicSalts = new ThaumaturgicSalt(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_THAUMATURGIC_SALT",
                new ItemStack(Material.REDSTONE),
                ThemeType.TOOL,
                "Thaumaturgic Salts",
                "A special formulation of salts",
                "that can absorb unwanted crysta",
                "from a Liquefaction Basin.",
                "",
                "Right click on a Liquefaction Basin",
                "to empty it."
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                commonDust, commonDust, commonDust,
                commonDust, SlimefunItems.SALT, commonDust,
                commonDust, commonDust, commonDust
            }
        );

        // Slimefun Registry
        chargedPlate.register(CrystamaeHistoria.getInstance());
        inertPlate.register(CrystamaeHistoria.getInstance());
        staveBasic.register(plugin);
        staveAdvanced.register(plugin);
        refractingLens.register(plugin);
        thaumaturgicSalts.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(inertPlate, inertPlateRecipe);
    }
}
