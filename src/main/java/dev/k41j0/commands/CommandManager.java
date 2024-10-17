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
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length > 0){
                for(int i = 0; i < subCommands.size(); i++){
                    if(args[0].equalsIgnoreCase(subCommands.get(i).getName())){
                        getSubCommands().get(i).perform(p, args);
                    }
                }
            } else if(args.length == 0){
                p.sendMessage("----------------------------------------");
                for(int i = 0; i < subCommands.size(); i++){
                    p.sendMessage(subCommands.get(i).getSyntax() + " - " + subCommands.get(i).getDescription());
                }
                p.sendMessage("----------------------------------------");
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
