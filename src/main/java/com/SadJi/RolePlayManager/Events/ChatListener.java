package com.SadJi.RolePlayManager.Events;

import com.SadJi.RolePlayManager.RolePlayManager;
import com.SadJi.RolePlayManager.Utility.Localization;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.persistence.PersistentDataType;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ChatListener implements Listener {

    @EventHandler
    public void RP_Check(AsyncPlayerChatEvent event) {
        FileConfiguration config = RolePlayManager.getPlugin().getConfig(); //get the  config into specific file
        String message = event.getMessage(); // get the msg
        Player player = event.getPlayer(); // get sender
        String plName = player.getDisplayName(); // get sender's name
        Location playerLocation = event.getPlayer().getLocation(); // get sender's location to calculate distance
        Player p = event.getPlayer();

        //Distances
        int NRPDistance = config.getInt("NoneRPDistance");
        int ActionDistance = config.getInt("ActionDistance");
        int EnvironmentDistance = config.getInt("EnvironmentDistance");
        int ShoutDistance = config.getInt("ShoutDistance");
        int WhisperDistance = config.getInt("WhisperDistance");
        int Distance = config.getInt("ChatDistance");

        //Translations
        String WhisperMSG = Localization.getLocalized("Whisper");
        String ShoutMSG = Localization.getLocalized("Shout");

        //Colors
        String hexNRP = config.getString("NRP_color");
        String hexAction = config.getString("Action_color");
        String hexShout = config.getString("Shout_color");
        String hexWhisper = config.getString("Whisper_color");
        String hexChat = config.getString("Chat_color");
        String hexEnvironment = config.getString("Environment_color");
        String hexGlobal = config.getString("Global_color");

        //Custom colors
        //hex of NRP
        if (!p.getPersistentDataContainer().has(new NamespacedKey(RolePlayManager.getPlugin(), "hexNRP"), PersistentDataType.STRING)) {
            p.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexNRP"), PersistentDataType.STRING, hexNRP);
        } else {
            hexNRP = p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "hexNRP"), PersistentDataType.STRING);
        }
        //hex of Action
        if (!p.getPersistentDataContainer().has(new NamespacedKey(RolePlayManager.getPlugin(), "hexAction"), PersistentDataType.STRING)) {
            p.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexAction"), PersistentDataType.STRING, hexAction);
        } else {
            hexAction = p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "hexAction"), PersistentDataType.STRING);
        }
        //hex of Shout
        if (!p.getPersistentDataContainer().has(new NamespacedKey(RolePlayManager.getPlugin(), "hexShout"), PersistentDataType.STRING)) {
            p.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexShout"), PersistentDataType.STRING, hexShout);
        } else {
            hexShout = p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "hexShout"), PersistentDataType.STRING);
        }
        //hex of Whisper
        if (!p.getPersistentDataContainer().has(new NamespacedKey(RolePlayManager.getPlugin(), "hexWhisper"), PersistentDataType.STRING)) {
            p.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexWhisper"), PersistentDataType.STRING, hexWhisper);
        } else {
            hexWhisper = p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "hexWhisper"), PersistentDataType.STRING);
        }
        //hex of Chat
        if (!p.getPersistentDataContainer().has(new NamespacedKey(RolePlayManager.getPlugin(), "hexChat"), PersistentDataType.STRING)) {
            p.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexChat"), PersistentDataType.STRING, hexChat);
        } else {
            hexChat = p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "hexChat"), PersistentDataType.STRING);
        }
        //hex of Environment
        if (!p.getPersistentDataContainer().has(new NamespacedKey(RolePlayManager.getPlugin(), "hexEnv"), PersistentDataType.STRING)) {
            p.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexEnv"), PersistentDataType.STRING, hexEnvironment);
        } else {
            hexEnvironment = p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "hexEnv"), PersistentDataType.STRING);
        }
        //hex of Global
        if (!p.getPersistentDataContainer().has(new NamespacedKey(RolePlayManager.getPlugin(), "hexGlobal"), PersistentDataType.STRING)) {
            p.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "hexGlobal"), PersistentDataType.STRING, hexGlobal);
        } else {
            hexGlobal = p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "hexGlobal"), PersistentDataType.STRING);
        }



        if (message.startsWith("%")) {
            String[] R_Message = message.split("%", 2);
            for (Player pl : event.getRecipients()) { // start cycle
                if (pl.getLocation().distance(playerLocation) <= NRPDistance) { //calculate distance between sender and recipients
                    pl.sendMessage(ChatColor.of(Objects.requireNonNull(hexNRP)) + "NRP: " + plName + ": " + R_Message[1]); //send message to those who are nearby
                    event.setCancelled(true); // cancel the message event. Needed in order to send the previous message and make it visible in specific distance
                }
            }

        } else if (message.startsWith("!!")) {
            String[] R_Message = message.split("!!", 2);
            Bukkit.broadcastMessage(ChatColor.of(Objects.requireNonNull(hexGlobal)) + "<"+plName + "> "+ R_Message[1]); //global msg
            event.setCancelled(true);

        } else if (message.startsWith("--")) {
            String[] R_Message = message.split("--", 2);
            for (Player pl : event.getRecipients()) {
                if (pl.getLocation().distance(playerLocation) <= ActionDistance) {
                    pl.sendMessage(ChatColor.of(Objects.requireNonNull(hexAction)) + "*" + plName + " " + R_Message[1] + "*");
                    event.setCancelled(true);

                } else {
                    event.setCancelled(true);
                }
            }
        } else if (message.startsWith("-")) {
            String[] R_Message = message.split("-", 2);
            for (Player pl : event.getRecipients()) {
                if (pl.getLocation().distance(playerLocation) <= EnvironmentDistance) {
                    pl.sendMessage(ChatColor.of(Objects.requireNonNull(hexEnvironment)) + "**" + R_Message[1] + "**" + " " + "(" + plName + ")" );
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            }
        } else if (message.startsWith("!")) {
            String[] R_Message = message.split("!", 2);
            for (Player pl : event.getRecipients()) {
                if (pl.getLocation().distance(playerLocation) <= ShoutDistance) {
                    pl.sendMessage(ChatColor.of(Objects.requireNonNull(hexShout)) + "[" + ShoutMSG + "]" + " " + plName + ": " + R_Message[1]);
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            }
        } else if (message.startsWith(",")) {
            String[] R_Message = message.split(",", 2);
            for (Player pl : event.getRecipients()) {
                if (pl.getLocation().distance(playerLocation) <= WhisperDistance) {
                    pl.sendMessage(ChatColor.of(Objects.requireNonNull(hexWhisper)) + "[" + WhisperMSG + "]" + " " + plName + ": " + R_Message[1]);
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            }
        } else {
            for (Player pl : event.getRecipients()) {
                if (pl.getLocation().distance(playerLocation) <= Distance) {
                    pl.sendMessage(ChatColor.of(Objects.requireNonNull(hexChat)) + "<" + plName + ">" + ChatColor.of("#b8cbcf") + " " + message);
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }
}