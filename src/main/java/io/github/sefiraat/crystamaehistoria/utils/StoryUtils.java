package io.github.sefiraat.crystamaehistoria.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.BlockTier;
import io.github.sefiraat.crystamaehistoria.stories.StoriedBlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryChances;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStoriesDataType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@UtilityClass
public class StoryUtils {

    /**
     * Returns true if the block is able to have stories (is in the map)
     *
     * @param block The {@link Block}  to check
     * @return true if in the stories map
     */
    @ParametersAreNonnullByDefault
    public static boolean canBeStoried(Block block) {
        return canBeStoried(block.getType());
    }

    /**
     * Returns true if the block is able to have stories (is in the map)
     *
     * @param itemStack The {@link ItemStack} to check
     * @return true if in the stories map
     */
    @ParametersAreNonnullByDefault
    public static boolean canBeStoried(ItemStack itemStack) {
        return canBeStoried(itemStack.getType());
    }

    /**
     * Returns true if the block is able to have stories (is in the map)
     *
     * @param material The {@link Material} to check
     * @return true if in the stories map
     */
    @ParametersAreNonnullByDefault
    public static boolean canBeStoried(Material material) {
        return CrystamaeHistoria.getStoriesManager().getStoriedBlockDefinitionMap().containsKey(material);
    }

    /**
     * Returns true if the has been storied. This does not mean that is HAS
     * stories, only that it has started to be processed byu a chronicler
     *
     * @param itemStack The {@link ItemStack} to check
     * @return true if has previously been chronicled at any point
     */
    @ParametersAreNonnullByDefault
    public static boolean isStoried(ItemStack itemStack) {
        return PersistentDataAPI.hasBoolean(itemStack.getItemMeta(), Keys.PDC_IS_STORIED);
    }

    /**
     * Sets the ItemStack's PDC Storied to True. Also sets an initial story object
     *
     * @param itemStack The {@link ItemStack} whos meta will have the PDC element added to
     */
    @ParametersAreNonnullByDefault
    public static void makeStoried(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.setBoolean(itemMeta, Keys.PDC_IS_STORIED, true);
        setStoryLimits(itemMeta, getStoryLimits(itemStack));
        itemStack.setItemMeta(itemMeta);
    }

    /**
     * Returns true if the has been storied. This does not mean that is HAS
     * stories, only that it has started to be processed byu a chronicler
     *
     * @param itemStack The {@link ItemStack} to check
     * @return true if has previously been chronicled at any point
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static JsonObject getStoryLimits(ItemStack itemStack) {
        return PersistentDataAPI.getJsonObject(itemStack.getItemMeta(), Keys.PDC_POTENTIAL_STORIES, getInitialStoryLimits(itemStack));
    }

    /**
     * Sets the ItemStack's PDC StoryList
     *
     * @param itemMeta   The {@link ItemMeta} to add the PDC element to
     * @param jsonObject The {@link JsonObject} to add to the PDC
     */
    @ParametersAreNonnullByDefault
    private static void setStoryLimits(ItemMeta itemMeta, JsonObject jsonObject) {
        PersistentDataAPI.setJsonObject(itemMeta, Keys.PDC_POTENTIAL_STORIES, jsonObject);
    }

    /**
     * Gets the Item's max number of StoryList
     *
     * @param itemStack The {@link ItemStack} to add the PDC element to
     */
    @ParametersAreNonnullByDefault
    public static int getMaxStoryAmount(ItemStack itemStack) {
        return getStoryLimits(itemStack).get(Keys.JS_S_AVAILABLE_STORIES).getAsInt();
    }

    /**
     * Gets the ItemStack's current number of StoryList
     *
     * @param itemMeta The {@link ItemMeta} to get the count from
     */
    @ParametersAreNonnullByDefault
    public static int getStoryAmount(ItemMeta itemMeta) {
        return PersistentDataAPI.getInt(itemMeta, Keys.PDC_CURRENT_NUMBER_OF_STORIES, 0);
    }

