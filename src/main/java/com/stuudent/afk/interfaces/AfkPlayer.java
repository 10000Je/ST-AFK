package com.stuudent.afk.interfaces;

import com.stuudent.afk.enums.TitleType;
import org.bukkit.OfflinePlayer;

public interface AfkPlayer {

    /**
     * @return returns afkPlayer's total AfkTime(second).
     */
    int getTime();

    /**
     * @apiNote This Method will reset afkPlayer's AfkTime.
     */
    void resetTime();

    /**
     * @return returns afkPlayer's afkState.
     */
    boolean isEnabled();

    /**
     * @param value will be added to afkPlayer's total afkPoint.
     */
    void addPoint(int value);

    /**
     * @param value will be removed from afkPlayer's total afkPoint.
     */
    void removePoint(int value);

    /**
     * @param value will be set to afkPlayer's total afkPoint.
     */
    void setPoint(int value);

    /**
     * @return returns afkPlayer's afkPoint.
     */
    int getPoint();

    /**
     * @param state will be afkPlayer's afkState.
     */
    void setState(boolean state);

    /**
     * @return returns afkPlayer's readyTime is over the config value "AfkStart"
     */
    boolean isReady();

    /**
     * @apiNote it will increase afkPlayer's readyTime.
     */
    void increaseReadyTime();

    /**
     * @apiNote it will reset afkPlayer's readyTime.
     */
    void resetReadyTime();

    /**
     * @apiNote it will increase afkPlayer's afkTime.
     */
    void increaseTime();

    /**
     * @param titleType is the TitleType that you want to send to target.
     * @return returns formatted String.
     */
    String getTitleFormat(TitleType titleType);

    /**
     * @return returns Bukkit Player.
     */
    OfflinePlayer getBukkitPlayer();

}
