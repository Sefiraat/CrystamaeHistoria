package io.github.sefiraat.crystamaehistoria.utils;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.managers.StoriesManager;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.BlockTier;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryChances;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStoriesDataType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@UtilityClass
public class StoryUtils {

    /**
     * Returns true if the block is able to have stories (is in the map)
     *
     * @param itemStack The {@link ItemStack} to check
     * @return true if in the stories map
     */
    @ParametersAreNonnullByDefault
    public static boolean canBeStoried(ItemStack itemStack, int tier) {
        final Material material = itemStack.getType();
        final BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(material);

        return definition != null && definition.getBlockTier().tier <= tier && isAllowed(itemStack);
    }

    private static final Set<Material> metaBypass = EnumSet.of(
        Material.BEE_NEST,
        Material.BEEHIVE,
        Material.ENCHANTED_BOOK,
        Material.FILLED_MAP,
        Material.FIREWORK_ROCKET,
        Material.FIREWORK_STAR,
        Material.LINGERING_POTION,
        Material.PLAYER_HEAD,
        Material.POTION,
        Material.SPLASH_POTION,
        Material.SPAWNER,
        Material.SUSPICIOUS_STEW,
        Material.TIPPED_ARROW,
        Material.WRITTEN_BOOK,
        Material.AXOLOTL_BUCKET,
        Material.COD_BUCKET,
        Material.PUFFERFISH_BUCKET,
        Material.SALMON_BUCKET,
        Material.TROPICAL_FISH_BUCKET
    );

    @ParametersAreNonnullByDefault
    private static boolean isAllowed(ItemStack itemStack) {
        SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
        if (slimefunItem == null) {
            return metaBypass.contains(itemStack.getType()) || isStoried(itemStack) || !itemStack.hasItemMeta();
        } else {
            return itemStack.getType() == Material.SPAWNER;
        }
    }

    /**
     * Returns true if the has been storied. This does not mean that is HAS
     * stories, only that it has started to be processed byu a chronicler
     *
     * @param itemStack The {@link ItemStack} to check
     * @return true if has previously been chronicled at any point
     */
    @ParametersAreNonnullByDefault
    public static boolean isStoried(@Nonnull ItemStack itemStack) {
        return PersistentDataAPI.hasBoolean(itemStack.getItemMeta(), Keys.PDC_IS_STORIED);
    }

