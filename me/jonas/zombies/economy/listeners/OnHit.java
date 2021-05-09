package me.jonas.zombies.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.jonas.zombies.Main;
import net.md_5.bungee.api.ChatColor;

public class OnHit implements Listener {
	
	private Main plugin;
	
	public OnHit(Main plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent e){
		// this dosent give point by hit
		if (e.getEntity().getType() == EntityType.ZOMBIE && e.getDamager().getType() == EntityType.PLAYER) {
	            Player p = (Player) e.getDamager();
	            boolean isinlist = false;
	            for(Player player: Main.players){
	            	if(player == p){
	            		isinlist = true;
	            		break;
	            	}else{
	            		continue;
	            	}
	            }
	            if(!isinlist){
	            	return;
	            }
	            if (!plugin.bank.containsKey(p)){
					plugin.bank.put(p, 0);
				}
				int base_value = this.plugin.bank.get(p);
				plugin.bank.replace(p, base_value, base_value + 10);
	        }
	}
	
}
