package com.stuudent.AFK.data;

import com.stuudent.AFK.enums.DisabledType;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class AFKPlayer {

    public OfflinePlayer afkPlayer;
    public AFKData afkData;

    public AFKPlayer(Player afkPlayer) {
        this.afkPlayer = afkPlayer;
        this.afkData = new AFKData();
    }

    public AFKPlayer(OfflinePlayer afkPlayer) {
        this.afkPlayer = afkPlayer;
        this.afkData = new AFKData();
    }

    public void resetTime() {
        this.afkData.resetTime(this.afkPlayer.getPlayer());
    }

    public int getTime() {
        return this.afkData.getTime(this.afkPlayer.getPlayer());
    }

    public boolean isEnabled() {
        return this.afkData.isEnabled(this.afkPlayer.getPlayer());
    }

    // POINT 메소드

    public int getPoint() {
        return this.afkData.getPoint(this.afkPlayer);
    }

    public void addPoint(int value) {
        afkData.addPoint(this.afkPlayer, value);
    }

    public void removePoint(int value) {
        afkData.removePoint(this.afkPlayer, value);
    }

    public void setPoint(int value) {
        afkData.setPoint(this.afkPlayer, value);
    }

    // 잠수 활성화/비활성화 메소드

    public void enableAFK(boolean showTitle) {
        afkData.enableAFK(this.afkPlayer.getPlayer(), showTitle);
    }

    public void disableAFK(boolean showTitle, DisabledType reason) {
        afkData.disableAFK(this.afkPlayer.getPlayer(), reason, showTitle);
    }

    public boolean isReady() {
        return afkData.isReady(this.afkPlayer.getPlayer());
    }

    public void increaseReadyTime() {
        afkData.increaseReadyTime(this.afkPlayer.getPlayer());
    }

    public void resetReadyTime() {
        afkData.resetReadyTime(this.afkPlayer.getPlayer());
    }

    public void increaseTime() {
        afkData.increaseTime(this.afkPlayer.getPlayer());
    }

    public String getHour() {
        return afkData.getHour(this.afkPlayer.getPlayer());
    }

    public String getMinute() {
        return afkData.getMinute(this.afkPlayer.getPlayer());
    }

    public String getSecond() {
        return afkData.getSecond(this.afkPlayer.getPlayer());
    }

    public void sendTitle(boolean isEnabled, int fading) {
        afkData.sendTitle(this.afkPlayer.getPlayer(), isEnabled, fading);
    }

}
