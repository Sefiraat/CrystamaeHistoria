package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTick;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActiveStorage {

    @Getter
    private final Map<UUID, Pair<CastInformation, Long>> projectileMap = new HashMap<>();
    @Getter
    private final Map<SpellTick, Integer> tickingCastables = new HashMap<>();

    public void clearAll() {
        // Cancels all outstanding spells being cast
        for (SpellTick spellTick : tickingCastables.keySet()) {
            spellTick.cancel();
        }
        tickingCastables.clear();
        // Clear all projectiles created from spells
        for (UUID uuid : projectileMap.keySet()) {
            Entity entity = Bukkit.getEntity(uuid);
            if (entity != null) {
                entity.remove();
            }
        }
        projectileMap.clear();
    }

    // TODO Need a runnable to clear this down
    public void clearExpired() {
        for (Map.Entry<UUID, Pair<CastInformation, Long>> entry : projectileMap.entrySet()) {
            long time = System.currentTimeMillis();
            long expiration = entry.getValue().getSecondValue();
            if (time > expiration) {
                projectileMap.remove(entry.getKey());
            }
        }
    }

}
