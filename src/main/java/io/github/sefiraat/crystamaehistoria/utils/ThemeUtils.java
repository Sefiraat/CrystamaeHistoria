package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.StoryRarity;
import io.github.sefiraat.crystamaehistoria.theme.ThemeElement;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("SpellCheckingInspection")
@UtilityClass
public final class ThemeUtils {

    /**
     * List of names to be given to ArmourStands, invisible but mods and Minimaps can see them :)
     */
    @Nonnull
    public static final List<String> EGG_NAMES = Arrays.asList(
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

    /**
     * Gets the Color for the provided theme
     *
     * @param themeType The {@link ThemeType} to get from the ThemeMap
     * @return Returns the {@link ChatColor} from the {@link io.github.sefiraat.crystamaehistoria.theme.ThemeElement}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static ChatColor getThemeColor(ThemeType themeType) {
        return getThemeElement(themeType).getThemeColor();
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ThemeElement getRarityTheme(StoryRarity storyRarity) {
        switch (storyRarity) {
            case COMMON:
                return getThemeElement(ThemeType.RTY_COMMON);
            case UNCOMMON:
                return getThemeElement(ThemeType.RTY_UNCOMMON);
            case RARE:
                return getThemeElement(ThemeType.RTY_RARE);
            case EPIC:
                return getThemeElement(ThemeType.RTY_EPIC);
            case MYTHICAL:
                return getThemeElement(ThemeType.RTY_MYTHICAL);
            case UNIQUE:
                return getThemeElement(ThemeType.RTY_UNIQUE);
            default:
                throw new IllegalStateException("Unexpected value: " + storyRarity);
        }
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ThemeElement getThemeElement(ThemeType themeType) {
        return CrystamaeHistoria.getThemeManager().getThemeMap().get(themeType);
    }

    /**
     * Gets the Lore String for the provided theme
     *
     * @param themeType The {@link ThemeType} to get from the ThemeMap
     * @return Returns the lore stringfrom the {@link io.github.sefiraat.crystamaehistoria.theme.ThemeElement}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String getThemeLoreLine(ThemeType themeType) {
        String lore = CrystamaeHistoria.getThemeManager().getThemeMap().get(themeType).getThemeItemLore();
        if (lore != null) {
            return lore;
        } else {
            throw new IllegalStateException("ThemeType not set up correctly");
        }
    }

    /**
     * Gets the Color for the provided theme
     *
     * @param themeType The {@link ThemeType} to get from the ThemeMap
     * @return Returns the {@link org.bukkit.Particle.DustOptions} from the {@link io.github.sefiraat.crystamaehistoria.theme.ThemeElement}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static Particle.DustOptions getThemeDustOptions(ThemeType themeType) {
        Particle.DustOptions dustOptions = CrystamaeHistoria.getThemeManager().getThemeMap().get(themeType).getThemeParticles();
        if (dustOptions != null) {
            return dustOptions;
        } else {
            throw new IllegalStateException("ThemeType not set up correctly");
        }
    }

    /**
     * Applies the theme color to a given string
     *
     * @param t The {@link ThemeType} to apply the color from
     * @param s The string to apply the color to
     * @return Returns the string provides preceeded by the color
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String applyThemeToString(ThemeType t, String s) {
        return getThemeColor(t) + s;
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     *
     * @param id   The ID for the new {@link SlimefunItemStack}
     * @param i    The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param t    The {@link ThemeType} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name The name to apply to the {@link SlimefunItemStack}
     * @param lore The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSlimefunItemStack(String id, ItemStack i, ThemeType t, String name, String... lore) {
        ChatColor passiveColor = getThemeColor(ThemeType.PASSIVE);
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(ThemeType.CLICK_INFO, getThemeLoreLine(t)));
        return new SlimefunItemStack(
                id,
                i,
                applyThemeToString(t, name),
                finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

    /**
     * converts given string to Title Case
     *
     * @param string The input string
     * @return A new {@link String} in Title Case
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String toTitleCase(String string) {
        final char[] delimiters = {' ', '_'};
        return WordUtils.capitalizeFully(string, delimiters).replace("_", " ");
    }

    @Nonnull
    public static String getRandomEggName() {
        int rnd = ThreadLocalRandom.current().nextInt(0, EGG_NAMES.size());
        return EGG_NAMES.get(rnd);
    }


}
