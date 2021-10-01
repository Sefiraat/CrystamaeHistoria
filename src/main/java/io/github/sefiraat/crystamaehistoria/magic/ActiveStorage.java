package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTick;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.Getter;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class ActiveStorage {

    @Getter
    private final Map<Entity, Pair<SpellCastInformation, Long>> projectileMap = new HashMap<>();
    @Getter
    private final Map<SpellTick, Integer> tickingCastables = new HashMap<>();

    public void clearAll() {
        for (Entity entity : projectileMap.keySet()) {
            entity.remove();
        }
        projectileMap.clear();
        for (SpellTick spellTick : tickingCastables.keySet()) {
            spellTick.cancel();
        }
        tickingCastables.clear();
    }

    // TODO Need a runnable to clear this down
    public void clearExpired() {
        for (Map.Entry<Entity, Pair<SpellCastInformation, Long>> entry : projectileMap.entrySet()) {
            long time = System.currentTimeMillis();
            long expiration = entry.getValue().getSecondValue();
            if (time > expiration) {
                projectileMap.remove(entry.getKey());
            }
        }
    }

}
