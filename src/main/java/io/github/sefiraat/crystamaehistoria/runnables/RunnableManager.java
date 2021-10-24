package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import lombok.Getter;

public class RunnableManager {

    @Getter
    public final ClearActiveStorage clearActiveStorage;

    public RunnableManager() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        this.clearActiveStorage = new ClearActiveStorage();
        this.clearActiveStorage.runTaskTimer(plugin, 1, 20);
    }

}
