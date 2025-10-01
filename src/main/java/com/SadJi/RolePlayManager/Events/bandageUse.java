package com.SadJi.RolePlayManager.Events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.awt.*;

public class bandageUse implements Listener {
    @EventHandler
    public void bandageUsed(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getActiveItem();

        if (itemInHand != null && itemInHand.getType() == Material.PAPER) {
            ItemMeta itemMeta = itemInHand.getItemMeta();

            if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase("бинт")) {
                Entity clickedEntity = event.getRightClicked();

                if (clickedEntity instanceof Player) {
                    Player clickedPlayer = (Player) clickedEntity;
                    if (!(clickedPlayer.hasPotionEffect(PotionEffectType.REGENERATION))) {
                        clickedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 2));
                    }
                    else{
                        clickedPlayer.removePotionEffect(PotionEffectType.REGENERATION);
                        clickedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 2));
                    }
                    clickedPlayer.sendMessage("Вас перевязал " + ChatColor.GREEN + player.getDisplayName() + ".");
                    player.sendMessage("Вы перевязали " + ChatColor.of("") + clickedPlayer.getDisplayName());
                    itemInHand.setAmount(itemInHand.getAmount() - 1);
                }
            }
        }
    }
    @EventHandler
    public void OnAirBandage(PlayerInteractEvent event){
        Action action = event.getAction();
        Player p = event.getPlayer();
        ItemStack itemInHand = event.getItem();
        if (action == Action.RIGHT_CLICK_BLOCK){
            if (itemInHand != null && itemInHand.getType() == Material.PAPER) {
                ItemMeta itemMeta = itemInHand.getItemMeta();

                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase("бинт")) {
                    if (!(p.hasPotionEffect(PotionEffectType.REGENERATION))) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 2));
                    }
                    else{
                        p.removePotionEffect(PotionEffectType.REGENERATION);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 2));
                    }
                    event.getPlayer().sendMessage(ChatColor.of(Color.GREEN) + "Вы перевязались.");
                    itemInHand.setAmount(itemInHand.getAmount() - 1);
                }
            }

        }
    }
}

