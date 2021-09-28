package io.github.sefiraat.crystamaehistoria.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

@UtilityClass
public class AnimateUtils {

    public static void panelAnimationStep(ArmorStand armorStand, boolean directionUp) {
        armorStand.setHeadPose(armorStand.getHeadPose().add(0, 0.1, 0));
        Location location = directionUp ? armorStand.getLocation().add(0, 0.01, 0) : armorStand.getLocation().add(0, -0.01, 0);
        armorStand.teleport(location);
    }

    public static void panelAnimationReset(ArmorStand armorStand, Block block) {
        armorStand.setHeadPose(new EulerAngle(0, 0, 0));
        armorStand.teleport(block.getLocation().clone().add(0.5, -0.6, 0.5));
    }


}
