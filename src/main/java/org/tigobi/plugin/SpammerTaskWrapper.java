package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class SpammerTaskWrapper {
    public String message;
    public BukkitTask task;


    public String getMessage() {
        return message;
    }

    public BukkitTask getTask() {
        return task;
    }

    public SpammerTaskWrapper(String message, BukkitTask task) {
        this.message = message;
        this.task = task;
    }
}
