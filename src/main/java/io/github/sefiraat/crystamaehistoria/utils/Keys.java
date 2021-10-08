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

    // ItemGroup
    private final NamespacedKey catMain = new NamespacedKey(CrystamaeHistoria.inst(), "main");
    private final NamespacedKey catMechanical = new NamespacedKey(CrystamaeHistoria.inst(), "mech");
    private final NamespacedKey catCrystal = new NamespacedKey(CrystamaeHistoria.inst(), "crystal");
    private final NamespacedKey catTools = new NamespacedKey(CrystamaeHistoria.inst(), "tools");

    // Recipe Types
    private final NamespacedKey realisationDummyCrystal = new NamespacedKey(CrystamaeHistoria.inst(), "r_d_c");

    // PDC
    // Items
    private final NamespacedKey pdcIsStoried = new NamespacedKey(CrystamaeHistoria.inst(), "is_s");
    private final NamespacedKey pdcPotentialStories = new NamespacedKey(CrystamaeHistoria.inst(), "s_pot");
    private final NamespacedKey pdcCurrentNumberOfStories = new NamespacedKey(CrystamaeHistoria.inst(), "s_cur_n");
    private final NamespacedKey pdcStories = new NamespacedKey(CrystamaeHistoria.inst(), "s_list");
    private final NamespacedKey pdcStaveInstance = new NamespacedKey(CrystamaeHistoria.inst(), "stv");
    private final NamespacedKey pdcStaveLevel = new NamespacedKey(CrystamaeHistoria.inst(), "stv_lvl");

    // Entities
    private final NamespacedKey pdcArmourStandName = new NamespacedKey(CrystamaeHistoria.inst(), "a_nm");
    private final NamespacedKey pdcIsDisplayStand = new NamespacedKey(CrystamaeHistoria.inst(), "a_dpy");

    // CastableInstant Effects
    private final NamespacedKey spellCaster = new NamespacedKey(CrystamaeHistoria.inst(), "caster");
    private final NamespacedKey spellIsMagicProjectile = new NamespacedKey(CrystamaeHistoria.inst(), "is_mp");
    private final NamespacedKey spellMagicProjectileDamage = new NamespacedKey(CrystamaeHistoria.inst(), "mp_dmg");
    private final NamespacedKey spellMagicProjectileAoeRange = new NamespacedKey(CrystamaeHistoria.inst(), "mp_aoe");
    private final NamespacedKey spellMagicProjectileKnockback = new NamespacedKey(CrystamaeHistoria.inst(), "mp_kb");
    private final NamespacedKey spellMagicProjectileCooldownMulti = new NamespacedKey(CrystamaeHistoria.inst(), "mp_cm");
    private final NamespacedKey spellMagicProjectileDurabilityMulti = new NamespacedKey(CrystamaeHistoria.inst(), "mp_dm");

    // Chunk Storage
    private final NamespacedKey resolutionCrystalMap = new NamespacedKey(CrystamaeHistoria.inst(), "c_r_c");
    private final NamespacedKey resolutionRarityMap = new NamespacedKey(CrystamaeHistoria.inst(), "c_r_r");
    private final NamespacedKey resolutionStoryMap = new NamespacedKey(CrystamaeHistoria.inst(), "c_r_s");


}
