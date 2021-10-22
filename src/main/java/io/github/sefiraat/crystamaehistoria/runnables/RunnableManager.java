package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import lombok.Getter;

public class RunnableManager {

    @Getter
    public final TemporaryBlockRemoval temporaryBlockRemoval;

    public RunnableManager() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        this.temporaryBlockRemoval = new TemporaryBlockRemoval();
        this.temporaryBlockRemoval.runTaskTimer(plugin, 1, 20);
    }

}
