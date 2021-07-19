package io.github.sefiraat.crystamaehistoria;

import io.github.mooy1.infinitylib.AbstractAddon;
import io.github.mooy1.infinitylib.bstats.bukkit.Metrics;
import io.github.sefiraat.crystamaehistoria.listeners.ListenerManager;
import io.github.sefiraat.crystamaehistoria.slimefun.Structure;
import io.github.sefiraat.crystamaehistoria.theme.ThemeManager;
import io.github.sefiraat.crystamaehistoria.utils.KeyHolder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    @Override
    public void onAddonEnable() {

        instance = this;

        getLogger().info("########################################");
        getLogger().info("           Crystamae Historia           ");
        getLogger().info("           Created by Sefiraat          ");
        getLogger().info("########################################");

        this.keyHolder = new KeyHolder();
        this.themeManager = new ThemeManager();
        this.structure = new Structure();
        this.listenerManager = new ListenerManager();

    }

    @Override
    protected void onAddonDisable() {
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

}
