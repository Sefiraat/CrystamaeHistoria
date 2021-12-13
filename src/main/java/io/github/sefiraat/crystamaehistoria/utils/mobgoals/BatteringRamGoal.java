package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Goat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BatteringRamGoal extends AbstractGoal<Goat> {

    public BatteringRamGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public void tick() {
        double velX = Math.abs(self.getVelocity().getX());
        double velZ = Math.abs(self.getVelocity().getZ());

        if (self.isOnGround() || (velX < 0.1 && velZ < 0.1)) {
            ParticleUtils.displayParticleEffect(self, Particle.VILLAGER_ANGRY, 1, 5);
            self.remove();
            return;
        }

        final Location location = self.getLocation().add(0, 0, 0);
        final int radius = 2;
        final List<BlockPosition> blocks = new ArrayList<>();

        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int y = location.getBlockY(); y <= location.getBlockY() + radius; y++) {
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
            final BlockData blockData = block.getBlockData();
            if (GeneralUtils.blockCanBeBroken(this.owner, block)) {
                block.setType(Material.AIR);
                final FallingBlock fallingBlock = block.getWorld().spawnFallingBlock(block.getLocation(), blockData);
                GeneralUtils.pushEntity(this.owner, self.getLocation(), fallingBlock, 0.5);
            }
        }
    }

    @Override
    public boolean getTickCondition() {
        return false;
    }

    @Override
    public boolean getTargetsEnemies() {
        return false;
    }

    @Override
    public boolean getFollowsPlayer() {
        return false;
    }
}
