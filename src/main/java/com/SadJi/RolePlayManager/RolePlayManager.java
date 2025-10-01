package com.SadJi.RolePlayManager;

import com.SadJi.RolePlayManager.Commands.*;
import com.SadJi.RolePlayManager.Events.ChatListener;
import com.SadJi.RolePlayManager.Events.MyPlayerListener;
import com.SadJi.RolePlayManager.Events.bandageUse;
import com.SadJi.RolePlayManager.Tasks.CycleSeasonTask;
import com.SadJi.RolePlayManager.Tasks.DelayedTask;
import com.SadJi.RolePlayManager.Commands.JobCommand;
import com.SadJi.RolePlayManager.Utility.Menu;
import com.SadJi.RolePlayManager.Utility.SeasonChanger;
import com.SadJi.RolePlayManager.Utility.SeasonExpansion;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Logger;


public class RolePlayManager extends JavaPlugin{
    Logger log = getLogger();



    private static RolePlayManager plugin;
    public static RolePlayManager getPlugin() {
        return plugin;
    }
    @Override
    public void onEnable ( ) {
        saveConfig();
        plugin = this;
        Long Time = Bukkit.getWorlds().get(0).getTime();
        Long DayFull = 24000L;

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new SeasonExpansion(this, plugin).register();
        }

        log.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        log.info("    SadJi's RolePlay Manager");
        log.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        log.info("  Plugin Enabled Successfully");
        log.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        log.info("     Plugin version: DEV");
        log.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

        log.info(" ");
        log.info(ChatColor.GOLD + "Current Time:");
        log.info(String.valueOf(Time));
        log.info(" ");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getLogger().warning("PAPI is required to work.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            for(Player player : Bukkit.getOnlinePlayers()) {
                SeasonChanger.updateBiome(player);
            }
        }, 10L, 50L);


        getServer().getPluginManager().registerEvents(new MyPlayerListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new bandageUse(), this);
        getCommand("name").setExecutor(new NameChanger());
        getCommand("menu").setExecutor(new Menu());
        getCommand("job").setExecutor(new JobCommand());
        getCommand("setseason").setExecutor(new SetSeason());
        getCommand("setseason").setDescription("Ставит сезон");
        getCommand("getseason").setExecutor(new GetSeason());
        getCommand("call").setExecutor(new Calls());
        getCommand("getday").setExecutor(new GetDay());
        getCommand("experiment").setExecutor(new Experiments());
        getCommand("exptest").setExecutor(new ExpTest());
        getCommand("sethex").setExecutor(new SetHex());

        new DelayedTask(this);
        BukkitTask cycleSeasonTask = new CycleSeasonTask(this).runTaskTimer(this, (DayFull - Time), 24000L);
    }
    @Override
    public void onDisable () {
        saveConfig();
        log.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        log.info("   SadJi's RolePlay Manager");
        log.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        log.info(" Plugin disabled Successfully");
        log.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        log.info("For support: contact @ItzzS4dJ1");
        log.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}





