package org.tigobi.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldPlugin extends JavaPlugin {
    public void onEnable(){
        getLogger().info("Plugin enabled🙁");
        this.getCommand("timerGive").setExecutor(new TimerGiveTigobi());
    }
    public void onDisable(){
        getLogger().info("Plugin disabled");
    }

}
