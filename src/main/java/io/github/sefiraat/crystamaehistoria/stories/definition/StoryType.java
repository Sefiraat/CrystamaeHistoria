package io.github.sefiraat.crystamaehistoria.stories.definition;

import lombok.Getter;

import javax.annotation.Nullable;

public enum StoryType {
    ELEMENTAL(1),
    MECHANICAL(2),
    ALCHEMICAL(3),
    HISTORICAL(4),
    HUMAN(5),
    ANIMAL(6),
    CELESTIAL(7),
    VOID(8),
    PHILOSOPHICAL(9);

    @Getter
    private static final StoryType[] values = values();

    @Getter
    private final int id;

    StoryType(int id) {
        this.id = id;
    }

    @Nullable
    public static StoryType getById(int id) {
        for (StoryType storyType : getValues()) {
            if (storyType.id == id) {
                return storyType;
            }
        }
        return null;
    }
}
