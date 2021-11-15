package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;

public enum BlockRank {

    SME(100, ThemeType.RANK_BLOCK_SME),
    MASTER_OF(90, ThemeType.RANK_BLOCK_MASTER_OF),
    EXPERT_OF(70, ThemeType.RANK_BLOCK_EXPERT_OF),
    RESEARCHED(50, ThemeType.RANK_BLOCK_RESEARCHED),
    DETAILED(30, ThemeType.RANK_BLOCK_DETAILED),
    KNOWN(20, ThemeType.RANK_BLOCK_KNOWN),
    HEARD_OF(10, ThemeType.RANK_BLOCK_HEARD_OF),
    UNKNOWN(0, ThemeType.RANK_BLOCK_UNKNOWN);

    private final double numberRequired;
    @Getter
    private final ThemeType theme;

    BlockRank(double numberRequired, ThemeType themeType) {
        this.numberRequired = numberRequired;
        this.theme = themeType;
    }

    public static BlockRank getByAmount(double percent) {
        if (percent >= SME.numberRequired) {
            return SME;
        } else if (percent >= MASTER_OF.numberRequired) {
            return MASTER_OF;
        } else if (percent >= EXPERT_OF.numberRequired) {
            return EXPERT_OF;
        } else if (percent >= RESEARCHED.numberRequired) {
            return RESEARCHED;
        } else if (percent >= DETAILED.numberRequired) {
            return DETAILED;
        } else if (percent >= KNOWN.numberRequired) {
            return KNOWN;
        } else if (percent >= HEARD_OF.numberRequired) {
            return HEARD_OF;
        } else {
            return UNKNOWN;
        }
    }
}
