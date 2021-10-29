package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.runnables.spells.ProjectileTick;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SummonedEntityTick;
import io.github.sefiraat.crystamaehistoria.runnables.spells.TemporaryBlocksTick;
import lombok.Getter;

public class RunnableManager {

    @Getter
    public final TemporaryBlocksTick temporaryBlocksTick;
    @Getter
    public final ProjectileTick projectileTick;
    @Getter
    public final SummonedEntityTick summonedEntityTick;

    public RunnableManager() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        this.temporaryBlocksTick = new TemporaryBlocksTick();
        this.temporaryBlocksTick.runTaskTimer(plugin, 1, 20);

        this.projectileTick = new ProjectileTick();
        this.projectileTick.runTaskTimer(plugin, 1, 2);

        this.summonedEntityTick = new SummonedEntityTick();
        this.summonedEntityTick.runTaskTimer(plugin, 1, 20);
    }
}
