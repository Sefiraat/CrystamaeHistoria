package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.slimefun.recipetypes.LiquefactionBasinCraftingRecipeType;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedBeacon;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedFertilityPharo;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedHarvester;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedSeaBreeze;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedTime;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedWeather;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.block.Biome;
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
    @Getter
    private static ExaltedTime exaltedDawn;
    @Getter
    private static ExaltedTime exaltedDusk;
    @Getter
    private static ExaltedWeather exaltedSun;
    @Getter
    private static ExaltedWeather exaltedStorm;
    @Getter
    private static ExaltedSeaBreeze exaltedSeaBreeze;

    public static void setup() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        final ItemStack amalgamateIngotMythical = Materials.getAmalgamateIngotMythical().getItem();
        final ItemStack amalgamateDustMythical = Materials.getAmalgamateDustMythical().getItem();

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
            LiquefactionBasinCraftingRecipeType.TYPE,
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
            LiquefactionBasinCraftingRecipeType.TYPE,
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
            LiquefactionBasinCraftingRecipeType.TYPE,
            exaltedHarvesterRecipe.getDisplayRecipe()
        );

        // Exalted Dawn
        RecipeItem exaltedDawnRecipe = new RecipeItem(
            amalgamateDustMythical,
            StoryType.ELEMENTAL, 250,
            StoryType.HISTORICAL, 250,
            StoryType.CELESTIAL, 250,
            player -> player.getWorld().isDayTime()
        );
        exaltedDawn = new ExaltedTime(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_DAWN",
                new ItemStack(Material.YELLOW_WOOL),
                ThemeType.EXALTED,
                "Exalted Dawn",
                "A magical construct that burns",
                "brightly like the sun.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Crafted during the day"
            ),
            LiquefactionBasinCraftingRecipeType.TYPE,
            exaltedDawnRecipe.getDisplayRecipe(),
            6000
        );

        // Exalted Dusk
        RecipeItem exaltedDuskRecipe = new RecipeItem(
            amalgamateDustMythical,
            StoryType.ELEMENTAL, 250,
            StoryType.HISTORICAL, 250,
            StoryType.VOID, 250,
            player -> !player.getWorld().isDayTime()
        );
        exaltedDusk = new ExaltedTime(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_DUSK",
                new ItemStack(Material.BLACK_WOOL),
                ThemeType.EXALTED,
                "Exalted Dusk",
                "A magical construct shines",
                "as vivid as the new moon",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Crafted during the night"
            ),
            LiquefactionBasinCraftingRecipeType.TYPE,
            exaltedDuskRecipe.getDisplayRecipe(),
            18000
        );

        // Exalted Sun
        RecipeItem exaltedSunRecipe = new RecipeItem(
            amalgamateDustMythical,
            StoryType.ELEMENTAL, 250,
            StoryType.ALCHEMICAL, 250,
            StoryType.CELESTIAL, 250,
            player -> player.getWorld().isClearWeather()
        );
        exaltedSun = new ExaltedWeather(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_SUN",
                new ItemStack(Material.MAGMA_BLOCK),
                ThemeType.EXALTED,
                "Exalted Sun",
                "A magical construct emanating",
                "the power of a sun.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Crafted during a clear day"
            ),
            LiquefactionBasinCraftingRecipeType.TYPE,
            exaltedSunRecipe.getDisplayRecipe(),
            WeatherType.CLEAR
        );

        // Exalted Storm
        RecipeItem exaltedStormRecipe = new RecipeItem(
            amalgamateDustMythical,
            StoryType.ELEMENTAL, 250,
            StoryType.ALCHEMICAL, 250,
            StoryType.VOID, 250,
            player -> !player.getWorld().isClearWeather()
        );
        exaltedStorm = new ExaltedWeather(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXALTED_STORM",
                new ItemStack(Material.GRAY_WOOL),
                ThemeType.EXALTED,
                "Exalted Storm",
                "A magical construct emanating",
                "the destruction of a storm.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Crafted during a storm"
            ),
            LiquefactionBasinCraftingRecipeType.TYPE,
            exaltedStormRecipe.getDisplayRecipe(),
            WeatherType.DOWNFALL
        );

        // Exalted Sea Breeze
        RecipeItem exaltedSeeBreezeRecipe = new RecipeItem(
            new ItemStack(Material.HEART_OF_THE_SEA),
            StoryType.ELEMENTAL, 125,
            StoryType.ALCHEMICAL, 200,
            StoryType.CELESTIAL, 150,
            player -> player.getWorld().getBiome(player.getLocation()) == Biome.BEACH
        );
        exaltedSeaBreeze = new ExaltedSeaBreeze(
            ItemGroups.EXALTED,
            ThemeType.themedSlimefunItemStack(
                "CRY_SEA_BREEZE",
                new ItemStack(Material.TUBE_CORAL_BLOCK),
                ThemeType.EXALTED,
                "Exalted Sea Breeze",
                "A magical construct that brings",
                "the sea air to nearby blocks, weathering",
                "and oxidizing them.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Crafted on a beach"
            ),
            LiquefactionBasinCraftingRecipeType.TYPE,
            exaltedSeeBreezeRecipe.getDisplayRecipe()
        );

        // Slimefun Registry
        exaltedBeacon.register(plugin);
        exaltedFertilityPharo.register(plugin);
        exaltedHarvester.register(plugin);
        exaltedDawn.register(plugin);
        exaltedDusk.register(plugin);
        exaltedSun.register(plugin);
        exaltedStorm.register(plugin);
        exaltedSeaBreeze.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(exaltedBeacon, exaltedBeaconRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedFertilityPharo, exaltedFertilityPharoRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedHarvester, exaltedHarvesterRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedDawn, exaltedDawnRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedDusk, exaltedDuskRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedSun, exaltedSunRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedStorm, exaltedStormRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedSeaBreeze, exaltedSeeBreezeRecipe);
    }

    private static boolean isMaxStoryRank(Player player) {
        return PlayerStatistics.getStoryRank(player.getUniqueId()) == StoryRank.EMERITUS_PROFESSOR;
    }

    private static boolean isMaxSpellRank(Player player) {
        return PlayerStatistics.getSpellRank(player.getUniqueId()) == SpellRank.GRANDMASTER_MAGI;
    }
}
