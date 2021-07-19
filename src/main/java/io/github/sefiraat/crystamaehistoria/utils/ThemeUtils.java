package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@UtilityClass
public final class ThemeUtils {

    /**
     * Gets the Color for the provided theme
     * @param themeType The {@link ThemeType} to get from the ThemeMap
     * @return Returns the {@link ChatColor} from the {@link io.github.sefiraat.crystamaehistoria.theme.ThemeElement}
     */
    public static ChatColor getThemeColor(ThemeType themeType) {
        return CrystamaeHistoria.inst().getThemeManager().getThemeMap().get(themeType).getThemeColor();
    }

    /**
     * Gets the Lore String for the provided theme
     * @param themeType The {@link ThemeType} to get from the ThemeMap
     * @return Returns the lore stringfrom the {@link io.github.sefiraat.crystamaehistoria.theme.ThemeElement}
     */
    public static String getThemeLoreLine(ThemeType themeType) {
        return CrystamaeHistoria.inst().getThemeManager().getThemeMap().get(themeType).getThemeItemLore();
    }

    /**
     * Gets the Color for the provided theme
     * @param themeType The {@link ThemeType} to get from the ThemeMap
     * @return Returns the {@link org.bukkit.Particle.DustOptions} from the {@link io.github.sefiraat.crystamaehistoria.theme.ThemeElement}
     */
    public static Particle.DustOptions getThemeDustOptions(ThemeType themeType) {
        return CrystamaeHistoria.inst().getThemeManager().getThemeMap().get(themeType).getThemeParticles();
    }

    /**
     * Applies the theme color to a given string
     * @param t The {@link ThemeType} to apply the color from
     * @param s The string to apply the color to
     * @return Returns the string provides preceeded by the color
     */
    public static String applyThemeToString(ThemeType t, String s) {
        return getThemeColor(t) + s;
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     * @param id The ID for the new {@link SlimefunItemStack}
     * @param i The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param t The {@link ThemeType} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name The name to apply to the {@link SlimefunItemStack}
     * @param lore The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    public static SlimefunItemStack themedSlimefunItemStack(String id, ItemStack i, ThemeType t, String name, String... lore) {
        ChatColor passiveColor = getThemeColor(ThemeType.PASSIVE);
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(ThemeType.CLICK_INFO, getThemeLoreLine(t)));
        CrystamaeHistoria.inst().getLogger().info(applyThemeToString(t, name));
        return new SlimefunItemStack(
                id,
                i,
                applyThemeToString(t, name),
                finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

}
