package com.badbones69.crazyauctions.tasks.objects;

import com.badbones69.crazyauctions.Methods;
import com.badbones69.crazyauctions.api.builders.ItemBuilder;
import com.badbones69.crazyauctions.api.enums.ShopType;
import com.badbones69.crazyauctions.api.enums.misc.Files;
import com.badbones69.crazyauctions.api.enums.misc.Keys;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@SuppressWarnings("UnusedAssignment")
public class Auction {

    private final UUID uuid;
    private final String name;

    private final String id;

    private final String store_id;
    private final ItemStack itemStack;
    private final long price;
    private final long time_till_expire;
    private final long full_expire;

    private final String bidder_uuid;
    private final String bidder_name;
    private final boolean isBiddable;

    public Auction(final String uuid, final String name, final String id, final String item, final String store_id, final long price, final long time_till_expire, final long full_expire, final String bidder_uuid, final String bidder_name, final boolean biddable) {
        this.uuid = UUID.fromString(uuid);
        this.name = name;
        this.id = id;

        this.itemStack = Methods.fromBase64(item);

        this.store_id = store_id;
        this.price = price;

        this.time_till_expire = time_till_expire;
        this.full_expire = full_expire;

        this.bidder_uuid = bidder_uuid;
        this.bidder_name = bidder_name;
        this.isBiddable = biddable;
    }

    public final UUID getUuid() {
        return this.uuid;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getStoreID() {
        return this.store_id;
    }

    public final long getPrice() {
        return this.price;
    }

    public final long getTimeTillExpire() {
        return this.time_till_expire;
    }

    public final long getFullExpire() {
        return this.full_expire;
    }

    public final String getBidderUUID() {
        return this.bidder_uuid;
    }

    public final String getBidderName() {
        return this.bidder_name;
    }

    public final boolean isBiddable() {
        return this.isBiddable;
    }

    public final ItemBuilder getItemBuilder(final ShopType shopType) {
        final FileConfiguration configuration = Files.config.getConfiguration();

        final ItemBuilder itemBuilder = ItemBuilder.convertItemStack(this.itemStack.clone());

        final String priceFormat = String.format(Locale.ENGLISH, "%,d", this.price);

        final String time = Methods.convertToTime(getTimeTillExpire());

        final List<String> lore = new ArrayList<>(itemBuilder.getUpdatedLore());

        lore.add(" ");

        if (shopType == ShopType.BID && isBiddable()) {
            for (final String line : configuration.getStringList("Settings.GUISettings.Bidding")) {
                String newLine = line.replace("%TopBid%", priceFormat)
                        .replace("%topbid%", priceFormat);

                newLine = line.replace("%Seller%", getName())
                        .replace("%seller%", getName());

                newLine = line.replace("%TopBidder%", getBidderName())
                        .replace("%topbid%", getBidderName());

                lore.add(newLine.replace("%Time%", time)
                        .replace("%time%", time));
            }
        } else {
            for (final String line : configuration.getStringList("Settings.GUISettings.SellingItemLore")) {
                String newLine = line.replace("%TopBid%", priceFormat)
                        .replace("%topbid%", priceFormat);

                newLine = line.replace("%Seller%", getName())
                        .replace("%seller%", getName());

                lore.add(newLine.replace("%Time%", time)
                        .replace("%time%", time)
                        .replace("%price%", priceFormat).replace("%Price%", priceFormat));
            }
        }

        itemBuilder.setLore(lore)
                .addString(getStoreID(), Keys.auction_store_id.getNamespacedKey());

        return itemBuilder;
    }

    public final ItemBuilder getItemBuilder() {
        final FileConfiguration configuration = Files.config.getConfiguration();

        final ItemBuilder itemBuilder = ItemBuilder.convertItemStack(this.itemStack.clone());

        final String priceFormat = String.format(Locale.ENGLISH, "%,d", this.price);

        final String time = Methods.convertToTime(getTimeTillExpire());

        final List<String> lore = new ArrayList<>(itemBuilder.getUpdatedLore());

        lore.add(" ");

        for (final String line : configuration.getStringList("Settings.GUISettings.CurrentLore")) {
            lore.add(line.replace("%Time%", time).replace("%time%", time)
                    .replace("%price%", priceFormat)
                    .replace("%Price%", priceFormat));
        }

        itemBuilder.setLore(lore)
                .addString(getStoreID(), Keys.auction_store_id.getNamespacedKey());;

        return itemBuilder;
    }
}