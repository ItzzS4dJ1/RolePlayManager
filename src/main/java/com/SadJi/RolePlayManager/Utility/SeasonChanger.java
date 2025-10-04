package com.SadJi.RolePlayManager.Utility;

import com.SadJi.RolePlayManager.RolePlayManager;
import net.kyori.adventure.key.Key;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.entity.Player;


public class SeasonChanger {


    protected static FileConfiguration config = RolePlayManager.getPlugin().getConfig();

    private static String namespace = config.getString("namespace");

    private static String summerName = config.getString("summer-name");
    private static String autumnName = config.getString("autumn-name");
    private static String winterName = config.getString("winter-name");
    private static String springName = config.getString("spring-name");

    private static final NamespacedKey summerNKey = new NamespacedKey(namespace, summerName);
    private static final NamespacedKey autumnNKey = new NamespacedKey(namespace, autumnName);
    private static final NamespacedKey winterNKey = new NamespacedKey(namespace, winterName);
    private static final NamespacedKey springNKey = new NamespacedKey(namespace, springName);

/*    private static Key summerKey = Key.key(namespace, summerName);
    private static Key autumnKey = Key.key(namespace, autumnName);              PAPER FORMAT
    private static Key winterKey = Key.key(namespace, winterName);
    private static Key springKey = Key.key(namespace, springName);*/

    private static final Biome biomeSummer = Registry.BIOME.get(summerNKey);
    private static final Biome biomeAutumn = Registry.BIOME.get(autumnNKey);
    private static final Biome biomeWinter = Registry.BIOME.get(winterNKey);
    private static final Biome biomeSpring = Registry.BIOME.get(springNKey);

    public static void updateBiome(Player pl){
        if(config.getBoolean("seasons-enabled")){
            int season = getValue();
            switch (season){
                case 1:
                    pl.getWorld().setBiome(pl.getLocation(), biomeSummer);
                case 2:
                    pl.getWorld().setBiome(pl.getLocation(), biomeAutumn);
                case 3:
                    pl.getWorld().setBiome(pl.getLocation(), biomeWinter);
                case 4:
                    pl.getWorld().setBiome(pl.getLocation(), biomeSpring);
                case 0:
                    System.out.println("Error in seasons changing");
            }
        }
    }

    private static int getValue(){
        String season = config.getString("current-season");

        switch (season){
            case "Summer":
                return 1;
            case "Autumn":
                return 2;
            case "Winter":
                return 3;
            case "Spring":
                return 4;
            default:
                return 0;
        }

    }

}
