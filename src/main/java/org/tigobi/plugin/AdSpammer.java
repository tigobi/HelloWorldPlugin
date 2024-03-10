package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

public class AdSpammer implements CommandExecutor {
    private Plugin plugin;
    private HashMap<Integer, BukkitTask> messageTasks = new HashMap<>();
    public AdSpammer(Plugin plugin) {
        this.plugin = plugin;
    }

    private int playerId = 0;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        playerId = commandSender.hashCode();
        String message = argsToStringFrom2Element(args);
        if (args[0].equalsIgnoreCase("start")) {
            if (messageTasks.get(playerId) == null || messageTasks.get(playerId).isCancelled()) {
                messageTasks = whenStarts(message, playerId, messageTasks);
            } else {
                commandSender.sendMessage("Spam is already running!");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("stop")) {
            commandSender.sendMessage("adSpammer stop");
            messageTasks.get(playerId).cancel();
            messageTasks.remove(commandSender.hashCode());

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

    private HashMap<Integer, BukkitTask> whenStarts(String spamString, int playerId, HashMap<Integer, BukkitTask> tasks) {
        BukkitTask positionTask;
        positionTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(spamString);
            }
        }.runTaskTimer(plugin, 0, 100);
        tasks.put(playerId, positionTask);
        return tasks;
    }
}