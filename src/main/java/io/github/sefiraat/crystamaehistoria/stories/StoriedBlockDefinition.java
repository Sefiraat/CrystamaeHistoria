package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;

@Getter
public class StoriedBlockDefinition {

       private final BlockTier tier;
       private final StoryPoolProfile pools;
       private final Story archetypeHistoria;

    public StoriedBlockDefinition(BlockTier tier, StoryPoolProfile pools, Story archetypeHistoria) {
        this.tier = tier;
        this.pools = pools;
        this.archetypeHistoria = archetypeHistoria;
    }
}
