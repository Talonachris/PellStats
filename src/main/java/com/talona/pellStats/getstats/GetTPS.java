package com.talona.pellStats.getstats;

import com.talona.pellStats.Strings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GetTPS implements CommandExecutor {

    private final Plugin plugin;
    private final List<Double> recentTps = new ArrayList<>();
    private long lastTime = System.currentTimeMillis();

    public GetTPS(Plugin plugin) {
        this.plugin = plugin;
        startTpsMonitoring();
    }

    private void startTpsMonitoring() {
        new BukkitRunnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                double tps = 20.0 / ((currentTime - lastTime) / 1000.0);
                if (tps > 20.0) {
                    tps = 20.0;
                }
                recentTps.add(tps);
                if (recentTps.size() > 200) {
                    recentTps.remove(0);
                }
                lastTime = currentTime;
            }
        }.runTaskTimerAsynchronously(plugin, 0L, 1L);
    }

    private double getAverageTps(int seconds) {
        if (recentTps.isEmpty()) {
            return 20.0;
        }

        int ticks = seconds * 20;
        int startIndex = Math.max(0, recentTps.size() - ticks);
        double sum = 0;
        for (int i = startIndex; i < recentTps.size(); i++) {
            sum += recentTps.get(i);
        }
        return sum / (recentTps.size() - startIndex);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pelltps")) {
            double tps1 = getAverageTps(60);
            double tps5 = getAverageTps(60 * 5);
            double tps10 = getAverageTps(60 * 10);
            DecimalFormat df = new DecimalFormat("0.00");
            String output = ChatColor.GREEN + "--- Server TPS ---\n" +
                    ChatColor.YELLOW + "Letzte Minute: " + ChatColor.WHITE + df.format(tps1) + "\n" +
                    ChatColor.YELLOW + "Letzte 5 Minuten: " + ChatColor.WHITE + df.format(tps5) + "\n" +
                    ChatColor.YELLOW + "Letzte 10 Minuten: " + ChatColor.WHITE + df.format(tps10);

            if (!(sender instanceof Player)) {
                sender.sendMessage(output);
                return true;
            } else {
                Player player = (Player) sender;
                if (player.hasPermission("pellstats.tps")) { // Überprüfe die Permission
                    player.sendMessage(Strings.PREFIX + ChatColor.GOLD + "--------------[TPS]--------------");
                    player.sendMessage(ChatColor.BLUE + "Last minute: " + ChatColor.YELLOW + "" + ChatColor.BOLD + df.format(tps1));
                    player.sendMessage(ChatColor.BLUE + "Last 5 minutes: " + ChatColor.YELLOW + "" + ChatColor.BOLD + df.format(tps5));
                    player.sendMessage(ChatColor.BLUE + "Last 10 minutes: " + ChatColor.YELLOW + "" + ChatColor.BOLD + df.format(tps10));
                    player.sendMessage(Strings.PREFIX + ChatColor.GOLD + "---------------------------------");
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "You are not permitted to use this command!");
                    return true;
                }
            }
        }
        return false;
    }
}