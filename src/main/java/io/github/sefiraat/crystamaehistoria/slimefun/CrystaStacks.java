package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.MagicPaintbrush;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Skulls;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.text.MessageFormat;

@UtilityClass
public class CrystaStacks {

    // Materials
    public static final SlimefunItemStack CRYSTAL_BLANK;
    public static final SlimefunItemStack CRYSTAL_POLYCHROMATIC;
    public static final SlimefunItemStack CRYSTAL_KALEIDOSCOPIC;
    public static final SlimefunItemStack CRYSTAL_MOTLEY;
    public static final SlimefunItemStack CRYSTAL_PRISMATIC;
    public static final SlimefunItemStack AMALGAMATE_DUST_COMMON;
    public static final SlimefunItemStack AMALGAMATE_DUST_UNCOMMON;
    public static final SlimefunItemStack AMALGAMATE_DUST_RARE;
    public static final SlimefunItemStack AMALGAMATE_DUST_EPIC;
    public static final SlimefunItemStack AMALGAMATE_DUST_MYTHICAL;
    public static final SlimefunItemStack AMALGAMATE_DUST_UNIQUE;
    public static final SlimefunItemStack AMALGAMATE_INGOT_COMMON;
    public static final SlimefunItemStack AMALGAMATE_INGOT_UNCOMMON;
    public static final SlimefunItemStack AMALGAMATE_INGOT_RARE;
    public static final SlimefunItemStack AMALGAMATE_INGOT_EPIC;
    public static final SlimefunItemStack AMALGAMATE_INGOT_MYTHICAL;
    public static final SlimefunItemStack AMALGAMATE_INGOT_UNIQUE;
    public static final SlimefunItemStack ARCANE_SIGIL;
    public static final SlimefunItemStack IMBUED_GLASS;
    public static final SlimefunItemStack UNCANNY_PEARL;
    public static final SlimefunItemStack GILDED_PEARL;
    public static final SlimefunItemStack BASIC_FIBRES;
    public static final SlimefunItemStack POWDERED_ESSENCE;
    public static final SlimefunItemStack MAGICAL_MILK;

    // Runes
    public static final SlimefunItemStack RUNE_A;
    public static final SlimefunItemStack RUNE_B;
    public static final SlimefunItemStack RUNE_C;
    public static final SlimefunItemStack RUNE_D;
    public static final SlimefunItemStack RUNE_E;
    public static final SlimefunItemStack RUNE_F;
    public static final SlimefunItemStack RUNE_G;
    public static final SlimefunItemStack RUNE_H;
    public static final SlimefunItemStack RUNE_I;
    public static final SlimefunItemStack RUNE_J;
    public static final SlimefunItemStack RUNE_K;
    public static final SlimefunItemStack RUNE_L;
    public static final SlimefunItemStack RUNE_M;
    public static final SlimefunItemStack RUNE_N;
    public static final SlimefunItemStack RUNE_O;
    public static final SlimefunItemStack RUNE_P;
    public static final SlimefunItemStack RUNE_Q;
    public static final SlimefunItemStack RUNE_R;
    public static final SlimefunItemStack RUNE_S;
    public static final SlimefunItemStack RUNE_T;
    public static final SlimefunItemStack RUNE_U;
    public static final SlimefunItemStack RUNE_V;
    public static final SlimefunItemStack RUNE_W;
    public static final SlimefunItemStack RUNE_X;
    public static final SlimefunItemStack RUNE_Y;
    public static final SlimefunItemStack RUNE_Z;


    // Artistic
    public static final SlimefunItemStack PAINT_BRUSH_BLACK_100;
    public static final SlimefunItemStack PAINT_BRUSH_BLUE_100;
    public static final SlimefunItemStack PAINT_BRUSH_BROWN_100;
    public static final SlimefunItemStack PAINT_BRUSH_CYAN_100;
    public static final SlimefunItemStack PAINT_BRUSH_GRAY_100;
    public static final SlimefunItemStack PAINT_BRUSH_GREEN_100;
    public static final SlimefunItemStack PAINT_BRUSH_LIGHT_BLUE_100;
    public static final SlimefunItemStack PAINT_BRUSH_LIGHT_GRAY_100;
    public static final SlimefunItemStack PAINT_BRUSH_LIME_100;
    public static final SlimefunItemStack PAINT_BRUSH_MAGENTA_100;
    public static final SlimefunItemStack PAINT_BRUSH_ORANGE_100;
    public static final SlimefunItemStack PAINT_BRUSH_PINK_100;
    public static final SlimefunItemStack PAINT_BRUSH_PURPLE_100;
    public static final SlimefunItemStack PAINT_BRUSH_RED_100;
    public static final SlimefunItemStack PAINT_BRUSH_WHITE_100;
    public static final SlimefunItemStack PAINT_BRUSH_YELLOW_100;
    public static final SlimefunItemStack PAINT_BRUSH_BLACK_1000;
    public static final SlimefunItemStack PAINT_BRUSH_BLUE_1000;
    public static final SlimefunItemStack PAINT_BRUSH_BROWN_1000;
    public static final SlimefunItemStack PAINT_BRUSH_CYAN_1000;
    public static final SlimefunItemStack PAINT_BRUSH_GRAY_1000;
    public static final SlimefunItemStack PAINT_BRUSH_GREEN_1000;
    public static final SlimefunItemStack PAINT_BRUSH_LIGHT_BLUE_1000;
    public static final SlimefunItemStack PAINT_BRUSH_LIGHT_GRAY_1000;
    public static final SlimefunItemStack PAINT_BRUSH_LIME_1000;
    public static final SlimefunItemStack PAINT_BRUSH_MAGENTA_1000;
    public static final SlimefunItemStack PAINT_BRUSH_ORANGE_1000;
    public static final SlimefunItemStack PAINT_BRUSH_PINK_1000;
    public static final SlimefunItemStack PAINT_BRUSH_PURPLE_1000;
    public static final SlimefunItemStack PAINT_BRUSH_RED_1000;
    public static final SlimefunItemStack PAINT_BRUSH_WHITE_1000;
    public static final SlimefunItemStack PAINT_BRUSH_YELLOW_1000;
    public static final SlimefunItemStack PAINT_BRUSH_INFINITE;
    public static final SlimefunItemStack MYSTICAL_PIGMENTATO;
    public static final SlimefunItemStack MYSTICAL_TINTANNO;
    public static final SlimefunItemStack BODY_STAND;
    public static final SlimefunItemStack MIND_STAND;
    public static final SlimefunItemStack SOUL_STAND;
    public static final SlimefunItemStack MYSTICAL_ATTITUDINIZER;
    public static final SlimefunItemStack IMBUED_STAND;
    public static final SlimefunItemStack POSE_CHANGER;
    public static final SlimefunItemStack POSE_CLONER;

    // Exalted
    public static final SlimefunItemStack EXALTED_BEACON;
    public static final SlimefunItemStack EXALTED_BAELFIRE;
    public static final SlimefunItemStack FERTILITY_PHARO;
    public static final SlimefunItemStack FERTILITY_TOTEM;
    public static final SlimefunItemStack EXALTED_HARVESTER;
    public static final SlimefunItemStack EXALTED_AGRONOMIST;
    public static final SlimefunItemStack EXALTED_DAWN;
    public static final SlimefunItemStack EXALTED_DUSK;
    public static final SlimefunItemStack EXALTED_SUN;
    public static final SlimefunItemStack EXALTED_STORM;
    public static final SlimefunItemStack EXALTED_SEA_BREEZE;

    // Gadgets
    public static final SlimefunItemStack ABSTRACTION_LAMP;
    public static final SlimefunItemStack DISPERSION_LAMP;
    public static final SlimefunItemStack EXODUS_LAMP;
    public static final SlimefunItemStack INVERSION_VACUUM;
    public static final SlimefunItemStack ANTIPODAL_VACUUM;
    public static final SlimefunItemStack COUNTERPOLE_VACUUM;
    public static final SlimefunItemStack CURSED_EARTH;
    public static final SlimefunItemStack DREADFUL_DIRT;
    public static final SlimefunItemStack SOULFILLED_SOIL;
    public static final SlimefunItemStack SEARING_PLATE;
    public static final SlimefunItemStack DOOMED_PLATE;
    public static final SlimefunItemStack EVISCERATING_PLATE;
    public static final SlimefunItemStack SHREDDING_PLATE;
    public static final SlimefunItemStack TRAP_PLATE;
    public static final SlimefunItemStack EXP_COLLECTOR_BASIC;
    public static final SlimefunItemStack EXP_COLLECTOR_INFUSED;
    public static final SlimefunItemStack EXP_COLLECTOR_ARCANE;
    public static final SlimefunItemStack ENDER_INHIBITOR_BASIC;
    public static final SlimefunItemStack ENDER_INHIBITOR_ADVANCED;
    public static final SlimefunItemStack MOB_CANDLE_DIM;
    public static final SlimefunItemStack MOB_CANDLE_BRIGHT;
    public static final SlimefunItemStack MOB_CANDLE_SCINTILLATING;
    public static final SlimefunItemStack MOB_CANDLE_CORUSCATING;
    public static final SlimefunItemStack MYSTERIOUS_POTTED_PLANT;
    public static final SlimefunItemStack MYSTERIOUS_PLANT;
    public static final SlimefunItemStack MYSTERIOUS_GLASS;
    public static final SlimefunItemStack MYSTERIOUS_WOOL;
    public static final SlimefunItemStack MYSTERIOUS_TERRACOTTA;
    public static final SlimefunItemStack MYSTERIOUS_GLAZED_TERRACOTTA;
    public static final SlimefunItemStack MYSTERIOUS_CONCRETE;
    public static final SlimefunItemStack GREEN_HOUSE_GLASS;
    public static final SlimefunItemStack GREEN_HOUSE_GLASS_FOCUSED;
    public static final SlimefunItemStack GREEN_HOUSE_GLASS_MAGNIFYING;
    public static final SlimefunItemStack TROPHY_STAND;
    public static final SlimefunItemStack EXALTATION_STAND;
    public static final SlimefunItemStack WAYSTONE;
    public static final SlimefunItemStack ANGEL_BLOCK;
    public static final SlimefunItemStack PHILOSOPHERS_SPRAY;
    public static final SlimefunItemStack GLASS_OF_MILK;
    public static final SlimefunItemStack FRAGMENTED_VOID;
    public static final SlimefunItemStack SHATTERED_VOID;

