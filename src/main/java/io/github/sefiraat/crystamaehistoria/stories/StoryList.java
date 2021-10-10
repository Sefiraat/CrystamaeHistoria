package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StoryList {

    @Getter
    private final List<Story> list = new ArrayList<>();

}
