package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class HelloWorldPlugin extends JavaPlugin {
    public static BukkitTask timerTask;

    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled");
        var runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Timer is working");
            }
        };
        timerTask = runnable.runTaskTimer(this, 0, 100);
    }

    @Override
    public void onDisable() {
        timerTask.cancel();
        getLogger().info("Plugin disabled");
    }
}
