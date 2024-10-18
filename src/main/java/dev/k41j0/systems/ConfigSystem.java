package dev.k41j0.systems;

import dev.k41j0.ApesTogetherStrong;
import org.bukkit.configuration.ConfigurationSection;

public class ConfigSystem {
    private static ApesTogetherStrong plugin;

    public ConfigSystem(ApesTogetherStrong plugin) {
        this.plugin = plugin;
    }

    private boolean pluginEnabled = true;
    private boolean debuggingEnabled = false;
    private boolean toggleMaxHealth = false;

    private double baseHealth = 20.0D;
    private double addedHealth = 2.0D;
    private double maxHealth = 40.0D;

    private ConfigurationSection range;
    private int rangeX = 16;
    private int rangeY = 16;
    private int rangeZ = 16;

    public void load() {
        pluginEnabled = plugin.getConfig().getBoolean("plugin-enabled", true);
        debuggingEnabled = plugin.getConfig().getBoolean("debugging-enabled", false);
        toggleMaxHealth = plugin.getConfig().getBoolean("toggle-max-health", false);
        baseHealth = plugin.getConfig().getDouble("base-health", 20.0D);
        addedHealth = plugin.getConfig().getDouble("added-health", 2.0D);
        maxHealth = plugin.getConfig().getDouble("max-health", 40.0D);

        range = plugin.getConfig().getConfigurationSection("range");

        rangeX = range != null ? range.getInt("x", 16) : 16;
        rangeY = range != null ? range.getInt("y", 16) : 16;
        rangeZ = range != null ? range.getInt("z", 16) : 16;
    }

    public boolean isPluginEnabled() {
        return pluginEnabled;
    }

    public boolean isDebuggingEnabled() {
        return debuggingEnabled;
    }

    public boolean isToggleMaxHealth() {
        return toggleMaxHealth;
    }

    public double getBaseHealth() {
        return baseHealth;
    }

    public double getAddedHealth() {
        return addedHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public int getRangeX() {
        return rangeX;
    }

    public int getRangeY() {
        return rangeY;
    }

    public int getRangeZ() {
        return rangeZ;
    }


}
