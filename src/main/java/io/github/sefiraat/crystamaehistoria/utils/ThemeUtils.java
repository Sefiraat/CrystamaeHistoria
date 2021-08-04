package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.StoryRarity;
import io.github.sefiraat.crystamaehistoria.theme.ThemeElement;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("SpellCheckingInspection")
@UtilityClass
public final class ThemeUtils {

    /**
     * Gets the Color for the provided theme
     * @param themeType The {@link ThemeType} to get from the ThemeMap
     * @return Returns the {@link ChatColor} from the {@link io.github.sefiraat.crystamaehistoria.theme.ThemeElement}
     */
    public static ChatColor getThemeColor(ThemeType themeType) {
        return getThemeElement(themeType).getThemeColor();
    }

    public static ThemeElement getRarityTheme(StoryRarity storyRarity) {
        switch (storyRarity) {
            case Common:
                return getThemeElement(ThemeType.RTY_COMMON);
            case Uncommon:
                return getThemeElement(ThemeType.RTY_UNCOMMON);
            case Rare:
                return getThemeElement(ThemeType.RTY_RARE);
            case Epic:
                return getThemeElement(ThemeType.RTY_EPIC);
            case Mythical:
                return getThemeElement(ThemeType.RTY_MYTHICAL);
            default:
                throw new IllegalStateException("Unexpected value: " + storyRarity);
        }
    }

    public static ThemeElement getThemeElement(ThemeType themeType) {
        return CrystamaeHistoria.inst().getThemeManager().getThemeMap().get(themeType);
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

    /**
     * converts given string to Title Case
     * @param string The input string
     * @return A new {@link String} in Title Case
     */
    public static String toTitleCase(String string) {
        final char[] delimiters = { ' ', '_' };
        return WordUtils.capitalizeFully(string, delimiters).replace("_"," ");
    }

    /**
     * List of names to be given to ArmourStands, invisible but mods and Minimaps can see them :)
     */
    public static List<String> EGG_NAMES = Arrays.asList(
            "TheBusyBiscuit",
            "Walshy",
            "Jeff",
            "Seggan",
            "BOOMER_1",
            "svr333",
            "variananora",
            "ProfElements",
            "Riley",
            "FluffyBear",
            "GallowsDove",
            "Apeiros",
            "Martin",
            "Bunnky",
            "ReasonFoundDecoy",
            "Oah",
            "Azak"
    );

    public static String getRandomEggName() {
        int rnd = ThreadLocalRandom.current().nextInt(0, EGG_NAMES.size());
        return EGG_NAMES.get(rnd);
    }


}
