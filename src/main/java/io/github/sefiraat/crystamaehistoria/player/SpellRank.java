package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;

public enum SpellRank {

    GRANDMASTER_MAGI(100, ThemeType.RANK_SPELL_GRANDMASTER_MAGI),
    MASTER_MAGI(90, ThemeType.RANK_SPELL_MASTER_MAGI),
    MAGI(70, ThemeType.RANK_SPELL_MAGI),
    SORCERER(50, ThemeType.RANK_SPELL_SORCERER),
    CONJURER(30, ThemeType.RANK_SPELL_CONJURER),
    WIZARD(20, ThemeType.RANK_SPELL_WIZARD),
    MAGE(10, ThemeType.RANK_SPELL_MAGE),
    APPRENTICE(0, ThemeType.RANK_SPELL_APPRENTICE);

    private final double percentRequired;
    @Getter
    private final ThemeType theme;

    SpellRank(double percentRequired, ThemeType themeType) {
        this.percentRequired = percentRequired;
        this.theme = themeType;
    }

    public static SpellRank getByPercent(double percent) {
        if (percent >= GRANDMASTER_MAGI.percentRequired) {
            return GRANDMASTER_MAGI;
        } else if (percent >= MASTER_MAGI.percentRequired) {
            return MASTER_MAGI;
        } else if (percent >= MAGI.percentRequired) {
            return MAGI;
        } else if (percent >= SORCERER.percentRequired) {
            return SORCERER;
        } else if (percent >= CONJURER.percentRequired) {
            return CONJURER;
        } else if (percent >= WIZARD.percentRequired) {
            return WIZARD;
        } else if (percent >= MAGE.percentRequired) {
            return MAGE;
        } else {
            return APPRENTICE;
        }
    }
}
