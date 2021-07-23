package io.github.sefiraat.crystamaehistoria;

import io.github.mooy1.infinitylib.AbstractAddon;
import io.github.mooy1.infinitylib.bstats.bukkit.Metrics;
import io.github.sefiraat.crystamaehistoria.listeners.ListenerManager;
import io.github.sefiraat.crystamaehistoria.slimefun.Structure;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.sefiraat.crystamaehistoria.theme.ThemeManager;
import io.github.sefiraat.crystamaehistoria.utils.KeyHolder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CrystamaeHistoria extends AbstractAddon {

    private static CrystamaeHistoria instance;
    public static CrystamaeHistoria inst() {
        return instance;
    }

    @Getter
    private KeyHolder keyHolder;
    @Getter
    private ThemeManager themeManager;
    @Getter
    private Structure structure;
    @Getter
    private ListenerManager listenerManager;
    @Getter
    private StoriesManager storiesManager;

    @Override
    public void enable() {

        instance = this;

        getLogger().info("########################################");
        getLogger().info("           Crystamae Historia           ");
        getLogger().info("           Created by Sefiraat          ");
        getLogger().info("########################################");

        this.keyHolder = new KeyHolder();
        this.themeManager = new ThemeManager();
        this.structure = new Structure();
        this.listenerManager = new ListenerManager();
        this.storiesManager = new StoriesManager();

    }

    @Override
    protected void disable() {
        saveConfig();
        instance = null;
    }

    @Override
    protected Metrics setupMetrics() {
        return new Metrics(this,12065);
    }

    @Override
    protected @NotNull String getGithubPath() {
        return "Sefiraat/CrystamaeHistoria/master";
    }

    @Nullable
    @Override
    public String getAutoUpdatePath() {
        return "auto-update";
    }

    public static StoriesManager getStoriesManager() {
        return instance.storiesManager;
    }

}
