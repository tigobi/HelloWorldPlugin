package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class AdSpammer implements CommandExecutor {
    private Plugin plugin;
    private BukkitTask positionTask;
    private HashMap<String, BukkitTask> messageTasks = new HashMap<>();
    public AdSpammer(Plugin plugin) {
        this.plugin = plugin;
    }

    String message = "";
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        message = "";
        if (args[0].equalsIgnoreCase("start")) {
            if (positionTask == null || positionTask.isCancelled()) {
                message = argsToStringFrom2Element(args);
                messageTasks.put(message, positionTask);
                whenStarts(message, messageTasks);
            } else {
                commandSender.sendMessage("Spam is already running!");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("stop")) {
            message = argsToStringFrom2Element(args);
            commandSender.sendMessage("adSpammer stop");
            messageTasks.get(message).cancel();
            messageTasks.remove(message);

            return true;
        }
        return false;
    }

    private String argsToStringFrom2Element(String[] args) {
        StringBuilder a = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            a.append(args[i] + " ");
        }
        return a.toString();
    }

    private void whenStarts(String spamString, HashMap<String, BukkitTask> messageTasks) {
        positionTask = messageTasks.get(spamString);
        positionTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(spamString);
            }
        }.runTaskTimer(plugin, 0, 100);
    }
}