package me.lulu.biomereplacer.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@UtilityClass
public class Messenger {

    public void send(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }

    public String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(color(message));
    }
}
