package org.tigobi.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled");
        MyTimerCommandExecutor myTimerCommandExecutor = new MyTimerCommandExecutor(this);
        this.getCommand("messageStart").setExecutor(myTimerCommandExecutor);
        this.getCommand("messageEnd").setExecutor(myTimerCommandExecutor);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }
}