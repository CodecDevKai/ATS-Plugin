package dev.k41j0;

import dev.k41j0.commands.CommandManager;
import dev.k41j0.systems.ConfigSystem;
import dev.k41j0.systems.HealthSystem;
import dev.k41j0.systems.DebugSystem;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ApesTogetherStrong extends JavaPlugin{
    private DebugSystem debugSystem;
    private HealthSystem healthSystem;
    private ConfigSystem configSystem;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        debugSystem = new DebugSystem(this);
        healthSystem = new HealthSystem(this);
        configSystem = new ConfigSystem(this);

        configSystem.load();

        getCommand("ats").setExecutor(new CommandManager(this));
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        System.out.println("Apes Together Strong has successfully loaded!");
    }

    @Override
    public void onDisable() {
        for (Player player : getServer().getOnlinePlayers()) {
            healthSystem.resetHealth(player);
            System.out.println("Successfully reset health for " + player.getName());
        }
        System.out.println("Hope to see you again!");
    }

    public DebugSystem getDebugSystem() {
        return debugSystem;
    }

    public HealthSystem getHealthSystem() {
        return healthSystem;
    }

    public ConfigSystem getConfigSystem() {
        return configSystem;
    }
}