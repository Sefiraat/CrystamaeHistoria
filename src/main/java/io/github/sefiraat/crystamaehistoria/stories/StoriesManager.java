package io.github.sefiraat.crystamaehistoria.stories;

import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoriesManager {

    @Getter
    public static final Map<Integer, BlockTier> BLOCK_TIER_MAP = new HashMap<>();

    static {
        BLOCK_TIER_MAP.put(
                1,
                new BlockTier(
                        1,
                        25,
                        3,
                        1,
                        new StoryChances(
                                90,
                                8,
                                2,
                                0,
                                0
                        )
                )
        );
    }

    @Getter
    public static final Map<Material, StoriedBlockDefinition> STORIED_BLOCK_MAP = new EnumMap<>(Material.class);

    static {
        STORIED_BLOCK_MAP.put(
                Material.STONE,
                new StoriedBlockDefinition(
                        BLOCK_TIER_MAP.get(1),
                        new StoryPoolProfile(
                                true,
                                false,
                                false,
                                true,
                                false,
                                false,
                                false,
                                false,
                                false
                        ),
                        new Story(
                                "Resolute",
                                StoryRarity.Common,
                                StoryType.Elemental,
                                new StoryShardProfile(2,0,1,1,0,0,0,0,0),
                                "Stone is the resolute backbone of the world's",
                                "makeup. Ever present and everlasting."
                        )
                )
        );
    }

    @Getter
    public static final List<Story> STORY_LIST_COMMON = new ArrayList<>();
    @Getter
    public static final List<Story> STORY_LIST_UNCOMMON = new ArrayList<>();
    @Getter
    public static final List<Story> STORY_LIST_RARE = new ArrayList<>();
    @Getter
    public static final List<Story> STORY_LIST_EPIC = new ArrayList<>();
    @Getter
    public static final List<Story> STORY_LIST_MYTHICAL = new ArrayList<>();

    static {

    }

    public static void fillCommon() {
        STORY_LIST_COMMON.add(new Story(
                "Quiet Days",
                StoryRarity.Common,
                StoryType.Elemental,
                new StoryShardProfile(1, 0, 1, 1, 0, 0, 0, 0, 0),
                "Present during the beginning of the world.",
                "Everything was quiet and peaceful and serene"
                )
        );
        STORY_LIST_COMMON.add(new Story(
                "Everlasting",
                StoryRarity.Common,
                StoryType.Historical,
                new StoryShardProfile(0, 0, 0, 2, 0, 0, 0, 0, 1),
                "Having seen the rise and fall over empires,",
                "This block is proven everlasting and resolute."
                )
        );
    }

    public static void fillUncommon() {
        STORY_LIST_COMMON.add(new Story(
                "Quiet Days",
                StoryRarity.Uncommon,
                StoryType.Elemental,
                new StoryShardProfile(1, 0, 1, 1, 0, 0, 0, 0, 0),
                "Present during the beginning of the world.",
                "Everything was quiet and peaceful and serene"
                )
        );
        STORY_LIST_COMMON.add(new Story(
                "Everlasting",
                StoryRarity.Uncommon,
                StoryType.Historical,
                new StoryShardProfile(0, 0, 0, 2, 0, 0, 0, 0, 1),
                "Having seen the rise and fall over empires,",
                "Ethis block is proven everlasting and resolute."
                )
        );
    }

    public static void fillRare() {
        STORY_LIST_COMMON.add(new Story(
                "Quiet Days",
                StoryRarity.Rare,
                StoryType.Elemental,
                new StoryShardProfile(1, 0, 1, 1, 0, 0, 0, 0, 0),
                "Present during the beginning of the world.",
                "Everything was quiet and peaceful and serene"
                )
        );
        STORY_LIST_COMMON.add(new Story(
                "Everlasting",
                StoryRarity.Rare,
                StoryType.Historical,
                new StoryShardProfile(0, 0, 0, 2, 0, 0, 0, 0, 1),
                "Having seen the rise and fall over empires,",
                "Ethis block is proven everlasting and resolute."
                )
        );
    }


}
