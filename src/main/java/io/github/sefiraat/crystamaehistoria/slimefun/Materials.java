package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.resource.Skulls;
import io.github.sefiraat.crystamaehistoria.slimefun.categories.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar.DummyRealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.BlankPlateBasic;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class Materials {

    public static final Map<StoryRarity, Map<StoryType, SlimefunItem>> CRYSTAL_MAP = new HashMap<>();
    public static SlimefunItem PLATE_T1;
    private final Structure parent;
    private final CrystamaeHistoria plugin;

    @ParametersAreNonnullByDefault
    public Materials(Structure s, CrystamaeHistoria p) {
        this.parent = s;
        this.plugin = p;
    }

    public void setup() {
        setUpCrystals();
        PLATE_T1 = new BlankPlateBasic(
                ItemGroups.CRYSTALS,
                ThemeType.themeStack(
                        "CRY_SPELL_PLATE_1",
                        new ItemStack(Material.PAPER),
                        ThemeType.CRAFTING,
                        "Basic Spell BlankPlateBasic",
                        "Magical Crystamae in it's physical form"
                ),
                RecipeType.ORE_WASHER,
                new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
                1
        );
        PLATE_T1.register(plugin);
    }

    private void setUpCrystals() {
        for (StoryRarity rarity : StoryRarity.getValues()) {
            Map<StoryType, SlimefunItem> storyTypeSlimefunItemMap = new HashMap<>();
            for (StoryType type : StoryType.values()) {
                ThemeType theme = ThemeType.getByRarity(rarity);
                SlimefunItem sfItem = new Crystal(
                        ItemGroups.CRYSTALS,
                        ThemeType.themeStack(
                                "CRY_CRYSTAL_" + rarity.toString() + "_" + type.toString(),
                                Skulls.getByTypeAndRarity(rarity, type).getPlayerHead(),
                                ThemeType.CRYSTAL,
                                theme.getChatColor() + TextUtils.toTitleCase(rarity.toString() + " " + type.toString()) + " Crystal",
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
