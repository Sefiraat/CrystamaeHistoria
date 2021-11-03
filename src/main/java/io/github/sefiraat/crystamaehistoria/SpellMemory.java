package io.github.sefiraat.crystamaehistoria;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicSummon;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTickRunnable;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class SpellMemory {

    @Getter
    private final Map<MagicProjectile, Pair<CastInformation, Long>> projectileMap = new HashMap<>();
    @Getter
    private final Map<UUID, Pair<CastInformation, Long>> strikeMap = new HashMap<>();
    @Getter
    private final Map<SpellTickRunnable, Integer> tickingCastables = new HashMap<>();
    @Getter
    private final Map<BlockPosition, Long> blocksToRemove = new HashMap<>();
    @Getter
    private final Map<MagicSummon, Long> summonedEntities = new HashMap<>();
    @Getter
    private final Map<UUID, Long> playersWithFlight = new HashMap<>();

    public void clearAll() {
        // Cancels all outstanding spells being cast
        for (SpellTickRunnable spellTickRunnable : tickingCastables.keySet()) {
            spellTickRunnable.cancel();
        }
        tickingCastables.clear();

        // Clear all projectiles created from spells
        removeProjectiles(true);
        projectileMap.clear();

        // Clear all spawned entities created from spells
        removeEntities(true);
        summonedEntities.clear();

        // Remove all temporary blocks
        removeBlocks(true);
        blocksToRemove.clear();

        // Remove and disable all players flight
        removeFlight(true);
        playersWithFlight.clear();
    }

    public void removeProjectiles(boolean forceRemoveAll) {
        Set<MagicProjectile> set = new HashSet<>(CrystamaeHistoria.getProjectileMap().keySet());
        for (MagicProjectile magicProjectile : set) {
            long expiry = projectileMap.get(magicProjectile).getSecondValue();
            if (System.currentTimeMillis() > expiry || forceRemoveAll) {
                magicProjectile.kill();
            } else {
                magicProjectile.run();
            }
        }
    }

    public void removeEntities(boolean forceRemoveAll) {
        Set<MagicSummon> set = new HashSet<>(CrystamaeHistoria.getSummonedEntityMap().keySet());
        for (MagicSummon magicSummon : set) {
            long expiry = summonedEntities.get(magicSummon);
            if (System.currentTimeMillis() > expiry || magicSummon.getMob() == null || forceRemoveAll) {
                magicSummon.kill();
            } else {
                magicSummon.run();
            }
        }
    }

    public void removeBlocks(boolean forceRemoveAll) {
        long time = System.currentTimeMillis();
        final Set<Map.Entry<BlockPosition, Long>> set = new HashSet<>(blocksToRemove.entrySet());
        for (Map.Entry<BlockPosition, Long> entry : set) {
            if (forceRemoveAll || entry.getValue() < time) {
                entry.getKey().getBlock().setType(Material.AIR);
                blocksToRemove.remove(entry.getKey());
            }
        }
    }

    public void removeFlight(boolean forceRemoveAll) {
        long time = System.currentTimeMillis();
        final Set<Map.Entry<UUID, Long>> set = new HashSet<>(playersWithFlight.entrySet());
        for (Map.Entry<UUID, Long> entry : set) {
            if (forceRemoveAll || entry.getValue() < time) {
                Player player = Bukkit.getPlayer(entry.getKey());
                if (player != null) {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    playersWithFlight.remove(entry.getKey());
                }
            }
        }
    }

    public void removeFlight(Player player) {
        if (playersWithFlight.containsKey(player.getUniqueId())) {
            player.setAllowFlight(false);
            player.setFlying(false);
            playersWithFlight.remove(player.getUniqueId());
        }
    }

    public void stopBlockRemoval(Block block) {
        BlockPosition blockPosition = new BlockPosition(block);
        blocksToRemove.remove(blockPosition);
    }
}
