package dev.k41j0;

import dev.k41j0.commands.CommandManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ApesTogetherStrong extends JavaPlugin{

    FileConfiguration config = this.getConfig();

    @Override
    public void onDisable() {
        System.out.println("Hope to see you again!");
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("ats").setExecutor(new CommandManager(this));
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        System.out.println("Apes Together Strong has successfully loaded!");
    }
}