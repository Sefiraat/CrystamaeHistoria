package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import org.bukkit.scheduler.BukkitRunnable;

public class SpellTick extends BukkitRunnable {

    private final SpellCastInformation spellCastInformation;
    private int numberOfRuns;

    public SpellTick(SpellCastInformation spellCastInformation, int numberOfRuns) {
        this.spellCastInformation = spellCastInformation;
        this.numberOfRuns = numberOfRuns;
    }

    @Override
    public void run() {
        if (numberOfRuns <= 0) {
            spellCastInformation.runAfterTicksEvent();
            this.cancel();
        } else {
            spellCastInformation.runTickEvent();
        }
        numberOfRuns--;
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        CrystamaeHistoria.getActiveStorage().getTickingCastables().remove(this);
    }

}
