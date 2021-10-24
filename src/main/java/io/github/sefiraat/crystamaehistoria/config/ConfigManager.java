package io.github.sefiraat.crystamaehistoria.config;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
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

    public ConfigManager() {
        this.blocks = getConfig("blocks.yml");
        this.blocks.options().copyDefaults(true);
        this.stories = getConfig("generic-stories.yml");
        this.stories.options().copyDefaults(true);
        this.research = getConfig("research.yml");
        this.research.options().copyDefaults(true);
    }

    /**
     * @noinspection ResultOfMethodCallIgnored
     */
    private FileConfiguration getConfig(String fileName) {
        // Todo remove commented code and add config diff
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        File file = new File(plugin.getDataFolder(), fileName);
        //if (!file.exists()) {
        file.getParentFile().mkdirs();
        plugin.saveResource(fileName, true);
        //}
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
}
