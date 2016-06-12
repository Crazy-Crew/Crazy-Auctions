package me.badbones69.crazyauctions;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CrazyAuctions {
	static CrazyAuctions instance = new CrazyAuctions();
	
	public static CrazyAuctions getInstance() {
		return instance;
	}
	
	public ArrayList<ItemStack> getItems(Player player){
		FileConfiguration data = Main.settings.getData();
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		if(data.contains("Items")){
			for(String i : data.getConfigurationSection("Items").getKeys(false)){
				items.add(data.getItemStack("Items."+i+".Item").clone());
			}
		}
		return items;
	}
	
	public ArrayList<ItemStack> getItems(Player player, Shop type){
		FileConfiguration data = Main.settings.getData();
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		if(data.contains("Items")){
			for(String i : data.getConfigurationSection("Items").getKeys(false)){
				if(data.getBoolean("Items."+i+".Biddable")){
					if(type==Shop.BID){
						items.add(data.getItemStack("Items."+i+".Item").clone());
					}
				}else{
					if(type==Shop.SELL){
						items.add(data.getItemStack("Items."+i+".Item").clone());
					}
				}
			}
		}
		return items;
	}
}