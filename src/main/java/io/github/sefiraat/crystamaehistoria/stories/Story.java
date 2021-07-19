package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;

@Getter
public class Story {

    private final String storyName;
    private final StoryRarity rarity;
    private final StoryType type;
    private final StoryShardProfile storyShardProfile;
    private final String[] story;


    public Story(String storyName, StoryRarity rarity, StoryType type, StoryShardProfile storyShardProfile, String... story) {
        this.storyName = storyName;
        this.rarity = rarity;
        this.type = type;
        this.storyShardProfile = storyShardProfile;
        this.story = story;
    }
}
