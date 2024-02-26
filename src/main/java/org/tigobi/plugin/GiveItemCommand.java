package org.tigobi.plugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GiveItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("daymne")) {
            Player player = (Player) sender;

            if (args.length < 2) {
                player.sendMessage("Usage: /daymne <item_name> <amount>");
                return true;
            }

            String itemName = args[0];
            int amount;
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("Введено не число предметов");
                return true;
            }

            ItemStack item = new ItemStack(Material.matchMaterial(itemName), amount);
            player.getInventory().addItem(item);
            player.sendMessage("Держи " + amount + " " + itemName + ".");

        }
        return true;
    }
}

