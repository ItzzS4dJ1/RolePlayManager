package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManager;
import com.SadJi.RolePlayManager.Utility.Localization;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class JobCommand implements CommandExecutor {

    FileConfiguration localize = Localization.getFile();

    String onlyForPlayers = localize.getString("OnlyForPlayers");
    String physicalPower = localize.getString("PhysicalPower");
    String physDesc1 = localize.getString("PhysicalPowerDesc1");
    String physDesc2 = localize.getString("PhysicalPowerDesc2");
    String brainpower = localize.getString("Brainpower");
    String brainpowerDesc1 = localize.getString("BrainpowerDesc1");
    String brainpowerDesc2 = localize.getString("BrainpowerDesc2");
    String creativityPower = localize.getString("CreativityPower");
    String creativityDesc1 = localize.getString("CreativityPowerDesc1");
    String creativityDesc2 = localize.getString("CreativityPowerDesc2");
    String chooseYourSkill = localize.getString("ChooseYourSkill");
    String waitMsg = localize.getString("Wait");
    String hMsg = localize.getString("Hours");
    String mMsg = localize.getString("Minutes");
    String beforeUsing = localize.getString("BeforeUsing");

    public final HashMap<UUID, Long> cooldown;

    public JobCommand() {
        this.cooldown = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(onlyForPlayers);
        }
        Player p = (Player) commandSender;

        // check cooldown
        if (this.cooldown.containsKey(p.getUniqueId())){
            long timeElapsed = System.currentTimeMillis() - this.cooldown.get(p.getUniqueId());


            if (timeElapsed < 25920000){ // 3h
                long timeLeft = 25920000 - timeElapsed;
                int hoursLeft = (int) timeLeft/100/60/60;
                int minutesLeft = (int) timeLeft/100/60 - hoursLeft*60;
                p.sendMessage(net.md_5.bungee.api.ChatColor.DARK_RED + waitMsg+" " + hoursLeft + " "+hMsg+" " + minutesLeft + " "+mMsg + " "+beforeUsing);
                return true;
            }

        }
        this.cooldown.put(p.getUniqueId(), System.currentTimeMillis());

        ItemStack physicalButton = new ItemStack(Material.IRON_SWORD);
        ItemMeta physMeta = physicalButton.getItemMeta();
        physMeta.setDisplayName(ChatColor.GREEN + physicalPower);
        List<String> lorePhys = new ArrayList<>();
        lorePhys.add(ChatColor.WHITE + physDesc1);
        lorePhys.add(ChatColor.GRAY + physDesc2);
        physMeta.setLore(lorePhys);
        physicalButton.setItemMeta(physMeta);

        ItemStack mindButton = new ItemStack(Material.BREWING_STAND);
        ItemMeta mindMeta = mindButton.getItemMeta();
        mindMeta.setDisplayName(ChatColor.AQUA + brainpower);
        List<String> mindLore = new ArrayList<>();
        mindLore.add(ChatColor.WHITE + brainpowerDesc1);
        mindLore.add(ChatColor.GRAY + brainpowerDesc2);
        mindMeta.setLore(mindLore);
        mindButton.setItemMeta(mindMeta);

        ItemStack creativityButton = new ItemStack(Material.GLOBE_BANNER_PATTERN);
        ItemMeta createMeta = creativityButton.getItemMeta();
        createMeta.setDisplayName(ChatColor.YELLOW + creativityPower);
        List<String> createLore = new ArrayList<>();
        createLore.add(ChatColor.WHITE + creativityDesc1);
        createLore.add(ChatColor.GRAY + creativityDesc2);
        createMeta.setLore(createLore);
        creativityButton.setItemMeta(createMeta);

        Inventory inventory = Bukkit.createInventory(p, 9, chooseYourSkill);

        inventory.setItem(1, physicalButton);
        inventory.setItem(4, mindButton);
        inventory.setItem(7, creativityButton);

        p.openInventory(inventory);
        p.setMetadata("OpenedJobs", new FixedMetadataValue(RolePlayManager.getPlugin(), inventory));
        return true;
    }
}
