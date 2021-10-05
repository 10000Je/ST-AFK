package com.stuudent.afk.listeners;

import com.stuudent.afk.AfkAPI;
import com.stuudent.afk.enums.DisabledType;
import com.stuudent.afk.enums.TitleType;
import com.stuudent.afk.event.AfkDisableEvent;
import com.stuudent.afk.interfaces.AfkPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class DetectAction implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        AfkPlayer afkPlayer = AfkAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            AfkDisableEvent event = new AfkDisableEvent(e.getPlayer(), DisabledType.JOIN);
            Bukkit.getPluginManager().callEvent(event);
            if(event.isCancelled()) return;
            afkPlayer.setState(false);
            afkPlayer.resetTime();
            e.getPlayer().sendTitle(afkPlayer.getTitleFormat(TitleType.DISABLED_TITLE), afkPlayer.getTitleFormat(TitleType.ENABLED_SUBTITLE), 0, 40, 20);
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        AfkPlayer afkPlayer = AfkAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            AfkDisableEvent event = new AfkDisableEvent(e.getPlayer(), DisabledType.QUIT);
            Bukkit.getPluginManager().callEvent(event);
            if(event.isCancelled()) return;
            afkPlayer.setState(false);
            afkPlayer.resetTime();
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if(e.getEntity().getPlayer() == null)
            return;
        AfkPlayer afkPlayer = AfkAPI.getPlayer(e.getEntity().getPlayer());
        if(afkPlayer.isEnabled()) {
            AfkDisableEvent event = new AfkDisableEvent(e.getEntity().getPlayer(), DisabledType.DEATH);
            Bukkit.getPluginManager().callEvent(event);
            if(event.isCancelled()) return;
            afkPlayer.setState(false);
            afkPlayer.resetTime();
            e.getEntity().getPlayer().sendTitle(afkPlayer.getTitleFormat(TitleType.DISABLED_TITLE), afkPlayer.getTitleFormat(TitleType.ENABLED_SUBTITLE), 0, 40, 20);
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        AfkPlayer afkPlayer = AfkAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            AfkDisableEvent event = new AfkDisableEvent(e.getPlayer(), DisabledType.MOVEMENT);
            Bukkit.getPluginManager().callEvent(event);
            if(event.isCancelled()) return;
            afkPlayer.setState(false);
            afkPlayer.resetTime();
            e.getPlayer().sendTitle(afkPlayer.getTitleFormat(TitleType.DISABLED_TITLE), afkPlayer.getTitleFormat(TitleType.ENABLED_SUBTITLE), 0, 40, 20);
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        AfkPlayer afkPlayer = AfkAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            AfkDisableEvent event = new AfkDisableEvent(e.getPlayer(), DisabledType.CHAT);
            Bukkit.getPluginManager().callEvent(event);
            if(event.isCancelled()) return;
            afkPlayer.setState(false);
            afkPlayer.resetTime();
            e.getPlayer().sendTitle(afkPlayer.getTitleFormat(TitleType.DISABLED_TITLE), afkPlayer.getTitleFormat(TitleType.ENABLED_SUBTITLE), 0, 40, 20);
        } else {
            afkPlayer.resetReadyTime();
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        AfkPlayer afkPlayer = AfkAPI.getPlayer(e.getPlayer());
        if(afkPlayer.isEnabled()) {
            AfkDisableEvent event = new AfkDisableEvent(e.getPlayer(), DisabledType.COMMAND);
            Bukkit.getPluginManager().callEvent(event);
            if(event.isCancelled()) return;
            afkPlayer.setState(false);
            afkPlayer.resetTime();
            e.getPlayer().sendTitle(afkPlayer.getTitleFormat(TitleType.DISABLED_TITLE), afkPlayer.getTitleFormat(TitleType.ENABLED_SUBTITLE), 0, 40, 20);
        } else {
            afkPlayer.resetReadyTime();
        }
    }
}























