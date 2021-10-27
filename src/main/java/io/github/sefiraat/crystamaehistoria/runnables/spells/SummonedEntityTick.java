package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicSummon;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class SummonedEntityTick extends BukkitRunnable {

    @Override
    public void run() {
        Set<MagicSummon> set = new HashSet<>(CrystamaeHistoria.getSummonedEntityMap().keySet());
        for (MagicSummon magicSummon : set) {
            long expiry = CrystamaeHistoria.getSummonedEntityMap().get(magicSummon);
            if (System.currentTimeMillis() > expiry || magicSummon.getMob() == null) {
                magicSummon.kill();
            } else {
                magicSummon.run();
            }
        }
    }
}
