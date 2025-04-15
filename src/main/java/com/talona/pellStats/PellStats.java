package com.talona.pellStats;
import com.talona.pellStats.commands.GitHub;
import com.talona.pellStats.commands.Info;
import com.talona.pellStats.getstats.GetCPU;
import com.talona.pellStats.getstats.GetRAM;
import com.talona.pellStats.getstats.GetStorage;
import com.talona.pellStats.getstats.GetTPS;
import org.bukkit.plugin.java.JavaPlugin;

public final class PellStats extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("pellinfo").setExecutor(new Info(this));
        getCommand("pellcpu").setExecutor(new GetCPU());
        GetTPS tpsCommand = new GetTPS(this);
        getCommand("pelltps").setExecutor(tpsCommand);
        getCommand("pellstorage").setExecutor(new GetStorage(this));
        getCommand("pellgit").setExecutor(new GitHub());
        getCommand("pellram").setExecutor(new GetRAM());
        getLogger().info("Pelle is watching the server!");

}

    @Override
    public void onDisable() {
        getLogger().info("Pelle says goodbye!");
    }
}