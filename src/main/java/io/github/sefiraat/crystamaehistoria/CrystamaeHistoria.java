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
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTickRunnable;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.Machines;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.Tools;
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

    private ConfigManager configManager;
    private StoriesManager storiesManager;
    private ListenerManager listenerManager;
    private RunnableManager runnableManager;
    private SpellMemory spellMemory;
    private EffectManager effectManager;

    public CrystamaeHistoria() {
        super("Sefiraat", "CrystamaeHistoria", "master", "auto-update");
    }

    public static CrystamaeHistoria getInstance() {
        return instance;
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

    public static SpellMemory getActiveStorage() {
        return instance.spellMemory;
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
        return instance.spellMemory.getProjectileMap();
    }

    @Nonnull
    public static Map<UUID, Pair<CastInformation, Long>> getStrikeMap() {
        return instance.spellMemory.getStrikeMap();
    }

    @Nonnull
    public static Map<MagicSummon, Long> getSummonedEntityMap() {
        return instance.spellMemory.getSummonedEntities();
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
    public static Map<SpellTickRunnable, Integer> getTickingMap() {
        return instance.spellMemory.getTickingCastables();
    }

    @Override
    public void enable() {
        instance = this;

        getLogger().info("########################################");
        getLogger().info("    Crystamae Historia - By Sefiraat    ");
        getLogger().info("########################################");

        this.configManager = new ConfigManager();
        this.storiesManager = new StoriesManager();
        this.listenerManager = new ListenerManager();
        this.runnableManager = new RunnableManager();
        this.spellMemory = new SpellMemory();
        this.effectManager = new EffectManager(this);

        setupSlimefun();
        configManager.setupConfigs();

        new Metrics(this, 12065);

        getAddonCommand().addSub(new TestSpell());
        CrystaTag.getCachedValues();
    }

    @Override
    protected void disable() {
        spellMemory.clearAll();
        saveConfig();
        instance = null;
    }

    private void setupSlimefun() {
        new ItemGroups();
        new Materials();
        new Machines();
        new Tools();
    }

}
