package me.lulu.biomereplacer.command;

import me.lulu.biomereplacer.BiomeReplacer;
import me.lulu.biomereplacer.utils.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BiomeReplacerCommand implements CommandExecutor {

    private BiomeReplacer plugin;

    public BiomeReplacerCommand(BiomeReplacer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if ("reload".equalsIgnoreCase(args[0])) {
            plugin.onReload();
        }

        return false;
    }
}
