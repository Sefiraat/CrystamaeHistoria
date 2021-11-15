package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerStatistics {

    public static void unlockSpell(Player player, SpellType spellType) {
        unlockSpell(player.getUniqueId(), spellType);
    }

    public static void unlockSpell(UUID player, SpellType spellType) {
        String path = MessageFormat.format("{0}.{1}.{2}.Unlocked", player, StatType.SPELL, spellType.getId());
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, true);
    }

    public static boolean hasUnlockedSpell(Player player, SpellType spellType) {
        return hasUnlockedSpell(player.getUniqueId(), spellType);
    }

    public static boolean hasUnlockedSpell(UUID player, SpellType spellType) {
        String path = MessageFormat.format("{0}.{1}.{2}.Unlocked", player, StatType.SPELL, spellType.getId());
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(path);
    }

    public static void addUsage(Player player, SpellType spellType) {
        addUsage(player.getUniqueId(), spellType);
    }

    public static void addUsage(UUID player, SpellType spellType) {
        int uses = getUsages(player, spellType);
        uses++;
        String path = MessageFormat.format("{0}.{1}.{2}.Times_Cast", player, StatType.SPELL, spellType.getId());
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, uses);
    }

    public static int getUsages(Player player, SpellType spellType) {
        return getUsages(player.getUniqueId(), spellType);
    }

    public static int getUsages(UUID player, SpellType spellType) {
        String path = MessageFormat.format("{0}.{1}.{2}.Times_Cast", player, StatType.SPELL, spellType.getId());
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getInt(path);
    }

    public static void unlockUniqueStory(Player player, BlockDefinition definition) {
        unlockUniqueStory(player.getUniqueId(), definition);
    }

    public static void unlockUniqueStory(UUID player, BlockDefinition definition) {
        String path = MessageFormat.format("{0}.{1}.{2}.Unlocked", player, StatType.STORY, definition.getMaterial());
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, true);
    }

    public static boolean hasUnlockedUniqueStory(Player player, BlockDefinition definition) {
        return hasUnlockedUniqueStory(player.getUniqueId(), definition);
    }

    public static boolean hasUnlockedUniqueStory(UUID player, BlockDefinition definition) {
        return hasUnlockedUniqueStory(player, definition.getMaterial());
    }

    public static boolean hasUnlockedUniqueStory(UUID player, Material material) {
        String path = MessageFormat.format("{0}.{1}.{2}.Unlocked", player, StatType.STORY, material);
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(path);
    }

    public static void addChronicle(Player player, BlockDefinition definition) {
        addChronicle(player.getUniqueId(), definition);
    }

    public static void addChronicle(UUID player, BlockDefinition definition) {
        int uses = getChronicle(player, definition);
        uses++;
        String path = MessageFormat.format("{0}.{1}.{2}.Times_Chronicled", player, StatType.STORY, definition.getMaterial());
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, uses);
    }

    public static int getChronicle(Player player, BlockDefinition definition) {
        return getChronicle(player.getUniqueId(), definition);
    }

    public static int getChronicle(UUID player, BlockDefinition definition) {
        String path = MessageFormat.format("{0}.{1}.{2}.Times_Chronicled", player, StatType.STORY, definition.getMaterial());
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getInt(path);
    }

    public static int getStoriesUnlocked(@Nonnull UUID uuid) {
        String path = MessageFormat.format("{0}.{1}", uuid, StatType.STORY);
        ConfigurationSection section = CrystamaeHistoria.getConfigManager().getPlayerStats().getConfigurationSection(path);
        int unlocked = 0;
        for (String story : section.getKeys(false)) {
            String storyPath = MessageFormat.format("{0}.{1}.{2}.Unlocked", uuid, StatType.STORY, story);
            if (CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(storyPath)) unlocked++;
        }
        return unlocked;
    }

    public static StoryRank getStoryRank(@Nonnull UUID uuid) {
        int total = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().size();
        final int unlocked = getStoriesUnlocked(uuid);
        return StoryRank.getByPercent(((double) unlocked / total) * 100);
    }

    public static String getStoryRankString(@Nonnull UUID uuid) {
        int total = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().size();
        int unlocked = getStoriesUnlocked(uuid);
        StoryRank storyRank = StoryRank.getByPercent(((double) unlocked / total) * 100);
        return MessageFormat.format(
            "{0}Chronicler Rank: {1}{2}{0} ({3}/{4})",
            ThemeType.PASSIVE.getColor(),
            storyRank.getTheme().getColor(),
            storyRank.getTheme().getLoreLine(),
            unlocked,
            total
        );
    }

    public static int getSpellsUnlocked(@Nonnull UUID uuid) {
        String path = MessageFormat.format("{0}.{1}", uuid, StatType.SPELL);
        ConfigurationSection section = CrystamaeHistoria.getConfigManager().getPlayerStats().getConfigurationSection(path);
        int unlocked = 0;
        for (String spell : section.getKeys(false)) {
            String storyPath = MessageFormat.format("{0}.{1}.{2}.Unlocked", uuid, StatType.SPELL, spell);
            if (CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(storyPath)) unlocked++;
        }
        return unlocked;
    }

    public static SpellRank getSpellRank(@Nonnull UUID uuid) {
        int total = SpellType.getEnabledSpells().length;
        int unlocked = getSpellsUnlocked(uuid);
        return SpellRank.getByPercent(((double) unlocked / total) * 100);
    }

    public static String getSpellRankString(@Nonnull UUID uuid) {
        int total = SpellType.getEnabledSpells().length;
        int unlocked = getSpellsUnlocked(uuid);
        SpellRank spellRank = SpellRank.getByPercent(((double) unlocked / total) * 100);
        return MessageFormat.format(
            "{0}Crystamae Rank: {1}{2}{0} ({3}/{4})",
            ThemeType.PASSIVE.getColor(),
            spellRank.getTheme().getColor(),
            spellRank.getTheme().getLoreLine(),
            unlocked,
            total
        );
    }

    enum StatType {
        SPELL,
        STORY
    }
}

