package com.stuudent.afk.data;

import com.stuudent.afk.enums.DisabledType;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerData {

    public OfflinePlayer afkPlayer;
    public AllData allData;

    public PlayerData(Player afkPlayer) {
        this.afkPlayer = afkPlayer;
        this.allData = new AllData();
    }

    public PlayerData(OfflinePlayer afkPlayer) {
        this.afkPlayer = afkPlayer;
        this.allData = new AllData();
    }

    public void resetTime() {
        this.allData.resetTime(this.afkPlayer.getPlayer());
    }

    public int getTime() {
        return this.allData.getTime(this.afkPlayer.getPlayer());
    }

    public boolean isEnabled() {
        return this.allData.isEnabled(this.afkPlayer.getPlayer());
    }

    // POINT 메소드

    public int getPoint() {
        return this.allData.getPoint(this.afkPlayer);
    }

    public void addPoint(int value) {
        allData.addPoint(this.afkPlayer, value);
    }

    public void removePoint(int value) {
        allData.removePoint(this.afkPlayer, value);
    }

    public void setPoint(int value) {
        allData.setPoint(this.afkPlayer, value);
    }

    // 잠수 활성화/비활성화 메소드

    public void enableAFK(boolean showTitle) {
        allData.enableAFK(this.afkPlayer.getPlayer(), showTitle);
    }

    public void disableAFK(boolean showTitle, DisabledType reason) {
        allData.disableAFK(this.afkPlayer.getPlayer(), reason, showTitle);
    }

    public boolean isReady() {
        return allData.isReady(this.afkPlayer.getPlayer());
    }

    public void increaseReadyTime() {
        allData.increaseReadyTime(this.afkPlayer.getPlayer());
    }

    public void resetReadyTime() {
        allData.resetReadyTime(this.afkPlayer.getPlayer());
    }

    public void increaseTime() {
        allData.increaseTime(this.afkPlayer.getPlayer());
    }

    public String getHour() {
        return allData.getHour(this.afkPlayer.getPlayer());
    }

    public String getMinute() {
        return allData.getMinute(this.afkPlayer.getPlayer());
    }

    public String getSecond() {
        return allData.getSecond(this.afkPlayer.getPlayer());
    }

    public void sendTitle(boolean isEnabled, int fading) {
        allData.sendTitle(this.afkPlayer.getPlayer(), isEnabled, fading);
    }

}
