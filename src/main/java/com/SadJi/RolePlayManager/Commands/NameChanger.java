package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManager;
import com.SadJi.RolePlayManager.Utility.Localization;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class NameChanger implements CommandExecutor {

    public final HashMap<UUID, Long> cooldown;

    FileConfiguration localize = Localization.getFile();
    String waitMsg = localize.getString("Wait");
    String hMsg = localize.getString("Hours");
    String mMsg = localize.getString("Minutes");
    String beforeUsing = localize.getString("BeforeUsing");
    String nameChangeSuccessful = localize.getString("NameChangeSuccessful");
    String argsErr = localize.getString("ArgsErr");
    String correctUsage = localize.getString("CorrectUsage");
    String nameUsageHelper = localize.getString("NameUsageHelper");

    public NameChanger() {
        this.cooldown = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String[] Name = strings;
        if(command.getName().equalsIgnoreCase("name")) {
            if(commandSender instanceof Player){

                Player p = (Player) commandSender;
                // check cooldown
                if (this.cooldown.containsKey(p.getUniqueId())){
                    long timeElapsed = System.currentTimeMillis() - this.cooldown.get(p.getUniqueId());


                    if (timeElapsed < 1080000){ // 3h
                        long timeLeft = 1080000 - timeElapsed;
                        int hoursLeft = (int) timeLeft/100/60/60;
                        int minutesLeft = (int) timeLeft/100/60 - hoursLeft*60;
                        p.sendMessage(ChatColor.DARK_RED + waitMsg+" " + hoursLeft + " "+hMsg+" " + minutesLeft + " "+mMsg + " "+beforeUsing);
                        return true;
                    }

                }
                this.cooldown.put(p.getUniqueId(), System.currentTimeMillis());


                if (Name.length == 2){
                    p.setDisplayName(Name[0] + " " + Name[1]);
                    p.setCustomName(Name[0] + " " + Name[1]);
                    p.setPlayerListName(Name[0] + " " + Name[1] + " " + "(" + commandSender.getName() + ")");
                    p.setCustomNameVisible(true);

                    p.sendMessage(nameChangeSuccessful);

                    PersistentDataContainer data = p.getPersistentDataContainer();
                    data.set(new NamespacedKey(RolePlayManager.getPlugin(), "Name"), PersistentDataType.STRING, Name[0] + " " + Name[1]);


                }else {
                    p.sendMessage(argsErr + ' ' + correctUsage);
                    p.sendMessage(nameUsageHelper);

                }

            }
        }



        return true;
    }
}
