package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManagerV3;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetDay implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        final RolePlayManagerV3 plugin = RolePlayManagerV3.getPlugin();

        Integer day = plugin.getConfig().getInt("current-day");


        if (sender instanceof Player){
            Player pl = (Player) sender;
            pl.sendMessage(ChatColor.of("#47be71") + String.valueOf(day));
        } else {System.out.println(day);}

        return true;
    }
}

