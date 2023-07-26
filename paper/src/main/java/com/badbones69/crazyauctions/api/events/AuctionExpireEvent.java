package com.badbones69.crazyauctions.api.events;

import com.badbones69.crazyauctions.CrazyAuctions;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

/**
 * Description: This event is fired when an auction expires.
 */
public class AuctionExpireEvent extends Event {

    private final CrazyAuctions plugin = JavaPlugin.getPlugin(CrazyAuctions.class);

    private static final HandlerList handlerList = new HandlerList();

    private final UUID uuid;
    private final Player player;

    private final ItemStack item;

    /**
     * A constructor to include values for when an item expired.
     *
     * @param uuid the uuid of the player whose auction expired.
     */
    public AuctionExpireEvent(UUID uuid, ItemStack item) {
        this.uuid = uuid;

        this.player = this.plugin.getServer().getPlayer(uuid);

        this.item = item;
    }

    public ItemStack getExpiredItem() {
        return this.item;
    }

    public Player getPlayer() {
        return this.player;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
}