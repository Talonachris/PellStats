package com.talona.pellStats.getstats;

import com.talona.pellStats.Strings;
import org.bukkit.ChatColor;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedList;

public class GetCPU implements CommandExecutor {

    private final CentralProcessor processor;
    private long[] prevTicks;

    // Pufferspeicher für 5 und 10 Minuten (1 Eintrag pro 10 Sekunden)
    private final LinkedList<Double> last5Min = new LinkedList<>();
    private final LinkedList<Double> last10Min = new LinkedList<>();

    public GetCPU() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        processor = hardware.getProcessor();
        prevTicks = processor.getSystemCpuLoadTicks();

        // Starte wiederholte Messung alle 10 Sekunden
        new BukkitRunnable() {
            @Override
            public void run() {
                double load = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
                prevTicks = processor.getSystemCpuLoadTicks();

                // Füge zur Liste hinzu
                last10Min.add(load);
                if (last10Min.size() > 60) last10Min.removeFirst(); // 10 Minuten

                last5Min.add(load);
                if (last5Min.size() > 30) last5Min.removeFirst(); // 5 Minuten
            }
        }.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("PellStats"), 0L, 200L); // 200L = 10 Sekunden
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("pellstats.cpu")) {
            if (command.getName().equalsIgnoreCase("pellcpu")) {
                // CPU-Daten holen und berechnen
                double current = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
                prevTicks = processor.getSystemCpuLoadTicks();

                double avg5 = average(last5Min);
                double avg10 = average(last10Min);

                // Ausgabe der Ergebnisse
                sender.sendMessage(Strings.PREFIX + ChatColor.GOLD + "--------------[CPU]--------------");
                sender.sendMessage(ChatColor.BLUE + "CPU usage now: " + ChatColor.YELLOW + "" + ChatColor.BOLD + String.format("%.2f%%", current));
                sender.sendMessage(ChatColor.BLUE + "CPU usage last 5 minutes: " + ChatColor.YELLOW + "" + ChatColor.BOLD + String.format("%.2f%%", avg5));
                sender.sendMessage(ChatColor.BLUE + "CPU usage last 10 minutes: " + ChatColor.YELLOW + "" + ChatColor.BOLD + String.format("%.2f%%", avg10));
                sender.sendMessage(Strings.PREFIX + ChatColor.GOLD + "---------------------------------");
                return true;
            }
        }
        return false;
    }

    private double average(LinkedList<Double> list) {
        if (list.isEmpty()) return 0.0;
        double sum = 0;
        for (double d : list) sum += d;
        return sum / list.size();
    }
}
