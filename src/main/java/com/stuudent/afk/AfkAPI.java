package com.stuudent.afk;

import com.stuudent.afk.data.PlayerData;
import com.stuudent.afk.interfaces.AfkPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AfkAPI {

    public static AfkPlayer getPlayer(Player afkPlayer) {
        return new PlayerData(afkPlayer);
    }

    public static AfkPlayer getPlayer(OfflinePlayer afkPlayer) {
        return new PlayerData(afkPlayer);
    }

    public static AfkPlayer getPlayer(UUID afkUUID) {
        return new PlayerData(Bukkit.getPlayer(afkUUID));
    }

    public static AfkPlayer getPlayer(String afkName) {
        return new PlayerData(Bukkit.getPlayer(afkName));
    }

}
