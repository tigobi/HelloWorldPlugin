package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class MyTimerCommandExecutor implements CommandExecutor {
    private Plugin plugin;
    private BukkitTask positionTask;

    public MyTimerCommandExecutor(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("messageStart")) {
            if (positionTask == null || positionTask.isCancelled()) {
                commandSender.sendMessage("Message start");
                whenStarts();
            } else {
                commandSender.sendMessage("Timer is already running!");
            }
            return true;
        }
        if (command.getName().equalsIgnoreCase("MessageEnd")) {
            commandSender.sendMessage("Message end");
            positionTask.cancel();
            positionTask = null;
            return true;
        }

        return false;
    }
    public void whenStarts() {
        positionTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Timer is working");
            }
        }.runTaskTimer(plugin, 0, 100);
    }
}