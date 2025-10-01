package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

/* TODO: finish the checking of the menu in player listener */

public class Experiments implements CommandExecutor {

    public final HashMap<UUID, Long> cooldown;

    public Experiments() {
        this.cooldown = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;


        // check sender

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage("Only for players.");
            return true;
            // return if not player
        }

        // import randint
        if (this.cooldown.containsKey(player.getUniqueId())){
            long timeElapsed = System.currentTimeMillis() - this.cooldown.get(player.getUniqueId());


            if (timeElapsed < 1080000){
                long timeLeft = 1080000 - timeElapsed;
                int hoursLeft = (int) timeLeft/100/60/60;
                int minutesLeft = (int) timeLeft/100/60 - hoursLeft*60;
                int secondsLeft = (int) timeLeft/100 - hoursLeft*60*60;
                player.sendMessage(ChatColor.DARK_RED + "Подождите"+" " + hoursLeft + " "+"часов " + minutesLeft + " "+"минут" + " "+"перед использованием.");
                return true;
            }

        }
        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
        Random random = new Random();

        ItemStack buttonWrong = new ItemStack(Material.BREWING_STAND);
        ItemMeta buttonWrongMeta = buttonWrong.getItemMeta();

        buttonWrongMeta.addEnchant(Enchantment.LURE, 1, true);

        buttonWrongMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        buttonWrongMeta.setDisplayName(ChatColor.RED + "Эксперимент");

        buttonWrong.setItemMeta(buttonWrongMeta);

        // wrong button above
        // right button below

        ItemStack buttonRight = new ItemStack(Material.BREWING_STAND);
        ItemMeta buttonRightMeta = buttonRight.getItemMeta();

        buttonRightMeta.addEnchant(Enchantment.FORTUNE, 1, true);

        buttonRightMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        buttonRightMeta.setDisplayName(ChatColor.RED + "Эксперимент 1");

        buttonRight.setItemMeta(buttonRightMeta);
        Player p =  (Player) commandSender;
        Inventory inventory = Bukkit.createInventory(p, 9*5, "Эксперимент");

        // generate positions

        /*
            12, 21, 30 -> min
            14, 23, 32 -> max
        */
        int rightSlotTop = random.nextInt((14 - 12)+1) + 12;

        int rightSlotMid = random.nextInt((23 - 21)+1) + 21;

        int rightSlotBottom = random.nextInt((32 - 30) + 1)+ 30;


        // set items

        IntStream.range(0, (9 * 5)).forEach(i -> {
            if (!(i < 12 || i > 14)) {
                if (i == rightSlotTop) {
                    inventory.setItem(i, buttonRight);
                } else {
                    inventory.setItem(i, buttonWrong);
                }
            }
            if (!(i < 21 || i > 23)) {
                if (i == rightSlotMid) {
                    inventory.setItem(i, buttonRight);
                } else {
                    inventory.setItem(i, buttonWrong);
                }
            }
            if (!(i < 30 || i > 32)) {
                if (i == rightSlotBottom) {
                    inventory.setItem(i, buttonRight);
                } else {
                    inventory.setItem(i, buttonWrong);
                }
            }
            if (i < 12) {
                inventory.setItem(i, new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
            }
            if (i > 14 && i < 21) {
                inventory.setItem(i, new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
            }
            if (i > 23 && i < 30) {
                inventory.setItem(i, new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
            }
            if (i > 32) {
                inventory.setItem(i, new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
            }
        });
        
        p.openInventory(inventory);
        p.setMetadata("OpenedExp", new FixedMetadataValue(RolePlayManager.getPlugin(), inventory));
        return true;
    }
}
