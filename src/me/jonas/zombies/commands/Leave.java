package me.jonas.zombies.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonas.zombies.Main;
 

public class Leave implements CommandExecutor {
	private Main plugin;
	
	public Leave(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("leave").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (Main.players.contains(p)){
			Main.players.remove(p);
			p.sendMessage(ChatColor.GREEN + "You just leaved the game");
			p.getServer().broadcastMessage(ChatColor.DARK_PURPLE + p.getName() + " just left !");
		}else{
			p.sendMessage(ChatColor.RED + "You are not in any game");
		}
		return false;
	}
}