    /**
     * Sets the ItemStack's PDC Storied to True. Also sets an initial story object
     *
     * @param itemStack The {@link ItemStack} whose meta will have the PDC element added to
     */
    @ParametersAreNonnullByDefault
    public static void makeStoried(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.setBoolean(itemMeta, Keys.PDC_IS_STORIED, true);
        setStoryLimits(itemMeta, getStoryLimits(itemStack));
        itemStack.setItemMeta(itemMeta);
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
     * Returns true if the has been storied. This does not mean that is HAS
     * stories, only that it has started to be processed byu a chronicler
     *
     * @param itemStack The {@link ItemStack} to check
     * @return true if has previously been chronicled at any point
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static JsonObject getStoryLimits(ItemStack itemStack) {
        return PersistentDataAPI.getJsonObject(
            itemStack.getItemMeta(),
            Keys.PDC_POTENTIAL_STORIES,
            getInitialStoryLimits(itemStack)
        );
    }

    /**
     * Creates a new JsonObject for a newly storied item.
     * We do this now to 'lock in' the story potential
     *
     * @param itemStack The {@link ItemStack} to compare against the storied map
     * @return New {@link JsonObject} with content for story count and tier.
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static JsonObject getInitialStoryLimits(ItemStack itemStack) {
        Material material = itemStack.getType();
        BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(material);
        Preconditions.checkNotNull(
            definition,
            "The selected material does not have a story definition. This shouldn't happen, SefiDumb™"
        );
        int availableStoryCount = ThreadLocalRandom.current()
                                                   .nextInt(
                                                       definition.getBlockTier().minStories,
                                                       definition.getBlockTier().maxStories + 1
                                                   );
        int tier = definition.getBlockTier().tier;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(Keys.JS_S_AVAILABLE_STORIES, new JsonPrimitive(availableStoryCount));
        jsonObject.add(Keys.JS_S_TIER, new JsonPrimitive(tier));
        return jsonObject;
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
     * Gets the ItemStack's remaining possible stories
     *
     * @param itemStack The {@link ItemStack} to check
     */
    @ParametersAreNonnullByDefault
    public static int getRemainingStoryAmount(ItemStack itemStack) {
        return getMaxStoryAmount(itemStack) - getStoryAmount(itemStack.getItemMeta());
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

    @ParametersAreNonnullByDefault
    public static void requestNewStory(ItemStack itemstack) {
        final StoriesManager manager = CrystamaeHistoria.getStoriesManager();
        final BlockDefinition definition = manager.getBlockDefinitionMap().get(itemstack.getType());
        final BlockTier tier = definition.getBlockTier();
        final StoryChances chance = tier.storyChances;
        final List<StoryType> pool = definition.getPools();
        int rnd = ThreadLocalRandom.current().nextInt(1, 101);

        if (rnd <= chance.getMythical()) {
            addStory(itemstack, pool, manager.getStoryMapMythical());
        } else if (rnd <= chance.getEpic()) {
            addStory(itemstack, pool, manager.getStoryMapEpic());
        } else if (rnd <= chance.getRare()) {
            addStory(itemstack, pool, manager.getStoryMapRare());
        } else if (rnd <= chance.getUncommon()) {
            addStory(itemstack, pool, manager.getStoryMapUncommon());
        } else {
            addStory(itemstack, pool, manager.getStoryMapCommon());
        }
    }

    @ParametersAreNonnullByDefault
    public static void addStory(ItemStack itemStack, List<StoryType> p, Map<String, Story> storyList) {
        final StoryType st = p.get(ThreadLocalRandom.current().nextInt(0, p.size()));
        final List<Story> availableStories = storyList.values()
                                                      .stream()
                                                      .filter(t -> t.getType() == st)
                                                      .collect(Collectors.toList());
        final Story story = availableStories.get(ThreadLocalRandom.current().nextInt(0, availableStories.size()));
        applyStory(itemStack, story);
        incrementStoryAmount(itemStack);
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

    /**
     * Sets the ItemStack's current number of StoryList
     *
     * @param itemStack The {@link ItemStack} to increment the story amount
     */
    @ParametersAreNonnullByDefault
    public static void incrementStoryAmount(ItemStack itemStack) {
        setStoryAmount(itemStack, getStoryAmount(itemStack.getItemMeta()) + 1);
    }

    @ParametersAreNonnullByDefault
    @Nullable
    public static List<Story> getAllStories(ItemStack itemStack) {
        return DataTypeMethods.getCustom(itemStack.getItemMeta(), Keys.PDC_STORIES, PersistentStoriesDataType.TYPE);
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

    @ParametersAreNonnullByDefault
    public static void requestUniqueStory(ItemStack itemstack) {
        final StoriesManager m = CrystamaeHistoria.getStoriesManager();
        final BlockDefinition s = m.getBlockDefinitionMap().get(itemstack.getType());
        Story unique = s.getUnique();
        applyStory(itemstack, unique);
    }

    @ParametersAreNonnullByDefault
    public static int removeStory(ItemStack itemStack, Story story) {
        final ItemMeta im = itemStack.getItemMeta();
        final List<Story> storyList = getAllStories(itemStack);
        Preconditions.checkNotNull(storyList, "No storyList found when trying to remove.");
        storyList.remove(story);
        DataTypeMethods.setCustom(im, Keys.PDC_STORIES, PersistentStoriesDataType.TYPE, storyList);
        itemStack.setItemMeta(im);
        return storyList.size();
    }
}
