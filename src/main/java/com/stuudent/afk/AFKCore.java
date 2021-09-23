package com.stuudent.afk;

import com.stuudent.afk.commands.AdminCommand;
import com.stuudent.afk.commands.UserCommand;
import com.stuudent.afk.data.AllData;
import com.stuudent.afk.data.PlaceHolders;
import com.stuudent.afk.data.PlayerData;
import com.stuudent.afk.listeners.DetectAction;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class AFKCore extends JavaPlugin {

    public static AFKCore instance;
    public static FileConfiguration cf;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new DetectAction(), instance);
        saveDefaultConfig();
        cf = getConfig();
        setCommandExecutors();
        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(instance, () -> {
            AllData allData = AFKAPI.getData();
            for(Player player : Bukkit.getOnlinePlayers()) {
                PlayerData playerData = AFKAPI.getPlayer(player);
                if(playerData.isEnabled()) {
                    playerData.increaseTime();
                    if(playerData.getTime() % cf.getInt("AFKPeriod", 60) == 0) {
                        playerData.addPoint(1);
                        allData.save();
                    }
                    playerData.sendTitle(true, 0);
                } else {
                    playerData.increaseReadyTime();
                    if(playerData.isReady()) {
                        playerData.enableAFK(true);
                    }
                }
            }
        }, 0, 20);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceHolders().register();
        } else {
            Bukkit.getConsoleSender().sendMessage("§6ST-§9AFK§ §ev" + getDescription().getVersion() + " §cPlaceholderAPI 플러그인이 존재하지 않습니다. Placeholder 기능은 사용할 수 없습니다.");
        }
        Bukkit.getConsoleSender().sendMessage("§6ST-§9AFK §ev" + getDescription().getVersion() + " §a플러그인이 활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }

    @Override
    public void onDisable() {
        AllData allData = AFKAPI.getData();
        allData.save();
        Bukkit.getConsoleSender().sendMessage("§6ST-§9AFK §ev" + getDescription().getVersion() + " §c플러그인이 비활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }

    public void setCommandExecutors() {
        getCommand("잠수").setExecutor(new UserCommand());
        getCommand("잠수").setTabCompleter(new UserCommand());
        getCommand("잠수관리").setExecutor(new AdminCommand());
        getCommand("잠수관리").setTabCompleter(new AdminCommand());
    }
}
