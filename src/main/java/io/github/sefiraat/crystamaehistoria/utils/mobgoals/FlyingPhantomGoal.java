package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FlyingPhantomGoal extends AbstractRidableGoal<Phantom> {

    public FlyingPhantomGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public void customActions(Player player) {
        if (self.getPassengers().isEmpty()) {
            self.remove();
        }
    }

    @Override
    public boolean getCanFly() {
        return true;
    }

    @Override
    public double getSpeed() {
        return 2;
    }
}
