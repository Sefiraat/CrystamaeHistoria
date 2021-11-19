package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FlyingBatGoal extends AbstractRidableGoal<Bat> {

    public FlyingBatGoal(UUID owningPlayer) {
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
