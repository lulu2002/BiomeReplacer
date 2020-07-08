package me.lulu.biomereplacer.settings;

import me.lulu.datounms.DaTouNMS;
import me.lulu.datounms.model.BiomeData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.settings.SimpleSettings;
import org.mineacademy.fo.settings.YamlConfigLoader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings extends SimpleSettings {

    @Override
    protected void preLoad() {
        super.preLoad();
        YamlConfigLoader.load(this.getClass());
    }

    @Override
    protected int getConfigVersion() {
        return 1;
    }

    public static class BiomeChanger {
        public static final Map<BiomeData, List<BiomeData>> SWAPS = new HashMap<>();

        private static void init() {
            loadSwaps();
        }

        private static void loadSwaps() {
            final YamlConfiguration config = getConfig();
            final ConfigurationSection section = config.getConfigurationSection("BiomeChanger");

            for (String biomeName : section.getKeys(false)) {
                BiomeData swapTo = BiomeData.getFromString(biomeName);

                String swapsPath = section.getCurrentPath() + "." + biomeName;
                List<BiomeData> toSwaps = getList(swapsPath, BiomeData.class);

                if (toSwaps.contains(BiomeData.ALL)) {
                    SWAPS.clear();
                    SWAPS.put(swapTo, Arrays.asList(BiomeData.values()));
                    return;
                }

                SWAPS.put(swapTo, toSwaps);
            }
        }
    }

    public static void swapBiomes() {
        Map<BiomeData, List<BiomeData>> swaps = BiomeChanger.SWAPS;

        swaps.forEach((swapToWhat, biomesToSwap) -> {
            for (BiomeData data : biomesToSwap) {
                swap(data, swapToWhat);
            }
        });
    }

    private static void swap(BiomeData from, BiomeData to) {
        if (from == BiomeData.ALL)
            return;

        try {
            DaTouNMS.getBiomeHandler().swap(from, to);
            Common.log(Messages.BIOME_REPLACED
                    .replace("{biome}", from.name())
                    .replace("{changed}", to.name()));
        } catch (IllegalAccessException | NoSuchFieldException |
                IndexOutOfBoundsException | NullPointerException e) {
            Common.log(Messages.BIOME_NOT_EXIST.replace("{biome}", from.name()));
        } catch (Exception e) {
            Common.log(Messages.CAN_NOT_REPLACE_BIOME
                    .replace("{biome}", from.name())
                    .replace("{changed}", to.name() + " -> "));
        }
    }
}
