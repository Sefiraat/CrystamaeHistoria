package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import lombok.Data;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;

@Data
public class Keys {

    // BlockStorage
    public static final String BS_CP_WORKING_ON = "BS_CP_WORKING_ON";
    public static final String BS_CP_ACTIVE_PLAYER = "BS_CP_ACTIVE_PLAYER";
    public static final String BS_CP_STORIED = "BS_CP_STORIED";
    public static final String BS_CP_STORIES = "BS_CP_STORIES";

    // PDC Json
    public static final String JS_S_AVAILABLE_STORIES = "JS_S_AS";
    public static final String JS_S_TIER = "JS_S_T";

    // Misc
    public static final String PANEL_STAND_PREFIX = "CH_PANEL_";

    // Recipe Types
    public static final NamespacedKey REALISATION_DUMMY_CRYSTAL = newKey("r_d_c");
    public static final NamespacedKey LIQUEFACTION_DUMMY_CRYSTAL = newKey("r_d_c");

    // PDC
    // Items
    public static final NamespacedKey PDC_IS_STORIED = newKey("is_s");
    public static final NamespacedKey PDC_POTENTIAL_STORIES = newKey("s_pot");
    public static final NamespacedKey PDC_CURRENT_NUMBER_OF_STORIES = newKey("s_cur_n");
    public static final NamespacedKey PDC_STORIES = newKey("s_list");
    public static final NamespacedKey PDC_PLATE_STORAGE = newKey("plt");
    public static final NamespacedKey PDC_STAVE_STORAGE = newKey("stv");

    // Type - Story
    public static final NamespacedKey STORY_ID = newKey("s_id");
    public static final NamespacedKey STORY_RARITY = newKey("s_r");
    public static final NamespacedKey STORY_TYPE = newKey("s_t");

    // Type - Plate
    public static final NamespacedKey PLATE_TIER = newKey("p_t");
    public static final NamespacedKey PLATE_SPELL = newKey("p_s");
    public static final NamespacedKey PLATE_CHARGES = newKey("p_c");
    public static final NamespacedKey PLATE_COOLDOWN = newKey("p_cd");

    // Type - Plate
    public static final NamespacedKey STAVE_SLOT = newKey("sv_s");
    public static final NamespacedKey STAVE_PLATE = newKey("sv_p");

    // Entities
    public static final NamespacedKey PDC_IS_DISPLAY_STAND = newKey("a_dpy");
    public static final NamespacedKey PDC_IS_INVULNERABLE = newKey("invul");
    public static final NamespacedKey PDC_IS_WEATHER_WITHER = newKey("weather");
    public static final NamespacedKey PDC_IS_SPAWN_OWNER = newKey("owner");

    // Chunk Storage
    public static final NamespacedKey RESOLUTION_CRYSTAL_MAP = newKey("c_r_c");
    public static final NamespacedKey RESOLUTION_RARITY_MAP = newKey("c_r_r");
    public static final NamespacedKey RESOLUTION_STORY_MAP = newKey("c_r_s");
    public static final NamespacedKey RESOLUTION_STORY_LOCATION = newKey("c_r_l");
    public static final NamespacedKey RESOLUTION_STORY_WORLD = newKey("c_r_w");

    private Keys() {
        throw new IllegalStateException("Utility Class");
    }

    @Nonnull
    public static NamespacedKey newKey(@Nonnull String value) {
        return new NamespacedKey(CrystamaeHistoria.getInstance(), value);
    }
}
