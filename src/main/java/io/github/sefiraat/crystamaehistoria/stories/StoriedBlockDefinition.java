package io.github.sefiraat.crystamaehistoria.stories;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import lombok.Getter;
import org.bukkit.Material;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@Getter
public class StoriedBlockDefinition {

    private final Material material;
    private final BlockTier tier;
    private final List<StoryType> pools;
    private final Story unique;

    @ParametersAreNonnullByDefault
    public StoriedBlockDefinition(Material material, BlockTier tier, List<StoryType> pools, Story unique) {
        this.material = material;
        this.tier = tier;
        this.pools = pools;
        this.unique = unique;
    }
}
