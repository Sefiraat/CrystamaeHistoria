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
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoriesManager {

    @Getter
    private final Map<Material, BlockDefinition> blockDefinitionMap = new EnumMap<>(Material.class);
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

    private void fillStories() {
        FileConfiguration stories = CrystamaeHistoria.getConfigManager().getStories();

        ConfigurationSection common = stories.getConfigurationSection("COMMON");
        Validate.notNull(common, "Common story configuration is not found, changed or deleted.");
        fillMap(storyMapCommon, common, StoryRarity.COMMON);

        ConfigurationSection uncommon = stories.getConfigurationSection("UNCOMMON");
        Validate.notNull(uncommon, "Uncommon story configuration is not found, changed or deleted.");
        fillMap(storyMapUncommon, uncommon, StoryRarity.UNCOMMON);

        ConfigurationSection rare = stories.getConfigurationSection("RARE");
        Validate.notNull(rare, "Rare story configuration is not found, changed or deleted.");
        fillMap(storyMapRare, rare, StoryRarity.RARE);

        ConfigurationSection epic = stories.getConfigurationSection("EPIC");
        Validate.notNull(epic, "Epic story configuration is not found, changed or deleted.");
        fillMap(storyMapEpic, epic, StoryRarity.EPIC);

        ConfigurationSection mythical = stories.getConfigurationSection("MYTHICAL");
        Validate.notNull(mythical, "Mythical story configuration is not found, changed or deleted.");
        fillMap(storyMapMythical, mythical, StoryRarity.MYTHICAL);
    }

    @ParametersAreNonnullByDefault
    private void fillMap(Map<String, Story> map, ConfigurationSection section, StoryRarity rarity) {
        for (String key : section.getKeys(false)) {
            ConfigurationSection storySection = section.getConfigurationSection(key);
            Validate.notNull(storySection, "Section is null, this doesn't make sense so don't worry.");
            Story story = new Story(storySection, rarity);
            map.put(story.getId(), story);
        }
    }

    private void fillBlockDefinitions() {
        final FileConfiguration blocks = CrystamaeHistoria.getConfigManager().getBlocks();
        for (String key : blocks.getKeys(false)) {
            final ConfigurationSection wholeSection = blocks.getConfigurationSection(key);
            Validate.notNull(wholeSection, "Section is null, this doesn't make sense so don't worry.");
            final ConfigurationSection storySection = wholeSection.getConfigurationSection("story");

            if (storySection == null) {
                CrystamaeHistoria.getInstance().getLogger().info(
                    MessageFormat.format("Ignoring a block with a missing story section -> {0}", key)
                );
                continue;
            }

            final String name = storySection.getString("name");
            final Material material = Material.getMaterial(key);

            if (name == null) {
                CrystamaeHistoria.getInstance().getLogger().info(
                    MessageFormat.format("Ignoring a story without a name -> {0}", key)
                );
                continue;
            }

            if (material == null) {
                CrystamaeHistoria.getInstance().getLogger().info(
                    MessageFormat.format("Ignoring a story with an invalid material -> {0}", key)
                );
                continue;
            }

            final Story story = new Story(storySection, StoryRarity.UNIQUE);
            final int tier = wholeSection.getInt("tier");
            final List<StoryType> types = wholeSection.getStringList("elements").stream()
                .map(StoryType::getByName)
                .collect(Collectors.toList());

            for (StoryType storyType : types) {
                if (storyType == null) {
                    CrystamaeHistoria.getInstance().getLogger().info(
                        MessageFormat.format("A block has a badly typed element -> {0}", key)
                    );
                }
            }

            final BlockDefinition blockDefinition = new BlockDefinition(
                material,
                blockTierMap.get(tier),
                types,
                story
            );
            blockDefinitionMap.put(material, blockDefinition);
            storyMapUnique.put(story.getId(), story);
        }
        CrystamaeHistoria.getInstance().getLogger().info(
            MessageFormat.format("Loaded: {0} unique (block) stories.", blockDefinitionMap.size())
        );
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
}
