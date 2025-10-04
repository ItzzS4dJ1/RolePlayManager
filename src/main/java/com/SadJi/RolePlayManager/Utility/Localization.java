package com.SadJi.RolePlayManager.Utility;

import com.SadJi.RolePlayManager.RolePlayManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Localization {


    protected static final RolePlayManager plugin = RolePlayManager.getPlugin();
    static Logger log = plugin.getLogger();
    private static FileConfiguration config = plugin.getConfig();
    private static String selectedLanguage = config.getString("Language");
    private static File file;
    private static FileConfiguration langTable = new YamlConfiguration();

    public static void init(){
        String filename = selectedLanguage;
        log.info("Selected language:" + filename);
        file = new File(plugin.getDataFolder(), filename);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        langTable = YamlConfiguration.loadConfiguration(file);

    }
    public static FileConfiguration getFile(){
        return langTable;
    }

    public static void save(){
        try{
            langTable.save(file);
        }catch (IOException e){
            System.out.println(langTable.getString("IOError"));
        }
    }

    public static void reload(){
        langTable = YamlConfiguration.loadConfiguration(file);
    }



}
