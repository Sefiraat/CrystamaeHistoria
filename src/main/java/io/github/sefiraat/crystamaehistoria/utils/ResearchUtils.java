package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.stories.StoriedBlockDefinition;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.UUID;

public class ResearchUtils {

    public static void unlockSpell(Player player, SpellType spellType) {
        setResearchConfig(player.getUniqueId(), ResearchType.SPELL, spellType.getId(), true);
    }

    public static void unlockSpell(UUID player, SpellType spellType) {
        setResearchConfig(player, ResearchType.SPELL, spellType.getId(), true);
    }

    public static boolean hasUnlockedSpell(Player player, SpellType spellType) {
        return getResearchBoolean(player.getUniqueId(), ResearchType.SPELL, spellType.getId());
    }

    public static boolean hasUnlockedSpell(UUID player, SpellType spellType) {
        return getResearchBoolean(player, ResearchType.SPELL, spellType.getId());
    }

    public static void unlockUniqueStory(Player player, StoriedBlockDefinition definition) {
        setResearchConfig(player.getUniqueId(), ResearchType.STORY, definition.getMaterial().toString(), true);
    }

    public static void unlockUniqueStory(UUID player, StoriedBlockDefinition definition) {
        setResearchConfig(player, ResearchType.STORY, definition.getMaterial().toString(), true);
    }

    public static boolean hasUnlockedUniqueStory(Player player, StoriedBlockDefinition definition) {
        return getResearchBoolean(player.getUniqueId(), ResearchType.STORY, definition.getMaterial().toString());
    }

    public static boolean hasUnlockedUniqueStory(UUID player, StoriedBlockDefinition definition) {
        return getResearchBoolean(player, ResearchType.STORY, definition.getMaterial().toString());
    }

    private static void setResearchConfig(UUID uuid, ResearchType researchType, String researchId, Object object) {
        String path = MessageFormat.format("{0}.{1}.{2}", uuid, researchType, researchId);
        CrystamaeHistoria.getConfigManager().getResearch().set(path, object);
        CrystamaeHistoria.getConfigManager().saveResearches();
    }

    private static boolean getResearchBoolean(UUID uuid, ResearchType researchType, String researchId) {
        String path = MessageFormat.format("{0}.{1}.{2}", uuid, researchType, researchId);
        return CrystamaeHistoria.getConfigManager().getResearch().getBoolean(path);
    }

    enum ResearchType {
        SPELL,
        STORY
    }
}

