package dev.k41j0;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventListener implements Listener {
    private final ApesTogetherStrong plugin;

    public EventListener(ApesTogetherStrong plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        boolean pluginEnabled = plugin.getConfig().getBoolean("plugin-enabled");
        int baseHealth = plugin.getConfig().getInt("base-health");
        int addedHealth = plugin.getConfig().getInt("added-health");
        boolean toggleMaxHealth = plugin.getConfig().getBoolean("toggle-max-health");
        int maxHealth = plugin.getConfig().getInt("max-health");

        ConfigurationSection range = plugin.getConfig().getConfigurationSection("range");
        int x = range.getInt("x");
        int y = range.getInt("y");
        int z = range.getInt("z");


        if(pluginEnabled) {
            int playerCount = player.getNearbyEntities(x, y, z).stream().filter(e -> e instanceof Player).mapToInt(e -> 1).reduce(0, (a, b) -> a + b);

            if(playerCount <= 0) {
                player.setMaxHealth(baseHealth);
            } else {
                if(toggleMaxHealth) {
                    player.setMaxHealth(Math.min(baseHealth + playerCount * addedHealth, maxHealth));
                }
                player.setMaxHealth(baseHealth + playerCount * addedHealth);
            }
        } else {
            player.setMaxHealth(baseHealth);
        }

    }
}
