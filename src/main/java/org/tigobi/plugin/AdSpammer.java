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
    private String message;


    public AdSpammer(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("AdSpammerStart")) {
            commandSender.sendMessage("Лолчик");
            if (positionTask == null || positionTask.isCancelled()) {
                message = args[0];
                int a = args.length;
                commandSender.sendMessage("Длина: " + a);

                whenStarts(argsToString(args));
            } else {
                commandSender.sendMessage("Spam is already running!");
            }
            return true;
        }
        if (command.getName().equalsIgnoreCase("AdSpammerEnd")) {
            commandSender.sendMessage("adSpammer end");
            positionTask.cancel();
            positionTask = null;
            return true;
        } else {
            int a = args.length;
            commandSender.sendMessage("Длина:" + a);
        }

        return false;
    }

    public String argsToString(String[] args) {
        String result = "";
        for (int i = 0; i < args.length; i++) {
            result += args[i] + " ";
        }
        return result;
    }


    public void whenStarts(String spamString) {
        positionTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(spamString);
            }
        }.runTaskTimer(plugin, 0, 100);
    }

}