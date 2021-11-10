package io.github.sefiraat.crystamaehistoria.stories;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryShardProfile;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class Story {

    @Nonnull
    private final String id;
    @Nullable
    private final String author;
    @Nonnull
    private final StoryRarity rarity;
    @Nonnull
    private final StoryType type;
    @Nonnull
    private final StoryShardProfile storyShardProfile;
    @Nonnull
    private final List<String> storyStrings;
    @Setter
    @Nullable
    private BlockPosition blockPosition;

    /**
     * @noinspection unchecked
     */
    @ParametersAreNonnullByDefault
    public Story(Map<String, Object> map, StoryRarity storyRarity) {
        this.rarity = storyRarity;
        this.id = (String) map.get("name");
        this.type = StoryType.getByName((String) map.get("type"));
        this.storyShardProfile = new StoryShardProfile((List<Integer>) map.get("shards"));
        this.storyStrings = (List<String>) map.get("lore");
        this.author = (String) map.get("author");
    }

    /**
     * @noinspection unchecked
     */
    @ParametersAreNonnullByDefault
    private Story(Story story) {
        this.rarity = story.rarity;
        this.id = story.getId();
        this.type = story.type;
        this.storyShardProfile = story.getStoryShardProfile();
        this.storyStrings = story.storyStrings;
        this.author = story.author;
    }

    public String getDisplayRarity() {
        return "[" + rarity.toString() + "] ";
    }

    public String getDisplayName() {
        final TextComponent rarityComponent = new TextComponent(getDisplayRarity());
        final TextComponent nameComponent = new TextComponent(this.id);

        rarityComponent.setColor(ThemeType.getByRarity(this.rarity).getColor());
        rarityComponent.setBold(true);
        nameComponent.setColor(ThemeType.CLICK_INFO.getColor());
        return BaseComponent.toLegacyText(rarityComponent, nameComponent);
    }

    public List<String> getStoryLore() {
        final ChatColor passive = ThemeType.PASSIVE.getColor();
        final List<String> l = new ArrayList<>();

        for (String s : storyStrings) {
            final TextComponent line = new TextComponent(s);

            line.setColor(passive);
            line.setItalic(false);
            l.add(BaseComponent.toLegacyText(line));
        }
        if (author != null) {
            l.add("");
            l.add(ThemeType.PASSIVE.getColor() + "Author: " + author);
        }
        return l;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Story) {
            Story story = (Story) obj;
            return this.id.equals(story.id)
                && this.rarity == story.rarity
                && this.type == story.type;
        } else {
            return false;
        }
    }

    public Story copy() {
        return new Story(this);
    }
}
