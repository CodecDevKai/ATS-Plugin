package dev.k41j0.commands;

import org.bukkit.entity.Player;

public abstract class SubCommand  {
    public abstract boolean playerOnly();
    public abstract String permissionsRequired();
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract void perform(Player player, String args[]);
}
