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
    public static void setDisplay(ArmorStand armorStand) {
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setCustomNameVisible(false);
        armorStand.setRemoveWhenFarAway(false);
        armorStand.setCollidable(false);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName(ThemeType.getRandomEggName());
        PersistentDataAPI.setBoolean(armorStand, CrystamaeHistoria.getKeys().getPdcIsDisplayStand(), true);
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
    public static boolean isDisplayStand(ArmorStand armorStand) {
        return PersistentDataAPI.getBoolean(armorStand, CrystamaeHistoria.getKeys().getPdcIsDisplayStand());
    }

}
