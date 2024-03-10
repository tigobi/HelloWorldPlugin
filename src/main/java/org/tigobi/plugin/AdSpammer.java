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
    private final Plugin plugin;
    private final HashMap<Integer, BukkitTask> messageTasks = new HashMap<>();

    public AdSpammer(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        int playerId = commandSender.hashCode();
        String message = argsToStringFrom2Element(args);
        if (args[0].equalsIgnoreCase("start")) {
            if (messageTasks.get(playerId) == null || messageTasks.get(playerId).isCancelled()) {
                commandSender.sendMessage("adSpammer start");
                messageTasks.put(playerId, startTimerTask(message));
            } else {
                commandSender.sendMessage("Spam is already running!");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("stopAll")) {
            stopAll(messageTasks);
            commandSender.sendMessage("adSpammer stopAll");
        }
        if (args[0].equalsIgnoreCase("stop")) {
            if (messageTasks.get(playerId) != null) {
                commandSender.sendMessage("adSpammer stop");
                messageTasks.get(playerId).cancel();
                messageTasks.remove(playerId);
            } else {
                commandSender.sendMessage("You don't have tasks running");
            }
            return true;
        }
        return false;
    }

    private void stopAll(HashMap<Integer, BukkitTask> map) {
        var iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            var entry = iterator.next();
            entry.getValue().cancel();
            iterator.remove();
        }
    }

    private String argsToStringFrom2Element(String[] args) {
        StringBuilder a = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            a.append(args[i] + " ");
        }
        return a.toString();
    }

    private BukkitTask startTimerTask(String spamString) {
        var positionTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(spamString);
            }
        }.runTaskTimer(plugin, 0, 100);
        return positionTask;
    }
}