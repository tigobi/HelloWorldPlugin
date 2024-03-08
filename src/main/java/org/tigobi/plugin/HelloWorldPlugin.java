package org.tigobi.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled");
        var myTimerCommandExecutor = new MyTimerCommandExecutor(this);
        var adSpammer = new AdSpammer(this);
        this.getCommand("messageStart").setExecutor(myTimerCommandExecutor);
        this.getCommand("messageEnd").setExecutor(myTimerCommandExecutor);
        this.getCommand("adSpammer").setExecutor(adSpammer);

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }
}