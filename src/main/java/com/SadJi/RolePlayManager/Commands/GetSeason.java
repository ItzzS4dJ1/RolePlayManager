package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class GetSeason implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        final RolePlayManager plugin = RolePlayManager.getPlugin();

        String season = plugin.getConfig().getString("current-season");


        if (sender instanceof Player){
            Player pl = (Player) sender;
            pl.sendMessage(ChatColor.of("#47be71") + season);
        } else {System.out.println(season);}

        return true;
    }
}
