package io.github.sefiraat.crystamaehistoria.managers;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.listeners.ArmorStandInteract;
import io.github.sefiraat.crystamaehistoria.listeners.PlayerInteract;
import io.github.sefiraat.crystamaehistoria.listeners.SpellEffectListener;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar.CrystalBreakListener;

public class ListenerManager {

    public ListenerManager() {
        CrystamaeHistoria.getPluginManager().registerEvents(new ArmorStandInteract(), CrystamaeHistoria.getInstance());
        CrystamaeHistoria.getPluginManager().registerEvents(new PlayerInteract(), CrystamaeHistoria.getInstance());
        CrystamaeHistoria.getPluginManager().registerEvents(new SpellEffectListener(), CrystamaeHistoria.getInstance());
        CrystamaeHistoria.getPluginManager().registerEvents(new CrystalBreakListener(), CrystamaeHistoria.getInstance());
    }

}
