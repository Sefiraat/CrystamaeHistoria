package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

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
        armorStand.setMarker(true);
        armorStand.setSilent(false);
        armorStand.setCustomName(ThemeType.getRandomEggName());
        PersistentDataAPI.setBoolean(armorStand, Keys.PDC_IS_DISPLAY_STAND, true);
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
        return PersistentDataAPI.getBoolean(armorStand, Keys.PDC_IS_DISPLAY_STAND);
    }

    @ParametersAreNonnullByDefault
    public static void panelAnimationStep(ArmorStand armorStand, boolean directionUp) {
        armorStand.setHeadPose(armorStand.getHeadPose().add(0, 0.1, 0));
    }

    @ParametersAreNonnullByDefault
    public static void panelAnimationReset(ArmorStand armorStand, Block block) {
        armorStand.setHeadPose(new EulerAngle(0, 0, 0));
        armorStand.teleport(block.getLocation().clone().add(0.5, -0.6, 0.5));
    }
}
