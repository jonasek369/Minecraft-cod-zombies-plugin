package me.jonas.zombies.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonas.zombies.Main;
import net.md_5.bungee.api.ChatColor;

public class Join implements CommandExecutor {
	private Main plugin;
	
	public Join(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("join").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (Main.players.contains(p)){
			p.sendMessage(ChatColor.RED + "You are already joined");
		}else{
			Main.players.add(p);
			p.sendMessage(ChatColor.GREEN + "You joined");
			p.getServer().broadcastMessage(ChatColor.DARK_PURPLE + p.getName() + " has joined !");
		}
		return false;
	}
}