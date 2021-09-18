package com.stuudent.AFK.data;

import com.stuudent.AFK.AFKCore;
import com.stuudent.AFK.enums.DisabledType;
import com.stuudent.AFK.event.AFKDisable;
import com.stuudent.AFK.event.AFKEnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class AFKData {

    public static File configFile;
    public static File tempFile;
    public static YamlConfiguration cf;
    public static YamlConfiguration tempCf;

    static {
        configFile = new File("plugins/" + AFKCore.instance.getName() + "/AFKData.yml");
        tempFile = new File("plugins/" + AFKCore.instance.getName() + "/tempData.yml");
        cf = YamlConfiguration.loadConfiguration(configFile);
        tempCf = YamlConfiguration.loadConfiguration(tempFile);
    }

    public void save(){
        try {cf.save(configFile);} catch (IOException ignored) {}
    }

    public void tf_save() { try {tempCf.save(tempFile);} catch(IOException ignored) {} }

    public void resetTime(Player afkPlayer) {
        cf.set(afkPlayer.getUniqueId() + ".TIME", 0);
    }

    public int getTime(Player afkPlayer) {
        return cf.getInt(afkPlayer.getUniqueId() + ".TIME", 0);
    }

    public boolean isEnabled(Player afkPlayer) {
        return cf.getBoolean(afkPlayer.getUniqueId() + ".STATE", false);
    }

    // POINT 메소드

    public int getPoint(OfflinePlayer afkPlayer) {
        return cf.getInt(afkPlayer.getUniqueId() + ".POINT", 0);
    }

    public void addPoint(OfflinePlayer afkPlayer, int value) {
        setPoint(afkPlayer,getPoint(afkPlayer) + value);
    }

    public void removePoint(OfflinePlayer afkPlayer, int value) {
        setPoint(afkPlayer, Math.max(getPoint(afkPlayer) - value, 0));
    }

    public void setPoint(OfflinePlayer afkPlayer, int value) {
        cf.set(afkPlayer.getUniqueId() + ".POINT", value);
    }

    // 잠수 활성화/비활성화 메소드

    public void enableAFK(Player afkPlayer, boolean showTitle) {
        AFKEnable event = new AFKEnable(afkPlayer);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled())
            return;
        resetReadyTime(afkPlayer);
        if(showTitle)
            sendTitle(afkPlayer,true, 20);
        cf.set(afkPlayer.getUniqueId() + ".STATE", true);
        save();
    }

    public void disableAFK(Player afkPlayer, DisabledType reason, boolean showTitle) {
        AFKDisable event = new AFKDisable(afkPlayer, reason);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled())
            return;
        if(showTitle)
            sendTitle(afkPlayer, false, 0);
        resetTime(afkPlayer);
        cf.set(afkPlayer.getUniqueId() + ".STATE", false);
        save();
    }

    public boolean isReady(Player afkPlayer) {
        return tempCf.getInt(afkPlayer.getUniqueId() + ".READYTIME", 0) >= AFKCore.cf.getInt("AfkStart", 60);
    }

    public void increaseReadyTime(Player afkPlayer) {
        tempCf.set(afkPlayer.getUniqueId() + ".READYTIME", tempCf.getInt(afkPlayer.getUniqueId() + ".READYTIME", 0) + 1);
    }

    public void resetReadyTime(Player afkPlayer) {
        tempCf.set(afkPlayer.getUniqueId() + ".READYTIME", null);
    }

    public void increaseTime(Player afkPlayer) {
        cf.set(afkPlayer.getUniqueId() + ".TIME", getTime(afkPlayer) + 1);
    }

    public String getHour(Player afkPlayer) {
        return (Math.floor(getTime(afkPlayer) / 3600) < 10) ? "0" + (int) Math.floor(getTime(afkPlayer) / 3600) : String.valueOf((int) Math.floor(getTime(afkPlayer) / 3600));
    }

    public String getMinute(Player afkPlayer) {
        return (Math.floor(getTime(afkPlayer) % 3600 / 60) < 10) ? "0" + (int) Math.floor(getTime(afkPlayer) % 3600 / 60) : String.valueOf((int) Math.floor(getTime(afkPlayer) % 3600 / 60));
    }

    public String getSecond(Player afkPlayer) {
        return (Math.floor(getTime(afkPlayer) % 3600 % 60) < 10) ? "0" + (int) Math.floor(getTime(afkPlayer) % 3600 % 60) : String.valueOf((int) Math.floor(getTime(afkPlayer) % 3600 % 60));
    }

    protected void sendTitle(Player afkPlayer, boolean isEnabled, int fading) {
        int receivedPoint = (int) Math.floor(getTime(afkPlayer) / 60);
        if(isEnabled) {
            String EnabledTitle = ChatColor.translateAlternateColorCodes('&',AFKCore.cf.getString("TitleFormat.Enabled.Title", "")
                    .replace("[HOUR]", getHour(afkPlayer)).replace("[MINUTE]", getMinute(afkPlayer)).replace("[SECOND]", getSecond(afkPlayer))
                    .replace("[POINT]", String.valueOf(getPoint(afkPlayer))).replace("[RECEIVED]", String.valueOf(receivedPoint)));
            String EnabledSubTitle = ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("TitleFormat.Enabled.SubTitle", "")
                    .replace("[HOUR]", getHour(afkPlayer)).replace("[MINUTE]", getMinute(afkPlayer)).replace("[SECOND]", getSecond(afkPlayer))
                    .replace("[POINT]", String.valueOf(getPoint(afkPlayer))).replace("[RECEIVED]", String.valueOf(receivedPoint)));
            afkPlayer.sendTitle(EnabledTitle, EnabledSubTitle, fading, 40, 0);
        } else {
            String DisabledTitle = ChatColor.translateAlternateColorCodes('&',AFKCore.cf.getString("TitleFormat.Disabled.Title", "")
                    .replace("[HOUR]", getHour(afkPlayer)).replace("[MINUTE]", getMinute(afkPlayer)).replace("[SECOND]", getSecond(afkPlayer))
                    .replace("[POINT]", String.valueOf(getPoint(afkPlayer))).replace("[RECEIVED]", String.valueOf(receivedPoint)));
            String DisabledSubTitle = ChatColor.translateAlternateColorCodes('&', AFKCore.cf.getString("TitleFormat.Disabled.SubTitle", "")
                    .replace("[HOUR]", getHour(afkPlayer)).replace("[MINUTE]", getMinute(afkPlayer)).replace("[SECOND]", getSecond(afkPlayer))
                    .replace("[POINT]", String.valueOf(getPoint(afkPlayer))).replace("[RECEIVED]", String.valueOf(receivedPoint)));
            afkPlayer.sendTitle(DisabledTitle, DisabledSubTitle, fading, 40, 40);
        }
    }
}
