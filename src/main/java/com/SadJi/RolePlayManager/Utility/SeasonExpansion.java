package com.SadJi.RolePlayManager.Utility;

import com.SadJi.RolePlayManager.RolePlayManagerV3;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class SeasonExpansion extends PlaceholderExpansion {

    private final RolePlayManagerV3 plugin;

    public SeasonExpansion(RolePlayManagerV3 rolePlayManagerV3, RolePlayManagerV3 plugin) {
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
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("roleplay-season")) {
            return plugin.getConfig().getString("placeholders.placeholder1", "default1"); //
        }

        if (params.equalsIgnoreCase("roleplay-day")) {
            return plugin.getConfig().getString("placeholders.placeholder1", "default1"); //
        }
        return null;

    }
}
