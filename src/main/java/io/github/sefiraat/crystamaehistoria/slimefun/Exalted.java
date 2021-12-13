package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.DummyLiquefactionBasinCrafting;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted.ExaltedBeacon;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted.ExaltedFertilityPharo;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted.ExaltedHarvester;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Exalted {

    @Getter
    private static ExaltedBeacon exaltedBeacon;
    @Getter
    private static ExaltedFertilityPharo exaltedFertilityPharo;
    @Getter
    private static ExaltedHarvester exaltedHarvester;

    public static void setup() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        final ItemStack amalgamateIngotMythical = Materials.getAmalgamateIngotMythical().getItem();

        // Exaltation Beacon
        RecipeItem exaltedBeaconRecipe = new RecipeItem(
            amalgamateIngotMythical,
            StoryType.ELEMENTAL, 500,
            StoryType.HUMAN, 500,
            StoryType.PHILOSOPHICAL, 500,
            Exalted::isMaxStoryRank
        );
        exaltedBeacon = new ExaltedBeacon(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_BEACON",
                new ItemStack(Material.BEACON),
                ThemeType.EXALTED,
                "Exalted Beacon",
                "A powerful beacon with incredible",
                "potential. Must be placed on a",
                "Exaltation Stand to operate.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Story Rank > Emeritus Professor"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedBeaconRecipe.getDisplayRecipe()
        );

        // Fertility Pharo
        RecipeItem exaltedFertilityPharoRecipe = new RecipeItem(
            amalgamateIngotMythical,
            StoryType.ELEMENTAL, 500,
            StoryType.ANIMAL, 500,
            StoryType.CELESTIAL, 500,
            Exalted::isMaxStoryRank
        );
        exaltedFertilityPharo = new ExaltedFertilityPharo(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_FERTILITY_PHARO",
                new ItemStack(Material.DIAMOND_BLOCK),
                ThemeType.EXALTED,
                "Exalted Fertility Pharo",
                "A magical construct able to",
                "breed nearby animals in a 20x20 area.",
                "Must be placed on an Exaltation",
                "Stand to operate.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Story Rank > Emeritus Professor"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedFertilityPharoRecipe.getDisplayRecipe()
        );

        // Exalted Harvester
        RecipeItem exaltedHarvesterRecipe = new RecipeItem(
            amalgamateIngotMythical,
            StoryType.ELEMENTAL, 500,
            StoryType.HISTORICAL, 500,
            StoryType.VOID, 500,
            Exalted::isMaxSpellRank
        );
        exaltedHarvester = new ExaltedHarvester(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_HARVESTER",
                new ItemStack(Material.HAY_BLOCK),
                ThemeType.EXALTED,
                "Exalted Harvester",
                "A magical construct able to",
                "harvest all crops in a 9x9 area.",
                "Must be placed on an Exaltation",
                "Stand to operate.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Spell Rank > Grandmaster Magus"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            exaltedHarvesterRecipe.getDisplayRecipe()
        );

        // Slimefun Registry
        exaltedBeacon.register(plugin);
        exaltedFertilityPharo.register(plugin);
        exaltedHarvester.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(exaltedBeacon, exaltedBeaconRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedFertilityPharo, exaltedFertilityPharoRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedHarvester, exaltedHarvesterRecipe);
    }

    private static boolean isMaxStoryRank(Player player) {
        return PlayerStatistics.getStoryRank(player.getUniqueId()) == StoryRank.EMERITUS_PROFESSOR;
    }

    private static boolean isMaxSpellRank(Player player) {
        return PlayerStatistics.getSpellRank(player.getUniqueId()) == SpellRank.GRANDMASTER_MAGI;
    }
}
