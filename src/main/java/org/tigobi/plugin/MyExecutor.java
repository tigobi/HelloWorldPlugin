package org.tigobi.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitTask;

public class MyExecutor implements CommandExecutor {
    public BukkitTask positionTask;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("messageStart")) {
            commandSender.sendMessage("message start");
            timerTask
            return true;
        }
        return false;
    }
}