package io.github.sefiraat.crystamaehistoria;

import de.slikey.effectlib.EffectManager;
import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.sefiraat.crystamaehistoria.commands.TestSpell;
import io.github.sefiraat.crystamaehistoria.config.ConfigManager;
import io.github.sefiraat.crystamaehistoria.listeners.ListenerManager;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicSummon;
import io.github.sefiraat.crystamaehistoria.runnables.RunnableManager;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTick;
import io.github.sefiraat.crystamaehistoria.slimefun.Structure;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
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

    private Structure structure;
    private ConfigManager configManager;
    private StoriesManager storiesManager;
    private ListenerManager listenerManager;
    private RunnableManager runnableManager;
    private ActiveStorage activeStorage;
    private EffectManager effectManager;

    public CrystamaeHistoria() {
        super("Sefiraat", "CrystamaeHistoria", "master", "auto-update");
    }

    public static CrystamaeHistoria getInstance() {
        return instance;
    }

    public static Structure getStructure() {
        return instance.structure;
    }

    public static ListenerManager getListenerManager() {
        return instance.listenerManager;
    }

    public static RunnableManager getRunnableManager() {
        return instance.runnableManager;
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
    public static Map<MagicProjectile, Pair<CastInformation, Long>> getProjectileMap() {
        return instance.activeStorage.getProjectileMap();
    }

    @Nonnull
    public static Map<UUID, Pair<CastInformation, Long>> getStrikeMap() {
        return instance.activeStorage.getStrikeMap();
    }

    @Nonnull
    public static Map<MagicSummon, Long> getSummonedEntityMap() {
        return instance.activeStorage.getSummonedEntities();
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getProjectileCastInfo(MagicProjectile magicProjectile) {
        CastInformation castInformation = getProjectileMap().get(magicProjectile).getFirstValue();
        Validate.notNull(castInformation, "Cast information is null, magical projectile spawned incorrectly.");
        return castInformation;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getStrikeCastInfo(UUID lightningStrike) {
        CastInformation castInformation = getStrikeMap().get(lightningStrike).getFirstValue();
        Validate.notNull(castInformation, "Cast information is null, magical projectile spawned incorrectly.");
        return castInformation;
    }

    @Nonnull
    public static Map<SpellTick, Integer> getTickingMap() {
        return instance.activeStorage.getTickingCastables();
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
        this.runnableManager = new RunnableManager();
        this.activeStorage = new ActiveStorage();
        this.effectManager = new EffectManager(this);

        structure.setup();

        new Metrics(this, 12065);

        getAddonCommand().addSub(new TestSpell());
        CrystaTag.getCachedValues();
    }

    @Override
    protected void disable() {
        activeStorage.clearAll();
        saveConfig();
        instance = null;
    }
}
