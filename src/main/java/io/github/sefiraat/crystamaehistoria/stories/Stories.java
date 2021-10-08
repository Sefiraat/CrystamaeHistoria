package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Stories {

    @Getter
    private final List<Story> storyList = new ArrayList<>();

    public int[] toPrimitive() {
        int[] ints = new int[0];
        for (Story story : getStoryList()) {
            ints = ArrayUtils.addAll(ints, story.toPrimitive());
        }
        return ints;
    }

}
