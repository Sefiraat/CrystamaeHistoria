package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import lombok.Data;
import org.bukkit.NamespacedKey;

@Data
public class KeyHolder {

    // BlockStorage
    public static final String BS_CP_WORKING_ON = "BS_CP_WORKING_ON";
    public static final String BS_CP_STORIED = "BS_CP_STORIED";
    public static final String BS_CP_STORIES = "BS_CP_STORIES";

    // PDC Json
    public static final String JS_S_AVAILABLE_STORIES = "JS_S_AS";
    public static final String JS_S_TIER = "JS_S_T";

    // Misc
    public static final String PANEL_STAND_PREFIX = "CH_PANEL_";

    // Categories
    private final NamespacedKey catMain = new NamespacedKey(CrystamaeHistoria.inst(), "crysta");
    private final NamespacedKey catMech1 = new NamespacedKey(CrystamaeHistoria.inst(), "crysta-mech-1");

    // PDC
    // Items
    private final NamespacedKey pdcIsStoried = new NamespacedKey(CrystamaeHistoria.inst(), "is_storied");
    private final NamespacedKey pdcStories = new NamespacedKey(CrystamaeHistoria.inst(), "story_potential");
    private final NamespacedKey pdcCurrentNumberOfStories = new NamespacedKey(CrystamaeHistoria.inst(), "story_current_number");
    private final NamespacedKey pdcAppliedStoryList = new NamespacedKey(CrystamaeHistoria.inst(), "story_list");

    // Entities
    private final NamespacedKey pdcArmourStandName = new NamespacedKey(CrystamaeHistoria.inst(), "a_name");
    private final NamespacedKey pdcIsDisplayStand = new NamespacedKey(CrystamaeHistoria.inst(), "a_display");

}
