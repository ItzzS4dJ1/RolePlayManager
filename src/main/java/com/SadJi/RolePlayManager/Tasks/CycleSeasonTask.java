package com.SadJi.RolePlayManager.Tasks;

import com.SadJi.RolePlayManager.RolePlayManager;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

// day cycle task //
public class CycleSeasonTask extends BukkitRunnable {

    RolePlayManager plugin;
    public CycleSeasonTask(RolePlayManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if(plugin.getConfig().getInt("current-day") == 30){
            plugin.getConfig().set("current-day", 0);
            if (plugin.getConfig().getInt("int-season") < 5){
                plugin.getConfig().set("int-season", plugin.getConfig().getInt("int-season") + 1);
            }
            if (plugin.getConfig().getInt("int-season") == 5 || plugin.getConfig().getInt("int-season") >= 6) {
                plugin.getConfig().set("int-season", 1);
            }
            int Integera = plugin.getConfig().getInt("int-season");
            if (Integera == 1) {
                plugin.getConfig().set("current-season", "Summer");
                System.out.println("Summer!");
            } else if (Integera == 2) {
                plugin.getConfig().set("current-season", "Autumn");
                System.out.println("Autumn!");
            } else if (Integera == 3) {
                plugin.getConfig().set("current-season", "Winter");
                System.out.println("Winter!");
            } else if (Integera == 4) {
                plugin.getConfig().set("current-season", "Spring");
                System.out.println("Spring!");
            } else{
                System.out.println("ERROR IN CYCLE!!");
            }

        }
        plugin.getConfig().set("current-day", plugin.getConfig().getInt("current-day") + 1);
        System.out.println("added a day");
        System.out.println(plugin.getConfig().getInt("current-day"));
    }
}
