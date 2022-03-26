package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Runes {

    @Getter
    private static UnplaceableBlock runeBeast;
    @Getter
    private static UnplaceableBlock runeBeginning;
    @Getter
    private static UnplaceableBlock runeMoon;
    @Getter
    private static UnplaceableBlock runeGate;
    @Getter
    private static UnplaceableBlock runeTrueEarth;
    @Getter
    private static UnplaceableBlock runeChange;
    @Getter
    private static UnplaceableBlock runeNight;
    @Getter
    private static UnplaceableBlock runeBlack;
    @Getter
    private static UnplaceableBlock runeTrueHoly;
    @Getter
    private static UnplaceableBlock runeDragon;
    @Getter
    private static UnplaceableBlock runeTrueWater;
    @Getter
    private static UnplaceableBlock runeSovereign;
    @Getter
    private static UnplaceableBlock runeSun;
    @Getter
    private static UnplaceableBlock runeDawn;
    @Getter
    private static UnplaceableBlock runeTwilight;
    @Getter
    private static UnplaceableBlock runeTrueFire;
    @Getter
    private static UnplaceableBlock runeCircle;
    @Getter
    private static UnplaceableBlock runeBlinking;
    @Getter
    private static UnplaceableBlock runeSoul;
    @Getter
    private static UnplaceableBlock runePunishment;
    @Getter
    private static UnplaceableBlock runeTrueLightning;
    @Getter
    private static UnplaceableBlock runeEightfold;
    @Getter
    private static UnplaceableBlock runeCharm;
    @Getter
    private static UnplaceableBlock runeTrueWind;
    @Getter
    private static UnplaceableBlock runeBlackSword;
    @Getter
    private static UnplaceableBlock runeBrightShield;

    public static void setup() {

        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Rune A
        runeBeast = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BEAST,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                SlimefunItems.INFERNAL_BONEMEAL, SlimefunItems.NECROTIC_SKULL, new ItemStack(Material.BONE),
                new ItemStack(Material.LEAD), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.LEAD),
                new ItemStack(Material.BONE), SlimefunItems.NECROTIC_SKULL, SlimefunItems.INFERNAL_BONEMEAL
            }
        );

        // Rune B
        runeBeginning = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BEGINNING,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.GILDED_PEARL, new ItemStack(Material.SHULKER_SHELL), SlimefunItems.URANIUM,
                new ItemStack(Material.AZURE_BLUET), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.AZURE_BLUET),
                SlimefunItems.URANIUM, new ItemStack(Material.SHULKER_SHELL), CrystaStacks.GILDED_PEARL
            }
        );

        // Rune C
        runeMoon = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_MOON,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.ANGEL_BLOCK, new ItemStack(Material.WITHER_ROSE), SlimefunItems.POWER_CRYSTAL,
                new ItemStack(Material.GRAY_DYE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.GRAY_DYE),
                SlimefunItems.POWER_CRYSTAL, new ItemStack(Material.WITHER_ROSE), CrystaStacks.ANGEL_BLOCK
            }
        );

        // Rune D
        runeGate = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_GATE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.STAVE_BASIC, new ItemStack(Material.BLAZE_ROD), SlimefunItems.GOLD_24K,
                new ItemStack(Material.CRIMSON_FENCE_GATE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.CRIMSON_FENCE_GATE),
                SlimefunItems.GOLD_24K, new ItemStack(Material.BLAZE_ROD), CrystaStacks.STAVE_BASIC
            }
        );

        // Rune E
        runeTrueEarth = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_EARTH,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.DREADFUL_DIRT, new ItemStack(Material.PODZOL), CrystaStacks.CURSED_EARTH,
                new ItemStack(Material.MOSS_BLOCK), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.MOSS_BLOCK),
                CrystaStacks.CURSED_EARTH, new ItemStack(Material.PODZOL), CrystaStacks.DREADFUL_DIRT
            }
        );

        // Rune F
        runeChange = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_CHANGE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.ENDER_INHIBITOR_BASIC, new ItemStack(Material.LEVER), SlimefunItems.PROGRAMMABLE_ANDROID,
                new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON),
                SlimefunItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.LEVER), CrystaStacks.ENDER_INHIBITOR_BASIC
            }
        );

        // Rune G
        runeNight = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_NIGHT,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.DISPLACED_VOID, new ItemStack(Material.CLOCK), SlimefunItems.NEPTUNIUM,
                new ItemStack(Material.WEEPING_VINES), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.WEEPING_VINES),
                SlimefunItems.NEPTUNIUM, new ItemStack(Material.CLOCK), CrystaStacks.DISPLACED_VOID
            }
        );

        // Rune H
        runeBlack = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BLACK,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.PAINT_BRUSH_BLACK_100, new ItemStack(Material.BLACK_CANDLE), SlimefunItems.NECROTIC_SKULL,
                new ItemStack(Material.BLACK_STAINED_GLASS_PANE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.BLACK_STAINED_GLASS_PANE),
                SlimefunItems.NECROTIC_SKULL, new ItemStack(Material.BLACK_CANDLE), CrystaStacks.PAINT_BRUSH_BLACK_100
            }
        );

        // Rune I
        runeTrueHoly = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_HOLY,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.SOUL_STAND, new ItemStack(Material.WHITE_TULIP), CrystaStacks.BODY_STAND,
                CrystaStacks.MIND_STAND, CrystaStacks.ARCANE_SIGIL, CrystaStacks.MIND_STAND,
                CrystaStacks.BODY_STAND, new ItemStack(Material.WHITE_TULIP), CrystaStacks.SOUL_STAND
            }
        );

        // Rune J
        runeDragon = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_DRAGON,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_RARE, new ItemStack(Material.DRAGON_HEAD), new ItemStack(Material.DRAGON_EGG),
                new ItemStack(Material.DRAGON_BREATH), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.DRAGON_BREATH),
                new ItemStack(Material.DRAGON_EGG), new ItemStack(Material.DRAGON_HEAD), CrystaStacks.AMALGAMATE_DUST_RARE
            }
        );

        // Rune K
        runeTrueWater = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_WATER,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_SEA_BREEZE, new ItemStack(Material.WATER_BUCKET), SlimefunItems.WATER_RUNE,
                new ItemStack(Material.NAUTILUS_SHELL), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.NAUTILUS_SHELL),
                SlimefunItems.WATER_RUNE, new ItemStack(Material.WATER_BUCKET), CrystaStacks.EXALTED_SEA_BREEZE
            }
        );

        // Rune L
        runeSovereign = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_SOVEREIGN,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.CONNECTING_COMPASS, new ItemStack(Material.BELL), SlimefunItems.CARBONADO,
                new ItemStack(Material.OBSIDIAN), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.OBSIDIAN),
                SlimefunItems.CARBONADO, new ItemStack(Material.BELL), CrystaStacks.CONNECTING_COMPASS
            }
        );

        // Rune M
        runeSun = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUN_SUN,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_SUN, new ItemStack(Material.SHROOMLIGHT), SlimefunItems.BLISTERING_INGOT_3,
                new ItemStack(Material.GLOWSTONE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.GLOWSTONE),
                SlimefunItems.BLISTERING_INGOT_3, new ItemStack(Material.SHROOMLIGHT), CrystaStacks.EXALTED_SUN
            }
        );

        // Rune N
        runeDawn = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_DAWN,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_DAWN, new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), SlimefunItems.APPLE_JUICE,
                new ItemStack(Material.ORANGE_TULIP), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.ORANGE_TULIP),
                SlimefunItems.APPLE_JUICE, new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), CrystaStacks.EXALTED_DAWN
            }
        );

        // Rune O
        runeTwilight = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TWILIGHT,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_DUSK, new ItemStack(Material.FIRE_CORAL_BLOCK), SlimefunItems.PUMPKIN_JUICE,
                new ItemStack(Material.CORNFLOWER), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.CORNFLOWER),
                SlimefunItems.PUMPKIN_JUICE, new ItemStack(Material.FIRE_CORAL_BLOCK), CrystaStacks.EXALTED_DUSK
            }
        );

        // Rune P
        runeTrueFire = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_FIRE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EVISCERATING_PLATE, new ItemStack(Material.SOUL_CAMPFIRE), SlimefunItems.FIRE_RUNE,
                new ItemStack(Material.FIRE_CHARGE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.FIRE_CHARGE),
                SlimefunItems.FIRE_RUNE, new ItemStack(Material.SOUL_CAMPFIRE), CrystaStacks.EVISCERATING_PLATE
            }
        );

        // Rune Q
        runeCircle = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_CIRCLE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_FERTILITY_PHARO, new ItemStack(Material.OBSERVER), SlimefunItems.ELECTRIC_INGOT_FACTORY_3,
                new ItemStack(Material.TARGET), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.TARGET),
                SlimefunItems.ELECTRIC_INGOT_FACTORY_3, new ItemStack(Material.OBSERVER), CrystaStacks.EXALTED_FERTILITY_PHARO
            }
        );

        // Rune R
        runeBlinking = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BLINKING,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.WAYSTONE, new ItemStack(Material.ENDER_EYE), SlimefunItems.GPS_EMERGENCY_TRANSMITTER,
                new ItemStack(Material.ENDER_PEARL), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.ENDER_PEARL),
                SlimefunItems.GPS_EMERGENCY_TRANSMITTER, new ItemStack(Material.ENDER_EYE), CrystaStacks.WAYSTONE
            }
        );

        // Rune S
        runeSoul = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_SOUL,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.SOUL_STAND, new ItemStack(Material.ELYTRA), SlimefunItems.SOULBOUND_RUNE,
                new ItemStack(Material.CAKE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.CAKE),
                SlimefunItems.SOULBOUND_RUNE, new ItemStack(Material.ELYTRA), CrystaStacks.SOUL_STAND
            }
        );

        // Rune T
        runePunishment = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_PUNISHMENT,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.MOB_CANDLE_DIM, new ItemStack(Material.LEAD), SlimefunItems.MONSTER_JERKY,
                new ItemStack(Material.SOUL_LANTERN), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.SOUL_LANTERN),
                SlimefunItems.MONSTER_JERKY, new ItemStack(Material.LEAD), CrystaStacks.MOB_CANDLE_DIM
            }
        );

        // Rune U
        runeTrueLightning = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_LIGHTNING,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.LUMINESCENCE_SCOOP, new ItemStack(Material.LIGHTNING_ROD), SlimefunItems.LIGHTNING_RUNE,
                new ItemStack(Material.CHAIN), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.CHAIN),
                SlimefunItems.LIGHTNING_RUNE, new ItemStack(Material.LIGHTNING_ROD), CrystaStacks.LUMINESCENCE_SCOOP
            }
        );

        // Rune V
        runeEightfold = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_EIGHTFOLD,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EPHEMERAL_WORKBENCH, new ItemStack(Material.SPONGE), SlimefunItems.ENCHANTMENT_RUNE,
                new ItemStack(Material.BASALT), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.BASALT),
                SlimefunItems.ENCHANTMENT_RUNE, new ItemStack(Material.SPONGE), CrystaStacks.EPHEMERAL_WORKBENCH
            }
        );

        // Rune W
        runeCharm = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_CHARM,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_BEACON, new ItemStack(Material.RED_CANDLE), SlimefunItems.RAINBOW_RUNE,
                new ItemStack(Material.POPPY), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.POPPY),
                SlimefunItems.RAINBOW_RUNE, new ItemStack(Material.RED_CANDLE), CrystaStacks.EXALTED_BEACON
            }
        );

        // Rune X
        runeTrueWind = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_WIND,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.SPIRITUAL_SILKEN, new ItemStack(Material.END_ROD), SlimefunItems.AIR_RUNE,
                new ItemStack(Material.FEATHER), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.FEATHER),
                SlimefunItems.AIR_RUNE, new ItemStack(Material.END_ROD), CrystaStacks.SPIRITUAL_SILKEN
            }
        );

        // Rune Y
        runeBlackSword = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BLACK_SWORD,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.INERT_PLATE, new ItemStack(Material.NETHERITE_SWORD), SlimefunItems.SWORD_OF_BEHEADING,
                new ItemStack(Material.GOLDEN_SWORD), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.GOLDEN_SWORD),
                SlimefunItems.SWORD_OF_BEHEADING, new ItemStack(Material.NETHERITE_SWORD), CrystaStacks.INERT_PLATE
            }
        );

        // Rune Z
        runeBrightShield = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BRIGHT_SHIELD,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.INERT_PLATE, new ItemStack(Material.TURTLE_HELMET), SlimefunItems.SOLAR_HELMET,
                new ItemStack(Material.SHIELD), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.SHIELD),
                SlimefunItems.SOLAR_HELMET, new ItemStack(Material.TURTLE_HELMET), CrystaStacks.INERT_PLATE
            }
        );

        // Slimefun Registry
        runeBeast.register(plugin);
        runeBeginning.register(plugin);
        runeMoon.register(plugin);
        runeGate.register(plugin);
        runeTrueEarth.register(plugin);
        runeChange.register(plugin);
        runeNight.register(plugin);
        runeBlack.register(plugin);
        runeTrueHoly.register(plugin);
        runeDragon.register(plugin);
        runeTrueWater.register(plugin);
        runeSovereign.register(plugin);
        runeSun.register(plugin);
        runeDawn.register(plugin);
        runeTwilight.register(plugin);
        runeTrueFire.register(plugin);
        runeCircle.register(plugin);
        runeBlinking.register(plugin);
        runeSoul.register(plugin);
        runePunishment.register(plugin);
        runeTrueLightning.register(plugin);
        runeEightfold.register(plugin);
        runeCharm.register(plugin);
        runeTrueWind.register(plugin);
        runeBlackSword.register(plugin);
        runeBrightShield.register(plugin);

    }

}
