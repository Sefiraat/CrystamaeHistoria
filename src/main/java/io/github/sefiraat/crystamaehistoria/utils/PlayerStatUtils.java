package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.UUID;

public class PlayerStatUtils {

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
        String path = MessageFormat.format("{0}.{1}.{2}.Unlocked", player, StatType.STORY, definition.getMaterial());
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

    enum StatType {
        SPELL,
        STORY
    }
}

