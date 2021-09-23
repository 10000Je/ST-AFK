package com.stuudent.afk.listeners;

import com.stuudent.afk.AFKAPI;
import com.stuudent.afk.data.PlayerData;
import com.stuudent.afk.enums.DisabledType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class DetectAction implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        PlayerData playerData = AFKAPI.getPlayer(e.getPlayer());
        if(playerData.isEnabled()) {
            playerData.disableAFK(false, DisabledType.JOIN);
        } else {
            playerData.resetReadyTime();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerData playerData = AFKAPI.getPlayer(e.getPlayer());
        if(playerData.isEnabled()) {
            playerData.disableAFK(false, DisabledType.QUIT);
        } else {
            playerData.resetReadyTime();
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if(e.getEntity().getPlayer() == null)
            return;
        PlayerData playerData = AFKAPI.getPlayer(e.getEntity().getPlayer());
        if(playerData.isEnabled()) {
            playerData.disableAFK(true, DisabledType.DEATH);
        } else {
            playerData.resetReadyTime();
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        PlayerData playerData = AFKAPI.getPlayer(e.getPlayer());
        if(playerData.isEnabled()) {
            playerData.disableAFK(true, DisabledType.MOVEMENT);
        } else {
            playerData.resetReadyTime();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        PlayerData playerData = AFKAPI.getPlayer(e.getPlayer());
        if(playerData.isEnabled()) {
            playerData.disableAFK(true, DisabledType.CHAT);
        } else {
            playerData.resetReadyTime();
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        PlayerData playerData = AFKAPI.getPlayer(e.getPlayer());
        if(playerData.isEnabled()) {
            playerData.disableAFK(true, DisabledType.COMMAND);
        } else {
            playerData.resetReadyTime();
        }
    }
}























