package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.slimefun.materials.Trophy;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.DummyLiquefactionBasinCrafting;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.time.LocalDate;

@UtilityClass
public class Uniques {

    @Getter
    private static Trophy storyTrophy;
    @Getter
    private static Trophy spellTrophy;
    @Getter
    private static Trophy christmasTrophy;
    @Getter
    private static Trophy valentinesTrophy;

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
            ThemeType.themedSlimefunItemStack(
                "CRY_SPELL_TROPHY",
                new ItemStack(Material.PAPER),
                ThemeType.CRAFTING,
                "Proofs: Grandmaster Magus",
                "Proof that you are simply the best.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Spell Rank > Grandmaster Magus"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
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
            ThemeType.themedSlimefunItemStack(
                "CRY_STORY_TROPHY",
                new ItemStack(Material.PAPER),
                ThemeType.CRAFTING,
                "Proofs: Emeritus Professor",
                "Proof that you are simply the best.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Story Rank > Emeritus Professor"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            storyTrophyRecipe.getDisplayRecipe(),
            location -> ParticleUtils.displayParticleEffect(
                location.add(0, 0.2, 0),
                0.2,
                3,
                new Particle.DustOptions(Color.AQUA, 1)
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
            ThemeType.themedSlimefunItemStack(
                "CRY_CHRISTMAS_TROPHY",
                new ItemStack(Material.SPRUCE_SAPLING),
                ThemeType.CRAFTING,
                "Merry Christmas",
                "A little gift for you... but you",
                "have to work for it!",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Can only be crafted during the holidays"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
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

        // Christmas Trophy
        RecipeItem valentinesTrophyRecipe = new RecipeItem(
            SlimefunItems.RAINBOW_WOOL_VALENTINE,
            StoryType.HUMAN, 250,
            StoryType.ELEMENTAL, 250,
            StoryType.HISTORICAL, 250,
            Uniques::isValentines
        );
        valentinesTrophy = new Trophy(
            ItemGroups.UNIQUES,
            ThemeType.themedSlimefunItemStack(
                "CRY_VALENTINES_TROPHY",
                new ItemStack(Material.PINK_DYE),
                ThemeType.CRAFTING,
                "Happy Valentines Day",
                "A little love goes a long way.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Requires: Can only be crafted when love is in the air"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
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

        spellTrophy.register(plugin);
        storyTrophy.register(plugin);
        christmasTrophy.register(plugin);
        valentinesTrophy.register(plugin);

        LiquefactionBasinCache.addCraftingRecipe(spellTrophy, spellTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(storyTrophy, storyTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(christmasTrophy, christmasTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(valentinesTrophy, valentinesTrophyRecipe);
    }

    private static boolean isMaxStoryRank(@Nonnull Player player) {
        return PlayerStatistics.getStoryRank(player.getUniqueId()) == StoryRank.EMERITUS_PROFESSOR;
    }

    private static boolean isMaxSpellRank(@Nonnull Player player) {
        return PlayerStatistics.getSpellRank(player.getUniqueId()) == SpellRank.GRANDMASTER_MAGI;
    }

    private static boolean isChristmas(@Nonnull Player player) {
        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final LocalDate start = LocalDate.of(year, 12, 20);
        final LocalDate end = LocalDate.of(year + 1, 1, 5);

        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isValentines(@Nonnull Player player) {
        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final LocalDate start = LocalDate.of(year, 2, 6);
        final LocalDate end = LocalDate.of(year, 2, 20);

        return now.isAfter(start) && now.isBefore(end);
    }

}
