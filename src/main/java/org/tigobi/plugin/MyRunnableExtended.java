package org.tigobi.plugin;

import org.bukkit.scheduler.BukkitRunnable;

public class MyRunnableExtended extends BukkitRunnable {
    public String message;

    public String getMessage() {
        return message;
    }

    public MyRunnableExtended(String message) {
        this.message = message;
    }

    @Override
    public void run() {

    }
}
