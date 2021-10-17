package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import lombok.Data;
import org.bukkit.NamespacedKey;

@Data
public class Keys {

    // BlockStorage
    public static final String BS_CP_WORKING_ON = "BS_CP_WORKING_ON";
    public static final String BS_CP_STORIED = "BS_CP_STORIED";
    public static final String BS_CP_STORIES = "BS_CP_STORIES";

    // PDC Json
    public static final String JS_S_AVAILABLE_STORIES = "JS_S_AS";
    public static final String JS_S_TIER = "JS_S_T";

    // Misc
    public static final String PANEL_STAND_PREFIX = "CH_PANEL_";

    // ItemGroups
    private final NamespacedKey catMain = new NamespacedKey(CrystamaeHistoria.getInstance(), "main");
    private final NamespacedKey catMechanical = new NamespacedKey(CrystamaeHistoria.getInstance(), "mech");
    private final NamespacedKey catCrystal = new NamespacedKey(CrystamaeHistoria.getInstance(), "crystal");
    private final NamespacedKey catTools = new NamespacedKey(CrystamaeHistoria.getInstance(), "TOOLS");

    // Recipe Types
    private final NamespacedKey realisationDummyCrystal = new NamespacedKey(CrystamaeHistoria.getInstance(), "r_d_c");

    // PDC
    // Items
    private final NamespacedKey pdcIsStoried = new NamespacedKey(CrystamaeHistoria.getInstance(), "is_s");
    private final NamespacedKey pdcPotentialStories = new NamespacedKey(CrystamaeHistoria.getInstance(), "s_pot");
    private final NamespacedKey pdcCurrentNumberOfStories = new NamespacedKey(CrystamaeHistoria.getInstance(), "s_cur_n");
    private final NamespacedKey pdcStories = new NamespacedKey(CrystamaeHistoria.getInstance(), "s_list");
    private final NamespacedKey pdcPlateStorage = new NamespacedKey(CrystamaeHistoria.getInstance(), "plt");
    private final NamespacedKey pdcStaveStorage = new NamespacedKey(CrystamaeHistoria.getInstance(), "stv");

    // Type - Story
    private final NamespacedKey storyId = new NamespacedKey(CrystamaeHistoria.getInstance(), "s_id");
    private final NamespacedKey storyRarity = new NamespacedKey(CrystamaeHistoria.getInstance(), "s_r");
    private final NamespacedKey storyType = new NamespacedKey(CrystamaeHistoria.getInstance(), "s_t");

    // Type - Plate
    private final NamespacedKey plateTier = new NamespacedKey(CrystamaeHistoria.getInstance(), "p_t");
    private final NamespacedKey plateSpell = new NamespacedKey(CrystamaeHistoria.getInstance(), "p_s");
    private final NamespacedKey plateCharges = new NamespacedKey(CrystamaeHistoria.getInstance(), "p_c");

    // Type - Plate
    private final NamespacedKey staveSlot = new NamespacedKey(CrystamaeHistoria.getInstance(), "sv_s");
    private final NamespacedKey stavePlate = new NamespacedKey(CrystamaeHistoria.getInstance(), "sv_p");

    // Entities
    private final NamespacedKey pdcArmourStandName = new NamespacedKey(CrystamaeHistoria.getInstance(), "a_nm");
    private final NamespacedKey pdcIsDisplayStand = new NamespacedKey(CrystamaeHistoria.getInstance(), "a_dpy");

    // CastableInstant Effects
    private final NamespacedKey spellCaster = new NamespacedKey(CrystamaeHistoria.getInstance(), "caster");
    private final NamespacedKey spellIsMagicProjectile = new NamespacedKey(CrystamaeHistoria.getInstance(), "is_mp");
    private final NamespacedKey spellMagicProjectileDamage = new NamespacedKey(CrystamaeHistoria.getInstance(), "mp_dmg");
    private final NamespacedKey spellMagicProjectileAoeRange = new NamespacedKey(CrystamaeHistoria.getInstance(), "mp_aoe");
    private final NamespacedKey spellMagicProjectileKnockback = new NamespacedKey(CrystamaeHistoria.getInstance(), "mp_kb");
    private final NamespacedKey spellMagicProjectileCooldownMulti = new NamespacedKey(CrystamaeHistoria.getInstance(), "mp_cm");
    private final NamespacedKey spellMagicProjectileDurabilityMulti = new NamespacedKey(CrystamaeHistoria.getInstance(), "mp_dm");

    // Chunk Storage
    private final NamespacedKey resolutionCrystalMap = new NamespacedKey(CrystamaeHistoria.getInstance(), "c_r_c");
    private final NamespacedKey resolutionRarityMap = new NamespacedKey(CrystamaeHistoria.getInstance(), "c_r_r");
    private final NamespacedKey resolutionStoryMap = new NamespacedKey(CrystamaeHistoria.getInstance(), "c_r_s");
    private final NamespacedKey resolutionStoryLocation = new NamespacedKey(CrystamaeHistoria.getInstance(), "c_r_l");
    private final NamespacedKey resolutionStoryWorld = new NamespacedKey(CrystamaeHistoria.getInstance(), "c_r_w");


}
