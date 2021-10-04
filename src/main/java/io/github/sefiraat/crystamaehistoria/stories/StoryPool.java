package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@Getter
public class StoryPool {

    private final StoryType storyType;
    private final List<Story> listCommon = new ArrayList<>();
    private final List<Story> listUncommon = new ArrayList<>();
    private final List<Story> listRare = new ArrayList<>();
    private final List<Story> listEpic = new ArrayList<>();
    private final List<Story> listMythical = new ArrayList<>();

    @ParametersAreNonnullByDefault
    public StoryPool(StoryType storyType) {
        this.storyType = storyType;
    }
}
