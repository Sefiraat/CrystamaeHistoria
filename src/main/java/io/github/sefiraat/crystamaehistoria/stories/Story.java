package io.github.sefiraat.crystamaehistoria.stories;

import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Story {

    private final int id;
    private final String storyName;
    private final StoryRarity rarity;
    private final StoryType type;
    private final StoryShardProfile storyShardProfile;
    private final String[] story;

    public Story(int id, String storyName, StoryRarity rarity, StoryType type, StoryShardProfile storyShardProfile, String... story) {
        this.id = id;
        this.storyName = storyName;
        this.rarity = rarity;
        this.type = type;
        this.storyShardProfile = storyShardProfile;
        this.story = story;
    }

    public String getDisplayRarity() {
        return "[" + rarity.toString() + "]";
    }

    public Component getDisplayName() {
        Style rarityStyle = ThemeUtils.getRarityTheme(rarity).getBold();
        Style nameStyle = ThemeUtils.getThemeElement(ThemeType.MAIN).getBold();
        Component rarity = Component.text(getDisplayRarity(), rarityStyle);
        Component name = Component.text(storyName, nameStyle);
        return rarity.append(Component.space()).append(name);
    }

    public List<Component> getStoryLore() {
        Style passiveStyle = ThemeUtils.getThemeElement(ThemeType.PASSIVE).getPlain();
        List<Component> l = new ArrayList<>();
        for (String s : story) {
            l.add(Component.text(s, passiveStyle));
        }
        return l;
    }

}
