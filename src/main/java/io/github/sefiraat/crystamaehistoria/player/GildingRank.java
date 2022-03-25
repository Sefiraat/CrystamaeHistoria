package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;

public enum GildingRank {

    OWNER(100, ThemeType.RANK_GILDING_OWNER),
    MANAGER(90, ThemeType.RANK_GILDING_MANAGER),
    CHIEF(70, ThemeType.RANK_GILDING_CHIEF),
    EXECUTIVE(50, ThemeType.RANK_GILDING_EXECUTIVE),
    OFFICER(30, ThemeType.RANK_GILDING_OFFICER),
    SECRETARY(20, ThemeType.RANK_GILDING_SECRETARY),
    MEMBER(10, ThemeType.RANK_GILDING_MEMBER),
    NOVICE(0, ThemeType.RANK_GILDING_NOVICE);

    private final double percentRequired;
    @Getter
    private final ThemeType theme;

    GildingRank(double percentRequired, ThemeType themeType) {
        this.percentRequired = percentRequired;
        this.theme = themeType;
    }

    public static GildingRank getByPercent(double percent) {
        if (percent >= OWNER.percentRequired) {
            return OWNER;
        } else if (percent >= MANAGER.percentRequired) {
            return MANAGER;
        } else if (percent >= CHIEF.percentRequired) {
            return CHIEF;
        } else if (percent >= EXECUTIVE.percentRequired) {
            return EXECUTIVE;
        } else if (percent >= OFFICER.percentRequired) {
            return OFFICER;
        } else if (percent >= SECRETARY.percentRequired) {
            return SECRETARY;
        } else if (percent >= MEMBER.percentRequired) {
            return MEMBER;
        } else {
            return NOVICE;
        }
    }
}
