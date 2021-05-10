package me.jonas.zombies.economy.listeners;

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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
	
	// lists thru the list of all Materials in game and if in name is sword/age/bow it will add it to mysterybox list to use afterwards
	public void create_list(){
		Bukkit.getServer().broadcastMessage("created list of materials");
		for (Material mat : mats){
			if(mat.name().contains("SWORD") ||  mat.name().contains("AXE") || mat.name().contains("BOW")){
				mysterybox.add(mat);
			}
		}
	}
	
	// Function for MysteryBox that gives the player random weapon 
	public void give_random_weapon(Player p){
		Random rand = new Random();
		p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(mysterybox.get(rand.nextInt(mysterybox.size())), 1)); 
	}
	
	// eventhandler that sets off every time you right click at block afterwards it checks if list named mysterybox is empty if it is 
	// it fills it up with weapons in function create_list and then checks if the item you click was a sign
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
	        		  if(plugin.bank.get(p) != null) {
	        			  // this is the MysteryBox shop
	        			  if(lines[0].toLowerCase().contains("[mb]")){
	        				  if(plugin.bank.get(p) >= 900){
	        					  p.sendMessage(ChatColor.GREEN + "you purchased MysteryBox");
	        					  int base = plugin.bank.get(p);
	        					  plugin.bank.replace(p, base, base - 900);
	        					  give_random_weapon(p);
	        				  }else{
	        					  p.sendMessage(ChatColor.RED + "you dont have enoght points");
	        				  }
	        			}
	        			  // this is DoubleTap shop
	        			if(lines[0].toLowerCase().contains("[dt]")){
	        				if(plugin.bank.get(p) >= 2000){
	        					p.sendMessage(ChatColor.GREEN + "you purchased DoubleTap");
	        					p.addPotionEffect((new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 90000, 0)));
	        					int base = plugin.bank.get(p);
	        					plugin.bank.replace(p, base, base - 2000);
	        				}else{
	        					  p.sendMessage(ChatColor.RED + "you dont have enought points");
	        				  }
	        			}
	        			
	        			// this is stamina up shop
	        			if(lines[0].toLowerCase().contains("[sp]")){
	        				if(plugin.bank.get(p) >= 2000){
	        					p.sendMessage(ChatColor.GREEN + "you purchased Stamina up");
	        					p.addPotionEffect((new PotionEffect(PotionEffectType.SPEED, 90000, 0)));
	        					int base = plugin.bank.get(p);
	        					plugin.bank.replace(p, base, base - 2000);
	        				}else{
	        					  p.sendMessage(ChatColor.RED + "you dont have enought points");
	        				  }
	        			}
	        			
	        			// this is speed cola shop
	        			if(lines[0].toLowerCase().contains("[sc]")){
	        				if(plugin.bank.get(p) >= 3000){
	        					p.sendMessage(ChatColor.GREEN + "you purchased Speed cola");
	        					p.addPotionEffect((new PotionEffect(PotionEffectType.FAST_DIGGING, 90000, 1)));
	        					int base = plugin.bank.get(p);
	        					plugin.bank.replace(p, base, base - 3000);
	        				}else{
	        					  p.sendMessage(ChatColor.RED + "you dont have enought points");
	        				}
	        			}
	        			
	        			// this is Juggernog shop
	        			if(lines[0].toLowerCase().contains("[jg]")){
		        			if(plugin.bank.get(p) >= 2500){
		        				p.sendMessage(ChatColor.GREEN + "you purchased Juggernog");
		        				p.addPotionEffect((new PotionEffect(PotionEffectType.ABSORPTION, 90000, 0)));
		        				int base = plugin.bank.get(p);
		        				plugin.bank.replace(p, base, base - 2500);
		        			}else{
		        				p.sendMessage(ChatColor.RED + "you dont have enought points");
		        			}
	        			}  
	                 }
	        	 } 
	          }
	     }
	 }
}
