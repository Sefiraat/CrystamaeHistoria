package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.ItemGroups;
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
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;
import java.util.Map;

public class Materials {

    public static final SlimefunItem INERT_PLATE_T_1;
    public static final SlimefunItem CHARGED_PLATE_T_1;

    static {
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
            new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
            1
        );

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

        INERT_PLATE_T_1.register(CrystamaeHistoria.getInstance());
        CHARGED_PLATE_T_1.register(CrystamaeHistoria.getInstance());

    }

    // TODO Create a class to store the value map
    @Getter
    private final Map<StoryRarity, Map<StoryType, SlimefunItem>> crystalMap = new EnumMap<>(StoryRarity.class);
    @Getter
    private final Map<StoryType, SlimefunItem> typeItemMap = new EnumMap<>(StoryType.class);
    private final CrystamaeHistoria plugin;
    public SlimefunItem amalgamateDust;
    public SlimefunItem amalgamateIngot;

    @ParametersAreNonnullByDefault
    public Materials(CrystamaeHistoria p) {
        this.plugin = p;
    }

    public void setup() {
        setUpCrystals();
        setUpDummyCrystalTypes();

        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        amalgamateDust = new SlimefunItem(
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
                crystalMap.get(StoryRarity.COMMON).get(StoryType.ELEMENTAL).getItem(),
                crystalMap.get(StoryRarity.COMMON).get(StoryType.MECHANICAL).getItem(),
                crystalMap.get(StoryRarity.COMMON).get(StoryType.ALCHEMICAL).getItem(),
                crystalMap.get(StoryRarity.COMMON).get(StoryType.HISTORICAL).getItem(),
                crystalMap.get(StoryRarity.COMMON).get(StoryType.HUMAN).getItem(),
                crystalMap.get(StoryRarity.COMMON).get(StoryType.ANIMAL).getItem(),
                crystalMap.get(StoryRarity.COMMON).get(StoryType.CELESTIAL).getItem(),
                crystalMap.get(StoryRarity.COMMON).get(StoryType.VOID).getItem(),
                crystalMap.get(StoryRarity.COMMON).get(StoryType.PHILOSOPHICAL).getItem()
            }
        );
        amalgamateDust.register(plugin);

        amalgamateIngot = new SlimefunItem(
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
                amalgamateDust.getItem()
            }
        );
        amalgamateIngot.register(plugin);

    }

    private void setUpCrystals() {
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
                crystalMap.put(rarity, storyTypeSlimefunItemMap);
            }
        }
    }

    private void setUpDummyCrystalTypes() {
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
                new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
                StoryRarity.COMMON,
                type
            );
            sfItem.register(plugin);
            typeItemMap.put(type, sfItem);
        }
    }


}
