package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.StoryRarity;
import io.github.sefiraat.crystamaehistoria.theme.ThemeElement;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
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
    @Nonnull
    public static ChatColor getThemeColor(@Nonnull ThemeType themeType) {
        return getThemeElement(themeType).getThemeColor();
    }

    @Nonnull
    public static ThemeElement getRarityTheme(@Nonnull StoryRarity storyRarity) {
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

    @Nonnull
    public static ThemeElement getThemeElement(@Nonnull ThemeType themeType) {
        return CrystamaeHistoria.getThemeManager().getThemeMap().get(themeType);
    }

    /**
     * Gets the Lore String for the provided theme
     * @param themeType The {@link ThemeType} to get from the ThemeMap
     * @return Returns the lore stringfrom the {@link io.github.sefiraat.crystamaehistoria.theme.ThemeElement}
     */
    @Nonnull
    public static String getThemeLoreLine(@Nonnull ThemeType themeType) {
        String lore = CrystamaeHistoria.getThemeManager().getThemeMap().get(themeType).getThemeItemLore();
        if (lore != null) {
            return lore;
        } else {
            throw new IllegalStateException("ThemeType not set up correctly");
        }
    }

    /**
     * Gets the Color for the provided theme
     * @param themeType The {@link ThemeType} to get from the ThemeMap
     * @return Returns the {@link org.bukkit.Particle.DustOptions} from the {@link io.github.sefiraat.crystamaehistoria.theme.ThemeElement}
     */
    @Nonnull
    public static Particle.DustOptions getThemeDustOptions(@Nonnull ThemeType themeType) {
        Particle.DustOptions dustOptions = CrystamaeHistoria.getThemeManager().getThemeMap().get(themeType).getThemeParticles();
        if (dustOptions != null) {
            return dustOptions;
        } else {
            throw new IllegalStateException("ThemeType not set up correctly");
        }
    }

    /**
     * Applies the theme color to a given string
     * @param t The {@link ThemeType} to apply the color from
     * @param s The string to apply the color to
     * @return Returns the string provides preceeded by the color
     */
    @Nonnull
    public static String applyThemeToString(@NonNull ThemeType t, @Nonnull String s) {
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
    @NonNull
    public static SlimefunItemStack themedSlimefunItemStack(@NonNull String id, @NonNull ItemStack i, @NonNull ThemeType t, @NonNull String name, String... lore) {
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
    @NonNull
    public static String toTitleCase(@NonNull String string) {
        final char[] delimiters = { ' ', '_' };
        return WordUtils.capitalizeFully(string, delimiters).replace("_"," ");
    }

    /**
     * List of names to be given to ArmourStands, invisible but mods and Minimaps can see them :)
     */
    @NonNull
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

    @NonNull
    public static String getRandomEggName() {
        int rnd = ThreadLocalRandom.current().nextInt(0, EGG_NAMES.size());
        return EGG_NAMES.get(rnd);
    }


}
