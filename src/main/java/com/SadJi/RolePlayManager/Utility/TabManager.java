package com.SadJi.RolePlayManager.Utility;

import com.SadJi.RolePlayManager.RolePlayManagerV3;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class TabManager {

    private final RolePlayManagerV3 plugin = RolePlayManagerV3.getPlugin();
    public int daysGone = plugin.getConfig().getInt("current-day");
    public String getSeason(){
        String season = plugin.getConfig().getString("current-season"); //
        assert season != null;
        String currSeason = " ";
        if (season.equalsIgnoreCase("Winter")){
            currSeason = "Зима";
        }
        if (season.equalsIgnoreCase("Autumn")){
            currSeason = "Осень";
        }
        if (season.equalsIgnoreCase("Summer")){
            currSeason = "Лето";
        }
        if (season.equalsIgnoreCase("Spring")){
            currSeason = "Весна";
        }
        return currSeason;
    }

    public int getDaysGone() {
        return daysGone;
    }

    private String logo = ChatColor.of("#F4C4F3") + "L" + ChatColor.of("#F6CCF1") + "o" + ChatColor.of("#F8D4EE") + "t" + ChatColor.of("#FADCEC") + "u" + ChatColor.of("#FBE0EF") + "s" + " " + ChatColor.of("#FBE1EF") + "R" + ChatColor.of("#FBDEEE") + "o" + ChatColor.of("#FADCEC") + "l" + ChatColor.of("#F6D1E5") + "e" + ChatColor.of("#F2C6DE") + "p" + ChatColor.of("#EAC8E4") + "l" + ChatColor.of("#E3CBEA") + "a" + ChatColor.of("#DBCDF0") + 'y';
    private List<String> header = new ArrayList<>();
    private List<String> footer = new ArrayList<>();

    public void addHeaderLine(String str){
        header.add(str);
    }
    public void addFooterLine(String str){
        header.add(str);
    }

    public void addServerIPFooter(Player player){
        player.setPlayerListFooter(ChatColor.of("#F4C4F3") + "L" + ChatColor.of("#F6CCF1") + "o" + ChatColor.of("#F8D4EE") + "t" + ChatColor.of("#FADCEC") + "u" + ChatColor.of("#FBE0EF") + "s" + " " + ChatColor.of("#FBE1EF") + "R" + ChatColor.of("#FBDEEE") + "o" + ChatColor.of("#FADCEC") + "l" + ChatColor.of("#F6D1E5") + "e" + ChatColor.of("#F2C6DE") + "p" + ChatColor.of("#EAC8E4") + "l" + ChatColor.of("#E3CBEA") + "a" + ChatColor.of("#DBCDF0") + "y");
    }

    private String convert (List<String> list){
        return String.join("\n", list);
    }

    public void update(Player pl){
        pl.setPlayerListHeader(color(convert(PlaceholderAPI.setPlaceholders(pl, header))));
        pl.setPlayerListFooter(color(convert(PlaceholderAPI.setPlaceholders(pl, footer))));
    }


    private String color(String str){return ChatColor.translateAlternateColorCodes('&', str);}









}
