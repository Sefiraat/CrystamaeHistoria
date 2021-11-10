package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;

/**
 * Unused currently until the finer details around mob's facing direction can be sorted out.
 *
 * @param <T> The mod type to use this goal.
 */
public abstract class AbstractRidableGoal<T extends Mob> extends AbstractGoal<T> {

    protected AbstractRidableGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public void tick() {
        if (!this.self.getPassengers().isEmpty()) {
            final Player player = Bukkit.getPlayer(owner);
            final Vector eyeDirection = player.getEyeLocation().getDirection();
            final Location destination = player.getLocation().clone().add(eyeDirection);

            if (getCanFly()) {
                // Flying Mobs
                self.setVelocity(eyeDirection.clone().multiply(getSpeed()));
            } else {
                // Non-flying mobs
                self.getPathfinder().moveTo(self.getLocation().add(0, 0, 1));
            }


        } else {
            super.tick();
        }
    }

    public boolean getCanFly() {
        return false;
    }

    public double getSpeed() {
        return 1;
    }
}
