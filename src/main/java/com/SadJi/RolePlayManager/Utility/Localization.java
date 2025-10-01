package com.SadJi.RolePlayManager.Utility;

import com.SadJi.RolePlayManager.RolePlayManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Localization {

    protected static final RolePlayManager plugin = RolePlayManager.getPlugin();
    private static FileConfiguration config = plugin.getConfig();
    private static String selectedLanguage = config.getString("Language");
    private static File file;
    private static FileConfiguration langTable;

    public static HashMap<String, String> localization = new HashMap<>();

    public static void init(String lang){
        String filename = lang + ".yml";
        file = new File(plugin.getDataFolder(), filename);

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.out.println(getLocalized("IO error"));
                //bad-bad developer
            }
        }
        langTable = YamlConfiguration.loadConfiguration(file);

        localization.put("Whisper", langTable.getString("Whisper"));
        localization.put("Shout", langTable.getString("Shout"));
        localization.put("IOError", langTable.getString("IOError"));
        localization.put("AnswerCall", langTable.getString("AnswerCall"));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));
        localization.put("", langTable.getString(""));

    }
    public static FileConfiguration getFile(){
        return langTable;
    }

    public static void save(){
        try{
            langTable.save(file);
        }catch (IOException e){
            System.out.println(getLocalized("IOError"));
        }
    }

    public static void reload(){
        langTable = YamlConfiguration.loadConfiguration(file);
    }

    public static String getLocalized(String key){
        return localization.get(key);
    }


}
