package com.talona.pellStats;

import com.talona.pellStats.getstats.GetRAM;
import org.bukkit.plugin.java.JavaPlugin;

public final class PellStats extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("pellram").setExecutor(new GetRAM());

        getLogger().info("Pelle is watching the server!");

    }

    @Override
    public void onDisable() {
        getLogger().info("Pelle says goodbye!");
    }
}
