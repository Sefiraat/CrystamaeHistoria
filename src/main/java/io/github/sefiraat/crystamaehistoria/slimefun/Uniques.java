package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.GildingRank;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Trophy;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class Uniques {

    @Getter
    private static Trophy storyTrophy;
    @Getter
    private static Trophy spellTrophy;
    @Getter
    private static Trophy gildingTrophy;
    @Getter
    private static Trophy christmasTrophy;
    @Getter
    private static Trophy valentinesTrophy;
    @Getter
    private static Trophy birthdayTrophyCheesy;
    @Getter
    private static Trophy birthdayTrophyBWhite;
    @Getter
    private static Trophy birthdayTrophyDecoy;
    @Getter
    private static Trophy birthdayTrophyOddish;

    public static void setup() {

        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Spell Trophy
        RecipeItem spellTrophyRecipe = new RecipeItem(
            new ItemStack(Material.PAPER),
            StoryType.ELEMENTAL, 100,
            StoryType.ALCHEMICAL, 100,
            StoryType.VOID, 100,
            Uniques::isMaxSpellRank
        );
        spellTrophy = new Trophy(
            ItemGroups.UNIQUES,
            CrystaStacks.SPELL_TROPHY,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            spellTrophyRecipe.getDisplayRecipe(),
            location -> ParticleUtils.displayParticleEffect(
                location.add(0, 0.2, 0),
                0.2,
                3,
                new Particle.DustOptions(Color.ORANGE, 1)
            )
        );

        // Story Trophy
        RecipeItem storyTrophyRecipe = new RecipeItem(
            new ItemStack(Material.PAPER),
            StoryType.ELEMENTAL, 100,
            StoryType.ALCHEMICAL, 100,
            StoryType.CELESTIAL, 100,
            Uniques::isMaxStoryRank
        );
        storyTrophy = new Trophy(
            ItemGroups.UNIQUES,
            CrystaStacks.STORY_TROPHY,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            storyTrophyRecipe.getDisplayRecipe(),
            location -> ParticleUtils.displayParticleEffect(
                location.add(0, 0.2, 0),
                0.2,
                3,
                new Particle.DustOptions(Color.AQUA, 1)
            )
        );

        // Gilding Trophy
        RecipeItem gildedTrophyRecipe = new RecipeItem(
            CrystaStacks.RUNE_SUN,
            StoryType.MECHANICAL, 100,
            StoryType.HUMAN, 100,
            StoryType.PHILOSOPHICAL, 100,
            Uniques::isMaxGildingRank
        );
        gildingTrophy = new Trophy(
            ItemGroups.UNIQUES,
            CrystaStacks.GILDING_TROPHY,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            gildedTrophyRecipe.getDisplayRecipe(),
            location -> ParticleUtils.displayParticleEffect(
                location.add(0, 0.2, 0),
                0.2,
                3,
                new Particle.DustOptions(Color.MAROON, 1)
            )
        );

        // Christmas Trophy
        RecipeItem christmasTrophyRecipe = new RecipeItem(
            new ItemStack(Material.SPRUCE_SAPLING),
            StoryType.HUMAN, 250,
            StoryType.PHILOSOPHICAL, 250,
            StoryType.CELESTIAL, 250,
            Uniques::isChristmas
        );
        christmasTrophy = new Trophy(
            ItemGroups.UNIQUES,
            CrystaStacks.CHRISTMAS_TROPHY,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            christmasTrophyRecipe.getDisplayRecipe(),
            location -> {
                ParticleUtils.displayParticleEffect(
                    location.add(0, 0.2, 0),
                    0.2,
                    3,
                    new Particle.DustOptions(Color.RED, 1)
                );
                ParticleUtils.displayParticleEffect(
                    location.add(0, 0.2, 0),
                    0.2,
                    3,
                    new Particle.DustOptions(Color.LIME, 1)
                );
            }
        );

        // Valentines Trophy
        RecipeItem valentinesTrophyRecipe = new RecipeItem(
            SlimefunItems.RAINBOW_WOOL_VALENTINE,
            StoryType.HUMAN, 250,
            StoryType.ELEMENTAL, 250,
            StoryType.HISTORICAL, 250,
            Uniques::isValentines
        );
        valentinesTrophy = new Trophy(
            ItemGroups.UNIQUES,
            CrystaStacks.VALENTINES_TROPHY,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            valentinesTrophyRecipe.getDisplayRecipe(),
            location -> {
                ParticleUtils.displayParticleEffect(
                    location.add(0, 0.2, 0),
                    Particle.HEART,
                    1,
                    3
                );
            }
        );

        // Birthday: Cheesy
        RecipeItem birthdayTrophyCheesyRecipe = new RecipeItem(
            new ItemStack(Material.CAKE),
            StoryType.HUMAN, 50,
            StoryType.ELEMENTAL, 50,
            StoryType.HISTORICAL, 50,
            Uniques::isBirthdayCheesy
        );
        birthdayTrophyCheesy = new Trophy(
            ItemGroups.UNIQUES,
            CrystaStacks.BIRTHDAY_TROPHY_CHEESY,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            birthdayTrophyCheesyRecipe.getDisplayRecipe(),
            location -> {
                final Location spawnLocation = location.add(0, 0.2, 0);
                final int rand = ThreadLocalRandom.current().nextInt(19);
                if (rand == 0) {
                    spawnBirthdayFirework(spawnLocation, Color.ORANGE);
                }
                ParticleUtils.displayParticleEffect(
                    spawnLocation,
                    1,
                    3,
                    new Particle.DustOptions(Color.ORANGE, 2)
                );
            }
        );

        // Birthday: BWhite
        RecipeItem birthdayTrophyBWhiteRecipe = new RecipeItem(
            new ItemStack(Material.CAKE),
            StoryType.HUMAN, 50,
            StoryType.VOID, 50,
            StoryType.PHILOSOPHICAL, 50,
            Uniques::isBirthdayBWhite
        );
        birthdayTrophyBWhite = new Trophy(
            ItemGroups.UNIQUES,
            CrystaStacks.BIRTHDAY_TROPHY_BWHITE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            birthdayTrophyBWhiteRecipe.getDisplayRecipe(),
            location -> {
                final Location spawnLocation = location.add(0, 0.2, 0);
                final int rand = ThreadLocalRandom.current().nextInt(19);
                if (rand == 0) {
                    spawnBirthdayFirework(spawnLocation, Color.RED);
                }
                ParticleUtils.displayParticleEffect(
                    spawnLocation,
                    1,
                    3,
                    new Particle.DustOptions(Color.RED, 2)
                );
            }
        );

        // Birthday: Decoy
        RecipeItem birthdayTrophyDecoyRecipe = new RecipeItem(
            new ItemStack(Material.CAKE),
            StoryType.HUMAN, 50,
            StoryType.CELESTIAL, 50,
            StoryType.PHILOSOPHICAL, 50,
            Uniques::isBirthdayDecoy
        );
        birthdayTrophyDecoy = new Trophy(
            ItemGroups.UNIQUES,
            CrystaStacks.BIRTHDAY_TROPHY_DECOY,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            birthdayTrophyDecoyRecipe.getDisplayRecipe(),
            location -> {
                final Location spawnLocation = location.add(0, 0.2, 0);
                final int rand = ThreadLocalRandom.current().nextInt(19);
                if (rand == 0) {
                    spawnBirthdayFirework(spawnLocation, Color.TEAL);
                }
                ParticleUtils.displayParticleEffect(
                    spawnLocation,
                    1,
                    3,
                    new Particle.DustOptions(Color.TEAL, 2)
                );
            }
        );

        // Birthday: Oddish
        RecipeItem birthdayTrophyOddishRecipe = new RecipeItem(
            new ItemStack(Material.CAKE),
            StoryType.ANIMAL, 50,
            StoryType.ELEMENTAL, 50,
            StoryType.CELESTIAL, 50,
            Uniques::isBirthdayOddish
        );
        birthdayTrophyOddish = new Trophy(
            ItemGroups.UNIQUES,
            CrystaStacks.BIRTHDAY_TROPHY_ODDISH,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            birthdayTrophyOddishRecipe.getDisplayRecipe(),
            location -> {
                final Location spawnLocation = location.add(0, 0.2, 0);
                final int rand = ThreadLocalRandom.current().nextInt(19);
                final Color color = Color.fromRGB(0, 87, 87);
                if (rand == 0) {
                    spawnBirthdayFirework(spawnLocation, color);
                }
                ParticleUtils.displayParticleEffect(
                    spawnLocation,
                    1,
                    3,
                    new Particle.DustOptions(color, 2)
                );
            }
        );

        spellTrophy.register(plugin);
        storyTrophy.register(plugin);
        gildingTrophy.register(plugin);
        christmasTrophy.register(plugin);
        valentinesTrophy.register(plugin);

        birthdayTrophyCheesy.register(plugin);
        birthdayTrophyBWhite.register(plugin);
        birthdayTrophyDecoy.register(plugin);
        birthdayTrophyOddish.register(plugin);

        LiquefactionBasinCache.addCraftingRecipe(spellTrophy, spellTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(storyTrophy, storyTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(christmasTrophy, christmasTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(valentinesTrophy, valentinesTrophyRecipe);

        LiquefactionBasinCache.addCraftingRecipe(birthdayTrophyCheesy, birthdayTrophyCheesyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(birthdayTrophyBWhite, birthdayTrophyBWhiteRecipe);
        LiquefactionBasinCache.addCraftingRecipe(birthdayTrophyDecoy, birthdayTrophyDecoyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(valentinesTrophy, birthdayTrophyOddishRecipe);
    }

    private static boolean isMaxStoryRank(@Nonnull Player player) {
        return PlayerStatistics.getStoryRank(player.getUniqueId()) == StoryRank.EMERITUS_PROFESSOR;
    }

    private static boolean isMaxSpellRank(@Nonnull Player player) {
        return PlayerStatistics.getSpellRank(player.getUniqueId()) == SpellRank.GRANDMASTER_MAGI;
    }

    private static boolean isMaxGildingRank(@Nonnull Player player) {
        return PlayerStatistics.getGildingRank(player.getUniqueId()) == GildingRank.OWNER;
    }

    private static boolean isChristmas(@Nonnull Player player) {
        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final LocalDate start = LocalDate.of(year, 12, 20);
        final LocalDate end = LocalDate.of(year + 1, 1, 5);

        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isBirthdayCheesy(@Nonnull Player player) {
        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final LocalDate start = LocalDate.of(year, 3, 29);
        final LocalDate end = LocalDate.of(year, 3, 30);

        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isBirthdayBWhite(@Nonnull Player player) {
        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final LocalDate start = LocalDate.of(year, 9, 10);
        final LocalDate end = LocalDate.of(year, 9, 10);

        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isBirthdayDecoy(@Nonnull Player player) {
        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final LocalDate start = LocalDate.of(year, 12, 11);
        final LocalDate end = LocalDate.of(year, 12, 11);

        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isBirthdayOddish(@Nonnull Player player) {
        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final LocalDate start = LocalDate.of(year, 2, 12);
        final LocalDate end = LocalDate.of(year, 2, 12);

        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isValentines(@Nonnull Player player) {
        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final LocalDate start = LocalDate.of(year, 2, 6);
        final LocalDate end = LocalDate.of(year, 2, 20);

        return now.isAfter(start) && now.isBefore(end);
    }

    private static void spawnBirthdayFirework(@Nonnull Location location, @Nonnull Color color) {
        final Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        final FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(
            FireworkEffect.builder()
                .withColor(color)
                .with(FireworkEffect.Type.STAR)
                .withFlicker()
                .withTrail()
                .build()
        );
        firework.setFireworkMeta(fireworkMeta);
    }

}
