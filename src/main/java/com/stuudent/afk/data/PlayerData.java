package com.stuudent.afk.data;

import com.stuudent.afk.AfkCore;
import com.stuudent.afk.enums.TitleType;
import com.stuudent.afk.interfaces.AfkPlayer;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerData implements AfkPlayer {

    public static File afkDataFile;
    public static YamlConfiguration afkData;
    public static YamlConfiguration tempData;

    public OfflinePlayer afkPlayer;

    static {
        afkDataFile = new File("plugins/" + AfkCore.instance.getName() + "/afkData.yml");
        afkData = YamlConfiguration.loadConfiguration(afkDataFile);
        tempData = new YamlConfiguration();
    }

    public static void save(){
        try {
            afkData.save(afkDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerData(Player afkPlayer) {
        this.afkPlayer = afkPlayer;
    }

    public PlayerData(OfflinePlayer afkPlayer) {
        this.afkPlayer = afkPlayer;
    }

    @Override
    public int getTime() {
        return tempData.getInt(this.afkPlayer.getUniqueId() + ".TIME", 0);
    }

    @Override
    public void resetTime() {
        tempData.set(this.afkPlayer.getUniqueId() + ".TIME", 0);
    }

    @Override
    public boolean isEnabled() {
        return tempData.getBoolean(this.afkPlayer.getUniqueId() + ".STATE", false);
    }

    @Override
    public int getPoint() {
        return afkData.getInt(this.afkPlayer.getUniqueId().toString(), 0);
    }

    @Override
    public void addPoint(int value) {
        setPoint(getPoint() + value);
    }

    @Override
    public void removePoint(int value) {
        setPoint(Math.max(getPoint() - value, 0));
    }

    @Override
    public void setPoint(int value) {
        afkData.set(this.afkPlayer.getUniqueId().toString(), value);
    }

    @Override
    public void setState(boolean state) {
        tempData.set(this.afkPlayer.getUniqueId() + ".STATE", state);
    }

    @Override
    public boolean isReady() {
        return tempData.getInt(afkPlayer.getUniqueId() + ".READYTIME", 0) >= AfkCore.cf.getInt("AfkStart", 60);
    }

    @Override
    public void increaseReadyTime() {
        tempData.set(afkPlayer.getUniqueId() + ".READYTIME", tempData.getInt(afkPlayer.getUniqueId() + ".READYTIME", 0) + 1);
    }

    @Override
    public void resetReadyTime() {
        tempData.set(this.afkPlayer.getUniqueId() + ".READYTIME", null);
    }

    @Override
    public void increaseTime() {
        tempData.set(this.afkPlayer.getUniqueId() + ".TIME", getTime() + 1);
    }

    @Override
    public String getTitleFormat(TitleType titleType) {
        int receivedPoint = (int) Math.floor(getTime() / 60); String targetString;
        if(titleType.equals(TitleType.ENABLED_TITLE)) {
            targetString = ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("TitleFormat.Enabled.Title", ""));
        }
        else if(titleType.equals(TitleType.ENABLED_SUBTITLE)) {
            targetString = ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("TitleFormat.Enabled.SubTitle", ""));
        }
        else if(titleType.equals(TitleType.DISABLED_TITLE)) {
            targetString = ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("TitleFormat.Disabled.Title", ""));
        }
        else if(titleType.equals(TitleType.DISABLED_SUBTITLE)) {
            targetString = ChatColor.translateAlternateColorCodes('&', AfkCore.cf.getString("TitleFormat.Disabled.SubTitle", ""));
        }
        else {
            return null;
        }
        return targetString.replace("%afk_hour%", getHour()).replace("%afk_minute%", getMinute()).replace("%afk_second%", getSecond())
                .replace("%afk_point%", String.valueOf(getPoint())).replace("%received_point%", String.valueOf(receivedPoint));
    }

    @Override
    public OfflinePlayer getBukkitPlayer() {
        return this.afkPlayer;
    }

    public String getHour() {
        return (Math.floor(getTime() / 3600) < 10) ? "0" + (int) Math.floor(getTime() / 3600) : String.valueOf((int) Math.floor(getTime() / 3600));
    }

    public String getMinute() {
        return (Math.floor(getTime() % 3600 / 60) < 10) ? "0" + (int) Math.floor(getTime() % 3600 / 60) : String.valueOf((int) Math.floor(getTime() % 3600 / 60));
    }

    public String getSecond() {
        return (Math.floor(getTime() % 3600 % 60) < 10) ? "0" + (int) Math.floor(getTime() % 3600 % 60) : String.valueOf((int) Math.floor(getTime() % 3600 % 60));
    }
}
