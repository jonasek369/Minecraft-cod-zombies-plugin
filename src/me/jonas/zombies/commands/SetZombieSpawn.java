package me.jonas.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonas.zombies.Main;

public class SetZombieSpawn implements CommandExecutor {
	private Main plugin;
	
	public SetZombieSpawn(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("setzombiespawn").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args[0] == ""){
			p.sendMessage("you dint set the name of spawner");
			return false;
		}
		String SpawnName = args[0];
		Location SpawnLocation = p.getLocation();
		if (Main.ZombieSpawns.containsKey(SpawnName) == true){
			p.sendMessage(ChatColor.RED + "Zombies spawn with name " + SpawnName + " already exists");
		}else{
			Main.ZombieSpawns.put(SpawnName, SpawnLocation);
			p.sendMessage(ChatColor.GREEN + "Created zombie spawn with name " + SpawnName + " on Location" + SpawnLocation);
		}
		return false;
	}
}