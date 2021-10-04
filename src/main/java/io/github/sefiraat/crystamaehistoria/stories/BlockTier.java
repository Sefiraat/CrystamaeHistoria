package io.github.sefiraat.crystamaehistoria.stories;

import javax.annotation.ParametersAreNonnullByDefault;

public class BlockTier {

    public final int tier;
    /**
     * Represents a X in 1000 chance to generate a story per tick.
     * Will subsequently be multiplied up by Panel tier
     * e.g. 20 = 2.5% chance per tick on T1 panel, 5% on T2 panel.
     */
    public final int chroniclingChance;
    public final int maxStories;
    public final int minStories;
    public final StoryChances storyChances;


    @ParametersAreNonnullByDefault
    public BlockTier(int tier, int chroniclingChance, int maxStories, int minStories, StoryChances storyChances) {
        this.tier = tier;
        this.chroniclingChance = chroniclingChance;
        this.maxStories = maxStories;
        this.minStories = minStories;
        this.storyChances = storyChances;
    }
}
