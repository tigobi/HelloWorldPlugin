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
    private final Plugin plugin;
    private final HashMap<Integer, ArrayList<BukkitRunnable>> messageTasks = new HashMap<>();

    public AdSpammer(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        int playerId = commandSender.hashCode();
        String message = argsToStringFrom2Element(args);
        if (args[0].equalsIgnoreCase("start")) {
            if (messageTasks.get(playerId) == null) {
                messageTasks.put(playerId, new ArrayList<BukkitRunnable>());
            }
            commandSender.sendMessage("adSpammer start");
            messageTasks.get(playerId).add(startTimerTask(message));
            return true;
        }
        if (args[0].equalsIgnoreCase("stopAll")) {
            stopAll();
            commandSender.sendMessage("adSpammer stopAll");
        }
        if (args[0].equalsIgnoreCase("list")) {
            if (messageTasks.get(playerId) == null || messageTasks.get(playerId).size() == 0) {
                commandSender.sendMessage("You don't have tasks running");
            } else if (messageTasks.get(playerId).size() == 1) {
                commandSender.sendMessage("You have 1 task");
            } else {
                commandSender.sendMessage("You have " + messageTasks.get(playerId).size() + " tasks");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("stop")) {
            if (messageTasks.get(playerId) != null) {
                if (args[1].equalsIgnoreCase("all")) {
                    stopAllArrayTasks(messageTasks.get(playerId));
                    commandSender.sendMessage("Stopped all your tasks🥰");
                    return true;
                }
                int stopTaskNumber = -1;
                String invalidTaskNumber = "Invalid task number should be \"all\" or number from 1 to " + messageTasks.get(playerId).size();
                try {
                    stopTaskNumber = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    commandSender.sendMessage(invalidTaskNumber);
                    return false;
                }
                if (stopTaskNumber > messageTasks.get(playerId).size() || stopTaskNumber < 1) {
                    commandSender.sendMessage(invalidTaskNumber);
                    return false;
                }
                commandSender.sendMessage("adSpammer stop");
                messageTasks.get(playerId).get(stopTaskNumber - 1).cancel();
                messageTasks.get(playerId).remove(stopTaskNumber - 1);
            } else {
                commandSender.sendMessage("You don't have tasks running");
            }
            return true;
        }
        return false;
    }

    private void stopAllArrayTasks(ArrayList<BukkitRunnable> tasks) {
        var arrayIterator = tasks.iterator();
        while (arrayIterator.hasNext()) {
            var arrayEntry = arrayIterator.next();
            arrayEntry.cancel();
            arrayIterator.remove();
        }
    }

    private void stopAll() {
        var iterator = messageTasks.entrySet().iterator();
        while (iterator.hasNext()) {
            stopAllArrayTasks(iterator.next().getValue());
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

    private BukkitRunnable startTimerTask(String spamString) {
        var positionTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(spamString);
            }
        }.runTaskTimer(plugin, 0, 100);
        return (BukkitRunnable) positionTask;
    }
}