    // Mechanisms
    public static final SlimefunItemStack CHRONICLER_PANEL_1;
    public static final SlimefunItemStack CHRONICLER_PANEL_2;
    public static final SlimefunItemStack CHRONICLER_PANEL_3;
    public static final SlimefunItemStack CHRONICLER_PANEL_4;
    public static final SlimefunItemStack CHRONICLER_PANEL_5;
    public static final SlimefunItemStack REALISATION_ALTAR_1;
    public static final SlimefunItemStack REALISATION_ALTAR_2;
    public static final SlimefunItemStack REALISATION_ALTAR_3;
    public static final SlimefunItemStack REALISATION_ALTAR_4;
    public static final SlimefunItemStack REALISATION_ALTAR_5;
    public static final SlimefunItemStack LIQUEFACTION_BASIN_1;
    public static final SlimefunItemStack LIQUEFACTION_BASIN_2;
    public static final SlimefunItemStack LIQUEFACTION_BASIN_3;
    public static final SlimefunItemStack LIQUEFACTION_BASIN_4;
    public static final SlimefunItemStack LIQUEFACTION_BASIN_5;
    public static final SlimefunItemStack STAVE_CONFIGURATOR;
    public static final SlimefunItemStack PRISMATIC_GILDER;

    // Tools
    public static final SlimefunItemStack INERT_PLATE;
    public static final SlimefunItemStack CHARGED_PLATE;
    public static final SlimefunItemStack STAVE_BASIC;
    public static final SlimefunItemStack STAVE_ADVANCED;
    public static final SlimefunItemStack STAVE_ARCANE;
    public static final SlimefunItemStack REFRACTING_LENS;
    public static final SlimefunItemStack THAUMATURGIC_SALTS;
    public static final SlimefunItemStack CRYSTA_RECALL_LATTICE;
    public static final SlimefunItemStack EPHEMERAL_CRAFT_TABLE;
    public static final SlimefunItemStack EPHEMERAL_WORKBENCH;
    public static final SlimefunItemStack LUMINESCENCE_SCOOP;
    public static final SlimefunItemStack BRILLIANCE_SCOOP;
    public static final SlimefunItemStack LUSTRE_SCOOP;
    public static final SlimefunItemStack RADIANCE_SCOOP;
    public static final SlimefunItemStack CONNECTING_COMPASS;
    public static final SlimefunItemStack SPIRITUAL_SILKEN;
    public static final SlimefunItemStack INCORPOREAL_SILKEN;
    public static final SlimefunItemStack SIMPLE_DISPLACER;
    public static final SlimefunItemStack ARCANE_DISPLACER;
    public static final SlimefunItemStack SPONGE_BALMY;
    public static final SlimefunItemStack SPONGE_SEARING;
    public static final SlimefunItemStack SPONGE_SUPER_MASSIVE;
    public static final SlimefunItemStack SLEEPING_BAG;
    public static final SlimefunItemStack DISPLACED_VOID;
    public static final SlimefunItemStack SATCHEL_1;
    public static final SlimefunItemStack SATCHEL_2;
    public static final SlimefunItemStack SATCHEL_3;
    public static final SlimefunItemStack SATCHEL_4;
    public static final SlimefunItemStack SATCHEL_5;
    public static final SlimefunItemStack SATCHEL_6;
    public static final SlimefunItemStack CARGO_COVER;
    public static final SlimefunItemStack ENERGY_NET_COVER;
    public static final SlimefunItemStack NETWORKS_COVER;

    // Uniques
    public static final SlimefunItemStack STORY_TROPHY;
    public static final SlimefunItemStack SPELL_TROPHY;
    public static final SlimefunItemStack CHRISTMAS_TROPHY;
    public static final SlimefunItemStack VALENTINES_TROPHY;
    public static final SlimefunItemStack BIRTHDAY_TROPHY_CHEESY;
    public static final SlimefunItemStack BIRTHDAY_TROPHY_BWHITE;
    public static final SlimefunItemStack BIRTHDAY_TROPHY_DECOY;
    public static final SlimefunItemStack BIRTHDAY_TROPHY_ODDISH;

    // Recipe Types
    public static final ItemStack RECIPE_TYPE_LIQUEFACTION_CRAFTING;
    public static final ItemStack RECIPE_TYPE_LIQUEFACTION_SPELL;
    public static final ItemStack RECIPE_TYPE_NETHER_DRAINING;
    public static final ItemStack RECIPE_TYPE_REALISATION_ALTAR_NORMAL;
    public static final ItemStack RECIPE_TYPE_REALISATION_ALTAR_SIGIL;

    static {

        // Materials

        CRYSTAL_BLANK = ThemeType.themedSlimefunItemStack(
            "CRY_CRYSTAL_BLANK",
            Skulls.CRYSTAL_CLEAR.getPlayerHead(),
            ThemeType.CRAFTING,
            "Blank Crystal",
            "A Crystal with all of it's Crysta drained."
        );

        CRYSTAL_POLYCHROMATIC = ThemeType.themedSlimefunItemStack(
            "CRY_CRYSTAL_POLYCHROMATIC",
            Skulls.CRYSTAL_POLYCHROME.getPlayerHead(),
            ThemeType.CRAFTING,
            "Polychromatic Crystal",
            "A Crystal shell with multiple crysta",
            "types infused within."
        );

        CRYSTAL_KALEIDOSCOPIC = ThemeType.themedSlimefunItemStack(
            "CRY_CRYSTAL_KALEIDOSCOPIC",
            Skulls.CRYSTAL_KALEIDOSCOPIC.getPlayerHead(),
            ThemeType.CRAFTING,
            "Kaleidoscopic Crystal",
            "A Crystal shell with multiple crysta",
            "types infused within."
        );

        CRYSTAL_MOTLEY = ThemeType.themedSlimefunItemStack(
            "CRY_CRYSTAL_MOTLEY",
            Skulls.CRYSTAL_MOTLEY.getPlayerHead(),
            ThemeType.CRAFTING,
            "Motley Crystal",
            "A Crystal shell with multiple crysta",
            "types infused within."
        );

        CRYSTAL_PRISMATIC = ThemeType.themedSlimefunItemStack(
            "CRY_CRYSTAL_PRISMATIC",
            Skulls.CRYSTAL_PRISMATIC.getPlayerHead(),
            ThemeType.CRAFTING,
            "Prismatic Crystal",
            "This crystal has so much magic it's",
            "near to bursting."
        );

        AMALGAMATE_DUST_COMMON = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_DUST_COMMON",
            new ItemStack(Material.GLOWSTONE_DUST),
            ThemeType.CRAFTING,
            "Amalgamate Dust (Common)",
            "A dust combining all magic types."
        );

