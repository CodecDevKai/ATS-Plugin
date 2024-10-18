package dev.k41j0.commands;

import dev.k41j0.ApesTogetherStrong;
import dev.k41j0.commands.subcommands.ReloadCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(ApesTogetherStrong plugin) {
        subCommands.add(new ReloadCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SubCommand cmd = null;

        if(args.length > 0) {
            for(SubCommand subCmd : subCommands) {
                if(args[0].equalsIgnoreCase(subCmd.getName())) {
                    cmd = subCmd;
                    break;
                }
            }
        }

        if (cmd != null) {
            executeCommand(sender, cmd, args);
        } else {
            showCommandList(sender);
        }
        return true;
    }

    private void executeCommand(CommandSender sender, SubCommand command, String[] args) {
        if (command.playerOnly() && !(sender instanceof Player)) {
            System.out.println("This command has to be executed by a player!");
            return;
        }

        Player player = (sender instanceof Player) ? (Player) sender : null;
        if (player == null || player.hasPermission(command.permissionsRequired())) {
            command.perform(player, args);
        }
    }

    private void showCommandList(CommandSender sender) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage("----------------------------------------");
            for (SubCommand subCmd : subCommands) {
                p.sendMessage(subCmd.getSyntax() + " - " + subCmd.getDescription());
            }
            p.sendMessage("----------------------------------------");
        } else {
            System.out.println("----------------------------------------");
            for (SubCommand subCmd : subCommands) {
                System.out.println(subCmd.getSyntax() + " - " + subCmd.getDescription());
            }
            System.out.println("----------------------------------------");
        }
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
