package org.tigobi.plugin;

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

    public SpammerTaskWrapper(String message, BukkitTask task) {
        this.message = message;
        this.task = task;
    }
}
