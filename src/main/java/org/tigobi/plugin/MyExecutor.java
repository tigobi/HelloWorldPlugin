package org.tigobi.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitTask;

public class MyExecutor implements CommandExecutor {
    private BukkitTask positionTask;
    private final HelloWorldPlugin plugin;

    public MyExecutor(HelloWorldPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("messageStart")) {
            BukkitTask timerTask = plugin.timerTask;
            commandSender.sendMessage("message start");
            return true;
        }
        return false;
    }
}