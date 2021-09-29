package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

public class SpellTick extends BukkitRunnable {

    private final SpellDefinition spellDefinition;
    private int numberOfRuns;

    public SpellTick(SpellDefinition spellDefinition, int numberOfRuns) {
        this.spellDefinition = spellDefinition;
        this.numberOfRuns = numberOfRuns;
    }

    @Override
    public void run() {
        if (numberOfRuns <= 0) {
            spellDefinition.runAfterTicksEvent();
            this.cancel();
        } else {
            spellDefinition.runTickEvent();
        }
        numberOfRuns--;
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        CrystamaeHistoria.getActiveStorage().getTickingCastables().remove(this);
    }

}
