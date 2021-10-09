package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.StoryList;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class StackUtils {

    @ParametersAreNonnullByDefault
    public static void rebuildStoriedStack(ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        setName(itemStack, im);
        List<String> lore = new ArrayList<>();
        StoryList storyList = StoryUtils.getAllStories(itemStack);
        for (Story story : storyList.getStoryList()) {
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
        name.setColor(ThemeType.MAIN.getChatColor());
        name.setBold(true);
        im.setDisplayName(name.toLegacyText());
    }

}
