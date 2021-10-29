package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import org.bukkit.event.Listener;

public class ListenerManager {

    public ListenerManager() {
        addListener(new ArmorStandInteract());
        addListener(new PlayerInteract());
        addListener(new SpellEffectListener());
        addListener(new CrystalBreakListener());
        addListener(new BlockRemovalListener());
        addListener(new MaintenanceListener());
    }

    private void addListener(Listener listener) {
        CrystamaeHistoria.getPluginManager().registerEvents(listener, CrystamaeHistoria.getInstance());
    }

}
