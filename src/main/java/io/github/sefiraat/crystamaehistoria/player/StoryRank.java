package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;

public enum StoryRank {

    EMERITUS_PROFESSOR(100, ThemeType.RANK_STORY_EMERITUS_PROFESSOR),
    ADJUNCT_PROFESSOR(90, ThemeType.RANK_STORY_ADJUNCT_PROFESSOR),
    PROFESSOR(70, ThemeType.RANK_STORY_PROFESSOR),
    LECTURER(50, ThemeType.RANK_STORY_LECTURER),
    READER(30, ThemeType.RANK_STORY_READER),
    RESEARCHER(20, ThemeType.RANK_STORY_RESEARCHER),
    STUDENT(10, ThemeType.RANK_STORY_STUDENT),
    PUPIL(0, ThemeType.RANK_STORY_PUPIL);

    private final double percentRequired;
    @Getter
    private final ThemeType theme;

    StoryRank(double percentRequired, ThemeType themeType) {
        this.percentRequired = percentRequired;
        this.theme = themeType;
    }

    public static StoryRank getByPercent(double percent) {
        if (percent >= EMERITUS_PROFESSOR.percentRequired) {
            return EMERITUS_PROFESSOR;
        } else if (percent >= ADJUNCT_PROFESSOR.percentRequired) {
            return ADJUNCT_PROFESSOR;
        } else if (percent >= PROFESSOR.percentRequired) {
            return PROFESSOR;
        } else if (percent >= LECTURER.percentRequired) {
            return LECTURER;
        } else if (percent >= READER.percentRequired) {
            return READER;
        } else if (percent >= RESEARCHER.percentRequired) {
            return RESEARCHER;
        } else if (percent >= STUDENT.percentRequired) {
            return STUDENT;
        } else {
            return PUPIL;
        }
    }
}
