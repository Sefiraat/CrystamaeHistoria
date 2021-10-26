package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicProjectile;
import org.bukkit.scheduler.BukkitRunnable;

public class ProjectileTick extends BukkitRunnable {

    @Override
    public void run() {
        for (MagicProjectile magicProjectile : CrystamaeHistoria.getProjectileMap().keySet()) {
            long expiry = CrystamaeHistoria.getProjectileMap().get(magicProjectile).getSecondValue();
            if (System.currentTimeMillis() > expiry) {
                magicProjectile.kill();
            } else {
                magicProjectile.run();
            }
        }
    }
}
