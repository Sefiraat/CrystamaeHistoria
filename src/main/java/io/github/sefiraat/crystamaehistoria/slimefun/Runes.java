package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.PowderedEssence;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Skulls;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;

@UtilityClass
public class Runes {

    @Getter
    private static UnplaceableBlock rune_a;
    private static UnplaceableBlock rune_b;
    private static UnplaceableBlock rune_c;
    private static UnplaceableBlock rune_d;
    private static UnplaceableBlock rune_e;
    private static UnplaceableBlock rune_f;
    private static UnplaceableBlock rune_g;
    private static UnplaceableBlock rune_h;
    private static UnplaceableBlock rune_i;
    private static UnplaceableBlock rune_j;
    private static UnplaceableBlock rune_k;
    private static UnplaceableBlock rune_l;
    private static UnplaceableBlock rune_m;
    private static UnplaceableBlock rune_n;
    private static UnplaceableBlock rune_o;
    private static UnplaceableBlock rune_p;
    private static UnplaceableBlock rune_q;
    private static UnplaceableBlock rune_r;
    private static UnplaceableBlock rune_s;
    private static UnplaceableBlock rune_t;
    private static UnplaceableBlock rune_u;
    private static UnplaceableBlock rune_v;
    private static UnplaceableBlock rune_w;
    private static UnplaceableBlock rune_x;
    private static UnplaceableBlock rune_y;
    private static UnplaceableBlock rune_z;

    public static void setup() {

        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Rune A
        rune_a = new UnplaceableBlock(
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
        rune_b = new UnplaceableBlock(
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
        rune_c = new UnplaceableBlock(
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
        rune_d = new UnplaceableBlock(
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
        rune_e = new UnplaceableBlock(
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
        rune_f = new UnplaceableBlock(
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
        rune_g = new UnplaceableBlock(
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
        rune_h = new UnplaceableBlock(
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
        rune_i = new UnplaceableBlock(
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
        rune_j = new UnplaceableBlock(
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
        rune_k = new UnplaceableBlock(
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
        rune_l = new UnplaceableBlock(
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
        rune_m = new UnplaceableBlock(
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
        rune_n = new UnplaceableBlock(
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
        rune_o = new UnplaceableBlock(
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
        rune_p = new UnplaceableBlock(
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
        rune_q = new UnplaceableBlock(
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
        rune_r = new UnplaceableBlock(
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
        rune_s = new UnplaceableBlock(
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
        rune_t = new UnplaceableBlock(
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
        rune_u = new UnplaceableBlock(
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
        rune_v = new UnplaceableBlock(
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
        rune_w = new UnplaceableBlock(
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
        rune_x = new UnplaceableBlock(
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
        rune_y = new UnplaceableBlock(
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
        rune_z = new UnplaceableBlock(
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
        rune_a.register(plugin);
        rune_b.register(plugin);
        rune_c.register(plugin);
        rune_d.register(plugin);
        rune_e.register(plugin);
        rune_f.register(plugin);
        rune_g.register(plugin);
        rune_h.register(plugin);
        rune_i.register(plugin);
        rune_j.register(plugin);
        rune_k.register(plugin);
        rune_l.register(plugin);
        rune_m.register(plugin);
        rune_n.register(plugin);
        rune_o.register(plugin);
        rune_p.register(plugin);
        rune_q.register(plugin);
        rune_r.register(plugin);
        rune_s.register(plugin);
        rune_t.register(plugin);
        rune_u.register(plugin);
        rune_v.register(plugin);
        rune_w.register(plugin);
        rune_x.register(plugin);
        rune_y.register(plugin);
        rune_z.register(plugin);

    }

}
