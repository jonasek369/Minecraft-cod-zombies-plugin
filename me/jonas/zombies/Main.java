package me.jonas.zombies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

import me.jonas.zombies.commands.Balance;
import me.jonas.zombies.commands.Join;
import me.jonas.zombies.commands.Leave;
import me.jonas.zombies.commands.RemoveZombieSpawn;
import me.jonas.zombies.commands.SetZombieSpawn;
import me.jonas.zombies.commands.Start;
import me.jonas.zombies.commands.Stopz;
import me.jonas.zombies.economy.listeners.OnHit;
import me.jonas.zombies.economy.listeners.OnKillEconomy;
import me.jonas.zombies.economy.listeners.SignListener;
import me.jonas.zombies.listeners.OnZombieKill;

public class Main extends JavaPlugin{
	public static HashMap<String, Location> ZombieSpawns = new HashMap<String, Location>();
	
	public static int wave = 0;
	public static int zombie_count = 0;
	
	public HashMap<Player, Integer> bank = new HashMap<Player, Integer>();
	public static List<Player> players = new ArrayList<>();
	public static boolean start_running = false;
	
	@Override
	public void onEnable(){
		new Start(this);
		new Stopz(this);
		new Join(this);
		new Leave(this);
		new SetZombieSpawn(this);
		new RemoveZombieSpawn(this);
		new OnZombieKill(this);
		new OnHit(this);
		new OnKillEconomy(this);
		new Balance(this);
		new SignListener(this);
	}

}


