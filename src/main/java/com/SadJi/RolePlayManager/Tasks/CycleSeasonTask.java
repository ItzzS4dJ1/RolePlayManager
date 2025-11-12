package com.SadJi.RolePlayManager.Tasks;

import com.SadJi.RolePlayManager.RolePlayManager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;

// day cycle task //
public class CycleSeasonTask extends BukkitRunnable {

    protected final RolePlayManager plugin;

    public CycleSeasonTask(RolePlayManager plugin) {
        this.plugin = plugin;
    }


    @Override
    public void run() {
        FileConfiguration cfg = plugin.getConfig();
        int day = cfg.getInt("current-day");
        int monthsPerSeason = cfg.getInt("months-per-season");
        int daysPerMonth = cfg.getInt("days-per-month");
        int month = cfg.getInt("int-month");
        int year = cfg.getInt("year");
        int season = cfg.getInt("int-season");

        day+=1;
        cfg.set("current-day", day);
        if(day > daysPerMonth){
            month += 1;
            cfg.set("int-month", month);
            day = 1;
            cfg.set("current-day", day);
            if(month > monthsPerSeason){
                season += 1;
                cfg.set("int-season", season);
                month = 1;
                cfg.set("int-month", month);
                if(season > 4){
                    year += 1;
                    cfg.set("year", year);
                    season = 1;
                    cfg.set("int-season", season);
                }
            }
        }

        switch (season){
            case 1:
                cfg.set("current-season", "Spring");
            case 2:
                cfg.set("current-season", "Summer");
            case 3:
                cfg.set("current-season", "Autumn");
            case 4:
                cfg.set("current-season", "Winter");
            default:
                Logger logger = RolePlayManager.getPlugin().getLogger();
                logger.warning("ERROR! Module: seasons cycling");
        }

    }
}
