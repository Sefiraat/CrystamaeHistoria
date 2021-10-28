package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import org.bukkit.entity.Mob;

import java.util.UUID;

public class DeityGoal extends AbstractGoal<Mob> {

    public DeityGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public boolean getTargetsEnemies() {
        return false;
    }
}
