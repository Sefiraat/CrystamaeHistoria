package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.chroniclerpanel.ChroniclerPanel;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.prismaticgilder.PrismaticGilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar.RealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.staveconfigurator.StaveConfigurator;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Mechanisms {

    @Getter
    private static ChroniclerPanel chroniclerPanel1;
    @Getter
    private static ChroniclerPanel chroniclerPanel2;
    @Getter
    private static ChroniclerPanel chroniclerPanel3;
    @Getter
    private static ChroniclerPanel chroniclerPanel4;
    @Getter
    private static ChroniclerPanel chroniclerPanel5;
    @Getter
    private static RealisationAltar realisationAltar1;
    @Getter
    private static RealisationAltar realisationAltar2;
    @Getter
    private static RealisationAltar realisationAltar3;
    @Getter
    private static RealisationAltar realisationAltar4;
    @Getter
    private static RealisationAltar realisationAltar5;
    @Getter
    private static LiquefactionBasin liquefactionBasin1;
    @Getter
    private static LiquefactionBasin liquefactionBasin2;
    @Getter
    private static LiquefactionBasin liquefactionBasin3;
    @Getter
    private static LiquefactionBasin liquefactionBasin4;
    @Getter
    private static LiquefactionBasin liquefactionBasin5;
    @Getter
    private static StaveConfigurator staveConfigurator;
    @Getter
    private static PrismaticGilder prismaticGilder;

    public static void setup() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Chronicler Tier 1
        chroniclerPanel1 = new ChroniclerPanel(
            ItemGroups.MECHANISMS,
            CrystaStacks.CHRONICLER_PANEL_1,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.COBBLED_DEEPSLATE), new ItemStack(Material.COBBLED_DEEPSLATE), new ItemStack(Material.COBBLED_DEEPSLATE),
                SlimefunItems.CORINTHIAN_BRONZE_INGOT, new ItemStack(Material.AMETHYST_CLUSTER), SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                SlimefunItems.MAGIC_LUMP_2, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.MAGIC_LUMP_2,
            },
            1
        );

        // Chronicler Tier 2
        chroniclerPanel2 = new ChroniclerPanel(
            ItemGroups.MECHANISMS,
            CrystaStacks.CHRONICLER_PANEL_2,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.DEEPSLATE_BRICKS), new ItemStack(Material.DEEPSLATE_BRICKS), new ItemStack(Material.DEEPSLATE_BRICKS),
                CrystaStacks.AMALGAMATE_INGOT_UNCOMMON, CrystaStacks.CHRONICLER_PANEL_1, CrystaStacks.AMALGAMATE_INGOT_UNCOMMON,
                SlimefunItems.MAGIC_LUMP_3, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.MAGIC_LUMP_3,
            },
            2
        );

        // Chronicler Tier 3
        RecipeItem chroniclerT3Recipe = new RecipeItem(
            CrystaStacks.CHRONICLER_PANEL_2,
            StoryType.ELEMENTAL, 150,
            StoryType.CELESTIAL, 200,
            StoryType.VOID, 50
        );
        chroniclerPanel3 = new ChroniclerPanel(
            ItemGroups.MECHANISMS,
            CrystaStacks.CHRONICLER_PANEL_3,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            chroniclerT3Recipe.getDisplayRecipe(),
            3
        );

        // Chronicler Tier 4
        RecipeItem chroniclerT4Recipe = new RecipeItem(
            CrystaStacks.CHRONICLER_PANEL_3,
            StoryType.ELEMENTAL, 1000,
            StoryType.CELESTIAL, 850,
            StoryType.VOID, 650
        );
        chroniclerPanel4 = new ChroniclerPanel(
            ItemGroups.MECHANISMS,
            CrystaStacks.CHRONICLER_PANEL_4,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            chroniclerT4Recipe.getDisplayRecipe(),
            4
        );

        // Chronicler Tier 5
        chroniclerPanel5 = new ChroniclerPanel(
            ItemGroups.MECHANISMS,
            CrystaStacks.CHRONICLER_PANEL_5,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_DAWN, null,
                CrystaStacks.RUNE_BEAST, CrystaStacks.CHRONICLER_PANEL_4, CrystaStacks.RUNE_EIGHTFOLD,
                null, CrystaStacks.RUNE_NIGHT, null
            },
            5
        );

        // Realisation Tier 1
        realisationAltar1 = new RealisationAltar(
            ItemGroups.MECHANISMS,
            CrystaStacks.REALISATION_ALTAR_1,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, new ItemStack(Material.BOOK), null,
                SlimefunItems.CORINTHIAN_BRONZE_INGOT, new ItemStack(Material.AMETHYST_CLUSTER), SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                SlimefunItems.MAGIC_LUMP_2, SlimefunItems.COMMON_TALISMAN, SlimefunItems.MAGIC_LUMP_2,
            },
            1
        );

        // Realisation Tier 2
        realisationAltar2 = new RealisationAltar(
            ItemGroups.MECHANISMS,
            CrystaStacks.REALISATION_ALTAR_2,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, new ItemStack(Material.BOOK), null,
                CrystaStacks.AMALGAMATE_INGOT_UNCOMMON, realisationAltar1.getItem(), CrystaStacks.AMALGAMATE_INGOT_UNCOMMON,
                SlimefunItems.MAGIC_LUMP_3, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.MAGIC_LUMP_3,
            },
            2
        );

        // Realisation Tier 3
        RecipeItem realisationT3Recipe = new RecipeItem(
            CrystaStacks.REALISATION_ALTAR_2,
            StoryType.HISTORICAL, 100,
            StoryType.HUMAN, 350,
            StoryType.PHILOSOPHICAL, 150
        );
        realisationAltar3 = new RealisationAltar(
            ItemGroups.MECHANISMS,
            CrystaStacks.REALISATION_ALTAR_3,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            realisationT3Recipe.getDisplayRecipe(),
            3
        );

        // Realisation Tier 4
        RecipeItem realisationT4Recipe = new RecipeItem(
            CrystaStacks.REALISATION_ALTAR_3,
            StoryType.HISTORICAL, 1100,
            StoryType.HUMAN, 720,
            StoryType.PHILOSOPHICAL, 450
        );
        realisationAltar4 = new RealisationAltar(
            ItemGroups.MECHANISMS,
            CrystaStacks.REALISATION_ALTAR_4,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            realisationT4Recipe.getDisplayRecipe(),
            4
        );

        // Realisation Tier 5
        realisationAltar5 = new RealisationAltar(
            ItemGroups.MECHANISMS,
            CrystaStacks.REALISATION_ALTAR_5,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_TRUE_HOLY, null,
                CrystaStacks.RUNE_GATE, CrystaStacks.REALISATION_ALTAR_4, CrystaStacks.RUNE_TRUE_FIRE,
                null, CrystaStacks.RUNE_TRUE_WIND, null
            },
            5
        );

        // Liquefaction T1
        liquefactionBasin1 = new LiquefactionBasin(
            ItemGroups.MECHANISMS,
            CrystaStacks.LIQUEFACTION_BASIN_1,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, null, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.CAULDRON), SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.COMMON_TALISMAN, SlimefunItems.REINFORCED_ALLOY_INGOT
            },
            500,
            Color.fromRGB(150, 150, 150)
        );

        // Liquefaction T2
        liquefactionBasin2 = new LiquefactionBasin(
            ItemGroups.MECHANISMS,
            CrystaStacks.LIQUEFACTION_BASIN_2,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_RARE, null, CrystaStacks.AMALGAMATE_DUST_RARE,
                CrystaStacks.AMALGAMATE_DUST_RARE, CrystaStacks.LIQUEFACTION_BASIN_1, CrystaStacks.AMALGAMATE_DUST_RARE,
                CrystaStacks.AMALGAMATE_DUST_RARE, SlimefunItems.ENCHANTMENT_RUNE, CrystaStacks.AMALGAMATE_DUST_RARE
            },
            1250,
            Color.fromRGB(195, 195, 150)
        );

        // Liquefaction T3
        RecipeItem liquefactionT3Recipe = new RecipeItem(
            CrystaStacks.LIQUEFACTION_BASIN_2,
            StoryType.MECHANICAL, 90,
            StoryType.ALCHEMICAL, 250,
            StoryType.ANIMAL, 185
        );
        liquefactionBasin3 = new LiquefactionBasin(
            ItemGroups.MECHANISMS,
            CrystaStacks.LIQUEFACTION_BASIN_3,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            liquefactionT3Recipe.getDisplayRecipe(),
            2500,
            Color.fromRGB(215, 200, 110)
        );

        // Liquefaction T4
        RecipeItem liquefactionT4Recipe = new RecipeItem(
            CrystaStacks.LIQUEFACTION_BASIN_3,
            StoryType.MECHANICAL, 750,
            StoryType.ALCHEMICAL, 700,
            StoryType.ANIMAL, 600
        );
        liquefactionBasin4 = new LiquefactionBasin(
            ItemGroups.MECHANISMS,
            CrystaStacks.LIQUEFACTION_BASIN_4,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            liquefactionT4Recipe.getDisplayRecipe(),
            5000,
            Color.fromRGB(240, 220, 26)
        );

        // Liquefaction 5
        liquefactionBasin5 = new LiquefactionBasin(
            ItemGroups.MECHANISMS,
            CrystaStacks.LIQUEFACTION_BASIN_5,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_SOVEREIGN, null,
                CrystaStacks.RUNE_MOON, CrystaStacks.LIQUEFACTION_BASIN_4, CrystaStacks.RUNE_BLACK,
                null, CrystaStacks.RUNE_SOUL, null
            },
            10000,
            Color.fromRGB(240, 220, 200)
        );

        // Stave Configurator
        RecipeItem staveConfiguratorRecipe = new RecipeItem(
            new ItemStack(Material.COPPER_BLOCK),
            StoryType.ELEMENTAL, 300,
            StoryType.MECHANICAL, 200,
            StoryType.ALCHEMICAL, 510
        );
        staveConfigurator = new StaveConfigurator(
            ItemGroups.MECHANISMS,
            CrystaStacks.STAVE_CONFIGURATOR,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            staveConfiguratorRecipe.getDisplayRecipe()
        );

        // Prismatic Gilder
        RecipeItem prismaticGilderRecipe = new RecipeItem(
            CrystaStacks.LIQUEFACTION_BASIN_3,
            StoryType.MECHANICAL, 200,
            StoryType.VOID, 200,
            StoryType.CELESTIAL, 200,
            Exalted::isMaxStoryRank
        );
        prismaticGilder = new PrismaticGilder(
            ItemGroups.MECHANISMS,
            CrystaStacks.PRISMATIC_GILDER,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            prismaticGilderRecipe.getDisplayRecipe()
        );

        // Slimefun Registry
        chroniclerPanel1.register(plugin);
        chroniclerPanel2.register(plugin);
        chroniclerPanel3.register(plugin);
        chroniclerPanel4.register(plugin);
        chroniclerPanel5.register(plugin);

        realisationAltar1.register(plugin);
        realisationAltar2.register(plugin);
        realisationAltar3.register(plugin);
        realisationAltar4.register(plugin);
        realisationAltar5.register(plugin);

        liquefactionBasin1.register(plugin);
        liquefactionBasin2.register(plugin);
        liquefactionBasin3.register(plugin);
        liquefactionBasin4.register(plugin);
        liquefactionBasin5.register(plugin);

        staveConfigurator.register(plugin);
        prismaticGilder.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(chroniclerPanel3, chroniclerT3Recipe);
        LiquefactionBasinCache.addCraftingRecipe(chroniclerPanel4, chroniclerT4Recipe);

        LiquefactionBasinCache.addCraftingRecipe(realisationAltar3, realisationT3Recipe);
        LiquefactionBasinCache.addCraftingRecipe(realisationAltar4, realisationT4Recipe);

        LiquefactionBasinCache.addCraftingRecipe(liquefactionBasin3, liquefactionT3Recipe);
        LiquefactionBasinCache.addCraftingRecipe(liquefactionBasin4, liquefactionT4Recipe);

        LiquefactionBasinCache.addCraftingRecipe(staveConfigurator, staveConfiguratorRecipe);

        LiquefactionBasinCache.addCraftingRecipe(prismaticGilder, prismaticGilderRecipe);
    }
}
