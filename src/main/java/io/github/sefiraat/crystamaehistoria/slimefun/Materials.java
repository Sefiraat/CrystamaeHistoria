package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.PowderedEssence;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.recipetypes.LiquefactionBasinCraftingRecipeType;
import io.github.sefiraat.crystamaehistoria.slimefun.recipetypes.NetherDrainingRecipeType;
import io.github.sefiraat.crystamaehistoria.slimefun.recipetypes.RealisationAltarRecipeType;
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
public class Materials {

    private static final Map<StoryType, SlimefunItem> DUMMY_CRYSTAL_MAP = new EnumMap<>(StoryType.class);
    static final Map<StoryRarity, Map<StoryType, SlimefunItem>> CRYSTAL_MAP = new EnumMap<>(StoryRarity.class);

    @Getter
    private static UnplaceableBlock blankCrystal;
    @Getter
    private static UnplaceableBlock polychromaticCrystal;
    @Getter
    private static UnplaceableBlock kaleidoscopicCrystal;
    @Getter
    private static UnplaceableBlock motleyCrystal;
    @Getter
    private static UnplaceableBlock prismaticCrystal;
    @Getter
    private static SlimefunItem amalgamateDustCommon;
    @Getter
    private static SlimefunItem amalgamateIngotCommon;
    @Getter
    private static SlimefunItem amalgamateDustUncommon;
    @Getter
    private static SlimefunItem amalgamateIngotUncommon;
    @Getter
    private static SlimefunItem amalgamateDustRare;
    @Getter
    private static SlimefunItem amalgamateIngotRare;
    @Getter
    private static SlimefunItem amalgamateDustEpic;
    @Getter
    private static SlimefunItem amalgamateIngotEpic;
    @Getter
    private static SlimefunItem amalgamateDustMythical;
    @Getter
    private static SlimefunItem amalgamateIngotMythical;
    @Getter
    private static SlimefunItem amalgamateDustUnique;
    @Getter
    private static SlimefunItem amalgamateIngotUnique;
    @Getter
    private static SlimefunItem imbuedGlass;
    @Getter
    private static SlimefunItem uncannyPearl;
    @Getter
    private static SlimefunItem gildedPearl;
    @Getter
    private static SlimefunItem basicFibres;
    @Getter
    private static PowderedEssence powderedEssence;
    @Getter
    private static SlimefunItem magicalMilk;

    public static void setup() {

        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Dummy Crystals (for recipe & compendium displays)
        for (StoryType type : StoryType.getCachedValues()) {
            ThemeType theme = ThemeType.getByType(type);
            SlimefunItem sfItem = new Crystal(
                ItemGroups.DUMMY_ITEM_GROUP,
                ThemeType.themedSlimefunItemStack(
                    "CRY_CRYSTAL_DUMMY_" + type + "_" + type,
                    Skulls.getByType(type).getPlayerHead(),
                    ThemeType.CRYSTAL,
                    theme.getColor() + TextUtils.toTitleCase(type + " Crystal"),
                    "Magical Crystamae in it's physical form"
                ),
                RealisationAltarRecipeType.TYPE,
                new ItemStack[]{},
                StoryRarity.COMMON,
                type
            );
            sfItem.register(plugin);
            DUMMY_CRYSTAL_MAP.put(type, sfItem);
        }

        // Live Crystals
        for (StoryRarity rarity : StoryRarity.getCachedValues()) {
            Map<StoryType, SlimefunItem> storyTypeSlimefunItemMap = new EnumMap<>(StoryType.class);
            for (StoryType type : StoryType.values()) {
                ThemeType theme = ThemeType.getByRarity(rarity);
                SlimefunItem slimefunItem = new Crystal(
                    ItemGroups.CRYSTALS,
                    ThemeType.themedSlimefunItemStack(
                        "CRY_CRYSTAL_" + rarity + "_" + type.toString(),
                        Skulls.getByType(type).getPlayerHead(),
                        ThemeType.CRYSTAL,
                        theme.getColor() + TextUtils.toTitleCase(rarity + " " + type) + " Crystal",
                        "Magical Crystamae in it's physical form",
                        "Higher tier blocks are more likely to",
                        "provide rarer Crystal types.",
                        "",
                        "Provides " + Crystal.getRarityValueMap().get(rarity) + " Crysta."
                    ),
                    RealisationAltarRecipeType.TYPE,
                    new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
                    rarity,
                    type
                );
                slimefunItem.register(plugin);
                storyTypeSlimefunItemMap.put(type, slimefunItem);
                CRYSTAL_MAP.put(rarity, storyTypeSlimefunItemMap);
            }
        }

        // Blank Crystal
        blankCrystal = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.CRYSTAL_BLANK,
            NetherDrainingRecipeType.TYPE,
            NetherDrainingRecipeType.getDummyRecipe(CrystaStacks.CRYSTAL_BLANK)
        );

