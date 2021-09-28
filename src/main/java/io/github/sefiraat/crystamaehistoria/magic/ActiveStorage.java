package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableTicking;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.Getter;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class ActiveStorage {

    @Getter
    private final Map<Entity, Pair<SpellDefinition, Long>> projectileMap = new HashMap<>();
    @Getter
    private final Map<CastableTicking, Integer> tickingCastables = new HashMap<>();

    public void clearAll() {
        for (Entity entity : projectileMap.keySet()) {
            entity.remove();
        }
        projectileMap.clear();
    }

    public void clearExpired() {
        for (Map.Entry<Entity, Pair<SpellDefinition, Long>> entry : projectileMap.entrySet()) {
            long time = System.currentTimeMillis();
            long expiration = entry.getValue().getSecondValue();
            if (time > expiration) {
                projectileMap.remove(entry.getKey());
            }
        }
    }

}
