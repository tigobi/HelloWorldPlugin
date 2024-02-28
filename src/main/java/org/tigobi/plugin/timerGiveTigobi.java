package org.tigobi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class timerGiveTigobi extends HelloWorldPlugin {

    private int amount;
    private int timerIn;
    private String itemName;
    private Player player;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("timerGive")) {
            Player player = (Player) sender;

            if (args.length < 3) {
                player.sendMessage("Usage: /timerGive <item_name> <amount> <timer time in seconds>");
                return true;
            }
            itemName = args[0];
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("Введено не число предметов");
                return true;
            }
            try {
                timerIn = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage("Введено не время");
                return true;
            }

            // Запускаем таймер
            timerEnable();
        }
        return true;
    }

    public void timerEnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (timerIn > 0) {
                    Bukkit.broadcastMessage("Осталось времени: " + timerIn);
                    timerIn--;
                } else {
                    cancel();
                    ItemStack item = new ItemStack(Material.matchMaterial(itemName), amount);
                    player.getInventory().addItem(item);
                    player.sendMessage("Держи " + amount + " " + itemName + " через " + timerIn + " секунд");
                    Bukkit.broadcastMessage("Таймер завершен!");
                }
            }
        }.runTaskTimer(this, 0, 20);
    }
}