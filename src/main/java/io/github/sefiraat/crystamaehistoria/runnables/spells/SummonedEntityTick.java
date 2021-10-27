package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class SummonedEntityTick extends BukkitRunnable {

    @Override
    public void run() {
        Set<Entity> set = new HashSet<>(CrystamaeHistoria.getSummonedEntityMap().keySet());
        for (Entity entity : set) {
            long expiry = CrystamaeHistoria.getSummonedEntityMap().get(entity);
            if (System.currentTimeMillis() > expiry) {
                entity.remove();
            }
        }
    }
}
