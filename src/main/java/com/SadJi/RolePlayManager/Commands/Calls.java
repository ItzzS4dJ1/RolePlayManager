package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Calls implements CommandExecutor {

    private final RolePlayManager plugin = RolePlayManager.getPlugin();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {
            if (args.length == 1){
              Player user = (Player) sender;
              Player abonent = Bukkit.getPlayer(args[0]);

              TextComponent answer = new TextComponent("Ответить");
              answer.setColor(ChatColor.of("#a3ff37"));
                assert abonent != null;
                answer.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + user.getName() + " "));
              answer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                      new ComponentBuilder("Нажмите, чтобы ответить!").color(ChatColor.of("#60ff38")).create()));
              TextComponent decline = new TextComponent("Отклонить ");
              decline.setColor(ChatColor.of("#cf2525"));
              decline.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + user.getName() + " " + "отклонил ваш вызов"));
              decline.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                      new ComponentBuilder("Нажмите, чтобы отклонить!").color(ChatColor.of("#af1717")).create()));

              if (plugin.getConfig().getBoolean("calls-enabled")){
                  abonent.sendMessage(ChatColor.of("#6db31b") + "Вам звонит"+" " + user.getName());
                  abonent.spigot().sendMessage(decline, answer);
                  user.sendMessage(ChatColor.of("#bf5281") + "Вы позвонили"+" " + abonent.getDisplayName());
              } else {user.sendMessage("Нет связи!");}

            } else {sender.sendMessage("Слишком много/мало аргументов");}
        }
        else{
            System.out.println("Only for players.");
            return false;}

        return true;
    }
}
