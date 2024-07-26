package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManagerV3;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class NameChanger implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        String[] Name = strings;
        if(command.getName().equalsIgnoreCase("name")) {
            if(commandSender instanceof Player){

                Player p = (Player) commandSender;
                if (Name.length == 2){
                    p.setDisplayName(Name[0] + " " + Name[1]);
                    p.setCustomName(Name[0] + " " + Name[1]);
                    p.setPlayerListName(Name[0] + " " + Name[1] + " " + "(" + commandSender.getName() + ")");
                    p.setCustomNameVisible(true);

                    p.sendMessage("Ваше имя успешно изменено!");

                    PersistentDataContainer data = p.getPersistentDataContainer();
                    data.set(new NamespacedKey(RolePlayManagerV3.getPlugin(), "Name"), PersistentDataType.STRING, Name[0] + " " + Name[1]);


                }else {
                    p.sendMessage("Вы ввели слишком много/мало аргументов или неверно ввели команду. Правильное использование:");
                    p.sendMessage("/name Иван Иванов");

                }

            }
        }



        return true;
    }
}
