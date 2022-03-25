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
    private static UnplaceableBlock runeA;
    @Getter
    private static UnplaceableBlock runeB;
    @Getter
    private static UnplaceableBlock runeC;
    @Getter
    private static UnplaceableBlock runeD;
    @Getter
    private static UnplaceableBlock runeE;
    @Getter
    private static UnplaceableBlock runeF;
    @Getter
    private static UnplaceableBlock runeG;
    @Getter
    private static UnplaceableBlock runeH;
    @Getter
    private static UnplaceableBlock runeI;
    @Getter
    private static UnplaceableBlock runeJ;
    @Getter
    private static UnplaceableBlock runeK;
    @Getter
    private static UnplaceableBlock runeL;
    @Getter
    private static UnplaceableBlock runeM;
    @Getter
    private static UnplaceableBlock runeN;
    @Getter
    private static UnplaceableBlock runeO;
    @Getter
    private static UnplaceableBlock runeP;
    @Getter
    private static UnplaceableBlock runeQ;
    @Getter
    private static UnplaceableBlock runeR;
    @Getter
    private static UnplaceableBlock runeS;
    @Getter
    private static UnplaceableBlock runeT;
    @Getter
    private static UnplaceableBlock runeU;
    @Getter
    private static UnplaceableBlock runeV;
    @Getter
    private static UnplaceableBlock runeW;
    @Getter
    private static UnplaceableBlock runeX;
    @Getter
    private static UnplaceableBlock runeY;
    @Getter
    private static UnplaceableBlock runeZ;

    public static void setup() {

        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Rune A
        runeA = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_A,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                SlimefunItems.INFERNAL_BONEMEAL, SlimefunItems.NECROTIC_SKULL, new ItemStack(Material.BONE),
                new ItemStack(Material.LEAD), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.LEAD),
                new ItemStack(Material.BONE), SlimefunItems.NECROTIC_SKULL, SlimefunItems.INFERNAL_BONEMEAL
            }
        );

        // Rune B
        runeB = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_B,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.GILDED_PEARL, new ItemStack(Material.SHULKER_SHELL), SlimefunItems.URANIUM,
                new ItemStack(Material.AZURE_BLUET), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.AZURE_BLUET),
                SlimefunItems.URANIUM, new ItemStack(Material.SHULKER_SHELL), CrystaStacks.GILDED_PEARL
            }
        );

        // Rune C
        runeC = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_C,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.ANGEL_BLOCK, new ItemStack(Material.WITHER_ROSE), SlimefunItems.POWER_CRYSTAL,
                new ItemStack(Material.GRAY_DYE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.GRAY_DYE),
                SlimefunItems.POWER_CRYSTAL, new ItemStack(Material.WITHER_ROSE), CrystaStacks.ANGEL_BLOCK
            }
        );

        // Rune D
        runeD = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_D,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.STAVE_BASIC, new ItemStack(Material.BLAZE_ROD), SlimefunItems.GOLD_24K,
                new ItemStack(Material.CRIMSON_FENCE_GATE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.CRIMSON_FENCE_GATE),
                SlimefunItems.GOLD_24K, new ItemStack(Material.BLAZE_ROD), CrystaStacks.STAVE_BASIC
            }
        );

        // Rune E
        runeE = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_E,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.DREADFUL_DIRT, new ItemStack(Material.PODZOL), CrystaStacks.CURSED_EARTH,
                new ItemStack(Material.MOSS_BLOCK), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.MOSS_BLOCK),
                CrystaStacks.CURSED_EARTH, new ItemStack(Material.PODZOL), CrystaStacks.DREADFUL_DIRT
            }
        );

        // Rune F
        runeF = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_F,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.ENDER_INHIBITOR_BASIC, new ItemStack(Material.LEVER), SlimefunItems.PROGRAMMABLE_ANDROID,
                new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON),
                SlimefunItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.LEVER), CrystaStacks.ENDER_INHIBITOR_BASIC
            }
        );

        // Rune G
        runeG = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_G,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.DISPLACED_VOID, new ItemStack(Material.CLOCK), SlimefunItems.NEPTUNIUM,
                new ItemStack(Material.WEEPING_VINES), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.WEEPING_VINES),
                SlimefunItems.NEPTUNIUM, new ItemStack(Material.CLOCK), CrystaStacks.DISPLACED_VOID
            }
        );

        // Rune H
        runeH = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_H,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.PAINT_BRUSH_BLACK_100, new ItemStack(Material.BLACK_CANDLE), SlimefunItems.NECROTIC_SKULL,
                new ItemStack(Material.BLACK_STAINED_GLASS_PANE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.BLACK_STAINED_GLASS_PANE),
                SlimefunItems.NECROTIC_SKULL, new ItemStack(Material.BLACK_CANDLE), CrystaStacks.PAINT_BRUSH_BLACK_100
            }
        );

        // Rune I
        runeI = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_I,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.SOUL_STAND, new ItemStack(Material.WHITE_TULIP), CrystaStacks.BODY_STAND,
                CrystaStacks.MIND_STAND, CrystaStacks.ARCANE_SIGIL, CrystaStacks.MIND_STAND,
                CrystaStacks.BODY_STAND, new ItemStack(Material.WHITE_TULIP), CrystaStacks.SOUL_STAND
            }
        );

        // Rune J
        runeJ = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_J,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_RARE, new ItemStack(Material.DRAGON_HEAD), new ItemStack(Material.DRAGON_EGG),
                new ItemStack(Material.DRAGON_BREATH), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.DRAGON_BREATH),
                new ItemStack(Material.DRAGON_EGG), new ItemStack(Material.DRAGON_HEAD), CrystaStacks.AMALGAMATE_DUST_RARE
            }
        );

        // Rune K
        runeK = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_K,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_SEA_BREEZE, new ItemStack(Material.WATER_BUCKET), SlimefunItems.WATER_RUNE,
                new ItemStack(Material.NAUTILUS_SHELL), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.NAUTILUS_SHELL),
                SlimefunItems.WATER_RUNE, new ItemStack(Material.WATER_BUCKET), CrystaStacks.EXALTED_SEA_BREEZE
            }
        );

        // Rune L
        runeL = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_L,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.CONNECTING_COMPASS, new ItemStack(Material.BELL), SlimefunItems.CARBONADO,
                new ItemStack(Material.OBSIDIAN), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.OBSIDIAN),
                SlimefunItems.CARBONADO, new ItemStack(Material.BELL), CrystaStacks.CONNECTING_COMPASS
            }
        );

        // Rune M
        runeM = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_M,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_SUN, new ItemStack(Material.SHROOMLIGHT), SlimefunItems.BLISTERING_INGOT_3,
                new ItemStack(Material.GLOWSTONE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.GLOWSTONE),
                SlimefunItems.BLISTERING_INGOT_3, new ItemStack(Material.SHROOMLIGHT), CrystaStacks.EXALTED_SUN
            }
        );

        // Rune N
        runeN = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_N,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_DAWN, new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), SlimefunItems.APPLE_JUICE,
                new ItemStack(Material.ORANGE_TULIP), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.ORANGE_TULIP),
                SlimefunItems.APPLE_JUICE, new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), CrystaStacks.EXALTED_DAWN
            }
        );

        // Rune O
        runeO = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_O,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_DUSK, new ItemStack(Material.FIRE_CORAL_BLOCK), SlimefunItems.PUMPKIN_JUICE,
                new ItemStack(Material.CORNFLOWER), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.CORNFLOWER),
                SlimefunItems.PUMPKIN_JUICE, new ItemStack(Material.FIRE_CORAL_BLOCK), CrystaStacks.EXALTED_DUSK
            }
        );

        // Rune P
        runeP = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_P,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EVISCERATING_PLATE, new ItemStack(Material.SOUL_CAMPFIRE), SlimefunItems.FIRE_RUNE,
                new ItemStack(Material.FIRE_CHARGE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.FIRE_CHARGE),
                SlimefunItems.FIRE_RUNE, new ItemStack(Material.SOUL_CAMPFIRE), CrystaStacks.EVISCERATING_PLATE
            }
        );

        // Rune Q
        runeQ = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_Q,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.FERTILITY_PHARO, new ItemStack(Material.OBSERVER), SlimefunItems.CROP_GROWTH_ACCELERATOR,
                new ItemStack(Material.TARGET), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.TARGET),
                SlimefunItems.CROP_GROWTH_ACCELERATOR, new ItemStack(Material.OBSERVER), CrystaStacks.FERTILITY_PHARO
            }
        );

        // Rune R
        runeR = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_R,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.WAYSTONE, new ItemStack(Material.ENDER_EYE), SlimefunItems.GPS_EMERGENCY_TRANSMITTER,
                new ItemStack(Material.ENDER_PEARL), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.ENDER_PEARL),
                SlimefunItems.GPS_EMERGENCY_TRANSMITTER, new ItemStack(Material.ENDER_EYE), CrystaStacks.WAYSTONE
            }
        );

        // Rune S
        runeS = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_S,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.SOUL_STAND, new ItemStack(Material.ELYTRA), SlimefunItems.SOULBOUND_RUNE,
                new ItemStack(Material.CAKE), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.CAKE),
                SlimefunItems.SOULBOUND_RUNE, new ItemStack(Material.ELYTRA), CrystaStacks.SOUL_STAND
            }
        );

        // Rune T
        runeT = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_T,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.MOB_CANDLE_DIM, new ItemStack(Material.LEAD), SlimefunItems.MONSTER_JERKY,
                new ItemStack(Material.SOUL_LANTERN), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.SOUL_LANTERN),
                SlimefunItems.MONSTER_JERKY, new ItemStack(Material.LEAD), CrystaStacks.MOB_CANDLE_DIM
            }
        );

        // Rune U
        runeU = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_U,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.LUMINESCENCE_SCOOP, new ItemStack(Material.LIGHTNING_ROD), SlimefunItems.LIGHTNING_RUNE,
                new ItemStack(Material.CHAIN), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.CHAIN),
                SlimefunItems.LIGHTNING_RUNE, new ItemStack(Material.LIGHTNING_ROD), CrystaStacks.LUMINESCENCE_SCOOP
            }
        );

        // Rune V
        runeV = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_V,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EPHEMERAL_WORKBENCH, new ItemStack(Material.SPONGE), SlimefunItems.ENCHANTMENT_RUNE,
                new ItemStack(Material.BASALT), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.BASALT),
                SlimefunItems.ENCHANTMENT_RUNE, new ItemStack(Material.SPONGE), CrystaStacks.EPHEMERAL_WORKBENCH
            }
        );

        // Rune W
        runeW = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_W,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.EXALTED_BEACON, new ItemStack(Material.RED_CANDLE), SlimefunItems.RAINBOW_RUNE,
                new ItemStack(Material.POPPY), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.POPPY),
                SlimefunItems.RAINBOW_RUNE, new ItemStack(Material.RED_CANDLE), CrystaStacks.EXALTED_BEACON
            }
        );

        // Rune X
        runeX = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_X,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.SPIRITUAL_SILKEN, new ItemStack(Material.END_ROD), SlimefunItems.AIR_RUNE,
                new ItemStack(Material.FEATHER), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.FEATHER),
                SlimefunItems.AIR_RUNE, new ItemStack(Material.END_ROD), CrystaStacks.SPIRITUAL_SILKEN
            }
        );

        // Rune Y
        runeY = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_Y,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.INERT_PLATE, new ItemStack(Material.NETHERITE_SWORD), SlimefunItems.SWORD_OF_BEHEADING,
                new ItemStack(Material.GOLDEN_SWORD), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.GOLDEN_SWORD),
                SlimefunItems.SWORD_OF_BEHEADING, new ItemStack(Material.NETHERITE_SWORD), CrystaStacks.INERT_PLATE
            }
        );

        // Rune Z
        runeZ = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_Z,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                CrystaStacks.INERT_PLATE, new ItemStack(Material.TURTLE_HELMET), SlimefunItems.SOLAR_HELMET,
                new ItemStack(Material.SHIELD), CrystaStacks.ARCANE_SIGIL, new ItemStack(Material.SHIELD),
                SlimefunItems.SOLAR_HELMET, new ItemStack(Material.TURTLE_HELMET), CrystaStacks.INERT_PLATE
            }
        );

        // Slimefun Registry
        runeA.register(plugin);
        runeB.register(plugin);
        runeC.register(plugin);
        runeD.register(plugin);
        runeE.register(plugin);
        runeF.register(plugin);
        runeG.register(plugin);
        runeH.register(plugin);
        runeI.register(plugin);
        runeJ.register(plugin);
        runeK.register(plugin);
        runeL.register(plugin);
        runeM.register(plugin);
        runeN.register(plugin);
        runeO.register(plugin);
        runeP.register(plugin);
        runeQ.register(plugin);
        runeR.register(plugin);
        runeS.register(plugin);
        runeT.register(plugin);
        runeU.register(plugin);
        runeV.register(plugin);
        runeW.register(plugin);
        runeX.register(plugin);
        runeY.register(plugin);
        runeZ.register(plugin);

    }

}
