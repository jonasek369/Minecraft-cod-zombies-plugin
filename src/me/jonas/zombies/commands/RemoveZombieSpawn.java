package me.jonas.zombies.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonas.zombies.Main;

public class RemoveZombieSpawn implements CommandExecutor {
	private Main plugin;
	
	public RemoveZombieSpawn(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("removezombiespawn").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		String SpawnName = args[0];
		p.sendMessage(""+Main.ZombieSpawns);
		if (Main.ZombieSpawns.containsKey(SpawnName) == true){
			p.sendMessage(""+Main.ZombieSpawns);
			Main.ZombieSpawns.remove(SpawnName);
			p.sendMessage(ChatColor.GREEN + "Removed zombies spawn with name " + SpawnName);
		}else{
			p.sendMessage(ChatColor.RED + "This zombie spawn dose not exist");
		}
		return false;
	
	}
		
}
	
	

