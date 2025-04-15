package com.talona.pellStats.getstats;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.text.DecimalFormat;

import com.talona.pellStats.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GetRAM implements CommandExecutor {

    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public static String getRamAuslastungsString() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        long usedMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        long maxMemory = memoryMXBean.getHeapMemoryUsage().getMax();

        double usedMB = (double) usedMemory / (1024 * 1024);
        double maxMB = (double) maxMemory / (1024 * 1024);
        double percentageUsed = (usedMB / maxMB) * 100;

        return "RAM usage: " + DF.format(usedMB) + " MB / " + DF.format(maxMB) + " MB (" + DF.format(percentageUsed) + "%)";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage( Strings.PREFIX + "RAM Usage: " + getRamAuslastungsString());
        return true;
    }
}