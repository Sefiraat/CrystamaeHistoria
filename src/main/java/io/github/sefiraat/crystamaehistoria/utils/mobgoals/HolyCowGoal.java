package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import org.bukkit.Location;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class HolyCowGoal extends AbstractGoal<Cow> {

    public HolyCowGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public void tick() {
        final Player player = removeOffline();

        if (player == null) {
            return;
        }

        if (self.getTarget() != null && self.getTarget().equals(player)) {
            self.setTarget(null);
            return;
        }

        final List<LivingEntity> entitiesAroundCow = new ArrayList<>(
            player.getWorld().getNearbyEntitiesByType(
                Monster.class,
                self.getLocation(),
                1,
                1,
                1,
                entity -> {
                    final UUID testOwner = DataTypeMethods.getCustom(entity, Keys.PDC_IS_SPAWN_OWNER, PersistentUUIDDataType.TYPE);
                    if (testOwner == null) {
                        return true;
                    } else {
                        return !testOwner.equals(getOwner());
                    }
                }
            )
        );

        if (!entitiesAroundCow.isEmpty()) {
            Entity entity = getSelf();
            entity.getLocation().getWorld().createExplosion(player, entity.getLocation(), 10, false, false);
            getSelf().remove();
            return;
        }

        if (self.getTarget() != null && !self.getTarget().isDead()) {
            return;
        }

        if (getTargetsEnemies()) {
            final List<LivingEntity> entities = new ArrayList<>(
                player.getWorld().getNearbyEntitiesByType(
                    Monster.class,
                    player.getLocation(),
                    15,
                    15,
                    15,
                    entity -> {
                        final UUID testOwner = DataTypeMethods.getCustom(entity, Keys.PDC_IS_SPAWN_OWNER, PersistentUUIDDataType.TYPE);
                        if (testOwner == null) {
                            return true;
                        } else {
                            return !testOwner.equals(owner);
                        }
                    }
                )
            );

            if (!entities.isEmpty()) {
                int random = ThreadLocalRandom.current().nextInt(entities.size());
                self.getPathfinder().moveTo(entities.get(random).getLocation());
                return;
            }
        }

        if (getFollowsPlayer()
            && self.getLocation().distance(player.getLocation()) > getStayNearDistance()
        ) {
            final Location location = player.getLocation().clone().add(
                ThreadLocalRandom.current().nextDouble(-1.5, 1.5),
                0,
                ThreadLocalRandom.current().nextDouble(-1.5, 1.5)
            );
            self.getPathfinder().moveTo(location);
        }
    }
}
