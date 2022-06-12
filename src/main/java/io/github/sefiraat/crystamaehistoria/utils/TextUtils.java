package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.StringUtils;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.WordUtils;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@UtilityClass
public class TextUtils {

    @Nonnull
    @ParametersAreNonnullByDefault
    public static String toTitleCase(String string) {
        final char[] delimiters = {' ', '_'};
        return WordUtils.capitalizeFully(string, delimiters).replace("_", " ");
    }

    @Nonnull
    public static String getLoreDivider() {
        ChatColor c = ThemeType.PASSIVE.getColor();
        return c + StringUtils.repeat("-", 25);
    }

}
