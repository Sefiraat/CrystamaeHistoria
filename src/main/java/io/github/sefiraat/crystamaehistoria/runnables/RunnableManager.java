package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.runnables.spells.ProjectileRunnable;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SummonedEntityRunnable;
import io.github.sefiraat.crystamaehistoria.runnables.spells.TemporaryBlocksRunnable;
import lombok.Getter;

public class RunnableManager {

    @Getter
    public final TemporaryBlocksRunnable temporaryBlocksRunnable;
    @Getter
    public final ProjectileRunnable projectileRunnable;
    @Getter
    public final SummonedEntityRunnable summonedEntityRunnable;

    public RunnableManager() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        this.temporaryBlocksRunnable = new TemporaryBlocksRunnable();
        this.temporaryBlocksRunnable.runTaskTimer(plugin, 1, 20);

        this.projectileRunnable = new ProjectileRunnable();
        this.projectileRunnable.runTaskTimer(plugin, 1, 2);

        this.summonedEntityRunnable = new SummonedEntityRunnable();
        this.summonedEntityRunnable.runTaskTimer(plugin, 1, 20);
    }
}
