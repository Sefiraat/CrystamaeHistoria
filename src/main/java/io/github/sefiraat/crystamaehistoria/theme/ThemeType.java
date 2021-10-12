package io.github.sefiraat.crystamaehistoria.theme;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
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
    WARNING(ChatColor.YELLOW, "Warning", null),
    ERROR(ChatColor.RED, "Error", null),
    NOTICE(ChatColor.WHITE, "Notice", null),
    PASSIVE(ChatColor.GRAY, "", null),
    SUCCESS(ChatColor.GREEN, "Success", null),
    MAIN(ChatColor.of("#21588f"), "Crystamae Historia", null),
    CLICK_INFO(ChatColor.of("#e4ed32"), "Click here", null),
    CRAFTING(ChatColor.of("#dbcea9"), "Crafting Material", null),
    CRYSTAL(ChatColor.of("#dbcea9"), "Crystal", null),
    MACHINE(ChatColor.of("#3295a8"), "Machine", null),
    MECHANISM(ChatColor.of("#3295a8"), "Mechanism", null),
    CHEST(ChatColor.of("#b89b1c"), "Chest", null),
    DROP(ChatColor.of("#bf307f"), "Rare Drop", null),
    BASE(ChatColor.of("#9e9e9e"), "Base Resource", null),
    MOLTEN_METAL(ChatColor.of("#21588f"), "Molten Metal", null),
    LIQUID(ChatColor.of("#65dbb4"), "Liquid", null),
    CAST(ChatColor.of("#ffe138"), "Cast", null),
    PART(ChatColor.of("#42c8f5"), "Part", null),
    TOOL(ChatColor.of("#c2fc03"), "Tool", null),
    STAVE(ChatColor.of("#c2fc03"), "Stave", null),
    ARMOUR(ChatColor.of("#c2fc03"), "Armour", null),
    INFO(ChatColor.of("#21588f"), "Information", null),
    MOD(ChatColor.of("#bf307f"), "Modification", null),
    PROP(ChatColor.of("#bf307f"), "Material Trait", null),
    SPELL(ChatColor.of("#bf307f"), "Spell", null),
    MULTIBLOCK(ChatColor.of("#ba12af"), "Multiblock", null),
    RARITY_COMMON(ChatColor.of("#dbdbdb"), "Common", null),
    RARITY_UNCOMMON(ChatColor.of("#97d16b"), "Uncommon", null),
    RARITY_RARE(ChatColor.of("#d1db5c"), "Rare", null),
    RARITY_EPIC(ChatColor.of("#b355d9"), "Epic", null),
    RARITY_MYTHICAL(ChatColor.of("#c42336"), "Mythical", null),
    RARITY_UNIQUE(ChatColor.of("#b35f12"), "Unique", null),
    TYPE_ELEMENTAL(ChatColor.of("#ba0000"), "Elemental", null),
    TYPE_MECHANICAL(ChatColor.of("#ba5d00"), "Mechanical", null),
    TYPE_ALCHEMICAL(ChatColor.of("#e5e81a"), "Alchemical", null),
    TYPE_HISTORICAL(ChatColor.of("#24e81a"), "Historical", null),
    TYPE_HUMAN(ChatColor.of("#201ae8"), "Human", null),
    TYPE_ANIMAL(ChatColor.of("#701ae8"), "Animal", null),
    TYPE_CELESTIAL(ChatColor.of("#ffffff"), "Celestial", null),
    TYPE_VOID(ChatColor.of("#000000"), "Void", null),
    TYPE_PHILOSOPHICAL(ChatColor.of("#4d4aa8"), "Philosophical", null);

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

    ThemeType(ChatColor color, String loreLine, @Nullable Particle.DustOptions dustOptions) {
        this.color = color;
        this.loreLine = loreLine;
        this.dustOptions = dustOptions;
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
        return t.getColor() + s;
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
    public static SlimefunItemStack themeStack(String id, ItemStack i, ThemeType t, String name, String... lore) {
        ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(ThemeType.CLICK_INFO, t.getLoreLine()));
        return new SlimefunItemStack(
            id,
            i,
            ThemeType.applyThemeToString(t, name),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     *
     * @param id   The ID for the new {@link SlimefunItemStack}
     * @param i    The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param spellType The {@link SpellType} used
     * @param lore The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themeSpellStack(String id, ItemStack i, SpellType spellType, String... lore) {
        ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(ThemeType.CLICK_INFO.getColor() + "Crysta Cost: " + ThemeType.NOTICE.getColor() + spellType.getSpell().getSpellCore().getCrystaCost());
        finalLore.add("");
        finalLore.add(applyThemeToString(ThemeType.CLICK_INFO, "Spell"));
        return new SlimefunItemStack(
            id,
            i,
            ThemeType.applyThemeToString(ThemeType.SPELL, toTitleCase(spellType.getId())),
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
