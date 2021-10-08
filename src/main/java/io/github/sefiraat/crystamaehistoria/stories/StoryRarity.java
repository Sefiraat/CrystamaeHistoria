package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;

import javax.annotation.Nullable;

public enum StoryRarity {
    COMMON(1),
    UNCOMMON(2),
    RARE(3),
    EPIC(4),
    MYTHICAL(5),
    UNIQUE(6);

    @Getter
    public static final StoryRarity[] values = values();

    @Getter
    private final int id;

    StoryRarity(int id) {
        this.id = id;
    }

    @Nullable
    public static StoryRarity getById(int id) {
        for (StoryRarity storyRarity : getValues()) {
            if (storyRarity.id == id) {
                return storyRarity;
            }
        }
        return null;
    }

}
