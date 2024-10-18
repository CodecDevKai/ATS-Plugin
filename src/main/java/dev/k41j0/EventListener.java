package dev.k41j0;

import dev.k41j0.systems.ConfigSystem;
import dev.k41j0.systems.HealthSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    private final ApesTogetherStrong plugin;

    public EventListener(ApesTogetherStrong plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.getHealthSystem().resetHealth(player);

        plugin.getDebugSystem().sendDebugMessage(player.getName() + " has quit, resetting health.");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ConfigSystem configSystem = plugin.getConfigSystem();
        HealthSystem healthSystem = plugin.getHealthSystem();

        if (!configSystem.isPluginEnabled()) {
            healthSystem.resetHealth(player);
            return;
        }

        int playerCount = (int) player.getNearbyEntities(configSystem.getRangeX(), configSystem.getRangeY(), configSystem.getRangeX())
                .stream()
                .filter(e -> e instanceof Player)
                .count();

        plugin.getDebugSystem().sendDebugMessage(player.getName() + " moved; nearby players: " + playerCount);

        if (playerCount <= 0) {
            healthSystem.setMaxHealth(player, configSystem.getBaseHealth());
        } else {
            double newHealth = calculateNewHealth(player, configSystem, playerCount);
            healthSystem.setMaxHealth(player, newHealth);
        }
    }

    private double calculateNewHealth(Player player, ConfigSystem configSystem, int playerCount) {
        double baseHealth = configSystem.getBaseHealth();
        double addedHealth = configSystem.getAddedHealth();
        double newHealth = baseHealth + playerCount * addedHealth;

        if (configSystem.isToggleMaxHealth()) {
            return Math.min(newHealth, configSystem.getMaxHealth());
        } else {
            return newHealth;
        }
    }
}
