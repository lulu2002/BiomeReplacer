package me.lulu.biomereplacer.api;

import lombok.RequiredArgsConstructor;
import me.lulu.datounms.DaTouNMS;
import me.lulu.datounms.model.biome.BiomeData;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class BiomeReplacerApi {

    public final JavaPlugin plugin;

    public void replace(BiomeData from, BiomeData to) throws IllegalAccessException, NoSuchFieldException, IndexOutOfBoundsException, Exception {
        DaTouNMS.getBiomeHandler().swap(from, to);
    }
}
