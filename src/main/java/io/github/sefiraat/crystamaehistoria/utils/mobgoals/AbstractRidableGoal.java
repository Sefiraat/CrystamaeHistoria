package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;

// TODO UNUSED
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
                final Block block = destination.getBlock();
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