        // Polychromatic Crystal
        RecipeItem polychromaticCrystalRecipe = new RecipeItem(
            CrystaStacks.CRYSTAL_BLANK,
            StoryType.ELEMENTAL, 10,
            StoryType.MECHANICAL, 10,
            StoryType.ALCHEMICAL, 10
        );
        polychromaticCrystal = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.CRYSTAL_POLYCHROMATIC,
            LiquefactionBasinCraftingRecipeType.TYPE,
            polychromaticCrystalRecipe.getDisplayRecipe(),
            CrystaStacks.CRYSTAL_POLYCHROMATIC.asQuantity(3)
        );

        // Kaleidoscopic Crystal
        RecipeItem kaleidoscopicCrystalRecipe = new RecipeItem(
            CrystaStacks.CRYSTAL_BLANK,
            StoryType.HISTORICAL, 10,
            StoryType.HUMAN, 10,
            StoryType.ANIMAL, 10
        );
        kaleidoscopicCrystal = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.CRYSTAL_KALEIDOSCOPIC,
            LiquefactionBasinCraftingRecipeType.TYPE,
            kaleidoscopicCrystalRecipe.getDisplayRecipe(),
            CrystaStacks.CRYSTAL_KALEIDOSCOPIC.asQuantity(3)
        );

        // Motley Crystal
        RecipeItem motleyCrystalRecipe = new RecipeItem(
            CrystaStacks.CRYSTAL_BLANK,
            StoryType.CELESTIAL, 10,
            StoryType.VOID, 10,
            StoryType.PHILOSOPHICAL, 10
        );
        motleyCrystal = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.CRYSTAL_MOTLEY,
            LiquefactionBasinCraftingRecipeType.TYPE,
            motleyCrystalRecipe.getDisplayRecipe(),
            CrystaStacks.CRYSTAL_MOTLEY.asQuantity(3)
        );

        // Prismatic Crystal
        prismaticCrystal = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.CRYSTAL_PRISMATIC,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, null, null,
                CrystaStacks.CRYSTAL_POLYCHROMATIC, CrystaStacks.CRYSTAL_KALEIDOSCOPIC, CrystaStacks.CRYSTAL_MOTLEY,
                null, null, null
            },
            CrystaStacks.CRYSTAL_PRISMATIC.asQuantity(3)
        );

        // Amalgamate Dust Common
        amalgamateDustCommon = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_DUST_COMMON,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.ELEMENTAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.MECHANICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.ALCHEMICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.HISTORICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.HUMAN).getItem(),
                CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.ANIMAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.CELESTIAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.VOID).getItem(),
                CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.PHILOSOPHICAL).getItem()
            }
        );

        // Amalgamate Dust Uncommon
        amalgamateDustUncommon = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_DUST_UNCOMMON,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.ELEMENTAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.MECHANICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.ALCHEMICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.HISTORICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.HUMAN).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.ANIMAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.CELESTIAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.VOID).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.PHILOSOPHICAL).getItem()
            }
        );

        // Amalgamate Dust Rare
        amalgamateDustRare = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_DUST_RARE,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.ELEMENTAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.MECHANICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.ALCHEMICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.HISTORICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.HUMAN).getItem(),
                CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.ANIMAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.CELESTIAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.VOID).getItem(),
                CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.PHILOSOPHICAL).getItem()
            }
        );

        // Amalgamate Dust Epic
        amalgamateDustEpic = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_DUST_EPIC,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.ELEMENTAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.MECHANICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.ALCHEMICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.HISTORICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.HUMAN).getItem(),
                CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.ANIMAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.CELESTIAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.VOID).getItem(),
                CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.PHILOSOPHICAL).getItem()
            }
        );

        // Amalgamate Dust Mythical
        amalgamateDustMythical = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_DUST_MYTHICAL,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.ELEMENTAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.MECHANICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.ALCHEMICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.HISTORICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.HUMAN).getItem(),
                CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.ANIMAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.CELESTIAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.VOID).getItem(),
                CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.PHILOSOPHICAL).getItem()
            }
        );

        // Amalgamate Dust Unique
        amalgamateDustUnique = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_DUST_UNIQUE,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.ELEMENTAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.MECHANICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.ALCHEMICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.HISTORICAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.HUMAN).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.ANIMAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.CELESTIAL).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.VOID).getItem(),
                CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.PHILOSOPHICAL).getItem()
            }
        );

        // Amalgamate Ingot Common
        amalgamateIngotCommon = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_INGOT_COMMON,
            RecipeType.SMELTERY,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_COMMON
            }
        );

        // Amalgamate Ingot Uncommon
        amalgamateIngotUncommon = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_INGOT_UNCOMMON,
            RecipeType.SMELTERY,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_UNCOMMON
            }
        );

        // Amalgamate Ingot Rare
        amalgamateIngotRare = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_INGOT_RARE,
            RecipeType.SMELTERY,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_RARE
            }
        );

        // Amalgamate Ingot Epic
        amalgamateIngotEpic = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_INGOT_EPIC,
            RecipeType.SMELTERY,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_EPIC
            }
        );

        // Amalgamate Ingot Mythical
        amalgamateIngotMythical = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_INGOT_MYTHICAL,
            RecipeType.SMELTERY,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_MYTHICAL
            }
        );

        // Amalgamate Ingot Unique
        amalgamateIngotUnique = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.AMALGAMATE_INGOT_UNIQUE,
            RecipeType.SMELTERY,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_UNIQUE
            }
        );

        // Imbued Glass
        RecipeItem imbuedGlassRecipe = new RecipeItem(
            new ItemStack(Material.GLASS_PANE),
            StoryType.ELEMENTAL, 10,
            StoryType.HUMAN, 10,
            StoryType.VOID, 10
        );
        imbuedGlass = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.IMBUED_GLASS,
            LiquefactionBasinCraftingRecipeType.TYPE,
            imbuedGlassRecipe.getDisplayRecipe()
        );

        // Uncanny Pearl
        RecipeItem uncannyPearlRecipe = new RecipeItem(
            new ItemStack(Material.ENDER_PEARL),
            StoryType.VOID, 25,
            StoryType.CELESTIAL, 25,
            StoryType.ALCHEMICAL, 25
        );
        uncannyPearl = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.UNCANNY_PEARL,
            LiquefactionBasinCraftingRecipeType.TYPE,
            uncannyPearlRecipe.getDisplayRecipe()
        );

        // Gilded Pearl
        gildedPearl = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.GILDED_PEARL,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON,
                SlimefunItems.GILDED_IRON, CrystaStacks.UNCANNY_PEARL, SlimefunItems.GILDED_IRON,
                SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON, SlimefunItems.GILDED_IRON
            }
        );

        // Basic Fibres
        RecipeItem basicFibresRecipe = new RecipeItem(
            new ItemStack(Material.WHEAT),
            StoryType.MECHANICAL, 5,
            StoryType.HISTORICAL, 5,
            StoryType.HUMAN, 5
        );
        basicFibres = new UnplaceableBlock(
            ItemGroups.MATERIALS,
            CrystaStacks.BASIC_FIBRES,
            LiquefactionBasinCraftingRecipeType.TYPE,
            basicFibresRecipe.getDisplayRecipe()
        );

        // Powdered Essence
        RecipeItem powderedEssenceRecipe = new RecipeItem(
            new ItemStack(Material.BONE_MEAL),
            StoryType.ELEMENTAL, 20,
            StoryType.ALCHEMICAL, 25,
            StoryType.PHILOSOPHICAL, 15
        );
        powderedEssence = new PowderedEssence(
            ItemGroups.MATERIALS,
            CrystaStacks.POWDERED_ESSENCE,
            LiquefactionBasinCraftingRecipeType.TYPE,
            powderedEssenceRecipe.getDisplayRecipe(),
            250
        );

        // Magical Milk
        RecipeItem magicalMilkRecipe = new RecipeItem(
            new ItemStack(Material.MILK_BUCKET),
            StoryType.ALCHEMICAL, 25,
            StoryType.HUMAN, 75,
            StoryType.ANIMAL, 50
        );
        magicalMilk = new SlimefunItem(
            ItemGroups.MATERIALS,
            CrystaStacks.MAGICAL_MILK,
            LiquefactionBasinCraftingRecipeType.TYPE,
            magicalMilkRecipe.getDisplayRecipe()
        );

        // Slimefun Registry
        blankCrystal.register(plugin);
        polychromaticCrystal.register(plugin);
        kaleidoscopicCrystal.register(plugin);
        motleyCrystal.register(plugin);
        amalgamateDustCommon.register(plugin);
        amalgamateDustUncommon.register(plugin);
        amalgamateDustRare.register(plugin);
        amalgamateDustEpic.register(plugin);
        amalgamateDustMythical.register(plugin);
        amalgamateDustUnique.register(plugin);
        amalgamateIngotCommon.register(plugin);
        amalgamateIngotUncommon.register(plugin);
        amalgamateIngotRare.register(plugin);
        amalgamateIngotEpic.register(plugin);
        amalgamateIngotMythical.register(plugin);
        amalgamateIngotUnique.register(plugin);
        imbuedGlass.register(plugin);
        uncannyPearl.register(plugin);
        gildedPearl.register(plugin);
        basicFibres.register(plugin);
        powderedEssence.register(plugin);
        magicalMilk.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(polychromaticCrystal, polychromaticCrystalRecipe);
        LiquefactionBasinCache.addCraftingRecipe(kaleidoscopicCrystal, kaleidoscopicCrystalRecipe);
        LiquefactionBasinCache.addCraftingRecipe(motleyCrystal, motleyCrystalRecipe);

        LiquefactionBasinCache.addCraftingRecipe(imbuedGlass, imbuedGlassRecipe);
        LiquefactionBasinCache.addCraftingRecipe(uncannyPearl, uncannyPearlRecipe);
        LiquefactionBasinCache.addCraftingRecipe(basicFibres, basicFibresRecipe);
        LiquefactionBasinCache.addCraftingRecipe(powderedEssence, powderedEssenceRecipe);
        LiquefactionBasinCache.addCraftingRecipe(magicalMilk, magicalMilkRecipe);
    }

    public static Map<StoryType, SlimefunItem> getDummyCrystalMap() {
        return DUMMY_CRYSTAL_MAP;
    }

    public static Map<StoryRarity, Map<StoryType, SlimefunItem>> getCrystalMap() {
        return CRYSTAL_MAP;
    }
}
