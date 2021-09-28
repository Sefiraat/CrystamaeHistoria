package io.github.sefiraat.crystamaehistoria.managers;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.listeners.ArmorStandInteract;
import io.github.sefiraat.crystamaehistoria.listeners.PlayerInteract;
import io.github.sefiraat.crystamaehistoria.listeners.SpellEffectListener;

public class ListenerManager {

    public ListenerManager() {
        CrystamaeHistoria.getPluginManager().registerEvents(new ArmorStandInteract(), CrystamaeHistoria.inst());
        CrystamaeHistoria.getPluginManager().registerEvents(new PlayerInteract(), CrystamaeHistoria.inst());
        CrystamaeHistoria.getPluginManager().registerEvents(new SpellEffectListener(), CrystamaeHistoria.inst());
    }

}
