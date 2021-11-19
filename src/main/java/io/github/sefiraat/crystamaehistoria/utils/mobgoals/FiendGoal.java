package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import org.bukkit.entity.Phantom;

import java.util.UUID;

public class FiendGoal extends AbstractGoal<Phantom> {

    public FiendGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public boolean getFollowsPlayer() {
        return false;
    }
}
