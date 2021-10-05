package com.stuudent.afk.data;

import com.stuudent.afk.AfkAPI;
import com.stuudent.afk.interfaces.AfkPlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceHolders extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "stafk";
    }

    @Override
    public @NotNull String getAuthor() {
        return "STuuDENT";
    }

    @Override
    public @NotNull String getVersion() {
        return "2.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if(player == null)
            return null;
        AfkPlayer afkPlayer = AfkAPI.getPlayer(player);
        switch(params) {
            case "point":
                return String.valueOf(afkPlayer.getPoint());
            case "state":
                if(afkPlayer.isEnabled())
                    return "켜짐";
                else
                    return "꺼짐";
            case "time":
                return String.valueOf(afkPlayer.getTime());
            default:
                return null;
        }
    }

}
