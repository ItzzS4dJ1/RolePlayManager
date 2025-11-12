package com.SadJi.RolePlayManager.Utility;

import com.SadJi.RolePlayManager.RolePlayManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class SeasonExpansion extends PlaceholderExpansion {

    private final RolePlayManager plugin;

    FileConfiguration localize = Localization.getFile();

    String summerName = localize.getString("Summer");
    String autumnName = localize.getString("Autumn");
    String winterName = localize.getString("Winter");
    String springName = localize.getString("Spring");

    public SeasonExpansion(RolePlayManager rolePlayManagerV3, RolePlayManager plugin) {
        this.plugin = plugin;
    }


    @Override
    public String getAuthor() {
        return String.join(", ", plugin.getDescription().getAuthors()); //
    }

    @Override
    public String getIdentifier() {
        return "RolePlayManager";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion(); //
    }

    @Override
    public boolean persist() {
        return true; //
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("rpm_season")) {
            String season = plugin.getConfig().getString("current-season"); //
            assert season != null;
            if (season.equalsIgnoreCase("Winter")){
                return winterName;
            }
            if (season.equalsIgnoreCase("Autumn")){
                return autumnName;
            }
            if (season.equalsIgnoreCase("Summer")){
                return summerName;
            }
            if (season.equalsIgnoreCase("Spring")){
                return springName;
            }
            return null;
        }

        if (params.equalsIgnoreCase("rpm_day")) {
            return String.valueOf(plugin.getConfig().getInt("current-day"));
        }
        if (params.equalsIgnoreCase("rpm_month")) {
            return String.valueOf(plugin.getConfig().getInt("month"));
        }
        return null;

    }
}
