package com.stuudent.afk.event;

import com.stuudent.afk.enums.DisabledType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AfkDisableEvent extends Event implements Cancellable {

    public boolean cancelled;
    public DisabledType reason;
    public Player afkPlayer;
    public static HandlerList handlers;

    static {
        handlers = new HandlerList();
    }

    public AfkDisableEvent(Player afkPlayer, DisabledType reason) {
        this.afkPlayer = afkPlayer;
        this.reason = reason;
    }

    public DisabledType getReason() {
        return this.reason;
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
