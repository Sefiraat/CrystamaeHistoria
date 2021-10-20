package io.github.sefiraat.crystamaehistoria.runnables.animation;

import io.github.sefiraat.crystamaehistoria.utils.ArmourStandUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.ParametersAreNonnullByDefault;

public class FloatingHeadAnimation extends BukkitRunnable {
    public static final double Y_VARIATION = 0.2;
    public static final long SPEED = 1;

    private final ArmorStand armorStand;
    private final double baseY;
    private boolean directionUp = true;

    @ParametersAreNonnullByDefault
    public FloatingHeadAnimation(ArmorStand armorStand) {
        this.armorStand = armorStand;
        baseY = armorStand.getLocation().getY();
    }

    @Override
    public void run() {
        if (directionUp) {
            ArmourStandUtils.panelAnimationStep(armorStand, true);
            if (armorStand.getLocation().getY() >= (baseY + Y_VARIATION)) {
                directionUp = false;
            }
        } else {
            ArmourStandUtils.panelAnimationStep(armorStand, false);
            if (armorStand.getLocation().getY() <= (baseY - Y_VARIATION)) {
                directionUp = true;
            }
        }
    }
}
