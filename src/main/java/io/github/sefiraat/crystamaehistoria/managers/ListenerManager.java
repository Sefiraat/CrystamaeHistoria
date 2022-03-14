package io.github.sefiraat.crystamaehistoria.managers;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.listeners.ArmorStandInteract;
import io.github.sefiraat.crystamaehistoria.listeners.BlockRemovalListener;
import io.github.sefiraat.crystamaehistoria.listeners.CrystaDowngradeListener;
import io.github.sefiraat.crystamaehistoria.listeners.CrystalBreakListener;
import io.github.sefiraat.crystamaehistoria.listeners.DisplayItemListener;
import io.github.sefiraat.crystamaehistoria.listeners.EndermanInhibitorListener;
import io.github.sefiraat.crystamaehistoria.listeners.MaintenanceListener;
import io.github.sefiraat.crystamaehistoria.listeners.MiscListener;
import io.github.sefiraat.crystamaehistoria.listeners.MobCandleListener;
import io.github.sefiraat.crystamaehistoria.listeners.NetherDrainingListener;
import io.github.sefiraat.crystamaehistoria.listeners.PhilosophersSprayListener;
import io.github.sefiraat.crystamaehistoria.listeners.PoseChangerListener;
import io.github.sefiraat.crystamaehistoria.listeners.RefractingLensListener;
import io.github.sefiraat.crystamaehistoria.listeners.SatchelListener;
import io.github.sefiraat.crystamaehistoria.listeners.SpellCastListener;
import io.github.sefiraat.crystamaehistoria.listeners.SpellEffectListener;
import io.github.sefiraat.crystamaehistoria.listeners.ThaumaturgicSaltsListener;
import org.bukkit.event.Listener;

public class ListenerManager {

    public ListenerManager() {
        addListener(new ArmorStandInteract());
        addListener(new SpellCastListener());
        addListener(new SpellEffectListener());
        addListener(new CrystalBreakListener());
        addListener(new BlockRemovalListener());
        addListener(new MaintenanceListener());
        addListener(new RefractingLensListener());
        addListener(new ThaumaturgicSaltsListener());
        addListener(new CrystaDowngradeListener());
        addListener(new NetherDrainingListener());
        addListener(new SatchelListener());
        addListener(new EndermanInhibitorListener());
        addListener(new MobCandleListener());
        addListener(new DisplayItemListener());
        addListener(new PoseChangerListener());
        addListener(new PhilosophersSprayListener());
        addListener(new MiscListener());
    }

    private void addListener(Listener listener) {
        CrystamaeHistoria.getPluginManager().registerEvents(listener, CrystamaeHistoria.getInstance());
    }

}
