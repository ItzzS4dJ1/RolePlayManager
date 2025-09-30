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

import java.util.ArrayList;
import java.util.List;

public class SetHex implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        if (strings.length != 3) return false;

        Player p = (Player) commandSender;
        Player tp = Bukkit.getPlayer(strings[0]);
        String hexType = strings[1];
        String hexColor = strings[2];

        switch (hexType){
            case "NRP":
                tp.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexNRP"), PersistentDataType.STRING, hexColor);
                break;
            case "Action":
                tp.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexAction"), PersistentDataType.STRING, hexColor);
                break;
            case "Shout":
                tp.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexShout"), PersistentDataType.STRING, hexColor);
                break;
            case "Whisper":
                tp.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexWhisper"), PersistentDataType.STRING, hexColor);
                break;
            case "Chat":
                tp.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexChat"), PersistentDataType.STRING, hexColor);
                break;
            case "Environment":
                tp.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexEnv"), PersistentDataType.STRING, hexColor);
                break;
            case "Global":
                tp.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexGlobal"), PersistentDataType.STRING, hexColor);
                break;
        }

        p.sendMessage("<RP_Chat> Changed color of " + hexType + " to " + ChatColor.of(hexColor) + "color.");
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
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
            arguments.add("NRP");
            arguments.add("Action");
            arguments.add("Shout");
            arguments.add("Whisper");
            arguments.add("Chat");
            arguments.add("Environment");
            arguments.add("Global");


            return arguments;
        }else if (args.length == 3){
            List<String> arguments = new ArrayList<>();
            arguments.add("#ffffff");
            return arguments;
        }
        return null;
    }
}