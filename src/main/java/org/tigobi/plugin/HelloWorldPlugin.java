package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class HelloWorldPlugin extends JavaPlugin {
    private boolean running = true;
    private BukkitRunnable myTask;

    public void onEnable() {

        getLogger().info("Plugin enabled");
        BukkitRunnable myRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Таймер работает(кажется) ");
            }
        };
        BukkitTask myTask = myRunnable.runTaskTimer(this, 20L, 100);
    }


    public void onDisable() {
        myTask.cancel();
        getLogger().info("Plugin disabled");
    }
}
