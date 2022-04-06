package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedBeacon;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedFertilityPharo;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedHarvester;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedSeaBreeze;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedTime;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedWeather;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
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
    private static ExaltedBeacon exaltedBaelfire;
    @Getter
    private static ExaltedFertilityPharo exaltedFertilityPharo;
    @Getter
    private static ExaltedFertilityPharo exaltedFertilityTotem;
    @Getter
    private static ExaltedHarvester exaltedHarvester;
    @Getter
    private static ExaltedHarvester exaltedAgronomist;
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

        // Exaltation Beacon
        RecipeItem exaltedBeaconRecipe = new RecipeItem(
            CrystaStacks.AMALGAMATE_INGOT_MYTHICAL,
            StoryType.ELEMENTAL, 250,
            StoryType.HUMAN, 250,
            StoryType.PHILOSOPHICAL, 250,
            Exalted::isMaxStoryRank
        );
        exaltedBeacon = new ExaltedBeacon(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_BEACON,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            exaltedBeaconRecipe.getDisplayRecipe(),
            2
        );

        // Exaltation Baelfire
        exaltedBaelfire = new ExaltedBeacon(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_BAELFIRE,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_BEAST, null,
                CrystaStacks.RUNE_TRUE_EARTH, CrystaStacks.EXALTED_BEACON, CrystaStacks.RUNE_TRUE_WATER,
                null, CrystaStacks.RUNE_BLINKING, null
            },
            3
        );

        // Fertility Pharo
        RecipeItem exaltedFertilityPharoRecipe = new RecipeItem(
            CrystaStacks.AMALGAMATE_INGOT_MYTHICAL,
            StoryType.ELEMENTAL, 350,
            StoryType.ANIMAL, 350,
            StoryType.CELESTIAL, 350,
            Exalted::isMaxStoryRank
        );
        exaltedFertilityPharo = new ExaltedFertilityPharo(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_FERTILITY_PHARO,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            exaltedFertilityPharoRecipe.getDisplayRecipe(),
            9
        );

        // Fertility Totem
        exaltedFertilityTotem = new ExaltedFertilityPharo(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_FERTILITY_TOTEM,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            new ItemStack[]{
                null, CrystaStacks.RUNE_MOON, null,
                CrystaStacks.RUNE_BLACK, CrystaStacks.EXALTED_FERTILITY_PHARO, CrystaStacks.RUNE_DAWN,
                null, CrystaStacks.RUNE_PUNISHMENT, null
            },
            13
        );

        // Exalted Harvester
        RecipeItem exaltedHarvesterRecipe = new RecipeItem(
            CrystaStacks.AMALGAMATE_INGOT_MYTHICAL,
            StoryType.ELEMENTAL, 350,
            StoryType.HISTORICAL, 350,
            StoryType.VOID, 350,
            Exalted::isMaxSpellRank
        );
        exaltedHarvester = new ExaltedHarvester(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_HARVESTER,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            exaltedHarvesterRecipe.getDisplayRecipe(),
            4
        );

        // Exalted Agronomist
        exaltedAgronomist = new ExaltedHarvester(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_AGRONOMIST,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            new ItemStack[]{
                null, CrystaStacks.RUNE_CHANGE, null,
                CrystaStacks.RUNE_DRAGON, CrystaStacks.EXALTED_HARVESTER, CrystaStacks.RUNE_TRUE_FIRE,
                null, CrystaStacks.RUNE_BLACK_SWORD, null
            },
            9
        );

        // Exalted Dawn
        RecipeItem exaltedDawnRecipe = new RecipeItem(
            CrystaStacks.AMALGAMATE_DUST_MYTHICAL,
            StoryType.ELEMENTAL, 250,
            StoryType.HISTORICAL, 250,
            StoryType.CELESTIAL, 250,
            player -> player.getWorld().isDayTime()
        );
        exaltedDawn = new ExaltedTime(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_DAWN,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            exaltedDawnRecipe.getDisplayRecipe(),
            6000
        );

        // Exalted Dusk
        RecipeItem exaltedDuskRecipe = new RecipeItem(
            CrystaStacks.AMALGAMATE_DUST_MYTHICAL,
            StoryType.ELEMENTAL, 250,
            StoryType.HISTORICAL, 250,
            StoryType.VOID, 250,
            player -> !player.getWorld().isDayTime()
        );
        exaltedDusk = new ExaltedTime(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_DUSK,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            exaltedDuskRecipe.getDisplayRecipe(),
            18000
        );

        // Exalted Sun
        RecipeItem exaltedSunRecipe = new RecipeItem(
            CrystaStacks.AMALGAMATE_DUST_MYTHICAL,
            StoryType.ELEMENTAL, 250,
            StoryType.ALCHEMICAL, 250,
            StoryType.CELESTIAL, 250,
            player -> player.getWorld().isClearWeather()
        );
        exaltedSun = new ExaltedWeather(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_SUN,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            exaltedSunRecipe.getDisplayRecipe(),
            WeatherType.CLEAR
        );

        // Exalted Storm
        RecipeItem exaltedStormRecipe = new RecipeItem(
            CrystaStacks.AMALGAMATE_DUST_MYTHICAL,
            StoryType.ELEMENTAL, 250,
            StoryType.ALCHEMICAL, 250,
            StoryType.VOID, 250,
            player -> !player.getWorld().isClearWeather()
        );
        exaltedStorm = new ExaltedWeather(
            ItemGroups.EXALTED,
            CrystaStacks.EXALTED_STORM,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
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
            CrystaStacks.EXALTED_SEA_BREEZE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            exaltedSeeBreezeRecipe.getDisplayRecipe()
        );

        // Slimefun Registry
        exaltedBeacon.register(plugin);
        exaltedBaelfire.register(plugin);
        exaltedFertilityPharo.register(plugin);
        exaltedFertilityTotem.register(plugin);
        exaltedHarvester.register(plugin);
        exaltedAgronomist.register(plugin);
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

    public static boolean isMaxStoryRank(Player player) {
        return PlayerStatistics.getStoryRank(player.getUniqueId()) == StoryRank.EMERITUS_PROFESSOR;
    }

    public static boolean isMaxSpellRank(Player player) {
        return PlayerStatistics.getSpellRank(player.getUniqueId()) == SpellRank.GRANDMASTER_MAGI;
    }
}
