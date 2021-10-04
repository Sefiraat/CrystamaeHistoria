package io.github.sefiraat.crystamaehistoria.theme;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Particle;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@Getter
public class ThemeElement {

    @Nonnull
    private final ChatColor themeColor;
    @Nullable
    private final String themeItemLore;
    @Nullable
    private final Particle.DustOptions themeParticles;

    @ParametersAreNonnullByDefault
    public ThemeElement(ChatColor themeColor) {
        this.themeColor = themeColor;
        this.themeItemLore = null;
        this.themeParticles = null;
    }

    @ParametersAreNonnullByDefault
    public ThemeElement(ChatColor themeColor, String themeItemLore) {
        this.themeColor = themeColor;
        this.themeItemLore = themeItemLore;
        this.themeParticles = null;
    }

    @ParametersAreNonnullByDefault
    public ThemeElement(ChatColor themeColor, String themeItemLore, Particle.DustOptions dustOptions) {
        this.themeColor = themeColor;
        this.themeItemLore = themeItemLore;
        this.themeParticles = dustOptions;
    }
}
