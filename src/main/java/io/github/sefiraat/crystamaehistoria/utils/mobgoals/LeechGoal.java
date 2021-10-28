package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;

import java.util.UUID;

public class LeechGoal extends AbstractGoal<Mob> {

    public LeechGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public boolean getFollowsPlayer() {
        return false;
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Mob.class;
    }
}
