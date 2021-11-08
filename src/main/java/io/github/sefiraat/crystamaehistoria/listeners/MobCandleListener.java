package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.BoundingBox;

public class MobCandleListener implements Listener {

    @EventHandler
    public void onInteract(CreatureSpawnEvent e) {
        final Entity entity = e.getEntity();
        if (entity instanceof Monster
            && e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER
            && e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG
            && e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.BUILD_WITHER
        ) {
            for (BoundingBox boundingBox : CrystamaeHistoria.getSpellMemory().getNoSpawningAreas().keySet()) {
                if (boundingBox.contains(e.getLocation().toVector())) {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }
}