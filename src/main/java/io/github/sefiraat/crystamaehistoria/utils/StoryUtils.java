package io.github.sefiraat.crystamaehistoria.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.mooy1.infinitylib.persistence.PersistenceUtils;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.StoriedBlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.cscorelib2.data.PersistentDataAPI;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

public class StoryUtils {

    /**
     * Returns true if the block is able to have stories (is in the map)
     * @param block The {@link Block}  to check
     * @return true if in the stories map
     */
    public static boolean canBeStoried(@Nonnull Block block) {
        return canBeStoried(block.getType());
    }

    /**
     * Returns true if the block is able to have stories (is in the map)
     * @param itemStack The {@link ItemStack} to check
     * @return true if in the stories map
     */
    public static boolean canBeStoried(@Nonnull ItemStack itemStack) {
        return canBeStoried(itemStack.getType());
    }

    /**
     * Returns true if the block is able to have stories (is in the map)
     * @param material The {@link Material} to check
     * @return true if in the stories map
     */
    public static boolean canBeStoried(@Nonnull Material material) {
        return StoriesManager.getSTORIED_BLOCK_MAP().containsKey(material);
    }

    /**
     * Returns true if the has been storied. This does not mean that is HAS
     * stories, only that it has started to be processed byu a chronicler
     * @param itemStack The {@link ItemStack} to check
     * @return true if has previously been chronicled at any point
     */
    public static boolean isStoried(@Nonnull ItemStack itemStack) {
        return PersistentDataAPI.hasBoolean(itemStack.getItemMeta(), CrystamaeHistoria.inst().getKeyHolder().getPdcIsStoried());
    }

    /**
     * Sets the ItemStack's PDC Storied to True. Also sets an initial story object
     * @param itemStack The {@link ItemStack} whos meta will have the PDC element added to
     */
    public static void makeStoried(@Nonnull ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.setBoolean(itemMeta, CrystamaeHistoria.inst().getKeyHolder().getPdcIsStoried(), true);
        setStories(itemMeta, getStories(itemStack));
        itemStack.setItemMeta(itemMeta);
    }

    /**
     * Returns true if the has been storied. This does not mean that is HAS
     * sotries, only that it has started to be processed byu a chronicler
     * @param itemStack The {@link ItemStack} to check
     * @return true if has previously been chronicled at any point
     */
    @Nonnull
    public static JsonObject getStories(@Nonnull ItemStack itemStack) {
        return PersistentDataAPI.getJsonObject(itemStack.getItemMeta(), CrystamaeHistoria.inst().getKeyHolder().getPdcStories(), getInitialStoriesObject(itemStack));
    }

    /**
     * Sets the ItemStack's PDC Stories
     * @param itemMeta The {@link ItemMeta} to add the PDC element to
     * @param jsonObject The {@link JsonObject} to add to the PDC
     */
    private static void setStories(@Nonnull ItemMeta itemMeta, @Nonnull JsonObject jsonObject) {
        PersistentDataAPI.setJsonObject(itemMeta, CrystamaeHistoria.inst().getKeyHolder().getPdcStories(), jsonObject);
    }

    /**
     * Gets the Item's max number of Stories
     * @param itemStack The {@link ItemStack} to add the PDC element to
     */
    public static int getMaxStoryAmount(@Nonnull ItemStack itemStack) {
        return getStories(itemStack).get(KeyHolder.JS_S_AVAILABLE_STORIES).getAsInt();
    }

    /**
     * Gets the ItemStack's current number of Stories
     * @param itemMeta The {@link ItemMeta} to get the count from
     */
    public static int getStoryAmount(@Nonnull ItemMeta itemMeta) {
        return PersistentDataAPI.getInt(itemMeta, CrystamaeHistoria.inst().getKeyHolder().getPdcCurrentNumberOfStories(), 0);
    }

    /**
     * Sets the ItemStack's current number of Stories
     * @param itemStack The {@link ItemStack} to add the PDC element to
     * @param amount The amount of stories to set
     */
    public static void setStoryAmount(@Nonnull ItemStack itemStack, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.setInt(itemMeta, CrystamaeHistoria.inst().getKeyHolder().getPdcCurrentNumberOfStories(), amount);
        if (amount >= getMaxStoryAmount(itemStack)) {
            itemMeta.addEnchant(Enchantment.LUCK, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
    }

    /**
     * Sets the ItemStack's current number of Stories
     * @param itemStack The {@link ItemStack} to increment the story amount
     */
    public static void incrementStoryAmount(@Nonnull ItemStack itemStack) {
        setStoryAmount(itemStack, getStoryAmount(itemStack.getItemMeta()) + 1);
    }

    /**
     * Gets the ItemStack's remaining possible stories
     * @param itemStack The {@link ItemMeta} to add the PDC element to
     */
    public static int getRemainingStoryAmount(@Nonnull ItemStack itemStack) {
        return getMaxStoryAmount(itemStack) - getStoryAmount(itemStack.getItemMeta());
    }

    /**
     * Creates a new jsonobject for a newly storied item.
     * We do this now to 'lock in' the story potential
     * @param itemStack The {@link ItemStack} to compare against the storied map
     * @return New {@link JsonObject} with content for story count and tier.
     */
    @Nonnull
    public static JsonObject getInitialStoriesObject(@Nonnull ItemStack itemStack) {
        Material m = itemStack.getType();
        StoriedBlockDefinition definition = StoriesManager.STORIED_BLOCK_MAP.get(m);
        Validate.notNull(definition, "The selected material does not have a story definition. This shouldn't happen, SefiDumb™");
        int availableStoryCount = ThreadLocalRandom.current().nextInt(definition.getTier().minStories, definition.getTier().maxStories + 1);
        int tier = definition.getTier().tier;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(KeyHolder.JS_S_AVAILABLE_STORIES, new JsonPrimitive(availableStoryCount));
        jsonObject.add(KeyHolder.JS_S_TIER, new JsonPrimitive(tier));
        return jsonObject;
    }


}
