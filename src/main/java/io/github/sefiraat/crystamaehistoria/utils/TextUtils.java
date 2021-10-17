package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

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
