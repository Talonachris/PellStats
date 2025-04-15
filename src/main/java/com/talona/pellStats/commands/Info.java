package com.talona.pellStats.commands;

import com.talona.pellStats.Strings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class Info implements CommandExecutor {

    private final JavaPlugin plugin;

    public Info(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pellinfo")) {
            PluginDescriptionFile pdf = plugin.getDescription();
            String version = pdf.getVersion();
            String message = Strings.PREFIX + ChatColor.BLUE + "Plugin Version V" + version + " - A Plugin by Talonachris!";

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("pellstats.brandhide")) {
                    player.sendMessage(message);
                    return true;
                }
                if (player.isOp()) {
                    player.sendMessage(message);
                    return true;
                }
                if (player.hasPermission("pellstats.githide")) {
                    player.sendMessage(ChatColor.RED + "You are not permitted to use this command!");
                    return true;
                }
                return true;
            } else {
                // Der Sender ist die Konsole
                sender.sendMessage(message);
                return true;
            }
        }
        return false;
    }
}