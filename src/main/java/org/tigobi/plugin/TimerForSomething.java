package org.tigobi.plugin;

import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TimerForSomething extends JavaPlugin {
    private BossBar bossBar;
    private int taskId;
    private int amount;
    private int giveInterval;
    private Player player;
    private String itemName;
    private ItemStack item;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("timerGive")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Эту команду могут использовать только игроки.");
                return true;
            }

            player = (Player) sender;

            if (args.length < 3) {
                player.sendMessage("Использование: /timerGive <item_name> <amount> <timer in seconds>");
                return true;
            }

            itemName = args[0];
            try {
                amount = Integer.parseInt(args[1]);
                giveInterval = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage("Некорректный ввод. Убедитесь, что количество предметов и интервал времени указаны в виде чисел.");
                return true;
            }

            if (giveInterval <= 0) {
                player.sendMessage("Интервал времени должен быть положительным числом.");
                return true;
            }
            ItemStack item = new ItemStack(Material.matchMaterial(itemName), amount);
            player.getInventory().addItem(item);
            onEnable();

            player.sendMessage("Вы будете получать " + amount + " " + itemName + " каждые " + giveInterval + " секунд.");

            if (bossBar == null) {
                bossBar = getServer().createBossBar("До следующей выдачи:", BarColor.RED, BarStyle.SOLID);
                bossBar.setVisible(true);

                // Создаем задачу планировщика для обновления таймера каждую секунду
                taskId = getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
                    updateTimer(player);
                }, 0L, 20L); // Первый аргумент - задержка (в тиках), второй - период (в тиках)
            }


        } else if (command.getName().equalsIgnoreCase("timerGiveStop")) {
            if (bossBar != null) {
                bossBar.removeAll();
                getServer().getScheduler().cancelTask(taskId);
                bossBar = null;
                sender.sendMessage("Таймер остановлен.");
            } else {
                sender.sendMessage("Таймер уже остановлен или не был запущен.");
            }
        }

        return true;
    }

    @Override
    public void onEnable() {
        bossBar = getServer().createBossBar("Timer", BarColor.RED, BarStyle.SOLID);
        bossBar.setVisible(true);

        taskId = getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            updateTimer();
        }, 0L, 20L); // Первый аргумент - задержка (в тиках), второй - период (в тиках)

        // Добавляем все онлайн игроки к нашей полосе состояния
        for (Player player : getServer().getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }
    }

    @Override
    public void onDisable() {
        bossBar.removeAll();
        getServer().getScheduler().cancelTask(taskId);
    }

    private void updateTimer() {
        if (giveInterval <= 0) {
            // Как только время заканчивается, удаляем полосу состояния и отменяем задачу планировщика
            bossBar.removeAll();
            getServer().getScheduler().cancelTask(taskId);
            return;
            ItemStack item = new ItemStack(Material.matchMaterial(itemName), amount);
            player.getInventory().addItem(item);
            giveInterval = this.giveInterval;
        }

        double progress = (double) giveInterval / 600; // Прогресс в долях от максимального времени (600 тиков)
        bossBar.setProgress(progress);

        // Форматируем оставшееся время в минуты и секунды
        int minutes = giveInterval / 1200;
        int seconds = (giveInterval % 1200) / 20;

        bossBar.setTitle("Timer: " + String.format("%02d:%02d", minutes, seconds));

        // Уменьшаем оставшееся время
        giveInterval--;
    }

}