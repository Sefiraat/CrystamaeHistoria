package io.github.sefiraat.crystamaehistoria.theme;

import lombok.Getter;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Particle;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Getter
public class ThemeElement {

    @Nonnull
    private final ChatColor themeColor;
    @Nullable
    private final String themeItemLore;
    @Nullable
    private final Particle.DustOptions themeParticles;

    public ThemeElement(ChatColor themeColor) {
        this.themeColor = themeColor;
        this.themeItemLore = null;
        this.themeParticles = null;
    }

    public ThemeElement(ChatColor themeColor, String themeItemLore) {
        this.themeColor = themeColor;
        this.themeItemLore = themeItemLore;
        this.themeParticles = null;
    }

    public ThemeElement(ChatColor themeColor, String themeItemLore, Particle.DustOptions dustOptions) {
        this.themeColor = themeColor;
        this.themeItemLore = themeItemLore;
        this.themeParticles = dustOptions;
    }

    public TextColor getTextColor() {
        return TextColor.color(
                themeColor.getColor().getRed(),
                themeColor.getColor().getGreen(),
                themeColor.getColor().getBlue()
        );
    }

    public Style getBold() {
        return Style.style().build()
                .color(getTextColor())
                .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)
                .decoration(TextDecoration.BOLD, TextDecoration.State.TRUE);
    }

    public Style getItalic() {
        return Style.style().build()
                .color(getTextColor())
                .decoration(TextDecoration.ITALIC, TextDecoration.State.TRUE);
    }

    public Style getUnderlined() {
        return Style.style().build()
                .color(getTextColor())
                .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)
                .decoration(TextDecoration.UNDERLINED, TextDecoration.State.TRUE);
    }

    public Style getPlain() {
        return Style.style().build()
                .color(getTextColor())
                .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }

}
