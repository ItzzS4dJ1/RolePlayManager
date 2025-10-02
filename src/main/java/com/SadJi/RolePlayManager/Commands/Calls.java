package com.SadJi.RolePlayManager.Commands;

import com.SadJi.RolePlayManager.RolePlayManager;
import com.SadJi.RolePlayManager.Utility.Localization;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Calls implements CommandExecutor {

    private final RolePlayManager plugin = RolePlayManager.getPlugin();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        FileConfiguration localize = Localization.getFile();

        String answerMsg = localize.getString("AnswerCall");
        String declineMsg = localize.getString("DeclineCall");
        String callReceiverMsg = localize.getString("CallReceiver");
        String callSenderMsg = localize.getString("CallSender");
        String pressToAccept = localize.getString("PressToAccept");
        String pressToDecline = localize.getString("PressToDecline");
        String declinedCall = localize.getString("DeclinedYourCall");
        String noNetwork = localize.getString("NoNetwork");
        String argsException = localize.getString("ArgsError");
        String onlyForPlayers = localize.getString("OnlyForPlayers");

        if(sender instanceof Player) {

            if (args.length == 1){
              Player CallSender = (Player) sender;
              Player CallReceiver = Bukkit.getPlayer(args[0]);

              TextComponent answer = new TextComponent(answerMsg);
              answer.setColor(ChatColor.of("#a3ff37"));
                assert CallReceiver != null;
                answer.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + CallSender.getName() + " "));
              answer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                      new ComponentBuilder(pressToAccept).color(ChatColor.of("#60ff38")).create()));
              TextComponent decline = new TextComponent(declineMsg+" ");
              decline.setColor(ChatColor.of("#cf2525"));
              decline.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + CallSender.getName() + " " + declinedCall));
              decline.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                      new ComponentBuilder(pressToDecline).color(ChatColor.of("#af1717")).create()));

              if (plugin.getConfig().getBoolean("calls-enabled")){
                  CallReceiver.sendMessage(ChatColor.of("#6db31b") + callReceiverMsg+" " + CallSender.getName());
                  CallReceiver.spigot().sendMessage(decline, answer);
                  CallSender.sendMessage(ChatColor.of("#bf5281") + callSenderMsg+" " + CallReceiver.getDisplayName());
              } else {CallSender.sendMessage(noNetwork);}

            } else {sender.sendMessage(argsException);}
        }
        else{
            System.out.println(onlyForPlayers);
            return false;}

        return true;
    }
}
