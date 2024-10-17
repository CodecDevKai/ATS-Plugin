package dev.k41j0.commands.subcommands;

import dev.k41j0.ApesTogetherStrong;
import dev.k41j0.commands.SubCommand;
import org.bukkit.entity.Player;


public class ReloadCommand extends SubCommand {
    private final ApesTogetherStrong plugin;

    public ReloadCommand(ApesTogetherStrong plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads ATS.";
    }

    @Override
    public String getSyntax() {
        return "/ats reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if(player.hasPermission("ats.reload")) {
            this.plugin.reloadConfig();
            player.sendMessage("Reloaded config!");
        } else {
            player.sendMessage(plugin.getCommand("ats").getPermissionMessage());
        }

    }
}
