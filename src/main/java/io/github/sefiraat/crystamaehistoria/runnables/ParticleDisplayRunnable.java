package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.LuminescenceScoop;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleDisplayRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            final SlimefunItem item = SlimefunItem.getByItem(player.getInventory().getItemInMainHand());
            final Block block = player.getLocation().getBlock();
            if (!(item instanceof LuminescenceScoop)) {
                return;
            }
            for (int x = -5; x < 6; x++) {
                for (int y = -5; y < 6; y++) {
                    for (int z = -5; z < 6; z++) {
                        final Block possibleLight = block.getRelative(x, y, z);
                        if (possibleLight.getType() == Material.LIGHT) {
                            final Location location = possibleLight.getLocation().clone().add(0.5, 0.5, 0.5);
                            location.getWorld().spawnParticle(Particle.WAX_ON, location, 1);
                        }
                    }
                }
            }
        }
    }
}
