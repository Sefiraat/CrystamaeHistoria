package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.DummyLiquefactionBasinCrafting;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.realisationaltar.DummyRealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.BlankPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
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
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;

public class Materials {

    public static final Map<StoryType, SlimefunItem> DUMMY_CRYSTAL_MAP = new EnumMap<>(StoryType.class);
    public static final Map<StoryRarity, Map<StoryType, SlimefunItem>> CRYSTAL_MAP = new EnumMap<>(StoryRarity.class);


    @Getter
    private static SlimefunItem inertPlate;
    @Getter
    private static SlimefunItem chargedPlate;
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

    public static void setup() {

        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Dummy Crystals (for recipe & compendium displays)
        for (StoryType type : StoryType.getCachedValues()) {
            ThemeType theme = ThemeType.getByType(type);
            SlimefunItem sfItem = new Crystal(
                ItemGroups.DUMMY_ITEM_GROUP,
                ThemeType.themedSlimefunItemStack(
                    "CRY_CRYSTAL_DUMMY_" + type.toString() + "_" + type.toString(),
                    Skulls.getByType(type).getPlayerHead(),
                    ThemeType.CRYSTAL,
                    theme.getColor() + TextUtils.toTitleCase(type.toString() + " Crystal"),
                    "Magical Crystamae in it's physical form"
                ),
                DummyRealisationAltar.TYPE,
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
                        "CRY_CRYSTAL_" + rarity.toString() + "_" + type.toString(),
                        Skulls.getByType(type).getPlayerHead(),
                        ThemeType.CRYSTAL,
                        theme.getColor() + TextUtils.toTitleCase(rarity.toString() + " " + type.toString()) + " Crystal",
                        "Magical Crystamae in it's physical form"
                    ),
                    DummyRealisationAltar.TYPE,
                    new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
                    rarity,
                    type
                );
                slimefunItem.register(plugin);
                storyTypeSlimefunItemMap.put(type, slimefunItem);
                CRYSTAL_MAP.put(rarity, storyTypeSlimefunItemMap);
            }
        }

        // Inert Plate
        RecipeItem inertRecipeItem = new RecipeItem(
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
                ThemeType.CRAFTING,
                "Basic Spell Plate",
                "A blank plate that has the potential to",
                "store magical energy"
            ),
            RecipeType.ORE_WASHER,
            inertRecipeItem.getDisplayRecipe(),
            1
        );


        // Charged Plate
        chargedPlate = new ChargedPlate(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_CHARGED_PLATE_1",
                new ItemStack(Material.PAPER),
                ThemeType.CRAFTING,
                "Charged Basic Spell Plate",
                "A magically charged plate storing magic",
                "potential."
            ),
            RecipeType.ORE_WASHER,
            new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
            1
        );

        // Amalgamate Dust Common
        amalgamateDustCommon = new SlimefunItem(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_DUST_COMMON",
                new ItemStack(Material.GLOWSTONE_DUST),
                ThemeType.CRAFTING,
                "Amalgamate Dust (Common)",
                "A dust combining all magic types."
            ),
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
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_DUST_UNCOMMON",
                new ItemStack(Material.GLOWSTONE_DUST),
                ThemeType.CRAFTING,
                "Amalgamate Dust (Uncommon)",
                "A dust combining all magic types."
            ),
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
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_DUST_RARE",
                new ItemStack(Material.GLOWSTONE_DUST),
                ThemeType.CRAFTING,
                "Amalgamate Dust (Rare)",
                "A dust combining all magic types."
            ),
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
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_DUST_EPIC",
                new ItemStack(Material.GLOWSTONE_DUST),
                ThemeType.CRAFTING,
                "Amalgamate Dust (Epic)",
                "A dust combining all magic types."
            ),
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
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_DUST_MYTHICAL",
                new ItemStack(Material.GLOWSTONE_DUST),
                ThemeType.CRAFTING,
                "Amalgamate Dust (Mythical)",
                "A dust combining all magic types."
            ),
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
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_DUST_UNIQUE",
                new ItemStack(Material.GLOWSTONE_DUST),
                ThemeType.CRAFTING,
                "Amalgamate Dust (Unique)",
                "A dust combining all magic types."
            ),
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
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_INGOT_COMMON",
                new ItemStack(Material.GOLD_INGOT),
                ThemeType.CRAFTING,
                "Amalgamate Ingot (Common)",
                "An ingot crafted of pure magics."
            ),
            RecipeType.SMELTERY,
            new ItemStack[]{
                amalgamateDustCommon.getItem()
            }
        );

        // Amalgamate Ingot Uncommon
        amalgamateIngotUncommon = new SlimefunItem(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_INGOT_UNCOMMON",
                new ItemStack(Material.GOLD_INGOT),
                ThemeType.CRAFTING,
                "Amalgamate Ingot (Uncommon)",
                "An ingot crafted of pure magics."
            ),
            RecipeType.SMELTERY,
            new ItemStack[]{
                amalgamateDustUncommon.getItem()
            }
        );

        // Amalgamate Ingot Rare
        amalgamateIngotRare = new SlimefunItem(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_INGOT_RARE",
                new ItemStack(Material.GOLD_INGOT),
                ThemeType.CRAFTING,
                "Amalgamate Ingot (Rare)",
                "An ingot crafted of pure magics."
            ),
            RecipeType.SMELTERY,
            new ItemStack[]{
                amalgamateDustRare.getItem()
            }
        );

        // Amalgamate Ingot Epic
        amalgamateIngotEpic = new SlimefunItem(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_INGOT_EPIC",
                new ItemStack(Material.GOLD_INGOT),
                ThemeType.CRAFTING,
                "Amalgamate Ingot (Epic)",
                "An ingot crafted of pure magics."
            ),
            RecipeType.SMELTERY,
            new ItemStack[]{
                amalgamateDustEpic.getItem()
            }
        );

        // Amalgamate Ingot Mythical
        amalgamateIngotMythical = new SlimefunItem(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_INGOT_MYTHICAL",
                new ItemStack(Material.GOLD_INGOT),
                ThemeType.CRAFTING,
                "Amalgamate Ingot (Mythical)",
                "An ingot crafted of pure magics."
            ),
            RecipeType.SMELTERY,
            new ItemStack[]{
                amalgamateDustMythical.getItem()
            }
        );

        // Amalgamate Ingot Unique
        amalgamateIngotUnique = new SlimefunItem(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_INGOT_UNIQUE",
                new ItemStack(Material.GOLD_INGOT),
                ThemeType.CRAFTING,
                "Amalgamate Ingot (Unique)",
                "An ingot crafted of pure magics."
            ),
            RecipeType.SMELTERY,
            new ItemStack[]{
                amalgamateDustUnique.getItem()
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
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_IMBUED_GLASS",
                new ItemStack(Material.GLASS_PANE),
                ThemeType.CRAFTING,
                "Imbued Glass",
                "Glass imbued with Crysta that has",
                "some strange properties."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            imbuedGlassRecipe.getDisplayRecipe()
        );

        // Slimefun Registry
        chargedPlate.register(CrystamaeHistoria.getInstance());
        inertPlate.register(CrystamaeHistoria.getInstance());
        amalgamateDustCommon.register(plugin);
        amalgamateIngotCommon.register(plugin);
        amalgamateDustUncommon.register(plugin);
        amalgamateIngotUncommon.register(plugin);
        amalgamateDustRare.register(plugin);
        amalgamateIngotRare.register(plugin);
        amalgamateDustEpic.register(plugin);
        amalgamateIngotEpic.register(plugin);
        amalgamateDustMythical.register(plugin);
        amalgamateIngotMythical.register(plugin);
        amalgamateDustUnique.register(plugin);
        amalgamateIngotUnique.register(plugin);
        imbuedGlass.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(inertPlate, inertRecipeItem);
        LiquefactionBasinCache.addCraftingRecipe(imbuedGlass, imbuedGlassRecipe);

    }
}
