package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import me.mrCookieSlime.Slimefun.cscorelib2.data.PersistentDataAPI;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class EntityUtils {

    public static void setArmourStandInternal(ArmorStand a, String s) {
        PersistentDataAPI.setString(a, CrystamaeHistoria.inst().getKeyHolder().getPdcArmourStandName(), s);
    }

    public static String getArmourStandInternal(ArmorStand a) {
        return PersistentDataAPI.getString(a, CrystamaeHistoria.inst().getKeyHolder().getPdcArmourStandName());
    }

    public static void setDisplayStand(ArmorStand a) {
        PersistentDataAPI.setBoolean(a, CrystamaeHistoria.inst().getKeyHolder().getPdcIsDisplayStand(), true);
    }

    public static Boolean isDisplayStand(ArmorStand a) {
        return PersistentDataAPI.getBoolean(a, CrystamaeHistoria.inst().getKeyHolder().getPdcIsDisplayStand());
    }


    public static void setDisplayOnlyArmourStand(ArmorStand a) {
        a.setVisible(false);
        a.setGravity(false);
        a.setBasePlate(false);
        a.setCustomNameVisible(false);
        a.setRemoveWhenFarAway(false);
        a.setCollidable(false);
        a.setInvulnerable(true);
        a.setCustomName(ThemeUtils.getRandomEggName());
        setDisplayStand(a);
    }

}
