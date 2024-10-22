package dev.k41j0.systems;

import dev.k41j0.ApesTogetherStrong;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class HealthSystem {
    private static ApesTogetherStrong plugin;

    public HealthSystem(ApesTogetherStrong plugin) {
        this.plugin = plugin;
    }

    public void setMaxHealth(Player player, double maxHealth) {
        if(player == null) return;
        if(player.getHealth() > maxHealth) {
            player.setHealth(maxHealth);
        }
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        plugin.getDebugSystem().sendDebugMessage("Successfully set max health for player " + player.getName() + " to " + String.valueOf(maxHealth));
    }

    public double getMaxHealth(Player player) {
        if(player == null) return 0;
        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    }

    public void resetHealth(Player player) {
        if(player == null) return;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20D);
        plugin.getDebugSystem().sendDebugMessage("Successfully reset max health for player " + player.getName() + " to " + String.valueOf(20D));
    }
}
