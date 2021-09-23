package com.stuudent.afk;

import com.stuudent.afk.data.AllData;
import com.stuudent.afk.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AFKAPI {

    public static AllData getData() {
        return new AllData();
    }

    public static PlayerData getPlayer(Player afkPlayer) {
        return new PlayerData(afkPlayer);
    }

    public static PlayerData getPlayer(OfflinePlayer afkPlayer) {
        return new PlayerData(afkPlayer);
    }

    public static PlayerData getPlayer(UUID afkUUID) {
        return new PlayerData(Bukkit.getPlayer(afkUUID));
    }

    public static PlayerData getPlayer(String afkName) {
        return new PlayerData(Bukkit.getPlayer(afkName));
    }

}
