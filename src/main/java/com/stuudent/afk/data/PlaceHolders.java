package com.stuudent.afk.data;

import com.stuudent.afk.AFKAPI;
import com.sun.istack.internal.NotNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceHolders extends PlaceholderExpansion {

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

        PlayerData playerData = AFKAPI.getPlayer(player);
        if(params.equals("point"))
            return String.valueOf(playerData.getPoint());

        if(params.equals("state"))
            return String.valueOf(playerData.isEnabled());

        if(params.equals("time"))
            return String.valueOf(playerData.getTime());

        return null;
    }
}
