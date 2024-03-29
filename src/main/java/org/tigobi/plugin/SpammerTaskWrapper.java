package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class SpammerTaskWrapper {
    private final String message;
    private final BukkitTask task;

    public String getMessage() {
        return message;
    }

    public BukkitTask getTask() {
        return task;
    }

    public SpammerTaskWrapper(String message, Plugin plugin) {
        this.message = message;
        task = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(message);
            }
        }.runTaskTimer(plugin, 0, 100);
    }
}
