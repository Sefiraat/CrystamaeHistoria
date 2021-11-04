package io.github.sefiraat.crystamaehistoria.config;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
public class ConfigManager {

    private final FileConfiguration blocks;
    private final FileConfiguration stories;
    private final FileConfiguration research;
    private final FileConfiguration blockColors;
    private final FileConfiguration spells;

    public ConfigManager() {
        this.blocks = getConfig("blocks.yml");
        this.blocks.options().copyDefaults(true);
        this.stories = getConfig("generic-stories.yml");
        this.stories.options().copyDefaults(true);
        this.research = getConfig("research.yml");
        this.research.options().copyDefaults(true);
        this.blockColors = getConfig("block_colors.yml");
        this.blockColors.options().copyDefaults(true);
        this.spells = getConfig("spells.yml");
        this.spells.options().copyDefaults(true);
    }

    /**
     * @noinspection ResultOfMethodCallIgnored
     */
    private FileConfiguration getConfig(String fileName) {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(fileName, true);
        }
        YamlConfiguration yaml = new YamlConfiguration();
        try {
            yaml.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return yaml;
    }

    public void saveResearches() {
        File file = new File(CrystamaeHistoria.getInstance().getDataFolder(), "research.yml");
        try {
            research.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean spellEnabled(Spell spell) {
        return spells.getBoolean(spell.getId());
    }

    public void setupConfigs() {
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
        }
    }

}
