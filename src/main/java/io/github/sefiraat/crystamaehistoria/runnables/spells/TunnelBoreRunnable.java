package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Removed due to issues. Will be replaced with a raycast version of the same spell.
 * Given the powerful nature of the spell, I want this to await tier 2 spells first.
 */
public class TunnelBoreRunnable extends BukkitRunnable {

    private final LivingEntity bore;
    private final int radius;
    private final UUID owner;
    private int iterations;

    @ParametersAreNonnullByDefault
    public TunnelBoreRunnable(LivingEntity bore, int radius, UUID owner, int iterations) {
        this.bore = bore;
        this.radius = radius;
        this.owner = owner;
        this.iterations = iterations;
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        bore.remove();
        super.cancel();
    }

    @Override
    public void run() {
        if (iterations <= 0) {
            this.cancel();
        } else {
            final Location location = bore.getLocation();
            final List<BlockPosition> blocks = new ArrayList<>();

            for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
                for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                    for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                        final BlockPosition blockPosition = new BlockPosition(location.getWorld(), x, y, z);
                        if (!blocks.contains(blockPosition)) {
                            blocks.add(blockPosition);
                        }
                    }
                }
            }

            for (BlockPosition blockPosition : blocks) {
                final Block block = blockPosition.getBlock();
                if (GeneralUtils.blockCanBeBroken(this.owner, block)) {
                    block.setType(Material.AIR);
                }
            }

            ParticleUtils.displayParticleEffect(bore, Particle.FALLING_LAVA, radius, 10);
        }
        iterations--;
    }
}
