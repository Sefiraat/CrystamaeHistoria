package io.github.sefiraat.crystamaehistoria.stories;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryChances;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class StoriesManager {

    @Getter
    private final Map<Material, StoriedBlockDefinition> storiedBlockDefinitionMap = new EnumMap<>(Material.class);
    @Getter
    private final Map<Integer, BlockTier> blockTierMap = new HashMap<>();
    @Getter
    private final Map<String, Story> storyMapCommon = new HashMap<>();
    @Getter
    private final Map<String, Story> storyMapUncommon = new HashMap<>();
    @Getter
    private final Map<String, Story> storyMapRare = new HashMap<>();
    @Getter
    private final Map<String, Story> storyMapEpic = new HashMap<>();
    @Getter
    private final Map<String, Story> storyMapMythical = new HashMap<>();
    @Getter
    private final Map<String, Story> storyMapUnique = new HashMap<>();

    public StoriesManager() {
        fillBlockTierMap();
        fillStories();
        fillBlockDefinitions();
    }

    private void fillBlockTierMap() {
        blockTierMap.put(
            1,
            new BlockTier(
                1,
                700,
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
        blockTierMap.put(
            2,
            new BlockTier(
                2,
                600,
                3,
                2,
                new StoryChances(
                    70,
                    20,
                    10,
                    0,
                    0
                )
            )
        );
        blockTierMap.put(
            3,
            new BlockTier(
                3,
                500,
                4,
                2,
                new StoryChances(
                    50,
                    30,
                    15,
                    5,
                    0
                )
            )
        );
        blockTierMap.put(
            4,
            new BlockTier(
                4,
                400,
                5,
                3,
                new StoryChances(
                    25,
                    35,
                    25,
                    10,
                    5
                )
            )
        );
        blockTierMap.put(
            5,
            new BlockTier(
                5,
                300,
                5,
                4,
                new StoryChances(
                    10,
                    30,
                    25,
                    20,
                    15
                )
            )
        );
    }

    /** @noinspection unchecked*/
    private void fillStories() {
        FileConfiguration stories = CrystamaeHistoria.getConfigManager().getStories();

        List<Map<String, Object>> common = (List<Map<String, Object>>) stories.getList("common");
        Validate.notNull(common, "Common story configuration is not found, changed or deleted.");
        fillMap(storyMapCommon, common, StoryRarity.COMMON);

        List<Map<String, Object>> uncommon = (List<Map<String, Object>>) stories.getList("uncommon");
        Validate.notNull(uncommon, "Uncommon story configuration is not found, changed or deleted.");
        fillMap(storyMapUncommon, uncommon, StoryRarity.UNCOMMON);

        List<Map<String, Object>> rare = (List<Map<String, Object>>) stories.getList("rare");
        Validate.notNull(rare, "Rare story configuration is not found, changed or deleted.");
        fillMap(storyMapRare, rare, StoryRarity.RARE);

        List<Map<String, Object>> epic = (List<Map<String, Object>>) stories.getList("epic");
        Validate.notNull(epic, "Epic story configuration is not found, changed or deleted.");
        fillMap(storyMapEpic, epic, StoryRarity.EPIC);

        List<Map<String, Object>> mythical = (List<Map<String, Object>>) stories.getList("mythical");
        Validate.notNull(mythical, "Mythical story configuration is not found, changed or deleted.");
        fillMap(storyMapMythical, mythical, StoryRarity.MYTHICAL);
    }

    @ParametersAreNonnullByDefault
    private void fillMap(Map<String, Story> map, List<Map<String, Object>> list, StoryRarity rarity) {
        for (Map<String, Object> storyDefinition : list) {
            Story story = new Story(storyDefinition, rarity);
            map.put(story.getId(), story);
        }
    }

    /** @noinspection unchecked*/
    private void fillBlockDefinitions() {
        final FileConfiguration blocks = CrystamaeHistoria.getConfigManager().getBlocks();
        final List<Map<String, Object>> blockList = (List<Map<String, Object>>) blocks.getList("blocks");
        for (Map<String, Object> map : blockList) {
            final Map<String, Object> storyMap = (Map<String, Object>) map.get("story");
            final String name = (String) storyMap.get("name");
            if (name != null) {
                final Story story = new Story(storyMap, StoryRarity.UNIQUE);

                storyMapUnique.put(story.getId(), story);

                final int tier = (int) map.get("tier");
                final Material material = Material.getMaterial((String) map.get("material"));
                final List<StoryType> types = ((List<String>) map.get("elements")).stream()
                    .map(StoryType::getByName)
                    .collect(Collectors.toList());
                final StoriedBlockDefinition blockDefinition = new StoriedBlockDefinition(
                    material,
                    blockTierMap.get(tier),
                    types,
                    story
                );
                storiedBlockDefinitionMap.put(material, blockDefinition);
            }
        }
        CrystamaeHistoria.log(Level.INFO, "Loaded: " + storiedBlockDefinitionMap.size() + " unique (block) stories.");
    }

    @ParametersAreNonnullByDefault
    public Story getStory(String id, StoryRarity storyRarity) {
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

    @ParametersAreNonnullByDefault
    public static void rebuildStoriedStack(ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        setName(itemStack, im);
        List<String> lore = new ArrayList<>();
        List<Story> storyList = StoryUtils.getAllStories(itemStack);
        for (Story story : storyList) {
            lore.add("");
            lore.add(story.getDisplayName());
            lore.addAll(story.getStoryLore());
        }
        im.setLore(lore);
        itemStack.setItemMeta(im);
    }

    @ParametersAreNonnullByDefault
    private static void setName(ItemStack itemStack, ItemMeta im) {
        TextComponent name = new TextComponent("Storied " + ThemeType.toTitleCase(itemStack.getType().toString()));
        name.setColor(ThemeType.MAIN.getColor());
        name.setBold(true);
        im.setDisplayName(name.toLegacyText());
    }
}
