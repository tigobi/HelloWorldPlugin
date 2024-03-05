package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class MyTimerCommandExecutor implements CommandExecutor {
    private BukkitTask positionTask;
    private HelloWorldPlugin plugin;

    public void myTimerCommandExecutor(HelloWorldPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("messageStart")) {
            commandSender.sendMessage("message start");
            positionTask.runTask
        }
        if (command.getName().equalsIgnoreCase("MessageEnd")) {
            commandSender.sendMessage("message end");
            positionTask.cancel();
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
        positionTask = myRunnable.runTaskTimer(plugin, 0, 100);
    }
}