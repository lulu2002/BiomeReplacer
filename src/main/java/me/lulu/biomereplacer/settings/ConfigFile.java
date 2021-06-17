package me.lulu.biomereplacer.settings;

import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;

public abstract class ConfigFile extends YamlConfiguration {

    private String fileName;
    private JavaPlugin plugin;
    private File file;

    @SneakyThrows
    public ConfigFile(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;

        loadFile(fileName);

        loadValues();
    }

    @SneakyThrows
    private void loadFile(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            InputStream resource = plugin.getResource(fileName);

            if (resource != null)
                plugin.saveResource(fileName, true);
            else
                file.createNewFile();
        }

        this.load(file);
    }

    protected abstract void loadValues();

    protected abstract void saveValues();

    @SneakyThrows
    public void saveConfig() {
        saveValues();
        save(file);
    }

}
