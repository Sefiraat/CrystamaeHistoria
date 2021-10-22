package io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SpellRecipe {

    private final SlimefunItem catalyst;
    private final List<StoryType> storyTypes;

    public SpellRecipe(SlimefunItem catalyst, StoryType type1, StoryType type2, StoryType type3) {
        this.catalyst = catalyst;
        this.storyTypes = Arrays.asList(type1, type2, type3);
    }

    public boolean recipeMatches(Set<StoryType> testTypes, SlimefunItem testCatalyst) {
        return storyTypes.containsAll(testTypes) && catalyst.getId().equals(testCatalyst.getId());
    }

    public SlimefunItem getCatalyst() {
        return catalyst;
    }

    public StoryType getInput(int index) {
        return storyTypes.get(index);
    }

}
