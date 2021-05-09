package me.jonas.zombies.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonas.zombies.Main;

public class Stopz implements CommandExecutor {
	private Main plugin;
	
	public Stopz(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("stopz").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(Main.start_running){
			p.sendMessage("stopping game");
			Main.start_running = false;
		}else{
			p.sendMessage("there are no game running");
		}
		return false;
	}
	
	

}