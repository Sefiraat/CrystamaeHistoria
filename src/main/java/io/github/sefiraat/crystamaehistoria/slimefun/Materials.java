package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar.DummyRealisationAltar;
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
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;

public class Materials {

    public static final Map<StoryType, SlimefunItem> DUMMY_CRYSTAL_MAP = new EnumMap<>(StoryType.class);
    public static final Map<StoryRarity, Map<StoryType, SlimefunItem>> CRYSTAL_MAP = new EnumMap<>(StoryRarity.class);

    public static SlimefunItem INERT_PLATE_T_1;
    public static SlimefunItem CHARGED_PLATE_T_1;
    public static SlimefunItem AMALGAMATE_DUST;
    public static SlimefunItem AMALGAMATE_INGOT;

    public Materials() {

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

        INERT_PLATE_T_1 = new BlankPlate(
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
        CHARGED_PLATE_T_1 = new ChargedPlate(
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

        // Amalgamate Dust
        AMALGAMATE_DUST = new SlimefunItem(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_DUST",
                new ItemStack(Material.GLOWSTONE_DUST),
                ThemeType.CRAFTING,
                "Amalgamate Dust",
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

        // Amalgamate Ingot
        AMALGAMATE_INGOT = new SlimefunItem(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_AMALGAMATE_INGOT",
                new ItemStack(Material.GOLD_INGOT),
                ThemeType.CRAFTING,
                "Amalgamate Ingot",
                "An ingot crafted of pure magics."
            ),
            RecipeType.SMELTERY,
            new ItemStack[]{
                AMALGAMATE_DUST.getItem()
            }
        );

        // Slimefun Registry
        CHARGED_PLATE_T_1.register(CrystamaeHistoria.getInstance());
        INERT_PLATE_T_1.register(CrystamaeHistoria.getInstance());
        AMALGAMATE_DUST.register(plugin);
        AMALGAMATE_INGOT.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(INERT_PLATE_T_1, inertRecipeItem);

    }
}
