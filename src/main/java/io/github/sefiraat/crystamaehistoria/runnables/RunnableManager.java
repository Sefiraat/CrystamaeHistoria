package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.runnables.spells.TemporaryEffectsRunnable;
import lombok.Getter;

public class RunnableManager {

    @Getter
    public final TemporaryEffectsRunnable temporaryEffectsRunnable;

    public RunnableManager() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        this.temporaryEffectsRunnable = new TemporaryEffectsRunnable();
        this.temporaryEffectsRunnable.runTaskTimer(plugin, 1, 20);
    }
}
