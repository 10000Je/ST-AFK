package com.stuudent.AFK.listeners;

import com.stuudent.AFK.AFKAPI;
import com.stuudent.AFK.data.AFKPlayer;
import com.stuudent.AFK.enums.DisabledType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class DetectAction implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        AFKPlayer afkPlayer = AFKAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            afkPlayer.disableAFK(false, DisabledType.JOIN);
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        AFKPlayer afkPlayer = AFKAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            afkPlayer.disableAFK(false, DisabledType.QUIT);
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if(e.getEntity().getPlayer() == null)
            return;
        AFKPlayer afkPlayer = AFKAPI.getPlayer(e.getEntity().getPlayer());
        if(afkPlayer.isEnabled()) {
            afkPlayer.disableAFK(true, DisabledType.DEATH);
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        AFKPlayer afkPlayer = AFKAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            afkPlayer.disableAFK(true, DisabledType.MOVEMENT);
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        AFKPlayer afkPlayer = AFKAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            afkPlayer.disableAFK(true, DisabledType.CHAT);
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        AFKPlayer afkPlayer = AFKAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            afkPlayer.disableAFK(true, DisabledType.COMMAND);
        } else {
            afkPlayer.resetReadyTime();
        }
    }
}























