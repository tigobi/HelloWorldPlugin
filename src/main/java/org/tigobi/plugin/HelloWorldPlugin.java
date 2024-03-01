package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class HelloWorldPlugin extends JavaPlugin {
    private BukkitRunnable myTask;

    public void onEnable() {
        getLogger().info("Plugin enabled");
        BukkitRunnable myRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Timer is working");
            }
        };
        BukkitTask myTask = myRunnable.runTaskTimer(this, 0, 100);
    }
    public void onDisable() {
        myTask.cancel();
        getLogger().info("Plugin disabled");
    }
}
