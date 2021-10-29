package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import org.bukkit.entity.Mob;

import java.util.UUID;

public class GolemGoal extends AbstractGoal<Mob> {

    public GolemGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public double getStayNearDistance() {
        return 3D;
    }
}