        AMALGAMATE_DUST_UNCOMMON = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_DUST_UNCOMMON",
            new ItemStack(Material.GLOWSTONE_DUST),
            ThemeType.CRAFTING,
            "Amalgamate Dust (Uncommon)",
            "A dust combining all magic types."
        );

        AMALGAMATE_DUST_RARE = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_DUST_RARE",
            new ItemStack(Material.GLOWSTONE_DUST),
            ThemeType.CRAFTING,
            "Amalgamate Dust (Rare)",
            "A dust combining all magic types."
        );

        AMALGAMATE_DUST_EPIC = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_DUST_EPIC",
            new ItemStack(Material.GLOWSTONE_DUST),
            ThemeType.CRAFTING,
            "Amalgamate Dust (Epic)",
            "A dust combining all magic types."
        );

        AMALGAMATE_DUST_MYTHICAL = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_DUST_MYTHICAL",
            new ItemStack(Material.GLOWSTONE_DUST),
            ThemeType.CRAFTING,
            "Amalgamate Dust (Mythical)",
            "A dust combining all magic types."
        );

        AMALGAMATE_DUST_UNIQUE = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_DUST_UNIQUE",
            new ItemStack(Material.GLOWSTONE_DUST),
            ThemeType.CRAFTING,
            "Amalgamate Dust (Unique)",
            "A dust combining all magic types."
        );

        AMALGAMATE_INGOT_COMMON = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_INGOT_COMMON",
            new ItemStack(Material.GOLD_INGOT),
            ThemeType.CRAFTING,
            "Amalgamate Ingot (Common)",
            "An ingot crafted of pure magics."
        );

        AMALGAMATE_INGOT_UNCOMMON = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_INGOT_UNCOMMON",
            new ItemStack(Material.GOLD_INGOT),
            ThemeType.CRAFTING,
            "Amalgamate Ingot (Uncommon)",
            "An ingot crafted of pure magics."
        );

        AMALGAMATE_INGOT_RARE = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_INGOT_RARE",
            new ItemStack(Material.GOLD_INGOT),
            ThemeType.CRAFTING,
            "Amalgamate Ingot (Rare)",
            "An ingot crafted of pure magics."
        );

        AMALGAMATE_INGOT_EPIC = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_INGOT_EPIC",
            new ItemStack(Material.GOLD_INGOT),
            ThemeType.CRAFTING,
            "Amalgamate Ingot (Epic)",
            "An ingot crafted of pure magics."
        );

        AMALGAMATE_INGOT_MYTHICAL = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_INGOT_MYTHICAL",
            new ItemStack(Material.GOLD_INGOT),
            ThemeType.CRAFTING,
            "Amalgamate Ingot (Mythical)",
            "An ingot crafted of pure magics."
        );

        AMALGAMATE_INGOT_UNIQUE = ThemeType.themedSlimefunItemStack(
            "CRY_AMALGAMATE_INGOT_UNIQUE",
            new ItemStack(Material.GOLD_INGOT),
            ThemeType.CRAFTING,
            "Amalgamate Ingot (Unique)",
            "An ingot crafted of pure magics."
        );

        ARCANE_SIGIL = ThemeType.themedSlimefunItemStack(
            "CRY_ARCANE_SIGIL",
            new ItemStack(Material.GLOW_LICHEN),
            ThemeType.CRAFTING,
            "Arcane Sigil",
            "An ancient mark left in the world",
            "by previous Crysta users."
        );

        IMBUED_GLASS = ThemeType.themedSlimefunItemStack(
            "CRY_IMBUED_GLASS",
            new ItemStack(Material.GLASS_PANE),
            ThemeType.CRAFTING,
            "Imbued Glass",
            "Glass imbued with Crysta that has",
            "some strange properties."
        );

        UNCANNY_PEARL = ThemeType.themedSlimefunItemStack(
            "CRY_UNCANNY_PEARL",
            new ItemStack(Material.ENDER_PEARL),
            ThemeType.CRAFTING,
            "Uncanny Pearl",
            "The internal resonance of this pearl",
            "has been quelled using crysta."
        );

        GILDED_PEARL = ThemeType.themedSlimefunItemStack(
            "CRY_GILDED_PEARL",
            new ItemStack(Material.ENDER_PEARL),
            ThemeType.CRAFTING,
            "Gilded Pearl",
            "With the pearl quelled, it can",
            "be safely gilded and used in certain",
            "crafts."
        );

        BASIC_FIBRES = ThemeType.themedSlimefunItemStack(
            "CRY_BASIC_FIBRES",
            new ItemStack(Material.DRIED_KELP),
            ThemeType.CRAFTING,
            "Basic Fibres",
            "Very basic and coarse fibres."
        );

        POWDERED_ESSENCE = ThemeType.themedSlimefunItemStack(
            "CRY_POWDERED_ESSENCE",
            new ItemStack(Material.WHITE_DYE),
            ThemeType.CRAFTING,
            "Powdered Essence",
            "A refined crafting material.",
            "Can be used as bone meal.",
            "",
            ChatColor.YELLOW + "250 Uses " + ChatColor.GRAY + "left"
        );

        MAGICAL_MILK = ThemeType.themedSlimefunItemStack(
            "CRY_MAGICAL_MILK_POWDER",
            new ItemStack(Material.SUGAR),
            ThemeType.CRAFTING,
            "Magical Milk Powder",
            "This powdered milk has something about it..."
        );

        // Runes

        RUNE_A = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_BEAST",
            Skulls.RUNE_A.getPlayerHead(),
            ThemeType.RUNE,
            "Beast Rune",
            "Unbridled passion and drive. The howl",
            "of the wolf echos from within."
        );

        RUNE_B = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_BEGINNING",
            Skulls.RUNE_B.getPlayerHead(),
            ThemeType.RUNE,
            "Rune of Beginning",
            "Chaos formed into order. The swirling",
            "vortex of the unknown made still."
        );

        RUNE_C = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_MOON",
            Skulls.RUNE_C.getPlayerHead(),
            ThemeType.RUNE,
            "Moon Rune",
            "The clash of both compassion and",
            "sheer destruction rage within."
        );

        RUNE_D = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_GATE",
            Skulls.RUNE_D.getPlayerHead(),
            ThemeType.RUNE,
            "Gate Rune",
            "Threads connecting worlds all pass",
            "through the magic within these runes."
        );

        RUNE_E = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_TRUE_EARTH",
            Skulls.RUNE_E.getPlayerHead(),
            ThemeType.RUNE,
            "True Earth Rune",
            "The single point of power for",
            "all earth magic."
        );

        RUNE_F = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_CHANGE",
            Skulls.RUNE_F.getPlayerHead(),
            ThemeType.RUNE,
            "Rune of Change",
            "All unknowns and futures of the universe",
            "are kept in harmony within."
        );

        RUNE_G = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_NIGHT",
            Skulls.RUNE_G.getPlayerHead(),
            ThemeType.RUNE,
            "Night Rune",
            "The magic running through this rune",
            "is the same that allows the undead to rise."
        );

        RUNE_H = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_BLACK",
            Skulls.RUNE_H.getPlayerHead(),
            ThemeType.RUNE,
            "Black Rune",
            "The power to control minds and wills",
            "held within."
        );

        RUNE_I = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_TRUE_HOLY",
            Skulls.RUNE_I.getPlayerHead(),
            ThemeType.RUNE,
            "True Holy Rune",
            "All inner light stems from this",
            "magical source."
        );

        RUNE_J = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_DRAGON",
            Skulls.RUNE_J.getPlayerHead(),
            ThemeType.RUNE,
            "Dragon Rune",
            "The existence of dragons both created",
            "and was caused by this magic. A paradox that",
            "will never be unwound."
        );

        RUNE_K = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_TRUE_WATER",
            Skulls.RUNE_K.getPlayerHead(),
            ThemeType.RUNE,
            "True Water Rune",
            "The beginning of all flowing freely."
        );

        RUNE_L = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_SOVEREIGN",
            Skulls.RUNE_L.getPlayerHead(),
            ThemeType.RUNE,
            "Sovereign Rune",
            "Order through rule, protection from",
            "one, protection for all."
        );

        RUNE_M = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_SUN",
            Skulls.RUNE_M.getPlayerHead(),
            ThemeType.RUNE,
            "Sun Rune",
            "Bringer of prosperity and life but",
            "at a toll for all."
        );

        RUNE_N = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_DAWN",
            Skulls.RUNE_N.getPlayerHead(),
            ThemeType.RUNE,
            "Dawn Rune",
            "The bringer of new, of regeneration",
            "and renewal."
        );

        RUNE_O = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_TWILIGHT",
            Skulls.RUNE_O.getPlayerHead(),
            ThemeType.RUNE,
            "Twilight Rune",
            "The removal of old, the bringer of",
            "rest and recuperation."
        );

        RUNE_P = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_TRUE_FIRE",
            Skulls.RUNE_P.getPlayerHead(),
            ThemeType.RUNE,
            "True Fire Rune",
            "An inferno without limits held",
            "back by nothing but it's own tempest."
        );

        RUNE_Q = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_CIRCLE",
            Skulls.RUNE_Q.getPlayerHead(),
            ThemeType.RUNE,
            "Circle Rune",
            "Brings both order and routine keeping",
            "other forms of magic in check.",
            "Circle Rune magic is weakened in",
            "the Minecraft world for some reason..."
        );

        RUNE_R = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_BLINKING",
            Skulls.RUNE_R.getPlayerHead(),
            ThemeType.RUNE,
            "Blinking Rune",
            "This rune contains space and in turn",
            "itself and is used to traverse",
            "large distances in moments."
        );

        RUNE_S = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_SOUL",
            Skulls.RUNE_S.getPlayerHead(),
            ThemeType.RUNE,
            "Souleater Rune",
            "The rune of life and death. Both",
            "kept in balance and check."
        );

        RUNE_T = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_PUNISHMENT",
            Skulls.RUNE_T.getPlayerHead(),
            ThemeType.RUNE,
            "Rune of Punishment",
            "This rune imposes order by crushing",
            "dissidence and wrong-doing, culling",
            "magic gone-astray."
        );

        RUNE_U = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_TRUE_LIGHTNING",
            Skulls.RUNE_U.getPlayerHead(),
            ThemeType.RUNE,
            "True Lightning Rune",
            "The tempest from above contained",
            "within a sealing magic."
        );

        RUNE_V = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_EIGHTFOLD",
            Skulls.RUNE_V.getPlayerHead(),
            ThemeType.RUNE,
            "Eightfold Rune",
            "The rule of reality and form.",
            "Magics from this rune have the power to",
            "create and form new matter."
        );

        RUNE_W = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_CHARM",
            Skulls.RUNE_W.getPlayerHead(),
            ThemeType.RUNE,
            "Charm Rune",
            "Passion, drive and love. These power",
            "and are powered by the magic within."
        );

        RUNE_X = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_TRUE_WIND",
            Skulls.RUNE_X.getPlayerHead(),
            ThemeType.RUNE,
            "True Wind Rune",
            "The never ending zephyr is contained",
            "within."
        );

        RUNE_Y = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_BLACK_SWORD",
            Skulls.RUNE_Y.getPlayerHead(),
            ThemeType.RUNE,
            "Black Sword Rune",
            "The magic of power, domination and drive."
        );

        RUNE_Z = ThemeType.themedSlimefunItemStack(
            "CRY_RUNE_BRIGHT_SHIELD",
            Skulls.RUNE_Y.getPlayerHead(),
            ThemeType.RUNE,
            "Bright Shield Rune",
            "The magic of power, protection and security."
        );

        // Artistic

        PAINT_BRUSH_BLACK_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_BLACK_100",
            MagicPaintbrush.getTippedBrush(DyeColor.BLACK),
            ThemeType.TOOL,
            "Magic Paintbrush (Black)",
            "Paints colored blocks black.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_BLUE_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_BLUE_100",
            MagicPaintbrush.getTippedBrush(DyeColor.BLUE),
            ThemeType.TOOL,
            "Magic Paintbrush (Blue)",
            "Paints colored blocks blue.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_BROWN_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_BROWN_100",
            MagicPaintbrush.getTippedBrush(DyeColor.BROWN),
            ThemeType.TOOL,
            "Magic Paintbrush (Brown)",
            "Paints colored blocks brown.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_CYAN_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_CYAN_100",
            MagicPaintbrush.getTippedBrush(DyeColor.CYAN),
            ThemeType.TOOL,
            "Magic Paintbrush (Cyan)",
            "Paints colored blocks cyan.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_GRAY_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_GRAY_100",
            MagicPaintbrush.getTippedBrush(DyeColor.GRAY),
            ThemeType.TOOL,
            "Magic Paintbrush (Gray)",
            "Paints colored blocks gray.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_GREEN_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_GREEN_100",
            MagicPaintbrush.getTippedBrush(DyeColor.GREEN),
            ThemeType.TOOL,
            "Magic Paintbrush (Green)",
            "Paints colored blocks green.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_LIGHT_BLUE_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_LIGHT_BLUE_100",
            MagicPaintbrush.getTippedBrush(DyeColor.LIGHT_BLUE),
            ThemeType.TOOL,
            "Magic Paintbrush (Light Blue)",
            "Paints colored blocks light blue.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_LIGHT_GRAY_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_LIGHT_GRAY_100",
            MagicPaintbrush.getTippedBrush(DyeColor.LIGHT_GRAY),
            ThemeType.TOOL,
            "Magic Paintbrush (Light Gray)",
            "Paints colored blocks light gray.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_LIME_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_LIME_100",
            MagicPaintbrush.getTippedBrush(DyeColor.LIME),
            ThemeType.TOOL,
            "Magic Paintbrush (Lime)",
            "Paints colored blocks lime.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_MAGENTA_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_MAGENTA_100",
            MagicPaintbrush.getTippedBrush(DyeColor.MAGENTA),
            ThemeType.TOOL,
            "Magic Paintbrush (Magenta)",
            "Paints colored blocks magenta.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_ORANGE_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_ORANGE_100",
            MagicPaintbrush.getTippedBrush(DyeColor.ORANGE),
            ThemeType.TOOL,
            "Magic Paintbrush (Orange)",
            "Paints colored blocks orange.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_PINK_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_PINK_100",
            MagicPaintbrush.getTippedBrush(DyeColor.PINK),
            ThemeType.TOOL,
            "Magic Paintbrush (Pink)",
            "Paints colored blocks pink.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_PURPLE_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_PURPLE_100",
            MagicPaintbrush.getTippedBrush(DyeColor.PURPLE),
            ThemeType.TOOL,
            "Magic Paintbrush (Purple)",
            "Paints colored blocks purple.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_RED_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_RED_100",
            MagicPaintbrush.getTippedBrush(DyeColor.RED),
            ThemeType.TOOL,
            "Magic Paintbrush (Red)",
            "Paints colored blocks red.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_WHITE_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_WHITE_100",
            MagicPaintbrush.getTippedBrush(DyeColor.WHITE),
            ThemeType.TOOL,
            "Magic Paintbrush (White)",
            "Paints colored blocks white.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_YELLOW_100 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_YELLOW_100",
            MagicPaintbrush.getTippedBrush(DyeColor.YELLOW),
            ThemeType.TOOL,
            "Magic Paintbrush (Yellow)",
            "Paints colored blocks yellow.",
            "",
            ChatColor.YELLOW + "100 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_BLACK_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_BLACK_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.BLACK),
            ThemeType.TOOL,
            "Magic Paintbrush (Black)",
            "Paints colored blocks black.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_BLUE_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_BLUE_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.BLUE),
            ThemeType.TOOL,
            "Magic Paintbrush (Blue)",
            "Paints colored blocks blue.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_BROWN_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_BROWN_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.BROWN),
            ThemeType.TOOL,
            "Magic Paintbrush (Brown)",
            "Paints colored blocks brown.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_CYAN_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_CYAN_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.CYAN),
            ThemeType.TOOL,
            "Magic Paintbrush (Cyan)",
            "Paints colored blocks cyan.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_GRAY_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_GRAY_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.GRAY),
            ThemeType.TOOL,
            "Magic Paintbrush (Gray)",
            "Paints colored blocks gray.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_GREEN_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_GREEN_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.GREEN),
            ThemeType.TOOL,
            "Magic Paintbrush (Green)",
            "Paints colored blocks green.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_LIGHT_BLUE_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_LIGHT_BLUE_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.LIGHT_BLUE),
            ThemeType.TOOL,
            "Magic Paintbrush (Light Blue)",
            "Paints colored blocks light blue.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_LIGHT_GRAY_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_LIGHT_GRAY_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.LIGHT_GRAY),
            ThemeType.TOOL,
            "Magic Paintbrush (Light Gray)",
            "Paints colored blocks light gray.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_LIME_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_LIME_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.LIME),
            ThemeType.TOOL,
            "Magic Paintbrush (Lime)",
            "Paints colored blocks lime.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_MAGENTA_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_MAGENTA_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.MAGENTA),
            ThemeType.TOOL,
            "Magic Paintbrush (Magenta)",
            "Paints colored blocks magenta.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_ORANGE_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_ORANGE_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.ORANGE),
            ThemeType.TOOL,
            "Magic Paintbrush (Orange)",
            "Paints colored blocks orange.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_PINK_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_PINK_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.PINK),
            ThemeType.TOOL,
            "Magic Paintbrush (Pink)",
            "Paints colored blocks pink.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_PURPLE_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_PURPLE_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.PURPLE),
            ThemeType.TOOL,
            "Magic Paintbrush (Purple)",
            "Paints colored blocks purple.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_RED_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_RED_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.RED),
            ThemeType.TOOL,
            "Magic Paintbrush (Red)",
            "Paints colored blocks red.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_WHITE_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_WHITE_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.WHITE),
            ThemeType.TOOL,
            "Magic Paintbrush (White)",
            "Paints colored blocks white.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_YELLOW_1000 = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_YELLOW_1000",
            MagicPaintbrush.getTippedBrush(DyeColor.YELLOW),
            ThemeType.TOOL,
            "Magic Paintbrush (Yellow)",
            "Paints colored blocks yellow.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        PAINT_BRUSH_INFINITE = ThemeType.themedSlimefunItemStack(
            "CRY_BRUSH_INFINITE",
            MagicPaintbrush.getTippedBrush(DyeColor.WHITE, true),
            ThemeType.TOOL,
            "Mythical Paintbrush",
            "Paints colored blocks and entities. Can",
            "paint in all colors and doesn't run out.",
            "",
            MessageFormat.format("{0}Shift Left Click: {1}Change Color", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor())
        );

        MYSTICAL_PIGMENTATO = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTICAL_PIGMENTATO",
            new ItemStack(Material.PRISMARINE),
            ThemeType.CRAFTING,
            "Mystical Pigmentato",
            "A condensed essence of color"
        );

        MYSTICAL_TINTANNO = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTICAL_TINTANNO",
            new ItemStack(Material.DARK_PRISMARINE),
            ThemeType.CRAFTING,
            "Mystical Tintanno",
            "A condensed essence of color"
        );

        BODY_STAND = ThemeType.themedSlimefunItemStack(
            "CRY_BODY_STAND",
            new ItemStack(Material.ARMOR_STAND),
            ThemeType.CRAFTING,
            "Armorstand of the Body",
            "Something about this stand is off."
        );

        MIND_STAND = ThemeType.themedSlimefunItemStack(
            "CRY_MIND_STAND",
            new ItemStack(Material.ARMOR_STAND),
            ThemeType.CRAFTING,
            "Armorstand of the Mind",
            "Something about this stand is off."
        );

        SOUL_STAND = ThemeType.themedSlimefunItemStack(
            "CRY_SOUL_STAND",
            new ItemStack(Material.ARMOR_STAND),
            ThemeType.CRAFTING,
            "Armorstand of the Soul",
            "Something about this stand is off."
        );

        MYSTICAL_ATTITUDINIZER = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTICAL_ATTITUDINIZER",
            new ItemStack(Material.BELL),
            ThemeType.CRAFTING,
            "Mystical Attitudinizer",
            "A condensed essence of posture"
        );

        IMBUED_STAND = ThemeType.themedSlimefunItemStack(
            "CRY_IMBUED_STAND",
            new ItemStack(Material.ARMOR_STAND),
            ThemeType.TOOL,
            "Imbued Armor Stand",
            "This Armor Stand can be made",
            "invisible or smaller with a pose",
            "changer."
        );

        POSE_CHANGER = ThemeType.themedSlimefunItemStack(
            "CRY_POSE_CHANGER",
            new ItemStack(Material.BAMBOO),
            ThemeType.TOOL,
            "Pose Changer",
            "This item is able to manipulate",
            "the pose of armor stands.",
            "Works with vanilla stand and",
            "can do additional things to",
            "Imbued Stands.",
            "",
            MessageFormat.format("{0}Left Click: {1}Change selected body part", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor()),
            MessageFormat.format("{0}Shift Left Click: {1}Change selected change method", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor()),
            MessageFormat.format("{0}Right Click: {1}Apply change (positive)", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor()),
            MessageFormat.format("{0}Shift Right Click: {1}Apply change (negative)", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor()),
            "",
            MessageFormat.format("{0}Pose Set To: {1}HEAD", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor()),
            MessageFormat.format("{0}Change Set To: {1}RESET", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor())
        );

        POSE_CLONER = ThemeType.themedSlimefunItemStack(
            "CRY_POSE_CLONER",
            new ItemStack(Material.SEA_PICKLE),
            ThemeType.TOOL,
            "Pose Cloner",
            "This item can copy the state",
            "of an Inbued Armorstand and",
            "make another match to suit.",
            "",
            MessageFormat.format("{0}Right Click: {1}Clone pose", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor()),
            MessageFormat.format("{0}Shift Right Click: {1}Apply stored clone", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor())
        );

        EXALTED_BEACON = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_BEACON",
            new ItemStack(Material.BEACON),
            ThemeType.EXALTED,
            "Exalted Beacon",
            "A powerful beacon with incredible",
            "potential. Must be placed on a",
            "Exaltation Stand to operate.",
            "",
            Theme.CLICK_INFO + "Power: " + Theme.PASSIVE + "2",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Story Rank > Emeritus Professor"
        );

        EXALTED_BAELFIRE = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_BAELFIRE",
            new ItemStack(Material.SOUL_CAMPFIRE),
            ThemeType.EXALTED,
            "Exalted Baelfire",
            "A powerful beacon with incredible",
            "potential. Must be placed on a",
            "Exaltation Stand to operate.",
            "",
            Theme.CLICK_INFO + "Power: " + Theme.PASSIVE + "3",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Story Rank > Emeritus Professor"
        );

        FERTILITY_PHARO = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_FERTILITY_PHARO",
            new ItemStack(Material.DIAMOND_BLOCK),
            ThemeType.EXALTED,
            "Exalted Fertility Pharo",
            "A magical construct able to",
            "breed nearby animals in a 10x10 area.",
            "Must be placed on an Exaltation",
            "Stand to operate.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Story Rank > Emeritus Professor"
        );

        FERTILITY_TOTEM = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_FERTILITY_TOTEM",
            new ItemStack(Material.EMERALD_BLOCK),
            ThemeType.EXALTED,
            "Exalted Fertility Totem",
            "A magical construct able to",
            "breed nearby animals in a 20x20 area.",
            "Must be placed on an Exaltation",
            "Stand to operate.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Story Rank > Emeritus Professor"
        );

        EXALTED_HARVESTER = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_HARVESTER",
            new ItemStack(Material.HAY_BLOCK),
            ThemeType.EXALTED,
            "Exalted Harvester",
            "A magical construct able to",
            "harvest all crops in a 9x9 area.",
            "Must be placed on an Exaltation",
            "Stand to operate.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Spell Rank > Grandmaster Magus"
        );

        EXALTED_AGRONOMIST = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_AGRONOMIST",
            new ItemStack(Material.DRIED_KELP_BLOCK),
            ThemeType.EXALTED,
            "Exalted Agronomist",
            "A magical construct able to",
            "harvest all crops in a 19x19 area.",
            "Must be placed on an Exaltation",
            "Stand to operate.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Spell Rank > Grandmaster Magus"
        );

        EXALTED_DAWN = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_DAWN",
            new ItemStack(Material.YELLOW_WOOL),
            ThemeType.EXALTED,
            "Exalted Dawn",
            "A magical construct that burns",
            "brightly like the sun.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Crafted during the day"
        );

        EXALTED_DUSK = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_DUSK",
            new ItemStack(Material.BLACK_WOOL),
            ThemeType.EXALTED,
            "Exalted Dusk",
            "A magical construct shines",
            "as vivid as the new moon",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Crafted during the night"
        );

        EXALTED_SUN = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_SUN",
            new ItemStack(Material.MAGMA_BLOCK),
            ThemeType.EXALTED,
            "Exalted Sun",
            "A magical construct emanating",
            "the power of a sun.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Crafted during a clear day"
        );

        EXALTED_STORM = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTED_STORM",
            new ItemStack(Material.GRAY_WOOL),
            ThemeType.EXALTED,
            "Exalted Storm",
            "A magical construct emanating",
            "the destruction of a storm.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Crafted during a storm"
        );

        EXALTED_SEA_BREEZE = ThemeType.themedSlimefunItemStack(
            "CRY_SEA_BREEZE",
            new ItemStack(Material.TUBE_CORAL_BLOCK),
            ThemeType.EXALTED,
            "Exalted Sea Breeze",
            "A magical construct that brings",
            "the sea air to nearby blocks, weathering",
            "and oxidizing them.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Crafted on a beach"
        );

        // Gadgets

        ABSTRACTION_LAMP = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_LAMP_1",
            new ItemStack(Material.LANTERN),
            ThemeType.GADGET,
            "Abstraction Lamp",
            "The abstraction lamp will push all",
            "nearby mobs away from it.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "5 Blocks",
            ThemeType.CLICK_INFO.getColor() + "Force: " + ThemeType.PASSIVE.getColor() + "3 CrystaPow™"
        );

        DISPERSION_LAMP = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_LAMP_2",
            new ItemStack(Material.SOUL_LANTERN),
            ThemeType.GADGET,
            "Dispersion Lamp",
            "The dispersion lamp will push all",
            "nearby mobs away from it.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "7 Blocks",
            ThemeType.CLICK_INFO.getColor() + "Force: " + ThemeType.PASSIVE.getColor() + "5 CrystaPow™"
        );

        EXODUS_LAMP = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_LAMP_3",
            GeneralUtils.getPreEnchantedItemStack(Material.SOUL_LANTERN),
            ThemeType.GADGET,
            "Exodus Lamp",
            "The exodus lamp will push all",
            "nearby mobs away from it.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "10 Blocks",
            ThemeType.CLICK_INFO.getColor() + "Force: " + ThemeType.PASSIVE.getColor() + "10 CrystaPow™"
        );

        INVERSION_VACUUM = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_FAN_1",
            new ItemStack(Material.REDSTONE_LAMP),
            ThemeType.GADGET,
            "Inversion Vacuum",
            "Creates a magically induced vacuum",
            "that pulls entities away from the",
            "mechanism.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Push Distance: " + ThemeType.PASSIVE.getColor() + "5 Blocks"
        );

        ANTIPODAL_VACUUM = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_FAN_2",
            new ItemStack(Material.NOTE_BLOCK),
            ThemeType.GADGET,
            "Antipodal Vacuum",
            "Creates a magically induced vacuum",
            "that pulls entities away from the",
            "mechanism.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Push Distance: " + ThemeType.PASSIVE.getColor() + "10 Blocks"
        );

        COUNTERPOLE_VACUUM = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_FAN_3",
            new ItemStack(Material.DEEPSLATE_BRICKS),
            ThemeType.GADGET,
            "Counterpole Vacuum",
            "Creates a magically induced vacuum",
            "that pulls entities away from the",
            "mechanism.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Push Distance: " + ThemeType.PASSIVE.getColor() + "15 Blocks"
        );

        CURSED_EARTH = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_DIRT_1",
            new ItemStack(Material.BROWN_WOOL),
            ThemeType.GADGET,
            "Cursed Earth",
            "Dark magics seep from this dirt giving",
            "a very ominous vibe!",
            "",
            ThemeType.CLICK_INFO.getColor() + "Tick Rate: " + ThemeType.PASSIVE.getColor() + "20",
            ThemeType.CLICK_INFO.getColor() + "Light Level: " + ThemeType.PASSIVE.getColor() + "7",
            ThemeType.CLICK_INFO.getColor() + "Spawns: " + ThemeType.PASSIVE.getColor() + "Basic"
        );

        DREADFUL_DIRT = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_DIRT_2",
            new ItemStack(Material.BLACK_WOOL),
            ThemeType.GADGET,
            "Dreadful Dirt",
            "Dark magics seep from this dirt giving",
            "a very ominous vibe!",
            "",
            ThemeType.CLICK_INFO.getColor() + "Tick Rate: " + ThemeType.PASSIVE.getColor() + "10",
            ThemeType.CLICK_INFO.getColor() + "Light Level: " + ThemeType.PASSIVE.getColor() + "15",
            ThemeType.CLICK_INFO.getColor() + "Spawns: " + ThemeType.PASSIVE.getColor() + "Advanced"
        );

        SOULFILLED_SOIL = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_DIRT_3",
            new ItemStack(Material.LIME_WOOL),
            ThemeType.GADGET,
            "Soulfilled Soil",
            "Dark magics have been purified clean.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Tick Rate: " + ThemeType.PASSIVE.getColor() + "10",
            ThemeType.CLICK_INFO.getColor() + "Light Level: " + ThemeType.PASSIVE.getColor() + "15",
            ThemeType.CLICK_INFO.getColor() + "Spawns: " + ThemeType.PASSIVE.getColor() + "Friendly"
        );

        SEARING_PLATE = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_PLATE_1",
            new ItemStack(Material.CRIMSON_PRESSURE_PLATE),
            ThemeType.GADGET,
            "Searing Plate",
            "A plate that is magically super-heated.",
            "Anything standing on this plate gets",
            "damaged.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Damage: " + ThemeType.PASSIVE.getColor() + "1",
            ThemeType.CLICK_INFO.getColor() + "Player Drops: " + ThemeType.PASSIVE.getColor() + "No"
        );

        DOOMED_PLATE = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_PLATE_2",
            new ItemStack(Material.WARPED_PRESSURE_PLATE),
            ThemeType.GADGET,
            "Doomed Plate",
            "A plate that is magically super-heated.",
            "Anything standing on this plate gets",
            "damaged.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Damage: " + ThemeType.PASSIVE.getColor() + "1",
            ThemeType.CLICK_INFO.getColor() + "Player Drops: " + ThemeType.PASSIVE.getColor() + "Yes"
        );

        EVISCERATING_PLATE = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_PLATE_3",
            new ItemStack(Material.POLISHED_BLACKSTONE_PRESSURE_PLATE),
            ThemeType.GADGET,
            "Eviscerating Plate",
            "A plate that is magically super-heated.",
            "Anything standing on this plate gets",
            "damaged.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Damage: " + ThemeType.PASSIVE.getColor() + "2",
            ThemeType.CLICK_INFO.getColor() + "Player Drops: " + ThemeType.PASSIVE.getColor() + "Yes"
        );

        SHREDDING_PLATE = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_PLATE_4",
            new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE),
            ThemeType.GADGET,
            "Shredding Plate",
            "A plate that is just too powerful.",
            "Anything standing on this plate gets",
            "damaged.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Damage: " + ThemeType.PASSIVE.getColor() + "3",
            ThemeType.CLICK_INFO.getColor() + "Player Drops: " + ThemeType.PASSIVE.getColor() + "Yes"
        );

        TRAP_PLATE = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_PLATE_TRAP",
            new ItemStack(Material.DARK_OAK_PRESSURE_PLATE),
            ThemeType.GADGET,
            "Trap Plate",
            "This plate no longer deals",
            "damage but now applies potion",
            "effects. Right click with",
            "a potion to assign."
        );

        EXP_COLLECTOR_BASIC = ThemeType.themedSlimefunItemStack(
            "CRY_EXP_COLLECTOR_1",
            new ItemStack(Material.LIGHTNING_ROD),
            ThemeType.GADGET,
            "Basic Exp Collector",
            "Infusing the Exp Collector with",
            "magic now allows it to work",
            "without electricity and flasks.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "4",
            ThemeType.CLICK_INFO.getColor() + "Capacity: " + ThemeType.PASSIVE.getColor() + "2500"
        );

        EXP_COLLECTOR_INFUSED = ThemeType.themedSlimefunItemStack(
            "CRY_EXP_COLLECTOR_2",
            new ItemStack(Material.LIGHTNING_ROD),
            ThemeType.GADGET,
            "Infused Exp Collector",
            "Further infusion has made the",
            "collector even more powerful.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "8",
            ThemeType.CLICK_INFO.getColor() + "Capacity: " + ThemeType.PASSIVE.getColor() + "10000"
        );

        EXP_COLLECTOR_ARCANE = ThemeType.themedSlimefunItemStack(
            "CRY_EXP_COLLECTOR_3",
            new ItemStack(Material.LIGHTNING_ROD),
            ThemeType.GADGET,
            "Arcane Exp Collector",
            "Magic sigils allow for even",
            "more storage.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "8",
            ThemeType.CLICK_INFO.getColor() + "Capacity: " + ThemeType.PASSIVE.getColor() + "999999"
        );

        ENDER_INHIBITOR_BASIC = ThemeType.themedSlimefunItemStack(
            "CRY_ENDER_INHIBITOR_1",
            new ItemStack(Material.REDSTONE_TORCH),
            ThemeType.GADGET,
            "Basic Ender Inhibitor",
            "By using an Enderman's own resonance",
            "against it, we can stop theme teleporting",
            "for a brief time.",
            "Duration is extended if still in range.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "4",
            ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "2 seconds"
        );

        ENDER_INHIBITOR_ADVANCED = ThemeType.themedSlimefunItemStack(
            "CRY_ENDER_INHIBITOR_2",
            new ItemStack(Material.SOUL_TORCH),
            ThemeType.GADGET,
            "Advanced Ender Inhibitor",
            "By using an Enderman's own resonance",
            "against it, we can stop theme teleporting",
            "for a brief time.",
            "Duration is extended if still in range.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "8",
            ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "2 seconds"
        );

        MOB_CANDLE_DIM = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_CANDLE_1",
            new ItemStack(Material.BLACK_CANDLE),
            ThemeType.GADGET,
            "Dim Verache Candle",
            "This candle stops mobs spawning",
            "in a radius around it.",
            "Doesn't last forever.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "16",
            ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "2 hour (real time)"
        );

        MOB_CANDLE_BRIGHT = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_CANDLE_2",
            new ItemStack(Material.BROWN_CANDLE),
            ThemeType.GADGET,
            "Bright Verache Candle",
            "This candle stops mobs spawning",
            "in a radius around it.",
            "Doesn't last forever.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "32",
            ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "24 hours (real time)"
        );

        MOB_CANDLE_SCINTILLATING = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_CANDLE_3",
            new ItemStack(Material.WHITE_CANDLE),
            ThemeType.GADGET,
            "Scintillating Verache Candle",
            "This candle stops mobs spawning",
            "in a radius around it.",
            "Doesn't last forever.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "64",
            ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "48 Hours (real time)"
        );

        MOB_CANDLE_CORUSCATING = ThemeType.themedSlimefunItemStack(
            "CRY_MOB_CANDLE_4",
            new ItemStack(Material.MAGENTA_CANDLE),
            ThemeType.GADGET,
            "Coruscating Verache Candle",
            "This candle stops mobs spawning",
            "in a radius around it.",
            "Doesn't last forever.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "64",
            ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "2 Weeks (real time)"
        );

        MYSTERIOUS_POTTED_PLANT = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTERIOUS_POTTED_PLANT",
            new ItemStack(Material.FLOWER_POT),
            ThemeType.GADGET,
            "Mysterious Potted Plant",
            "Just a pinch of magic can make",
            "wonderful things happen..."
        );

        MYSTERIOUS_PLANT = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTERIOUS_PLANT",
            new ItemStack(Material.OXEYE_DAISY),
            ThemeType.GADGET,
            "Mysterious Plant",
            "Removing it from that pot took work."
        );

        MYSTERIOUS_GLASS = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTERIOUS_GLASS",
            new ItemStack(Material.ORANGE_STAINED_GLASS),
            ThemeType.GADGET,
            "Mysterious Glass",
            "Like rainbow glass but far",
            "more random."
        );

        MYSTERIOUS_WOOL = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTERIOUS_WOOL",
            new ItemStack(Material.ORANGE_WOOL),
            ThemeType.GADGET,
            "Mysterious Wool",
            "Like rainbow wool but far",
            "more random."
        );

        MYSTERIOUS_TERRACOTTA = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTERIOUS_TERRACOTTA",
            new ItemStack(Material.ORANGE_TERRACOTTA),
            ThemeType.GADGET,
            "Mysterious Terracotta",
            "Like rainbow terracotta but far",
            "more random."
        );

        MYSTERIOUS_GLAZED_TERRACOTTA = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTERIOUS_GLAZED_TERRACOTTA",
            new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA),
            ThemeType.GADGET,
            "Mysterious Glazed Terracotta",
            "Like rainbow terracotta but far",
            "more random."
        );

        MYSTERIOUS_CONCRETE = ThemeType.themedSlimefunItemStack(
            "CRY_MYSTERIOUS_CONCRETE",
            new ItemStack(Material.ORANGE_CONCRETE),
            ThemeType.GADGET,
            "Mysterious Concrete",
            "Like rainbow concrete but far",
            "more random."
        );

        GREEN_HOUSE_GLASS = ThemeType.themedSlimefunItemStack(
            "CRY_CROP_GLASS_1",
            new ItemStack(Material.GLASS),
            ThemeType.GADGET,
            "Greenhouse Glass",
            "Crops under this glass will grow faster.",
            "Works during the day in light worlds only.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Rate: " + ThemeType.PASSIVE.getColor() + "5"
        );

        GREEN_HOUSE_GLASS_FOCUSED = ThemeType.themedSlimefunItemStack(
            "CRY_CROP_GLASS_2",
            new ItemStack(Material.YELLOW_STAINED_GLASS),
            ThemeType.GADGET,
            "Focused Greenhouse Glass",
            "Crops under this glass will grow faster.",
            "Works during the day in light worlds only.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Rate: " + ThemeType.PASSIVE.getColor() + "10"
        );

        GREEN_HOUSE_GLASS_MAGNIFYING = ThemeType.themedSlimefunItemStack(
            "CRY_CROP_GLASS_3",
            new ItemStack(Material.TINTED_GLASS),
            ThemeType.GADGET,
            "Magnifying Greenhouse Glass",
            "Crops under this glass will grow faster.",
            "Works during the day in light worlds only.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Rate: " + ThemeType.PASSIVE.getColor() + "20"
        );

        TROPHY_STAND = ThemeType.themedSlimefunItemStack(
            "CRY_TROPHY_DISPLAY_1",
            new ItemStack(Material.POLISHED_BLACKSTONE_BRICK_WALL),
            ThemeType.GADGET,
            "Trophy Display",
            "Used to place your trophies on to show",
            "off to the world.",
            "",
            "Currently allowable trophies include:",
            ThemeType.CLICK_INFO.getColor() + "A block in which you have a S.M.E. rank"
        );

        EXALTATION_STAND = ThemeType.themedSlimefunItemStack(
            "CRY_EXALTATION_STAND",
            new ItemStack(Material.PRISMARINE_WALL),
            ThemeType.GADGET,
            "Exaltation Stand",
            "Used to place 'Exalted' items on.",
            "These items require special conditions",
            "and impart powerful effects while nearby."
        );

        WAYSTONE = ThemeType.themedSlimefunItemStack(
            "CRY_WAYSTONE",
            new ItemStack(Material.END_STONE_BRICK_WALL),
            ThemeType.GADGET,
            "Diverging Waystone",
            "Can be used as a marker to recall",
            "back to."
        );

        ANGEL_BLOCK = ThemeType.themedSlimefunItemStack(
            "CRY_ANGEL_BLOCK",
            new ItemStack(Material.GLASS),
            ThemeType.GADGET,
            "Angel Block",
            "Can be placed anywhere, even in",
            "the air."
        );

        PHILOSOPHERS_SPRAY = ThemeType.themedSlimefunItemStack(
            "CRY_PHILOSOPHERS_SPRAY",
            new ItemStack(Material.DISPENSER),
            ThemeType.GADGET,
            "Philosophers Spray",
            "Will 'displace' the block above",
            "this one when triggered with Redstone."
        );

        GLASS_OF_MILK = ThemeType.themedSlimefunItemStack(
            "CRY_GLASS_OF_MILK",
            Skulls.ITEM_MILK.getPlayerHead(),
            ThemeType.GADGET,
            "Glass of Milk",
            "Right click, when placed, to remove",
            "all active effects.",
            "",
            ThemeType.WARNING.getColor() + "Can't be recovered when broken."
        );

        FRAGMENTED_VOID = ThemeType.themedSlimefunItemStack(
            "CRY_FRAGMENTED_VOID",
            new ItemStack(Material.BLACK_CONCRETE),
            ThemeType.GADGET,
            "Fragmented Void",
            "A portable hole in the universe.",
            "Will pull any nearby items into it.",
            "",
            ThemeType.WARNING.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "5"
        );

        SHATTERED_VOID = ThemeType.themedSlimefunItemStack(
            "CRY_SHATTERED_VOID",
            new ItemStack(Material.BLACK_CONCRETE),
            ThemeType.GADGET,
            "Shattered Void",
            "A portable hole in the universe.",
            "Will pull any nearby items into it.",
            "",
            ThemeType.WARNING.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "7"
        );

        CHRONICLER_PANEL_1 = ThemeType.themedSlimefunItemStack(
            "CRY_CHRONICLER_PANEL_1",
            new ItemStack(Material.COBBLED_DEEPSLATE_SLAB),
            ThemeType.MECHANISM,
            "Chronicler Panel (Tier 1)",
            "The chronicler panel will draw out",
            "the stories contained within a given",
            "block over time.",
            "",
            "Can chronicle stories for T1-T2 blocks."
        );

        CHRONICLER_PANEL_2 = ThemeType.themedSlimefunItemStack(
            "CRY_CHRONICLER_PANEL_2",
            new ItemStack(Material.DEEPSLATE_TILE_SLAB),
            ThemeType.MECHANISM,
            "Chronicler Panel (Tier 2)",
            "The chronicler panel will draw out",
            "the stories contained within a given",
            "block over time.",
            "",
            "Can chronicle stories for T1-T3 blocks."
        );

        CHRONICLER_PANEL_3 = ThemeType.themedSlimefunItemStack(
            "CRY_CHRONICLER_PANEL_3",
            new ItemStack(Material.NETHER_BRICK_SLAB),
            ThemeType.MECHANISM,
            "Chronicler Panel (Tier 3)",
            "The chronicler panel will draw out",
            "the stories contained within a given",
            "block over time.",
            "",
            "Can chronicle stories for T1-T4 blocks."
        );

        CHRONICLER_PANEL_4 = ThemeType.themedSlimefunItemStack(
            "CRY_CHRONICLER_PANEL_4",
            new ItemStack(Material.DARK_PRISMARINE_SLAB),
            ThemeType.MECHANISM,
            "Chronicler Panel (Tier 4)",
            "The chronicler panel will draw out",
            "the stories contained within a given",
            "block over time.",
            "",
            "Can chronicle stories for T1-T5 blocks."
        );

        CHRONICLER_PANEL_5 = ThemeType.themedSlimefunItemStack(
            "CRY_CHRONICLER_PANEL_5",
            new ItemStack(Material.SMOOTH_QUARTZ_SLAB),
            ThemeType.MECHANISM,
            "Chronicler Panel (Tier 5)",
            "The chronicler panel will draw out",
            "the stories contained within a given",
            "block over time.",
            "",
            "Can chronicle stories for T1-T5 blocks.",
            "Automatically picks up items thrown on",
            "top of it and pushes storied items below."
        );

        REALISATION_ALTAR_1 = ThemeType.themedSlimefunItemStack(
            "CRY_REALISATION_ALTAR_1",
            new ItemStack(Material.CHISELED_DEEPSLATE),
            ThemeType.MECHANISM,
            "Realisation Altar (Tier 1)",
            "The realisation altar takes storied",
            "blocks and converts their stories into",
            "a physical form.",
            "",
            "Can realise T1 - T2."
        );

        REALISATION_ALTAR_2 = ThemeType.themedSlimefunItemStack(
            "CRY_REALISATION_ALTAR_2",
            new ItemStack(Material.CHISELED_NETHER_BRICKS),
            ThemeType.MECHANISM,
            "Realisation Altar (Tier 2)",
            "The realisation altar takes storied",
            "blocks and converts their stories into",
            "a physical form.",
            "",
            "Can realise T1 - T3."
        );

        REALISATION_ALTAR_3 = ThemeType.themedSlimefunItemStack(
            "CRY_REALISATION_ALTAR_3",
            new ItemStack(Material.CHISELED_RED_SANDSTONE),
            ThemeType.MECHANISM,
            "Realisation Altar (Tier 3)",
            "The realisation altar takes storied",
            "blocks and converts their stories into",
            "a physical form.",
            "",
            "Can realise T1 - T4."
        );

        REALISATION_ALTAR_4 = ThemeType.themedSlimefunItemStack(
            "CRY_REALISATION_ALTAR_4",
            new ItemStack(Material.CHISELED_POLISHED_BLACKSTONE),
            ThemeType.MECHANISM,
            "Realisation Altar (Tier 4)",
            "The realisation altar takes storied",
            "blocks and converts their stories into",
            "a physical form.",
            "",
            "Can realise T1 - T5."
        );

        REALISATION_ALTAR_5 = ThemeType.themedSlimefunItemStack(
            "CRY_REALISATION_ALTAR_5",
            new ItemStack(Material.CHISELED_STONE_BRICKS),
            ThemeType.MECHANISM,
            "Realisation Altar (Tier 5)",
            "The realisation altar takes storied",
            "blocks and converts their stories into",
            "a physical form.",
            "",
            "Can realise T1 - T5.",
            "Automatically picks up items thrown on",
            "top of it."
        );

        LIQUEFACTION_BASIN_1 = ThemeType.themedSlimefunItemStack(
            "CRY_LIQUEFACTION_BASIN_1",
            new ItemStack(Material.CAULDRON),
            ThemeType.MECHANISM,
            "Liquefaction Basin (Tier 1)",
            "The liquefaction basin can take",
            "Crystals and convert them into their liquid",
            "Crystamae form. Used for magical crafting.",
            "Danger: Do not break while filled!",
            "",
            "Holds up to 500 Liquefied Crysta."
        );

        LIQUEFACTION_BASIN_2 = ThemeType.themedSlimefunItemStack(
            "CRY_LIQUEFACTION_BASIN_2",
            new ItemStack(Material.CAULDRON),
            ThemeType.MECHANISM,
            "Liquefaction Basin (Tier 2)",
            "The liquefaction basin can take",
            "Crystals and convert them into their liquid",
            "Crystamae form. Used for magical crafting.",
            "Danger: Do not break while filled!",
            "",
            "Holds up to 1250 Liquefied Crysta."
        );

        LIQUEFACTION_BASIN_3 = ThemeType.themedSlimefunItemStack(
            "CRY_LIQUEFACTION_BASIN_3",
            new ItemStack(Material.CAULDRON),
            ThemeType.MECHANISM,
            "Liquefaction Basin (Tier 3)",
            "The liquefaction basin can take",
            "Crystals and convert them into their liquid",
            "Crystamae form. Used for magical crafting.",
            "Danger: Do not break while filled!",
            "",
            "Holds up to 2500 Liquefied Crysta."
        );

        LIQUEFACTION_BASIN_4 = ThemeType.themedSlimefunItemStack(
            "CRY_LIQUEFACTION_BASIN_4",
            new ItemStack(Material.CAULDRON),
            ThemeType.MECHANISM,
            "Liquefaction Basin (Tier 4)",
            "The liquefaction basin can take",
            "Crystals and convert them into their liquid",
            "Crystamae form. Used for magical crafting.",
            "Danger: Do not break while filled!",
            "",
            "Holds up to 5000 Liquefied Crysta."
        );

        LIQUEFACTION_BASIN_5 = ThemeType.themedSlimefunItemStack(
            "CRY_LIQUEFACTION_BASIN_5",
            new ItemStack(Material.CAULDRON),
            ThemeType.MECHANISM,
            "Liquefaction Basin (Tier 5)",
            "The liquefaction basin can take",
            "Crystals and convert them into their liquid",
            "Crystamae form. Used for magical crafting.",
            "Danger: Do not break while filled!",
            "",
            "Holds up to 10000 Liquefied Crysta."
        );

        STAVE_CONFIGURATOR = ThemeType.themedSlimefunItemStack(
            "CRY_STAVE_CONFIGURATOR",
            new ItemStack(Material.CUT_COPPER),
            ThemeType.MECHANISM,
            "Stave Configurator",
            "The Stave Configurator allows you",
            "to add spell plates into your",
            "Staves."
        );

        PRISMATIC_GILDER = ThemeType.themedSlimefunItemStack(
            "CRY_PRISMATIC_GILDER",
            new ItemStack(Material.WARPED_FENCE),
            ThemeType.MECHANISM,
            "Prismatic Gilder",
            "Converts Prismatic Crysta into a",
            "Thin film that can overlay other",
            "magical items."
        );

        // Tools

        INERT_PLATE = ThemeType.themedSlimefunItemStack(
            "CRY_SPELL_PLATE_1",
            new ItemStack(Material.PAPER),
            ThemeType.TOOL,
            "Basic Spell Plate",
            "A blank plate that has the potential to",
            "store magical energy"
        );

        CHARGED_PLATE = ThemeType.themedSlimefunItemStack(
            "CRY_CHARGED_PLATE_1",
            new ItemStack(Material.PAPER),
            ThemeType.TOOL,
            "Charged Basic Spell Plate",
            "A magically charged plate storing magic",
            "potential."
        );

        STAVE_BASIC = ThemeType.themedSlimefunItemStack(
            "CRY_STAVE_1",
            new ItemStack(Material.STICK),
            ThemeType.STAVE,
            "Basic Stave",
            "A stave with the ability to hold",
            "magically charged plates."
        );

        STAVE_ADVANCED = ThemeType.themedSlimefunItemStack(
            "CRY_STAVE_2",
            new ItemStack(Material.STICK),
            ThemeType.STAVE,
            "Advanced Stave",
            "A stave with the ability to hold",
            "magically charged plates."
        );

        STAVE_ARCANE = ThemeType.themedSlimefunItemStack(
            "CRY_STAVE_3",
            GeneralUtils.getPreEnchantedItemStack(Material.STICK),
            ThemeType.STAVE,
            "Arcane Stave",
            "A stave with the ability to hold",
            "magically charged plates."
        );

        REFRACTING_LENS = ThemeType.themedSlimefunItemStack(
            "CRY_REFRACTING_LENS",
            new ItemStack(Material.SPYGLASS),
            ThemeType.TOOL,
            "Refracting Lens",
            "This magical lens has glass that can",
            "split the light of Crysta into its",
            "individual elements.",
            "",
            "Right click on a Crystamae Block for",
            "details if available.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Works with:",
            ChatColor.DARK_BLUE + "Liquefaction Basin",
            ChatColor.DARK_BLUE + "Exp Collector"
        );

        THAUMATURGIC_SALTS = ThemeType.themedSlimefunItemStack(
            "CRY_THAUMATURGIC_SALT",
            new ItemStack(Material.REDSTONE),
            ThemeType.TOOL,
            "Thaumaturgic Salts",
            "A special formulation of salts",
            "that can absorb unwanted crysta",
            "from a Liquefaction Basin.",
            "",
            "Right click on a Liquefaction Basin",
            "to empty it."
        );

        CRYSTA_RECALL_LATTICE = ThemeType.themedSlimefunItemStack(
            "CRY_RECALL_LATTICE",
            new ItemStack(Material.NETHER_STAR),
            ThemeType.TOOL,
            "Crystamae Recall Lattice",
            "Right click to recall to a linked",
            "Waystone.",
            "",
            "Shift Right Click on a Waystone",
            "to link"
        );

        EPHEMERAL_CRAFT_TABLE = ThemeType.themedSlimefunItemStack(
            "CRY_EPHEMERAL_CRAFT_TABLE",
            new ItemStack(Material.CRAFTING_TABLE),
            ThemeType.TOOL,
            "Ephemeral Crafting Table",
            "Right click to be able to craft",
            "from anywhere.",
            "Vanilla Only"
        );

        EPHEMERAL_WORKBENCH = ThemeType.themedSlimefunItemStack(
            "CRY_EPHEMERAL_WORKBENCH",
            new ItemStack(Material.CRAFTING_TABLE),
            ThemeType.TOOL,
            "Ephemeral Work Bench",
            "Right click to be able to craft",
            "from anywhere.",
            "Vanilla + Enhanced Crafting Table."
        );

        LUMINESCENCE_SCOOP = ThemeType.themedSlimefunItemStack(
            "CRY_LUMINESCENCE_SCOOP",
            new ItemStack(Material.LANTERN),
            ThemeType.TOOL,
            "Luminescence Scoop",
            "Right click to place a magical",
            "light source.",
            "Shift Right click to scoop back.",
            "",
            ChatColor.YELLOW + "25 Uses " + ChatColor.GRAY + "left"
        );

        BRILLIANCE_SCOOP = ThemeType.themedSlimefunItemStack(
            "CRY_BRILLIANCE_SCOOP",
            new ItemStack(Material.LANTERN),
            ThemeType.TOOL,
            "Brilliance Scoop",
            "Right click to place a magical",
            "light source.",
            "Shift Right click to scoop back.",
            "",
            ChatColor.YELLOW + "75 Uses " + ChatColor.GRAY + "left"
        );

        LUSTRE_SCOOP = ThemeType.themedSlimefunItemStack(
            "CRY_LUSTRE_SCOOP",
            new ItemStack(Material.SOUL_LANTERN),
            ThemeType.TOOL,
            "Lustre Scoop",
            "Right click to place a magical",
            "light source.",
            "Shift Right click to scoop back.",
            "",
            ChatColor.YELLOW + "250 Uses " + ChatColor.GRAY + "left"
        );

        RADIANCE_SCOOP = ThemeType.themedSlimefunItemStack(
            "CRY_RADIANCE_SCOOP",
            GeneralUtils.getPreEnchantedItemStack(Material.SOUL_LANTERN),
            ThemeType.TOOL,
            "Radiance Scoop",
            "Right click to place a magical",
            "light source.",
            "Shift Right click to scoop back.",
            "Left click to change the light level.",
            "",
            ChatColor.YELLOW + "500 Uses " + ChatColor.GRAY + "left"
        );

        CONNECTING_COMPASS = ThemeType.themedSlimefunItemStack(
            "CRY_CONNECTING_COMPASS",
            new ItemStack(Material.COMPASS),
            ThemeType.TOOL,
            "Connecting Compass",
            "Allows you to save a location",
            "to find your way back to it at",
            "a later time.",
            "",
            MessageFormat.format("{0}Right Click: {1}Display Stored Location", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor()),
            MessageFormat.format("{0}Shift Right Click: {1}Store Location", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor())
        );

        SPIRITUAL_SILKEN = ThemeType.themedSlimefunItemStack(
            "CRY_SPIRITUAL_SILKEN",
            new ItemStack(Material.BONE),
            ThemeType.TOOL,
            "Spiritual Silken",
            "This pick has been imbued with",
            "spiritual energies that allow it",
            "to keep certain items whole.",
            ChatColor.RED + "Only works on blocks that do",
            ChatColor.RED + "not normally drop themselves",
            ChatColor.RED + "both with and without silk.",
            "",
            ChatColor.YELLOW + "50 Uses " + ChatColor.GRAY + "left"
        );

        INCORPOREAL_SILKEN = ThemeType.themedSlimefunItemStack(
            "CRY_INCORPOREAL_SILKEN",
            new ItemStack(Material.BONE),
            ThemeType.TOOL,
            "Incorporeal Silken",
            "This pick has been imbued with",
            "spiritual energies that allow it",
            "to keep certain items whole.",
            ChatColor.RED + "Only works on blocks that do",
            ChatColor.RED + "not normally drop themselves",
            ChatColor.RED + "both with and without silk.",
            "",
            ChatColor.YELLOW + "1000 Uses " + ChatColor.GRAY + "left"
        );

        SIMPLE_DISPLACER = ThemeType.themedSlimefunItemStack(
            "CRY_SIMPLE_DISPLACER",
            new ItemStack(Material.TORCH),
            ThemeType.TOOL,
            "Simple Displacer",
            "The displacer can shift some",
            "things out of this world and",
            "replace it with something from",
            "another dimension.",
            ChatColor.YELLOW + "50 Uses " + ChatColor.GRAY + "left"
        );

        ARCANE_DISPLACER = ThemeType.themedSlimefunItemStack(
            "CRY_ARCANE_DISPLACER",
            new ItemStack(Material.REDSTONE_TORCH),
            ThemeType.TOOL,
            "Arcane Displacer",
            "The displacer can shift some",
            "things out of this world and",
            "replace it with something from",
            "another dimension.",
            ChatColor.YELLOW + "500 Uses " + ChatColor.GRAY + "left"
        );

        SPONGE_BALMY = ThemeType.themedSlimefunItemStack(
            "CRY_SPONGE_BALMY",
            new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK),
            ThemeType.TOOL,
            "Balmy Sponge",
            "This imbued sponge is capable",
            "of absorbing lava instead of water.",
            "Place near water to clean after use.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "4 Blocks"
        );

        SPONGE_SEARING = ThemeType.themedSlimefunItemStack(
            "CRY_SPONGE_SEARING",
            GeneralUtils.getPreEnchantedItemStack(Material.DEAD_FIRE_CORAL_BLOCK),
            ThemeType.TOOL,
            "Searing Sponge",
            "This imbued sponge is capable",
            "of absorbing lava instead of water.",
            "Place near water to clean after use.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "7 Blocks"
        );

        SPONGE_SUPER_MASSIVE = ThemeType.themedSlimefunItemStack(
            "CRY_SPONGE_SUPER_MASSIVE",
            GeneralUtils.getPreEnchantedItemStack(Material.DEAD_FIRE_CORAL_BLOCK),
            ThemeType.TOOL,
            "Super Massive Sponge",
            "This imbued sponge is capable",
            "of absorbing lava instead of water.",
            "Place near water to clean after use.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "10 Blocks"
        );

        SLEEPING_BAG = ThemeType.themedSlimefunItemStack(
            "CRY_SLEEPING_BAG",
            new ItemStack(Material.LIGHT_BLUE_BANNER),
            ThemeType.TOOL,
            "Rune-marked Sleeping Bag",
            "This sleeping bag emits a weak",
            "protection magic that will defend",
            "you in the wilds.",
            "Does not set your respawn point."
        );

        DISPLACED_VOID = ThemeType.themedSlimefunItemStack(
            "CRY_DISPLACED_VOID",
            new ItemStack(Material.COCOA_BEANS),
            ThemeType.TOOL,
            "Displaced Void",
            "A Shattered Void displaced into a",
            "pocket sized and portable form.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Hold Shift to pick up nearby Items"
        );

        SATCHEL_1 = ThemeType.themedSlimefunItemStack(
            "CRY_SATCHEL_1",
            new ItemStack(Material.WHITE_CONCRETE),
            ThemeType.TOOL,
            "Apprentice's Satchel",
            "This simple satchel will pick up",
            "and hold crystals for you.",
            "",
            ThemeType.WARNING.getColor() + "Rarities: " + ThemeType.PASSIVE.getColor() + "Unique only"
        );

        SATCHEL_2 = ThemeType.themedSlimefunItemStack(
            "CRY_SATCHEL_2",
            new ItemStack(Material.GRAY_CONCRETE),
            ThemeType.TOOL,
            "Crystamage's Satchel",
            "This simple satchel will pick up",
            "and hold crystals for you.",
            "",
            ThemeType.WARNING.getColor() + "Rarities: " + ThemeType.PASSIVE.getColor() + "Unique -> Common"
        );

        SATCHEL_3 = ThemeType.themedSlimefunItemStack(
            "CRY_SATCHEL_3",
            new ItemStack(Material.LIME_CONCRETE),
            ThemeType.TOOL,
            "Wizard's Satchel",
            "This simple satchel will pick up",
            "and hold crystals for you.",
            "",
            ThemeType.WARNING.getColor() + "Rarities: " + ThemeType.PASSIVE.getColor() + "Unique -> Uncommon"
        );

        SATCHEL_4 = ThemeType.themedSlimefunItemStack(
            "CRY_SATCHEL_4",
            new ItemStack(Material.YELLOW_CONCRETE),
            ThemeType.TOOL,
            "Conjurer's Satchel",
            "This simple satchel will pick up",
            "and hold crystals for you.",
            "",
            ThemeType.WARNING.getColor() + "Rarities: " + ThemeType.PASSIVE.getColor() + "Unique -> Rare"
        );

        SATCHEL_5 = ThemeType.themedSlimefunItemStack(
            "CRY_SATCHEL_5",
            new ItemStack(Material.PURPLE_CONCRETE),
            ThemeType.TOOL,
            "Sorcerer's Satchel",
            "This simple satchel will pick up",
            "and hold crystals for you.",
            "",
            ThemeType.WARNING.getColor() + "Rarities: " + ThemeType.PASSIVE.getColor() + "Unique -> Epic"
        );

        SATCHEL_6 = ThemeType.themedSlimefunItemStack(
            "CRY_SATCHEL_6",
            new ItemStack(Material.RED_CONCRETE),
            ThemeType.TOOL,
            "Grandmaster's Satchel",
            "This simple satchel will pick up",
            "and hold crystals for you.",
            "",
            ThemeType.WARNING.getColor() + "Rarities: " + ThemeType.PASSIVE.getColor() + "Unique -> Mythical"
        );

        CARGO_COVER = ThemeType.themedSlimefunItemStack(
            "CRY_CARGO_COVER",
            new ItemStack(Material.PAPER),
            ThemeType.TOOL,
            "Block Veil - Cargo",
            "Right click to place a magical",
            "block veil over a cargo node.",
            "The cover will mimic the block",
            "in your offhand.",
            "One time use per item."
        );

        ENERGY_NET_COVER = ThemeType.themedSlimefunItemStack(
            "CRY_ENERGY_NET_COVER",
            new ItemStack(Material.PAPER),
            ThemeType.TOOL,
            "Block Veil - Energy Net",
            "Right click to place a magical",
            "block veil over a cargo node.",
            "The cover will mimic the block",
            "in your offhand.",
            "One time use per item."
        );

        NETWORKS_COVER = ThemeType.themedSlimefunItemStack(
            "CRY_NETWORK_COVER",
            new ItemStack(Material.PAPER),
            ThemeType.TOOL,
            "Block Veil - Networks",
            "Right click to place a magical",
            "block veil over a Networks node.",
            "The cover will mimic the block",
            "in your offhand.",
            "One time use per item."
        );

        SPELL_TROPHY = ThemeType.themedSlimefunItemStack(
            "CRY_SPELL_TROPHY",
            new ItemStack(Material.PAPER),
            ThemeType.CRAFTING,
            "Proofs: Grandmaster Magus",
            "Proof that you are simply the best.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Spell Rank > Grandmaster Magus"
        );

        STORY_TROPHY = ThemeType.themedSlimefunItemStack(
            "CRY_STORY_TROPHY",
            new ItemStack(Material.PAPER),
            ThemeType.CRAFTING,
            "Proofs: Emeritus Professor",
            "Proof that you are simply the best.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Story Rank > Emeritus Professor"
        );

        CHRISTMAS_TROPHY = ThemeType.themedSlimefunItemStack(
            "CRY_CHRISTMAS_TROPHY",
            new ItemStack(Material.SPRUCE_SAPLING),
            ThemeType.CRAFTING,
            "Merry Christmas",
            "A little gift for you... but you",
            "have to work for it!",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Can only be crafted during the holidays"
        );

        VALENTINES_TROPHY = ThemeType.themedSlimefunItemStack(
            "CRY_VALENTINES_TROPHY",
            new ItemStack(Material.PINK_DYE),
            ThemeType.CRAFTING,
            "Happy Valentines Day",
            "A little love goes a long way.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Can only be crafted when love is in the air"
        );

        BIRTHDAY_TROPHY_CHEESY = ThemeType.themedSlimefunItemStack(
            "CRY_BIRTHDAY_TROPHY_CHEESY",
            new ItemStack(Material.CAKE),
            ThemeType.CRAFTING,
            "Happy Birthday Cheesy!",
            "A Gentleman at Heart",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Cheesy's Birthday!"
        );

        BIRTHDAY_TROPHY_BWHITE = ThemeType.themedSlimefunItemStack(
            "CRY_BIRTHDAY_TROPHY_BWHITE",
            new ItemStack(Material.CAKE),
            ThemeType.CRAFTING,
            "Happy Birthday BWhite!",
            "Joint creator of the biggest Tantrum ever!",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: BWhite's Birthday!"
        );

        BIRTHDAY_TROPHY_DECOY = ThemeType.themedSlimefunItemStack(
            "CRY_BIRTHDAY_TROPHY_DECOY",
            new ItemStack(Material.CAKE),
            ThemeType.CRAFTING,
            "Happy Birthday Decoy!",
            "Joint creator of the biggest Tantrum ever!",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Decoy's Birthday!"
        );

        BIRTHDAY_TROPHY_ODDISH = ThemeType.themedSlimefunItemStack(
            "CRY_BIRTHDAY_TROPHY_ODDISH",
            new ItemStack(Material.CAKE),
            ThemeType.CRAFTING,
            "Happy Birthday Oddish!",
            "A Gloom waiting to happen",
            "",
            ThemeType.CLICK_INFO.getColor() + "Requires: Oddish's Birthday!"
        );

        // Recipe Types

        RECIPE_TYPE_LIQUEFACTION_CRAFTING = ThemeType.themedItemStack(
            Material.CAULDRON,
            ThemeType.MECHANISM,
            "Liquefaction Basin",
            "Formed by dropping the relevant item",
            "into a filled Liquefaction Basin containing",
            "the correct Crystamae."
        );

        RECIPE_TYPE_LIQUEFACTION_SPELL = ThemeType.themedItemStack(
            Material.CAULDRON,
            ThemeType.MECHANISM,
            "Liquefaction Basin",
            "Formed by dropping a basic plate into a",
            "filled Liquefaction Basin containing",
            "the correct Crystamae."
        );

        RECIPE_TYPE_NETHER_DRAINING = ThemeType.themedItemStack(
            Material.OBSIDIAN,
            ThemeType.RESEARCH,
            "Nether Draining",
            "Crysta has a hard time transferring",
            "to the Nether. If it is in it's Crystal",
            "form, all the magic will be drained.",
            "Throw a Mythical Crystal into a nether",
            "portal to drain it.",
            "Any Mythical Crystal will work."
        );

        RECIPE_TYPE_REALISATION_ALTAR_NORMAL = ThemeType.themedItemStack(
            Material.CHISELED_DEEPSLATE,
            ThemeType.MECHANISM,
            "Realisation Altar",
            "Crystals are gathered when mining the fully",
            "formed crystals that gather around an active",
            "Altar. Giving the Altar storied blocks will",
            "produce crystals."
        );

        RECIPE_TYPE_REALISATION_ALTAR_SIGIL = ThemeType.themedItemStack(
            Material.CHISELED_DEEPSLATE,
            ThemeType.MECHANISM,
            "Realisation Altar",
            "Arcane Sigils have a small chance to be",
            "produced when realising Gilded Items."
        );
    }
}
