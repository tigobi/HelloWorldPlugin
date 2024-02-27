package org.tigobi.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldPlugin extends JavaPlugin {
    public void onEnable(){
        getLogger().info("Plugin enabled🙁");
        this.getCommand("tikhon").setExecutor(new MyCommandExecutor());
        this.getCommand("daymne").setExecutor(new GiveItemCommand());
        this.getCommand("timerGive").setExecutor(new TimerForSomething());
    }
    public void onDisable(){
        getLogger().info("Plugin disabled");
    }

}
