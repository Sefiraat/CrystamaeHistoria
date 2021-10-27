package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractGoal<T extends Mob> implements Goal<T> {

    private final UUID owner;
    private T self;
    private GoalKey<T> goalKey;

    public AbstractGoal(UUID owningPlayer) {
        super();
        this.owner = owningPlayer;
    }

    public void setSelf(T self) {
        this.self = self;
        Class<T> clazz = (Class<T>) self.getClass();
        this.goalKey = GoalKey.of(clazz, Keys.newKey("mob_goal"));
    }

    @Override
    public final boolean shouldActivate() {
        return true;
    }

    @Override
    public final boolean shouldStayActive() {
        return true;
    }

    @Override
    public final void stop() {
        self.getPathfinder().stopPathfinding();
        self.setTarget(null);
    }

    @Override
    public final void tick() {

        final Player player = removeOffline();

        if (player != null) {
            if (self.getTarget() != null && self.getTarget().equals(player)) {
                self.setTarget(null);
                return;
            }

            if (self.getTarget() == null || self.getTarget().isDead()) {
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
                        self.setTarget(entities.get(random));
                        return;
                    }
                }

                if (getFollowsPlayer()
                    && self.getLocation().distance(player.getLocation()) > getStayNearDistance()
                ) {
                    self.getPathfinder().moveTo(player);
                }
            }

            customActions();
        }
    }

    public void customActions() {

    }

    private Player removeOffline() {
        final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(owner);

        if (!offlinePlayer.isOnline()) {
            self.remove();
            return null;
        }
        return offlinePlayer.getPlayer();
    }

    @Override
    @Nonnull
    public final GoalKey<T> getKey() {
        return goalKey;
    }

    @Override
    @Nonnull
    public final EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.TARGET);
    }

    public double getStayNearDistance() {
        return 5D;
    }

    public boolean getTargetsEnemies() {
        return true;
    }

    public boolean getFollowsPlayer() {
        return true;
    }
}
