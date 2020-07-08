package me.lulu.biomereplacer.command;

import org.mineacademy.fo.command.ReloadCommand;
import org.mineacademy.fo.command.SimpleCommandGroup;

/**
 * 2020-07-05 2:40 PM
 */
public class BiomeReplacerCommandGroup extends SimpleCommandGroup {

    @Override
    protected void registerSubcommands() {
        registerSubcommand(new ReloadCommand());
    }
}