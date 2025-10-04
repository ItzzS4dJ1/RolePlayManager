package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetSeason implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player && !sender.hasPermission("RolePlayManager.admin")) return false;
        if (args.length == 1){
            final RolePlayManager plugin = RolePlayManager.getPlugin();
            plugin.getConfig().set("current-season", args[0]);
        } else{
            sender.sendMessage("Больше одного аргумента");
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> arguments = new ArrayList<>();
        arguments.add("Summer");
        arguments.add("Autumn");
        arguments.add("Winter");
        arguments.add("Spring");
        return arguments;
    }
}
