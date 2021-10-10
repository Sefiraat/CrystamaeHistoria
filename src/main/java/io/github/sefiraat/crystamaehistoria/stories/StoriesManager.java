package io.github.sefiraat.crystamaehistoria.stories;

import io.github.mooy1.infinitylib.core.AddonConfig;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryChances;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import lombok.Getter;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StoriesManager {

    @Getter
    private final Map<Material, StoriedBlockDefinition> storiedBlockDefinitionMap = new EnumMap<>(Material.class);
    @Getter
    private final Map<Integer, BlockTier> blockTierMap = new HashMap<>();
    @Getter
    private final Map<Integer, Story> storyMapCommon = new HashMap<>();
    @Getter
    private final Map<Integer, Story> storyMapUncommon = new HashMap<>();
    @Getter
    private final Map<Integer, Story> storyMapRare = new HashMap<>();
    @Getter
    private final Map<Integer, Story> storyMapEpic = new HashMap<>();
    @Getter
    private final Map<Integer, Story> storyMapMythical = new HashMap<>();
    @Getter
    private final Map<Integer, Story> storyMapUnique = new HashMap<>();

    public StoriesManager() {
        fillBlockTierMap();
        fillStories();
        fillBlockDefinitions();
    }

    @ParametersAreNonnullByDefault
    private static Story parseStory(ConfigurationSection sConf, StoryRarity rarity) {
        String type = sConf.getString("type");
        StoryType storyType = StoryType.valueOf(type.toUpperCase(Locale.ROOT));
        return new Story(
            Integer.parseInt(sConf.getName()),
            rarity.getId(),
            storyType.getId()
        );
    }

    private void fillBlockTierMap() {
        blockTierMap.put(
            1,
            new BlockTier(
                1,
                300,
                3,
                1,
                new StoryChances(
                    90,
                    10,
                    0,
                    0,
                    0
                )
            )
        );
    }

    private void fillStories() {
        AddonConfig c = CrystamaeHistoria.inst().getConfig();

        ConfigurationSection common = c.getConfigurationSection("stories.common");
        Validate.notNull(common, "COMMON story configuration is not found, changed or deleted.");
        fillMap(storyMapCommon, common, StoryRarity.COMMON);

        ConfigurationSection uncommon = c.getConfigurationSection("stories.uncommon");
        Validate.notNull(uncommon, "UNCOMMON story configuration is not found, changed or deleted.");
        fillMap(storyMapUncommon, uncommon, StoryRarity.UNCOMMON);

        ConfigurationSection rare = c.getConfigurationSection("stories.rare");
        Validate.notNull(rare, "RARE story configuration is not found, changed or deleted.");
        fillMap(storyMapRare, rare, StoryRarity.RARE);

        ConfigurationSection epic = c.getConfigurationSection("stories.epic");
        Validate.notNull(epic, "EPIC story configuration is not found, changed or deleted.");
        fillMap(storyMapEpic, epic, StoryRarity.EPIC);

        ConfigurationSection mythical = c.getConfigurationSection("stories.mythical");
        Validate.notNull(mythical, "MYTHICAL story configuration is not found, changed or deleted.");
        fillMap(storyMapMythical, mythical, StoryRarity.MYTHICAL);

        ConfigurationSection unique = c.getConfigurationSection("stories.unique");
        Validate.notNull(unique, "UNIQUE story configuration is not found, changed or deleted.");
        fillMap(storyMapUnique, unique, StoryRarity.UNIQUE);

    }

    @ParametersAreNonnullByDefault
    private void fillMap(Map<Integer, Story> map, ConfigurationSection section, StoryRarity rarity) {
        for (String s : section.getKeys(false)) {
            Story story = parseStory(section.getConfigurationSection(s), rarity);
            map.put(Integer.parseInt(s), story);
        }
    }

    private void fillBlockDefinitions() {
        storiedBlockDefinitionMap.put(
            Material.STONE,
            new StoriedBlockDefinition(
                blockTierMap.get(1),
                Arrays.asList(
                    StoryType.ELEMENTAL,
                    StoryType.HISTORICAL
                ),
                storyMapUnique.get(1)
            )
        );
        storiedBlockDefinitionMap.put(
            Material.CRAFTING_TABLE,
            new StoriedBlockDefinition(
                blockTierMap.get(1),
                Arrays.asList(
                    StoryType.HUMAN,
                    StoryType.PHILOSOPHICAL
                ),
                storyMapUnique.get(2)
            )
        );
    }

    @ParametersAreNonnullByDefault
    public Story getStory(int id, StoryRarity storyRarity) {
        switch (storyRarity) {
            case COMMON:
                return storyMapCommon.get(id);
            case UNCOMMON:
                return storyMapUncommon.get(id);
            case RARE:
                return storyMapRare.get(id);
            case EPIC:
                return storyMapEpic.get(id);
            case MYTHICAL:
                return storyMapMythical.get(id);
            case UNIQUE:
                return storyMapUnique.get(id);
            default:
                throw new IllegalStateException("Unexpected value: " + storyRarity);
        }
    }


}
