package me.jonas.zombies.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.jonas.zombies.Main;

public class OnZombieKill implements Listener{
	
	private Main plugin;
	
	public OnZombieKill(Main plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		if (e.getEntity().getCustomName() != null && e.getEntity().getType() == EntityType.ZOMBIE){
			Main.zombie_count--;
		}
	}
}


