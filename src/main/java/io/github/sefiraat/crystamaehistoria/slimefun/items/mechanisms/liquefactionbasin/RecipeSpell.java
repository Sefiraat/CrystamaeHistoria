package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RecipeSpell {

    private final int tier;
    private final List<StoryType> storyTypes;

    @ParametersAreNonnullByDefault
    public RecipeSpell(int tier, StoryType type1, StoryType type2, StoryType type3) {
        this.tier = tier;
        this.storyTypes = Arrays.asList(type1, type2, type3);
    }

    @ParametersAreNonnullByDefault
    public boolean recipeMatches(Set<StoryType> testTypes, int tier) {
        return this.storyTypes.containsAll(testTypes) && this.tier == tier;
    }

    public int getTier() {
        return this.tier;
    }

    public StoryType getInput(int index) {
        return this.storyTypes.get(index);
    }

}
