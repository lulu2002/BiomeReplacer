package me.lulu.biomereplacer.settings;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 2020-07-05 2:34 PM
 */
public class Messages extends ConfigFile {

    public String canNotReplaceBiome;
    public String biomeNotExist;
    public String biomeReplaced;

    public Messages(JavaPlugin plugin) {
        super(plugin, "messages.yml");
    }

    @Override
    protected void loadValues() {
        biomeReplaced = getString("Biome_Replaced", "&7Successfully replace &a{biome} &7to &a{changed}&7.");
        biomeNotExist = getString("Biome_Not_Exist", "&cBiome &4{biome} does not exist.");
        canNotReplaceBiome = getString("Can_Not_Replace_Biome", "&cError while replacing &4{biome} &cto &4{changed}&c.");
    }

    @Override
    protected void saveValues() {
        set("Biome_Replaced", biomeReplaced);
        set("Biome_Not_Exist", biomeNotExist);
        set("Can_Not_Replace_Biome", canNotReplaceBiome);
    }
}
