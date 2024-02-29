package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HelloWorldPlugin extends JavaPlugin {
    private boolean running = true;

    public void onEnable() {
        getLogger().info("Plugin enabled");
        BukkitRunnable myTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Таймер работает(кажется) ");
                getLogger().info("Таймер работает");
            }
        }.runTaskTimer(this, 0, 120);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!running) {
                    this.cancel();
                }
                Bukkit.broadcastMessage("Таймер работает(кажется) ");
                getLogger().info("Таймер работает");
            }
        }.runTaskTimer(this, 0, 120);
    }

    public void stopRunning() {
        this.running = false;
    }

    public void onDisable() {
        stopRunning();
        getLogger().info("Plugin disabled");
    }

}
