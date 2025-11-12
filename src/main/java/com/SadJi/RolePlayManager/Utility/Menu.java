package com.SadJi.RolePlayManager.Utility;

import com.SadJi.RolePlayManager.RolePlayManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Menu implements CommandExecutor {

    FileConfiguration localize = Localization.getFile();

    String plErr = localize.getString("OnlyForPlayers");
    String doCall = localize.getString("DoCall");
    String phoneName = localize.getString("PhoneName");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(plErr);
            return true;
        }

        ItemStack getButtonCall = new ItemStack(Material.FLOWER_POT);
        ItemMeta buttonCallMeta = getButtonCall.getItemMeta();
        buttonCallMeta.setDisplayName(ChatColor.RED + doCall);
        getButtonCall.setItemMeta(buttonCallMeta);
        Player p =  (Player) commandSender;
        Inventory inventory = Bukkit.createInventory(p, 9*6, "Коммуникатор-1");

        inventory.setItem(0, getButtonCall);
        inventory.setItem(4, getButtonCall);
        inventory.setItem(8, getButtonCall);
        p.openInventory(inventory);
        p.setMetadata("OpenedMenu", new FixedMetadataValue(RolePlayManager.getPlugin(), inventory));
        return true;
    }
}
