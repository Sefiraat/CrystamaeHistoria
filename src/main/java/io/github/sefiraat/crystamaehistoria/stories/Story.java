package io.github.sefiraat.crystamaehistoria.stories;

import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Story {

    private final int id;
    private final String storyName;
    private final String author;
    private final StoryRarity storyRarity;
    private final StoryType type;
    private final StoryShardProfile storyShardProfile;
    private final String[] storyStrings;

    @ParametersAreNonnullByDefault
    public Story(int id, String storyName, StoryRarity rarity, StoryType type, StoryShardProfile storyShardProfile, @Nullable String author, String... storyStrings) {
        this.id = id;
        this.storyName = storyName;
        this.storyRarity = rarity;
        this.type = type;
        this.storyShardProfile = storyShardProfile;
        this.storyStrings = storyStrings;
        this.author = author;
    }

    public String getDisplayRarity() {
        return "[" + storyRarity.toString() + "] ";
    }

    public String getDisplayName() {
        TextComponent rarity = new TextComponent(getDisplayRarity());
        rarity.setColor(ThemeUtils.getRarityTheme(storyRarity).getThemeColor());
        rarity.setBold(true);

        TextComponent name = new TextComponent(storyName);
        name.setColor(ThemeUtils.getThemeColor(ThemeType.CLICK_INFO));

        return TextComponent.toLegacyText(rarity, name);

    }

    public List<String> getStoryLore() {
        ChatColor passive = ThemeUtils.getThemeColor(ThemeType.PASSIVE);
        List<String> l = new ArrayList<>();
        for (String s : storyStrings) {
            TextComponent line = new TextComponent(s);
            line.setColor(passive);
            line.setItalic(false);
            l.add(TextComponent.toLegacyText(line));
        }
        if (author != null) {
            l.add(ThemeUtils.getThemeColor(ThemeType.NOTICE) + "Author : " + author);
        }
        return l;
    }

}
