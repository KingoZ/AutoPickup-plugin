package me.autopickup;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	private Main instance;
	public Main getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance=this;
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		List<String> disabledWorld = getConfig().getStringList("Disabled-Worlds");
		String wdName = player.getWorld().getName();
		if (!disabledWorld.contains(wdName)) {
			Collection<ItemStack> items = e.getBlock().getDrops();
			for (ItemStack itens : items) {
				player.getInventory().addItem(itens);
			}
			e.getBlock().setType(Material.AIR);
			e.getBlock().getDrops().clear();
		}
	}
	

}
