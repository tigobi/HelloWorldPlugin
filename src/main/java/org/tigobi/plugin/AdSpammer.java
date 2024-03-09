package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class AdSpammer implements CommandExecutor {
    private Plugin plugin;
    private BukkitTask positionTask;
    public AdSpammer(Plugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("start")) {
            if (positionTask == null || positionTask.isCancelled()) {
                whenStarts(argsToStringFrom2Element(args));
            } else {
                commandSender.sendMessage("Spam is already running!");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("stop")) {
            commandSender.sendMessage("adSpammer stop");
            positionTask.cancel();
            positionTask = null;
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

    private void whenStarts(String spamString) {
        positionTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(spamString);
            }
        }.runTaskTimer(plugin, 0, 100);
    }
}