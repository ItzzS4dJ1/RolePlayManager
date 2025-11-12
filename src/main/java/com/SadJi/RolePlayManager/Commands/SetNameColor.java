package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetNameColor implements TabExecutor {
    protected final RolePlayManager plugin = RolePlayManager.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player && !sender.hasPermission("RolePlayManager.admin")) return false;

        if (Bukkit.getPlayer(args[0]) instanceof Player){

            Player p = Bukkit.getPlayer(args[0]);

            String newName = ChatColor.of(args[1]) + p.getPersistentDataContainer().get(new NamespacedKey(plugin, "Name"), PersistentDataType.STRING);
            p.getPersistentDataContainer().set(new NamespacedKey(plugin, "ColorName"), PersistentDataType.STRING, newName);
            p.setDisplayName(p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "ColorName"), PersistentDataType.STRING));
            p.setPlayerListName(p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "ColorName"), PersistentDataType.STRING) + " " + "(" + p.getName() + ")");
            return true;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1){
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (int i = 0; i < players.length; i++){
                playerNames.add(players[i].getName());
            }

            return playerNames;

        }else if (args.length == 2){
            List<String> arguments = new ArrayList<>();
            arguments.add("#ffffff");
            return arguments;
        }
        return null;
    }
}
