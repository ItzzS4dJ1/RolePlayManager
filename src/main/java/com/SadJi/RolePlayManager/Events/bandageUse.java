package com.SadJi.RolePlayManager.Events;

import com.SadJi.RolePlayManager.Utility.Localization;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
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

    FileConfiguration localize = Localization.getFile();

    String youHealedSelf = localize.getString("YouHealedSelf");
    String youHealedSomeone = localize.getString("YouHealedSomeone");
    String youGotHealed = localize.getString("YouGotHealed");
    String bandageItemName = localize.getString("BandageItemName");


    @EventHandler
    public void bandageUsed(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getActiveItem();

        if (itemInHand != null && itemInHand.getType() == Material.PAPER) {
            ItemMeta itemMeta = itemInHand.getItemMeta();

            if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase(bandageItemName)) {
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
                    clickedPlayer.sendMessage(youGotHealed+" " + ChatColor.GREEN + player.getDisplayName() + ".");
                    player.sendMessage(ChatColor.GREEN + youHealedSomeone +" " + ChatColor.of("#5df542") + clickedPlayer.getDisplayName());
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

                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase(bandageItemName)) {
                    if (!(p.hasPotionEffect(PotionEffectType.REGENERATION))) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 2));
                    }
                    else{
                        p.removePotionEffect(PotionEffectType.REGENERATION);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 2));
                    }
                    event.getPlayer().sendMessage(ChatColor.of(Color.GREEN) + youHealedSelf);
                    itemInHand.setAmount(itemInHand.getAmount() - 1);
                }
            }

        }
    }
}

