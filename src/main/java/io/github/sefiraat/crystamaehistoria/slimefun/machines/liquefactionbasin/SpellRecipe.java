package io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

@Getter
public class SpellRecipe {

    private final SlimefunItem catalyst;
    private final EnumSet<StoryType> storyTypes;

    public SpellRecipe(SlimefunItem catalyst, StoryType type1, StoryType type2, StoryType type3) {
        this.catalyst = catalyst;
        this.storyTypes = EnumSet.of(type1, type2, type3);
    }

    public boolean recipeMatches(Set<StoryType> testTypes, SlimefunItem testCatalyst) {
        return storyTypes.containsAll(testTypes) && catalyst.getId().equals(testCatalyst.getId());
    }

}
