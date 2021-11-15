package io.github.sefiraat.crystamaehistoria.config;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Getter
public class ConfigManager {

    private final FileConfiguration blocks;
    private final FileConfiguration stories;
    private final FileConfiguration playerStats;
    private final FileConfiguration blockColors;
    private final FileConfiguration spells;

    public ConfigManager() {
        this.blocks = getConfig("blocks.yml", true);
        this.stories = getConfig("generic-stories.yml", true);
        this.playerStats = getConfig("player_stats.yml", false);
        this.blockColors = getConfig("block_colors.yml", false);
        this.spells = getConfig("spells.yml", false);
    }

    /**
     * @noinspection ResultOfMethodCallIgnored
     */
    private FileConfiguration getConfig(String fileName, boolean updateWithDefaults) {
        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        final File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(fileName, true);
        }
        final FileConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
            if (updateWithDefaults) {
                updateConfig(config, file, fileName);
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return config;
    }

    private void updateConfig(FileConfiguration config, File file, String fileName) throws IOException {
        final InputStream inputStream = CrystamaeHistoria.getInstance().getResource(fileName);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final YamlConfiguration defaults = YamlConfiguration.loadConfiguration(reader);
        config.addDefaults(defaults);
        config.options().copyDefaults(true);
        config.save(file);
    }

    public boolean spellEnabled(Spell spell) {
        return spells.getBoolean(spell.getId());
    }

    public void loadConfig() {
        // Spells
        for (SpellType spellType : SpellType.getCachedValues()) {
            Spell spell = spellType.getSpell();
            if (!spells.contains(spell.getId())) {
                try {
                    final File file = new File(CrystamaeHistoria.getInstance().getDataFolder(), "spells.yml");
                    spells.set(spell.getId(), true);
                    spells.save(file);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            boolean enabled = spells.getBoolean(spell.getId());
            spell.setEnabled(enabled);
            if (enabled) {
                LiquefactionBasinCache.addSpellRecipe(spellType, spell.getRecipe());
            }
        }
    }

    public void saveAll() {
        CrystamaeHistoria.getInstance().getLogger().info("Crystamae saving data.");
        CrystamaeHistoria.getInstance().getConfig().save();
        saveResearches();
    }

    private void saveResearches() {
        File file = new File(CrystamaeHistoria.getInstance().getDataFolder(), "player_stats.yml");
        try {
            playerStats.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
