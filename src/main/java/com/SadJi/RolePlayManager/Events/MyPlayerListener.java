package com.SadJi.RolePlayManager.Events;

import com.SadJi.RolePlayManager.RolePlayManagerV3;
import com.SadJi.RolePlayManager.Tasks.DelayedTask;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.Random;

import static org.bukkit.Material.*;

public final class MyPlayerListener implements Listener {
    Random random = new Random();
    private final RolePlayManagerV3 plugin = RolePlayManagerV3.getPlugin();
    @EventHandler
    public void PlayerHeight(PlayerMoveEvent event) {

        Player pl = event.getPlayer();
        PersistentDataContainer data = pl.getPersistentDataContainer();
        Location location = pl.getLocation();

        if ((pl.getPersistentDataContainer().get(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Job"), PersistentDataType.STRING) != "Physical")) {
            if (location.getY() <= 45 && location.getY() >= 25) {

                pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));

                pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1));

                location = pl.getLocation();

            } else if (location.getY() <= 24) {

                pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));

                pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 2));

                location = pl.getLocation();

            } else if (pl.getLocation().getY() >= 46) {

                pl.removePotionEffect(PotionEffectType.SLOW);

                pl.removePotionEffect(PotionEffectType.SLOW_DIGGING);

                location = pl.getLocation();

            }

        } else if (pl.getLocation().getY() < (-15)) {
            pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 0));
            pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));

        } else if (pl.getLocation().getY() >= -15) {

            pl.removePotionEffect(PotionEffectType.SLOW);

            pl.removePotionEffect(PotionEffectType.SLOW_DIGGING);

            location = pl.getLocation();
        }


        if (Objects.requireNonNull(plugin.getConfig().getString("current-season")).equalsIgnoreCase("summer")) {
            if (pl.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND || pl.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == RED_SAND) {
                int min = 1;
                int max = 201;
                int i = random.nextInt(max - min) + min;
                if (i == 99) {
                    pl.damage(0.5);
                    pl.sendMessage(ChatColor.of("#a62b2b") + "Вы обожглись о песок");
                }
            }
            if ((pl.getInventory().getHelmet() != null && pl.getInventory().getChestplate() != null && pl.getInventory().getLeggings() != null && pl.getInventory().getBoots() != null)) {
                pl.damage(0.5);
            }
            pl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3, 0));


        }
        if (Objects.requireNonNull(plugin.getConfig().getString("current-season")).equalsIgnoreCase("spring")) {
            if (pl.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == FARMLAND
                    || pl.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == DIRT
                    || pl.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == ROOTED_DIRT) {
                pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3, 1));
            }
            pl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3, 0));
        }
        if (Objects.requireNonNull(plugin.getConfig().getString("current-season")).equalsIgnoreCase("autumn")) {
            Location loc = pl.getLocation();
            if (pl.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == FARMLAND
                    || pl.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == DIRT
                    || pl.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == ROOTED_DIRT) {
                pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3, 1));
            }
            if(pl.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == WATER && pl.getLocation().getBlock().getRelative(BlockFace.UP, 2).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 3).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 4).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 5).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 6).getType() == AIR){
                pl.setFreezeTicks(150);
            }
            if(pl.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == WATER && pl.getLocation().getBlock().getRelative(BlockFace.SELF, 1).getType() == WATER){
                pl.setFreezeTicks(200);
            }
        }
        if (Objects.requireNonNull(plugin.getConfig().getString("current-season")).equalsIgnoreCase("winter")) {
            Location loc = pl.getLocation();
            World world = Bukkit.getWorlds().get(0);
            if (pl.getLocation().getBlock().getRelative(BlockFace.UP, 1).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 2).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 3).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 4).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 5).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 6).getType() == AIR){
                if (!(pl.getInventory().getHelmet() != null && pl.getInventory().getHelmet().getType() == LEATHER_HELMET && pl.getInventory().getChestplate() != null && pl.getInventory().getChestplate().getType() == LEATHER_CHESTPLATE && pl.getInventory().getLeggings() != null && pl.getInventory().getLeggings().getType() == LEATHER_LEGGINGS && pl.getInventory().getBoots() != null && pl.getInventory().getBoots().getType() == LEATHER_BOOTS)) {
                    pl.setFreezeTicks(150);
                }
            }
            if(pl.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == WATER && pl.getLocation().getBlock().getRelative(BlockFace.UP, 2).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 3).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 4).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 5).getType() == AIR && pl.getLocation().getBlock().getRelative(BlockFace.UP, 6).getType() == AIR){
                pl.setFreezeTicks(200);
            }

            if(pl.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == WATER && pl.getLocation().getBlock().getRelative(BlockFace.SELF, 1).getType() == WATER){
                pl.setFreezeTicks(250);
            }
        }
    }

    @EventHandler
    public void onJoined(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String InGameName = p.getDisplayName();
        if (!p.getPersistentDataContainer().has(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Name"), PersistentDataType.STRING)) {
            p.getPersistentDataContainer().set(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Name"), PersistentDataType.STRING, InGameName);
        } else {
            p.setCustomName(p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Name"), PersistentDataType.STRING));
            p.setDisplayName(p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Name"), PersistentDataType.STRING));
            p.setPlayerListName(p.getPersistentDataContainer().get(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Name"), PersistentDataType.STRING) + " " + "(" + p.getName() + ")");
            p.setCustomNameVisible(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (player.hasMetadata("OpenedMenu")) {

            if (e.getSlot() == 0) {
                player.sendMessage("KILL YOURSELF");
                player.performCommand("kill");
            } else if (e.getSlot() == 4) {
                player.sendMessage("You shall die");
            } else if (e.getSlot() == 8){
                    player.sendMessage("cleaning...");
                    player.performCommand("clear");
            }

            e.setCancelled(true);

        }
        if (player.hasMetadata("OpenedJobs")) {

            if (e.getSlot() == 1) {
                PersistentDataContainer data = player.getPersistentDataContainer();
                data.set(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Job"), PersistentDataType.STRING, "Physical");
            }
            if (e.getSlot() == 4) {
                PersistentDataContainer data = player.getPersistentDataContainer();
                data.set(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Job"), PersistentDataType.STRING, "Science");
            }
            if (e.getSlot() == 7) {
                PersistentDataContainer data = player.getPersistentDataContainer();
                data.set(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Job"), PersistentDataType.STRING, "Create");
            }

            e.setCancelled(true);

        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.hasMetadata("OpenedMenu")){
            p.removeMetadata("OpenedMenu", RolePlayManagerV3.getPlugin());
        }
        if (p.hasMetadata("OpenedJobs")){
            p.removeMetadata("OpenedJobs", RolePlayManagerV3.getPlugin());
        }
    }

}
