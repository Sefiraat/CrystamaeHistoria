package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@UtilityClass
public class ArmourStandUtils {

    @ParametersAreNonnullByDefault
    public static void setDisplay(ArmorStand a) {
        a.setVisible(false);
        a.setGravity(false);
        a.setBasePlate(false);
        a.setCustomNameVisible(false);
        a.setRemoveWhenFarAway(false);
        a.setCollidable(false);
        a.setInvulnerable(true);
        a.setCustomName(ThemeType.getRandomEggName());
        PersistentDataAPI.setBoolean(a, CrystamaeHistoria.getKeys().getPdcIsDisplayStand(), true);
    }

    @ParametersAreNonnullByDefault
    public void setDisplayItem(ArmorStand armorStand, Material material) {
        setDisplayItem(armorStand, new ItemStack(material));
    }

    @ParametersAreNonnullByDefault
    public void setDisplayItem(ArmorStand armorStand, ItemStack itemStack) {
        clearDisplayItem(armorStand);
        armorStand.getEquipment().setHelmet(itemStack);
    }

    public void clearDisplayItem(ArmorStand armorStand) {
        armorStand.getEquipment().setHelmet(null);
    }

    @ParametersAreNonnullByDefault
    public static boolean isDisplayStand(ArmorStand a) {
        return PersistentDataAPI.getBoolean(a, CrystamaeHistoria.getKeys().getPdcIsDisplayStand());
    }

}
