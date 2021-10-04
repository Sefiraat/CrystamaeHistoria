package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@Getter
public class StoriedBlockDefinition {

    private final BlockTier tier;
    private final List<StoryType> pools;
    private final Story archetypeHistoria;

    @ParametersAreNonnullByDefault
    public StoriedBlockDefinition(BlockTier tier, List<StoryType> pools, Story archetypeHistoria) {
        this.tier = tier;
        this.pools = pools;
        this.archetypeHistoria = archetypeHistoria;
    }
}
