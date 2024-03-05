package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HelloWorldPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled");
        MyTimerCommandExecutor myTimerCommandExecutor = new MyTimerCommandExecutor(this);
        this.getCommand("messageStart").setExecutor(myTimerCommandExecutor);
        var runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Timer is working");
            }
        };
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }
}