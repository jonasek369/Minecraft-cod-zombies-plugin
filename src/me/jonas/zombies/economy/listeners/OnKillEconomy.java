package me.jonas.zombies.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.jonas.zombies.Main;

public class OnKillEconomy implements Listener {
	
	private Main plugin;
	
	public OnKillEconomy(Main plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	void check_kill(EntityDeathEvent e, EntityType ent, int prize){
		if (e.getEntity().getKiller().getType() == EntityType.PLAYER && e.getEntity().getType() == ent){
			Player p = e.getEntity().getKiller();
			if (!plugin.bank.containsKey(p)){
				plugin.bank.put(p, 0);
			}
			int base_value = this.plugin.bank.get(p);
			plugin.bank.replace(p, base_value, base_value + prize);
		}
	}
		
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		if (e.getEntity().getCustomName() != null && e.getEntity().getType() == EntityType.ZOMBIE){
			Main.zombie_count--;
		}
		check_kill(e, EntityType.ZOMBIE, 100);
	}
}
