package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class HelloWorldPlugin extends JavaPlugin {
    private  BukkitTask myTask;

    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled");
        var myRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Timer is working");
            }
        };
             myTask =  myRunnable.runTaskTimer(this, 0, 100);
    }
    @Override
    public void onDisable() {
        myTask.cancel();
        getLogger().info("Plugin disabled");
    }
}
