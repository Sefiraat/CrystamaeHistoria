package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.ParametersAreNonnullByDefault;

public class SpellTickRunnable extends BukkitRunnable {

    private final CastInformation castInformation;
    private int numberOfRuns;

    @ParametersAreNonnullByDefault
    public SpellTickRunnable(CastInformation castInformation, int numberOfRuns) {
        this.castInformation = castInformation;
        this.numberOfRuns = numberOfRuns;
    }

    @Override
    public void run() {
        if (numberOfRuns <= 0) {
            castInformation.runAfterTicksEvent();
            this.cancel();
        } else {
            castInformation.runTickEvent();
        }
        numberOfRuns--;
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        CrystamaeHistoria.getSpellMemory().getTickingCastables().remove(this);
    }

}
