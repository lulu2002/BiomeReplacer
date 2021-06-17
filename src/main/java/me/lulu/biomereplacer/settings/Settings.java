package me.lulu.biomereplacer.settings;

import me.lulu.datounms.model.biome.BiomeData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Settings extends ConfigFile {
    public Map<BiomeData, List<BiomeData>> swaps;

    public Settings(JavaPlugin plugin) {
        super(plugin, "settings.yml");
    }

    @Override
    protected void loadValues() {
        loadSwaps();
    }

    @Override
    protected void saveValues() {

    }

    private void loadSwaps() {
        swaps = new HashMap<>();
        ConfigurationSection section = getConfigurationSection("BiomeChanger");

        for (String biomeName : section.getKeys(false)) {
            BiomeData swapTo = BiomeData.getFromString(biomeName);

            String swapsPath = section.getCurrentPath() + "." + biomeName;
            List<BiomeData> toSwaps = getStringList(swapsPath).stream()
                    .map(BiomeData::getFromString)
                    .collect(Collectors.toList());

            if (toSwaps.contains(BiomeData.ALL)) {
                swaps.clear();
                swaps.put(swapTo, Arrays.asList(BiomeData.values()));
                return;
            }

            swaps.put(swapTo, toSwaps);
        }
    }

}
