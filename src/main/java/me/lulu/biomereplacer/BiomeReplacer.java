package me.lulu.biomereplacer;

import lombok.Getter;
import me.lulu.biomereplacer.command.BiomeReplacerCommand;
import me.lulu.biomereplacer.settings.Messages;
import me.lulu.biomereplacer.settings.Settings;
import me.lulu.biomereplacer.utils.Messenger;
import me.lulu.datounms.DaTouNMS;
import me.lulu.datounms.UnSupportedNmsException;
import me.lulu.datounms.model.biome.BiomeData;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

/**
 * 2020-07-05 2:26 PM
 */
public class BiomeReplacer extends JavaPlugin {

    @Getter
    private static BiomeReplacer plugin;

    private Settings settings;
    private Messages messages;

    @Override
    public void onEnable() {
        plugin = this;
        onReload();
    }

    public void onReload() {
        try {
            DaTouNMS.setup(this);
            loadConfigFiles();
            registerCommands();
            swapBiomes();
        } catch (UnSupportedNmsException e) {
            System.out.println("&cUnSupported NMS Version! (only for 1.8 ~ 1.17.1)");
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        getCommand("biomereplacer").setExecutor(new BiomeReplacerCommand(this));
    }

    private void loadConfigFiles() {
        settings = new Settings(this);
        messages = new Messages(this);
    }


    public void swapBiomes() {
        Map<BiomeData, List<BiomeData>> swaps = settings.swaps;

        swaps.forEach((swapToWhat, biomesToSwap) -> {
            for (BiomeData data : biomesToSwap) {
                swap(data, swapToWhat);
            }
        });
    }

    private void swap(BiomeData from, BiomeData to) {
        if (from == BiomeData.ALL)
            return;

        try {
            DaTouNMS.getBiomeHandler().swap(from, to);
            Messenger.log(messages.biomeReplaced
                    .replace("{biome}", from.name())
                    .replace("{changed}", to.name()));
        } catch (IllegalAccessException | NoSuchFieldException |
                IndexOutOfBoundsException | NullPointerException e) {
            Messenger.log(messages.biomeNotExist.replace("{biome}", from.name()));
        } catch (Exception e) {
            e.printStackTrace();
            Messenger.log(messages.canNotReplaceBiome
                    .replace("{biome}", from.name())
                    .replace("{changed}", to.name()));
        }
    }
}
