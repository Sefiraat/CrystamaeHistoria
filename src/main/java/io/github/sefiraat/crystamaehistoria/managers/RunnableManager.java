package io.github.sefiraat.crystamaehistoria.managers;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.runnables.SaveConfigRunnable;
import io.github.sefiraat.crystamaehistoria.runnables.TemporaryEffectsRunnable;
import lombok.Getter;

public class RunnableManager {

    @Getter
    public final TemporaryEffectsRunnable temporaryEffectsRunnable;
    @Getter
    public final SaveConfigRunnable saveConfigRunnable;

    public RunnableManager() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        this.temporaryEffectsRunnable = new TemporaryEffectsRunnable();
        this.temporaryEffectsRunnable.runTaskTimer(plugin, 1, 20);

        this.saveConfigRunnable = new SaveConfigRunnable();
        this.saveConfigRunnable.runTaskTimer(plugin, 1, 12000);
    }
}
