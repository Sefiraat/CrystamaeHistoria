package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;

public class ListenerManager {

    public ListenerManager() {
        CrystamaeHistoria.inst().getServer().getPluginManager().registerEvents(new ListenerArmorStandInteract(), CrystamaeHistoria.inst());
    }

}
