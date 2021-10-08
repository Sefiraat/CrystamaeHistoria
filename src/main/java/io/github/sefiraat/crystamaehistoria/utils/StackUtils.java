package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.Stories;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.Validate;
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
        Validate.notNull(im, "Meta is null for stack, this should not be possible!");
        setName(itemStack, im);
        Validate.isTrue(PersistentDataAPI.hasBoolean(im, CrystamaeHistoria.getKeys().getPdcIsStoried()), "ItemStack is not storied and should not be having it's lore rebuilt");
        List<String> lore = new ArrayList<>();
        Stories stories = StoryUtils.getAllStories(itemStack);
        for (Story story : stories.getStoryList()) {
            lore.add("");
            lore.add(story.getDisplayName());
            lore.addAll(story.getStoryLore());
        }
        im.setLore(lore);
        itemStack.setItemMeta(im);
    }

    @ParametersAreNonnullByDefault
    private static void setName(ItemStack itemStack, ItemMeta im) {
        TextComponent name = new TextComponent("Storied " + ThemeUtils.toTitleCase(itemStack.getType().toString()));
        name.setColor(ThemeUtils.getThemeColor(ThemeType.MAIN));
        name.setBold(true);
        im.setDisplayName(name.toLegacyText());
    }

}
