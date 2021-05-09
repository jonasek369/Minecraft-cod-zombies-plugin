package me.jonas.zombies.economy.listeners;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.jonas.zombies.Main;
import net.md_5.bungee.api.ChatColor;

public class SignListener implements Listener {
	private Main plugin;
	
	ArrayList<Material> mysterybox = new ArrayList<>();
	Material[] mats = Material.values();
	
	public SignListener(Main plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public void create_list(){
		Bukkit.getServer().broadcastMessage("created list of materials");
		for (Material mat : mats){
			if(mat.name().contains("SWORD") ||  mat.name().contains("AXE") || mat.name().contains("BOW")){
				mysterybox.add(mat);
			}
		}
	}
	
	public void give_random_weapon(Player p){
		Random rand = new Random();
		p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(mysterybox.get(rand.nextInt(mysterybox.size())), 1)); 
	}
	
	 @EventHandler
	  public void onPlayerInteract(PlayerInteractEvent e) {
		 if(mysterybox.size() == 0){
			 create_list();
		 }
		 Player p = (Player) e.getPlayer();
	      if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
	          if (e.getClickedBlock().getType() == Material.OAK_WALL_SIGN || e.getClickedBlock().getType() == Material.OAK_SIGN) {
	        	  Sign s = (Sign) e.getClickedBlock().getState();
	        	  if(s.isPlaced()){
	        		  String[] lines = s.getLines();
	        		  if(lines[0].toLowerCase().contains("[mb]")){
	        			  if(plugin.bank.get(p) != null) {
	        				  if(plugin.bank.get(p) >= 900){
	        					  p.sendMessage(ChatColor.GREEN + "you purchased MysteryBox");
	        					  int base = plugin.bank.get(p);
	        					  plugin.bank.replace(p, base, base - 900);
	        					  give_random_weapon(p);
	        				  }else{
	        					  p.sendMessage(ChatColor.RED + "you dont have enoght points");
	        				  }
	        			 }
	                 }
	        	 }
	          }
	     }
	 }
}
