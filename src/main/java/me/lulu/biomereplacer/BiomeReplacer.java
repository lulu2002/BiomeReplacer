package me.lulu.biomereplacer;

import me.lulu.biomereplacer.command.BiomeReplacerCommandGroup;
import me.lulu.biomereplacer.settings.Messages;
import me.lulu.biomereplacer.settings.Settings;
import me.lulu.datounms.DaTouNMS;
import me.lulu.datounms.UnSupportedNmsException;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.Arrays;
import java.util.List;

/**
 * 2020-07-05 2:26 PM
 */
public class BiomeReplacer extends SimplePlugin {

    private SimpleCommandGroup mainCommand = new BiomeReplacerCommandGroup();

    @Override
    protected void onPluginStart() {
        
    }

    @Override
    protected void onReloadablesStart() {
        try {
            DaTouNMS.setup();
            Settings.swapBiomes();
        } catch (UnSupportedNmsException e) {
            System.out.println("&cUnSupported NMS Version! (only for 1.8 ~ 1.16.2)");
            e.printStackTrace();
        }
    }

    @Override
    public List<Class<? extends YamlStaticConfig>> getSettings() {
        return Arrays.asList(
                Settings.class,
                Messages.class
        );
    }

    @Override
    public SimpleCommandGroup getMainCommand() {
        return mainCommand;
    }
}
