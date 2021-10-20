package io.github.sefiraat.crystamaehistoria;

import de.slikey.effectlib.EffectManager;
import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.sefiraat.crystamaehistoria.commands.TestSpell;
import io.github.sefiraat.crystamaehistoria.config.ConfigManager;
import io.github.sefiraat.crystamaehistoria.listeners.ListenerManager;
import io.github.sefiraat.crystamaehistoria.magic.ActiveStorage;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTick;
import io.github.sefiraat.crystamaehistoria.slimefun.Structure;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.apache.commons.lang.Validate;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.UUID;

public class CrystamaeHistoria extends AbstractAddon {

    private static CrystamaeHistoria instance;

    private Keys keys;
    private Structure structure;
    private ConfigManager configManager;
    private StoriesManager storiesManager;
    private ListenerManager listenerManager;
    private ActiveStorage activeStorage;
    private EffectManager effectManager;


    public CrystamaeHistoria() {
        super("Sefiraat", "CrystamaeHistoria", "master", "auto-update");
    }

    public static CrystamaeHistoria getInstance() {
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

    public static ConfigManager getConfigManager() {
        return instance.configManager;
    }

    public static PluginManager getPluginManager() {
        return instance.getServer().getPluginManager();
    }

    @Nonnull
    public static Map<UUID, Pair<CastInformation, Long>> getProjectileMap() {
        return instance.activeStorage.getProjectileMap();
    }

    @Override
    public void enable() {
        instance = this;

        getLogger().info("########################################");
        getLogger().info("    Crystamae Historia - By Sefiraat    ");
        getLogger().info("########################################");

        this.structure = new Structure();
        this.configManager = new ConfigManager();
        this.storiesManager = new StoriesManager();
        this.listenerManager = new ListenerManager();
        this.activeStorage = new ActiveStorage();
        this.effectManager = new EffectManager(this);

        structure.setup();

        new Metrics(this, 12065);

        getAddonCommand().addSub(new TestSpell());
    }

    @Override
    protected void disable() {
        activeStorage.clearAll();
        saveConfig();
        instance = null;
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
}
