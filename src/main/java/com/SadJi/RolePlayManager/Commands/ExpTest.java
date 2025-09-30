package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class ExpTest implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "Exp_progress"), PersistentDataType.INTEGER) == 5){
                player.giveExp(120);
                player.sendMessage(ChatColor.of("#24d240") + "Вы завершили исследование!");

                player.getPersistentDataContainer().set(new NamespacedKey(RolePlayManager.getPlugin(), "Exp_progress"), PersistentDataType.INTEGER, 0);
                player.playSound((Entity) player, Sound.ITEM_TOTEM_USE, 0.25F, 0F);
            }
            else {
                int pdcExp = player.getPersistentDataContainer().get(new NamespacedKey(RolePlayManager.getPlugin(), "Exp_progress"), PersistentDataType.INTEGER);
                pdcExp += 1;
                player.sendMessage("+1 level");
            }
            return true;
        }
        else {
            sender.sendMessage(ChatColor.RED + "No, you can't use this command");
            return true;
        }

    }
}
