package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SpammerTaskWrapper extends BukkitRunnable {
    public String message;
    private final Plugin plugin;

    public SpammerTaskWrapper(Plugin plugin) {
        this.plugin = plugin;
    }

    public String getMessage() {
        return message;
    }

    public SpammerTaskWrapper(String message) {
        this.message = message;
        this.plugin = plugin;
        var positionTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(message);
            }
        }.runTaskTimer(plugin, 0, 100);

    }


    @Override
    public void run() {

    }
}
