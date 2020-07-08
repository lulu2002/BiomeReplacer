package me.lulu.biomereplacer.settings;

import org.mineacademy.fo.settings.YamlConfigLoader;
import org.mineacademy.fo.settings.YamlStaticConfig;

/**
 * 2020-07-05 2:34 PM
 */
public class Messages extends YamlStaticConfig {

    @Override
    protected void load() throws Exception {
        createFileAndLoad("messages.yml");
    }

    @Override
    protected void preLoad() {
        YamlConfigLoader.load(this.getClass());
    }

    public static String CAN_NOT_REPLACE_BIOME;
    public static String BIOME_NOT_EXIST;
    public static String BIOME_REPLACED;
}