package io.github.sefiraat.crystamaehistoria.stories;

import io.github.mooy1.infinitylib.core.AddonConfig;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
public class Story {

    private final int id;
    private @Nonnull final String name;
    private @Nullable final String author;
    private @Nonnull final StoryRarity rarity;
    private @Nonnull final StoryType type;
    private @Nonnull final StoryShardProfile storyShardProfile;
    private @Nonnull final List<String> storyStrings;

    @ParametersAreNonnullByDefault
    public Story(int[] ints) {
        this(ints[0], ints[1], ints[2]);
    }
    @ParametersAreNonnullByDefault
    public Story(int id, int rarity, int type) {
        this.id = id;
        this.rarity = StoryRarity.getById(rarity);
        this.type = StoryType.getById(type);
        AddonConfig config = CrystamaeHistoria.config();
        String rarityString = this.rarity.toString().toLowerCase(Locale.ROOT);
        this.name = config.getString("stories." + rarityString + "." + id + ".name");
        this.storyShardProfile = new StoryShardProfile(config.getIntegerList("stories." + rarityString + "." + id + ".shards"));
        this.storyStrings = config.getStringList("stories." + rarityString + "." + id + ".lore");
        this.author = config.getString("stories." + rarityString + "." + id + ".author");
    }

    public String getDisplayRarity() {
        return "[" + rarity.toString() + "] ";
    }

    public String getDisplayName() {
        final TextComponent rarity = new TextComponent(getDisplayRarity());
        final TextComponent name = new TextComponent(this.name);

        rarity.setColor(ThemeUtils.getRarityTheme(this.rarity).getThemeColor());
        rarity.setBold(true);
        name.setColor(ThemeUtils.getThemeColor(ThemeType.CLICK_INFO));
        return TextComponent.toLegacyText(rarity, name);
    }

    public List<String> getStoryLore() {
        final ChatColor passive = ThemeUtils.getThemeColor(ThemeType.PASSIVE);
        final List<String> l = new ArrayList<>();

        for (String s : storyStrings) {
            final TextComponent line = new TextComponent(s);

            line.setColor(passive);
            line.setItalic(false);
            l.add(TextComponent.toLegacyText(line));
        }
        if (author != null) {
            l.add(ThemeUtils.getThemeColor(ThemeType.NOTICE) + "Author : " + author);
        }
        return l;
    }

    public int[] toPrimitive() {
        return new int[]{this.id, this.rarity.getId(), this.type.getId()};
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result = 31 * result + name.hashCode();
        result = 31 * result + rarity.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Story) {
            Story story = (Story) obj;
            return this.id == story.id
                    && this.rarity == story.rarity
                    && this.type == story.type;
        } else {
            return false;
        }
    }
}
