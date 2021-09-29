package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastDefinition;
import org.bukkit.scheduler.BukkitRunnable;

public class SpellTick extends BukkitRunnable {

    private final CastDefinition castDefinition;
    private int numberOfRuns;

    public SpellTick(CastDefinition castDefinition, int numberOfRuns) {
        this.castDefinition = castDefinition;
        this.numberOfRuns = numberOfRuns;
    }

    @Override
    public void run() {
        if (numberOfRuns <= 0) {
            castDefinition.runAfterTicksEvent();
            this.cancel();
        } else {
            castDefinition.runTickEvent();
        }
        numberOfRuns--;
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        CrystamaeHistoria.getActiveStorage().getTickingCastables().remove(this);
    }

}
