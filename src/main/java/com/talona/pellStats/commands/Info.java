package com.talona.pellStats.commands;

import com.talona.pellStats.Strings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Info implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        commandSender.sendMessage(Strings.PREFIX + ChatColor.BLUE + "PellStats Version: 0.2 - Plugin made by " + ChatColor.BOLD + "Talonachris");
        return true;
    }
}
