package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import javax.annotation.ParametersAreNonnullByDefault;

@UtilityClass
public class TextUtils {

    @ParametersAreNonnullByDefault
    public static String getTitleCase(String string) {
        final char[] delimiters = { ' ', '_' };
        return WordUtils.capitalizeFully(string, delimiters).replace("_"," ");
    }

    public static String getLoreDivider() {
        ChatColor c = ThemeUtils.getThemeColor(ThemeType.PASSIVE);
        return  c + StringUtils.repeat("-", 25);
    }

}
