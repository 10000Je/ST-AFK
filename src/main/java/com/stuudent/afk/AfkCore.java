package com.stuudent.afk;

import com.stuudent.afk.commands.AdminCommand;
import com.stuudent.afk.commands.UserCommand;
import com.stuudent.afk.data.PlaceHolders;
import com.stuudent.afk.data.PlayerData;
import com.stuudent.afk.listeners.DetectAction;
import com.stuudent.afk.schedulers.AfkScheduler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class AfkCore extends JavaPlugin {

    public static AfkCore instance;
    public static FileConfiguration cf;
    public static boolean pha;

    @Override
    public void onEnable() {
        instance = this;
        softDependCheck();
        saveDefaultConfig();
        cf = getConfig();
        setCommandExecutors();
        setCommandTabCompleter();
        registerSchedulers();
        registerPlaceHolders();
        Bukkit.getPluginManager().registerEvents(new DetectAction(), instance);
        Bukkit.getConsoleSender().sendMessage("§6ST-§9AFK §ev" + getDescription().getVersion() + " §a플러그인이 활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }

    @Override
    public void onDisable() {
        PlayerData.save();
        Bukkit.getScheduler().cancelTasks(instance);
        Bukkit.getConsoleSender().sendMessage("§6ST-§9AFK §ev" + getDescription().getVersion() + " §c플러그인이 비활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }

    public void registerSchedulers() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new AfkScheduler(), 0, 20);
    }

    public void softDependCheck() {
        pha = getServer().getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    public void registerPlaceHolders() {
        if(pha)
            new PlaceHolders().register();
    }


    public void setCommandExecutors() {
        getCommand("잠수").setExecutor(new UserCommand());
        getCommand("잠수관리").setExecutor(new AdminCommand());
    }

    public void setCommandTabCompleter() {
        getCommand("잠수").setTabCompleter(new UserCommand());
        getCommand("잠수관리").setTabCompleter(new AdminCommand());
    }
}
