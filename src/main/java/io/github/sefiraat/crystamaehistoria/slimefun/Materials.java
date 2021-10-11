package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.resource.Skulls;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar.DummyRealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.BlankPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;
import java.util.Map;

public class Materials {

    // TODO Create a class to store the value map
    public static final Map<StoryRarity, Map<StoryType, SlimefunItem>> CRYSTAL_MAP = new EnumMap<>(StoryRarity.class);

    public static final SlimefunItem INERT_PLATE_T_1;
    public static final SlimefunItem CHARGED_PLATE_T_1;

    private final CrystamaeHistoria plugin;

    @ParametersAreNonnullByDefault
    public Materials(CrystamaeHistoria p) {
        this.plugin = p;
    }

    static {
        INERT_PLATE_T_1 = new BlankPlate(
            ItemGroups.CRYSTALS,
            ThemeType.themeStack(
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
            ItemGroups.CRYSTALS,
            ThemeType.themeStack(
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

        INERT_PLATE_T_1.register(CrystamaeHistoria.inst());
        CHARGED_PLATE_T_1.register(CrystamaeHistoria.inst());

    }

    public void setup() {
        setUpCrystals();
    }

    private void setUpCrystals() {
        for (StoryRarity rarity : StoryRarity.getValues()) {
            Map<StoryType, SlimefunItem> storyTypeSlimefunItemMap = new EnumMap<>(StoryType.class);
            for (StoryType type : StoryType.values()) {
                ThemeType theme = ThemeType.getByRarity(rarity);
                SlimefunItem sfItem = new Crystal(
                    ItemGroups.CRYSTALS,
                    ThemeType.themeStack(
                        "CRY_CRYSTAL_" + rarity.toString() + "_" + type.toString(),
                        Skulls.getByTypeAndRarity(rarity, type).getPlayerHead(),
                        ThemeType.CRYSTAL,
                        theme.getColor() + TextUtils.toTitleCase(rarity.toString() + " " + type.toString()) + " Crystal",
                        "Magical Crystamae in it's physical form"
                    ),
                    DummyRealisationAltar.TYPE,
                    new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
                    rarity,
                    type
                );
                sfItem.register(plugin);
                storyTypeSlimefunItemMap.put(type, sfItem);
            }
            CRYSTAL_MAP.put(rarity, storyTypeSlimefunItemMap);
        }
    }


}
