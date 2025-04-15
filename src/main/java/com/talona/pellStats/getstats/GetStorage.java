package com.talona.pellStats.getstats;

import com.talona.pellStats.Strings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class GetStorage implements CommandExecutor {

    private final JavaPlugin plugin;

    public GetStorage(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pellstorage")) {
            if (sender.hasPermission("pellstats.storage")) {
                // Server Speicher
                File serverFolder = plugin.getDataFolder().getParentFile();
                long serverFolderSize = getFolderSize(serverFolder);
                double serverFolderSizeGB = (double) serverFolderSize / (1024 * 1024 * 1024);

                // Host Speicher
                File root = new File("/");
                long totalSpace = root.getTotalSpace();
                double totalSpaceGB = (double) totalSpace / (1024 * 1024 * 1024);

                sender.sendMessage(Strings.PREFIX + org.bukkit.ChatColor.GOLD + "------------[Storage]------------");
                sender.sendMessage(ChatColor.BLUE + "Serveruse: " + ChatColor.YELLOW + String.format("%.2f", serverFolderSizeGB) + " GB");
                sender.sendMessage(ChatColor.BLUE + "Hostsize: " + ChatColor.YELLOW + String.format("%.2f", totalSpaceGB) + " GB");
                sender.sendMessage(Strings.PREFIX + org.bukkit.ChatColor.GOLD + "---------------------------------");
                return true;
            } else {
                sender.sendMessage(Strings.PREFIX + ChatColor.RED + "Du hast keine Berechtigung, diesen Befehl auszuführen!");
                return true;
            }
        }
        return false;
    }

    private long getFolderSize(File folder) {
        long size = 0;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += getFolderSize(file);
                }
            }
        }
        return size;
    }
}