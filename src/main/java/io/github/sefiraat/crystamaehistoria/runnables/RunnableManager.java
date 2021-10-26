package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.runnables.spells.ProjectileTick;
import lombok.Getter;

public class RunnableManager {

    @Getter
    public final ClearActiveStorage clearActiveStorage;
    @Getter
    public final ProjectileTick projectileTick;

    public RunnableManager() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        this.clearActiveStorage = new ClearActiveStorage();
        this.clearActiveStorage.runTaskTimer(plugin, 1, 20);

        this.projectileTick = new ProjectileTick();
        this.projectileTick.runTaskTimer(plugin, 1, 2);
    }
}
