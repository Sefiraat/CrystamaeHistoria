package io.github.sefiraat.crystamaehistoria.utils.theme;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum ThemeType {
    WARNING(ChatColor.YELLOW, "Warning"),
    ERROR(ChatColor.RED, "Error"),
    NOTICE(ChatColor.WHITE, "Notice"),
    PASSIVE(ChatColor.GRAY, ""),
    SUCCESS(ChatColor.GREEN, "Success"),
    MAIN(ChatColor.of("#21588f"), "Crystamae Historia"),
    CLICK_INFO(ChatColor.of("#e4ed32"), "Click here"),
    CRAFTING(ChatColor.of("#dbcea9"), "Crafting Material"),
    CRYSTAL(ChatColor.of("#dbcea9"), "Crystal"),
    MACHINE(ChatColor.of("#3295a8"), "Machine"),
    MECHANISM(ChatColor.of("#3295a8"), "Mechanism"),
    CHEST(ChatColor.of("#b89b1c"), "Chest"),
    DROP(ChatColor.of("#bf307f"), "Rare Drop"),
    BASE(ChatColor.of("#9e9e9e"), "Base Resource"),
    MOLTEN_METAL(ChatColor.of("#21588f"), "Molten Metal"),
    LIQUID(ChatColor.of("#65dbb4"), "Liquid"),
    CAST(ChatColor.of("#ffe138"), "Cast"),
    PART(ChatColor.of("#42c8f5"), "Part"),
    TOOL(ChatColor.of("#c2fc03"), "Tool"),
    STAVE(ChatColor.of("#c2fc03"), "Stave"),
    ARMOUR(ChatColor.of("#c2fc03"), "Armour"),
    INFO(ChatColor.of("#21588f"), "Information"),
    MOD(ChatColor.of("#bf307f"), "Modification"),
    PROP(ChatColor.of("#bf307f"), "Material Trait"),
    SPELL(ChatColor.of("#bf307f"), "Spell"),
    MULTIBLOCK(ChatColor.of("#ba12af"), "Multiblock"),
    RARITY_COMMON(ChatColor.of("#dbdbdb"), "Common"),
    RARITY_UNCOMMON(ChatColor.of("#97d16b"), "Uncommon"),
    RARITY_RARE(ChatColor.of("#d1db5c"), "Rare"),
    RARITY_EPIC(ChatColor.of("#b355d9"), "Epic"),
    RARITY_MYTHICAL(ChatColor.of("#c42336"), "Mythical"),
    RARITY_UNIQUE(ChatColor.of("#b35f12"), "Unique"),
    TYPE_ELEMENTAL(ChatColor.of("#ba0000"), "Elemental"),
    TYPE_MECHANICAL(ChatColor.of("#ba5d00"), "Mechanical"),
    TYPE_ALCHEMICAL(ChatColor.of("#e5e81a"), "Alchemical"),
    TYPE_HISTORICAL(ChatColor.of("#24e81a"), "Historical"),
    TYPE_HUMAN(ChatColor.of("#201ae8"), "Human"),
    TYPE_ANIMAL(ChatColor.of("#701ae8"), "Animal"),
    TYPE_CELESTIAL(ChatColor.of("#ffffff"), "Celestial"),
    TYPE_VOID(ChatColor.of("#222222"), "Void"),
    TYPE_PHILOSOPHICAL(ChatColor.of("#4d4aa8"), "Philosophical");

    /**
     * List of names to be given to ArmourStands, invisible but mods and Minimaps can see them :)
     */
    @Nonnull
    protected static final List<String> EGG_NAMES = Arrays.asList(
        "TheBusyBiscuit",
        "Alessio",
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
        "Azak",
        "andrewandy",
        "EpicPlayer10",
        "GentlemanCheesy"
    );
    private final ChatColor color;
    private final String loreLine;
    private final Particle.DustOptions dustOptions;

    ThemeType(ChatColor color, String loreLine) {
        this(color, loreLine, null);
    }
    
    ThemeType(ChatColor color, String loreLine, @Nullable Particle.DustOptions dustOptions) {
        this.color = color;
        this.loreLine = loreLine;
        this.dustOptions = dustOptions;
    }
    
    /**
     * Applies the theme color to a given string
     *
     * @param themeType The {@link ThemeType} to apply the color from
     * @param string The string to apply the color to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String applyThemeToString(ThemeType themeType, String string) {
        return themeType.getColor() + string;
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     *
     * @param id   The ID for the new {@link SlimefunItemStack}
     * @param itemStack    The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param themeType    The {@link ThemeType} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name The name to apply to the {@link SlimefunItemStack}
     * @param lore The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themeStack(String id, ItemStack itemStack, ThemeType themeType, String name, String... lore) {
        ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(ThemeType.CLICK_INFO, themeType.getLoreLine()));
        return new SlimefunItemStack(
            id,
            itemStack,
            ThemeType.applyThemeToString(themeType, name),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ThemeType getByRarity(StoryRarity storyRarity) {
        switch (storyRarity) {
            case COMMON:
                return RARITY_COMMON;
            case UNCOMMON:
                return RARITY_UNCOMMON;
            case RARE:
                return RARITY_RARE;
            case EPIC:
                return RARITY_EPIC;
            case MYTHICAL:
                return RARITY_MYTHICAL;
            case UNIQUE:
                return RARITY_UNIQUE;
            default:
                throw new IllegalStateException("Unexpected value: " + storyRarity);
        }
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ThemeType getByType(StoryType storyType) {
        switch (storyType) {
            case ELEMENTAL:
                return TYPE_ELEMENTAL;
            case MECHANICAL:
                return TYPE_MECHANICAL;
            case ALCHEMICAL:
                return TYPE_ALCHEMICAL;
            case HISTORICAL:
                return TYPE_HISTORICAL;
            case HUMAN:
                return TYPE_HUMAN;
            case ANIMAL:
                return TYPE_ANIMAL;
            case CELESTIAL:
                return TYPE_CELESTIAL;
            case VOID:
                return TYPE_VOID;
            case PHILOSOPHICAL:
                return TYPE_PHILOSOPHICAL;
            default:
                throw new IllegalStateException("Unexpected value: " + storyType);
        }
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
