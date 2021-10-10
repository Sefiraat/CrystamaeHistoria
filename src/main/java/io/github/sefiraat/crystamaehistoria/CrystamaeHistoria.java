package io.github.sefiraat.crystamaehistoria;

import de.slikey.effectlib.EffectManager;
import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.sefiraat.crystamaehistoria.commands.TestSpell;
import io.github.sefiraat.crystamaehistoria.magic.ActiveStorage;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.managers.ListenerManager;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTick;
import io.github.sefiraat.crystamaehistoria.slimefun.Structure;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.apache.commons.lang.Validate;
import org.bstats.bukkit.Metrics;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.UUID;

public class CrystamaeHistoria extends AbstractAddon {

    private static CrystamaeHistoria instance;

    private Keys keys;
    private Structure structure;
    private ListenerManager listenerManager;
    private StoriesManager storiesManager;
    private EffectManager effectManager;

    private ActiveStorage activeStorage;

    public CrystamaeHistoria() {
        super("Sefiraat", "CrystamaeHistoria", "master", "auto-update");
    }

    public static CrystamaeHistoria inst() {
        return instance;
    }

    public static Keys getKeys() {
        return instance.keys;
    }

    public static Structure getStructure() {
        return instance.structure;
    }

    public static ListenerManager getListenerManager() {
        return instance.listenerManager;
    }

    public static StoriesManager getStoriesManager() {
        return instance.storiesManager;
    }

    public static ActiveStorage getActiveStorage() {
        return instance.activeStorage;
    }

    public static EffectManager getEffectManager() {
        return instance.effectManager;
    }

    public static PluginManager getPluginManager() {
        return instance.getServer().getPluginManager();
    }

    public static Server getServ() {
        return instance.getServer();
    }

    @Nonnull
    public static Map<UUID, Pair<CastInformation, Long>> getProjectileMap() {
        return instance.activeStorage.getProjectileMap();
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getSpellCastInfo(UUID uuid) {
        CastInformation castInformation = getProjectileMap().get(uuid).getFirstValue();
        Validate.notNull(castInformation, "Cast information is null, magical projectile spawned incorrectly.");
        return castInformation;
    }

    @Nonnull
    public static Map<SpellTick, Integer> getTickingMap() {
        return instance.activeStorage.getTickingCastables();
    }

    @ParametersAreNonnullByDefault
    // TODO Remove before v1
    public static void logInfo(String... message) {
        for (String string : message) {
            instance.getServer().getLogger().info(string);
        }
    }

    @ParametersAreNonnullByDefault
    // TODO Remove before v1
    public static void logWarning(String... message) {
        for (String string : message) {
            instance.getServer().getLogger().warning(string);
        }
    }

    @Override
    public void enable() {

        instance = this;

        getLogger().info("########################################");
        getLogger().info("    Crystamae Historia - By Sefiraat    ");
        getLogger().info("########################################");

        this.keys = new Keys();
        this.structure = new Structure();
        this.listenerManager = new ListenerManager();
        this.storiesManager = new StoriesManager();
        this.activeStorage = new ActiveStorage();
        this.effectManager = new EffectManager(this);

        new Metrics(this, 12065);

        getAddonCommand().addSub(new TestSpell());

        // Just to load the class
        SpellType.getCachedValues();

    }

    @Override
    protected void disable() {
        activeStorage.clearAll();
        saveConfig();
        instance = null;
    }

}
