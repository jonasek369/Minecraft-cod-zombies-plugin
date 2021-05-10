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

public class Main extends JavaPlugin{
	public static HashMap<String, Location> ZombieSpawns = new HashMap<String, Location>();
	
	public static int wave = 0;
	public static int zombie_count = 0;
	
	public HashMap<Player, Integer> bank = new HashMap<Player, Integer>();
	public static List<Player> players = new ArrayList<>();
	public static boolean start_running = false;
	
	@Override
	public void onEnable(){
		// start of the game
		new Start(this);
		// stop of the game it stopz because if it was only stop it would stop the server
		new Stopz(this);
		// joins the player into game
		new Join(this);
		// leaves the player from game
		new Leave(this);
		// sets zombie spawn with name
		new SetZombieSpawn(this);
		// removes zombie spawn with certain name
		new RemoveZombieSpawn(this);
		// checks every hit of entity
		new OnHit(this);
		// checks every death of entity
		new OnKillEconomy(this);
		// shows you balance
		new Balance(this);
		// listens for right click event this is the sign shop also
		new SignListener(this);
	}

}


