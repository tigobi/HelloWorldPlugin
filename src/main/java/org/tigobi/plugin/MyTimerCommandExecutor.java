package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class MyTimerCommandExecutor implements CommandExecutor {
    private BukkitTask positionTask;
    private final HelloWorldPlugin plugin;

    public MyTimerCommandExecutor(HelloWorldPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("messageStart")) {
            commandSender.sendMessage("message start");
        }
        return false;
    }

    public MyTimerCommandExecutor(HelloWorldPlugin plugin) {
        this.plugin = plugin;
    }


    public void whenStarts() {
        var myRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Timer is working");
            }
        };
        myTask = myRunnable.runTaskTimer(this, 0, 100);
        myTask = myRunnable.runTaskTimer(this, 0, 100);
    }
}