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

    public ConfigManager() {
        this.blocks = getConfig("blocks.yml");
        this.blocks.options().copyDefaults(true);
        this.stories = getConfig("generic-stories.yml");
        this.stories.options().copyDefaults(true);
    }

    private FileConfiguration getConfig(String fileName) {
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
}
