package me.jonas.zombies.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonas.zombies.Main;
import net.md_5.bungee.api.ChatColor;

public class Balance implements CommandExecutor {
	private Main plugin;
	
	public Balance(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("balance").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (plugin.bank.get(p) == null){
			p.sendMessage(ChatColor.RED + "you cant use this command");
			return false;
		}
		p.sendMessage(ChatColor.GREEN + "your points : " + plugin.bank.get(p));
		return false;
	}
}
