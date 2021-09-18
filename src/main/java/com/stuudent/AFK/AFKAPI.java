package com.stuudent.AFK;

import com.stuudent.AFK.data.AFKData;
import com.stuudent.AFK.data.AFKPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AFKAPI {

    public static AFKData getData() {
        return new AFKData();
    }

    public static AFKPlayer getPlayer(Player afkPlayer) {
        return new AFKPlayer(afkPlayer);
    }

    public static AFKPlayer getPlayer(OfflinePlayer afkPlayer) {
        return new AFKPlayer(afkPlayer);
    }

    public static AFKPlayer getPlayer(UUID afkUUID) {
        return new AFKPlayer(Bukkit.getPlayer(afkUUID));
    }

    public static AFKPlayer getPlayer(String afkName) {
        return new AFKPlayer(Bukkit.getPlayer(afkName));
    }

}
