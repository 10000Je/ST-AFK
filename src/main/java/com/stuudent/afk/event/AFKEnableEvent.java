package com.stuudent.afk.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AFKEnableEvent extends Event implements Cancellable {

    public boolean cancelled;
    public Player afkPlayer;
    public static HandlerList handlers;

    static {
        handlers = new HandlerList();
    }

    public AFKEnableEvent(Player afkPlayer) {
        this.afkPlayer = afkPlayer;
    }

    public Player getPlayer() {
        return this.afkPlayer;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
