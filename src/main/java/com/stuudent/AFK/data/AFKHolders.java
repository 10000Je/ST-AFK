package com.stuudent.AFK.data;

import com.stuudent.AFK.AFKAPI;
import com.sun.istack.internal.NotNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class AFKHolders extends PlaceholderExpansion {

    @Override
    public @NotNull
    String getIdentifier() {
        return "afk";
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
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if(player == null)
            return null;

        AFKPlayer afkPlayer = AFKAPI.getPlayer(player);
        if(params.equals("point"))
            return String.valueOf(afkPlayer.getPoint());

        if(params.equals("state"))
            return String.valueOf(afkPlayer.isEnabled());

        if(params.equals("time"))
            return String.valueOf(afkPlayer.getTime());

        return null;
    }
}