    /**
     * Sets the ItemStack's current number of StoryList
     *
     * @param itemStack The {@link ItemStack} to add the PDC element to
     * @param amount    The amount of stories to set
     */
    @ParametersAreNonnullByDefault
    public static void setStoryAmount(ItemStack itemStack, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.setInt(itemMeta, Keys.PDC_CURRENT_NUMBER_OF_STORIES, amount);
        if (amount >= getMaxStoryAmount(itemStack)) {
            itemMeta.addEnchant(Enchantment.LUCK, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
    }

    /**
     * Sets the ItemStack's current number of StoryList
     *
     * @param itemStack The {@link ItemStack} to increment the story amount
     */
    @ParametersAreNonnullByDefault
    public static void incrementStoryAmount(ItemStack itemStack) {
        setStoryAmount(itemStack, getStoryAmount(itemStack.getItemMeta()) + 1);
    }

    /**
     * Gets the ItemStack's remaining possible stories
     *
     * @param itemStack The {@link ItemStack} to check
     */
    @ParametersAreNonnullByDefault
    public static int getRemainingStoryAmount(ItemStack itemStack) {
        return getMaxStoryAmount(itemStack) - getStoryAmount(itemStack.getItemMeta());
    }

    /**
     * Returns true if there is room for more stories
     *
     * @param itemStack The {@link ItemStack} to check
     */
    @ParametersAreNonnullByDefault
    public static boolean hasRemainingStorySlots(ItemStack itemStack) {
        return getRemainingStoryAmount(itemStack) > 0;
    }

    /**
     * Creates a new jsonobject for a newly storied item.
     * We do this now to 'lock in' the story potential
     *
     * @param itemStack The {@link ItemStack} to compare against the storied map
     * @return New {@link JsonObject} with content for story count and tier.
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static JsonObject getInitialStoryLimits(ItemStack itemStack) {
        Material m = itemStack.getType();
        StoriedBlockDefinition definition = CrystamaeHistoria.getStoriesManager().getStoriedBlockDefinitionMap().get(m);
        Validate.notNull(definition, "The selected material does not have a story definition. This shouldn't happen, SefiDumb™");
        int availableStoryCount = ThreadLocalRandom.current().nextInt(definition.getTier().minStories, definition.getTier().maxStories + 1);
        int tier = definition.getTier().tier;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(Keys.JS_S_AVAILABLE_STORIES, new JsonPrimitive(availableStoryCount));
        jsonObject.add(Keys.JS_S_TIER, new JsonPrimitive(tier));
        return jsonObject;
    }

    @ParametersAreNonnullByDefault
    public static void requestNewStory(ItemStack itemstack) {
        final StoriesManager m = CrystamaeHistoria.getStoriesManager();
        final StoriedBlockDefinition s = m.getStoriedBlockDefinitionMap().get(itemstack.getType());
        final BlockTier t = s.getTier();
        final StoryChances c = t.storyChances;
        final List<StoryType> p = s.getPools();
        int rnd = ThreadLocalRandom.current().nextInt(1, 101);

        if (rnd <= c.mythical) {
            addStory(itemstack, p, m.getStoryMapMythical());
        } else if (rnd <= c.epic) {
            addStory(itemstack, p, m.getStoryMapEpic());
        } else if (rnd <= c.rare) {
            addStory(itemstack, p, m.getStoryMapRare());
        } else if (rnd <= c.uncommon) {
            addStory(itemstack, p, m.getStoryMapUncommon());
        } else {
            addStory(itemstack, p, m.getStoryMapCommon());
        }
    }

    @ParametersAreNonnullByDefault
    public static void requestUniqueStory(ItemStack itemstack) {
        final StoriesManager m = CrystamaeHistoria.getStoriesManager();
        final StoriedBlockDefinition s = m.getStoriedBlockDefinitionMap().get(itemstack.getType());
        Story unique = s.getArchetypeHistoria();
        applyStory(itemstack, unique);
    }

    @ParametersAreNonnullByDefault
    public static void addStory(ItemStack itemStack, List<StoryType> p, Map<String, Story> storyList) {
        final StoryType st = p.get(ThreadLocalRandom.current().nextInt(0, p.size()));
        final List<Story> availableStories = storyList.values().stream().filter(t -> t.getType() == st).collect(Collectors.toList());
        final Story story = availableStories.get(ThreadLocalRandom.current().nextInt(0, availableStories.size()));
        applyStory(itemStack, story);
        incrementStoryAmount(itemStack);
    }

    @ParametersAreNonnullByDefault
    public static @Nullable
    List<Story> getAllStories(ItemStack itemStack) {
        return DataTypeMethods.getCustom(itemStack.getItemMeta(), Keys.PDC_STORIES, PersistentStoriesDataType.TYPE);
    }

    @ParametersAreNonnullByDefault
    public static void applyStory(ItemStack itemStack, Story story) {
        final ItemMeta im = itemStack.getItemMeta();
        List<Story> storyList = getAllStories(itemStack);
        if (storyList == null) {
            storyList = new ArrayList<>();
        }
        storyList.add(story);
        DataTypeMethods.setCustom(im, Keys.PDC_STORIES, PersistentStoriesDataType.TYPE, storyList);
        itemStack.setItemMeta(im);
    }

    @ParametersAreNonnullByDefault
    public static int removeStory(ItemStack itemStack, Story story) {
        final ItemMeta im = itemStack.getItemMeta();
        final List<Story> storyList = getAllStories(itemStack);
        Validate.notNull(storyList, "No storyList found when trying to remove.");
        storyList.remove(story);
        DataTypeMethods.setCustom(im, Keys.PDC_STORIES, PersistentStoriesDataType.TYPE, storyList);
        itemStack.setItemMeta(im);
        return storyList.size();
    }
}
