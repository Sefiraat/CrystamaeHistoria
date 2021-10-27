package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicProjectile;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class ProjectileTick extends BukkitRunnable {

    @Override
    public void run() {
        Set<MagicProjectile> set = new HashSet<>(CrystamaeHistoria.getProjectileMap().keySet());
        for (MagicProjectile magicProjectile : set) {
            long expiry = CrystamaeHistoria.getProjectileMap().get(magicProjectile).getSecondValue();
            if (System.currentTimeMillis() > expiry) {
                magicProjectile.kill();
            } else {
                magicProjectile.run();
            }
        }
    }
}
