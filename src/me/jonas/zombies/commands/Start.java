package me.jonas.zombies.commands;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.jonas.zombies.Main;
import net.md_5.bungee.api.ChatColor;

public class Start implements CommandExecutor {
	private Main plugin;
	
	public Start(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("start").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		BukkitScheduler sched = p.getServer().getScheduler();
		if (Main.players.isEmpty()){
			p.sendMessage(ChatColor.RED + "cant start there are no players joined");
			return false;
		}
		if (Main.ZombieSpawns.isEmpty()){
			p.sendMessage(ChatColor.RED + "cant start there are no zombie spawners placed");
			return false;
		}
		if (Main.start_running == true){
			p.sendMessage(ChatColor.RED + "arena is already running");
			return false;
		}
		Main.start_running = true;
		
		plugin.bank.clear();
		for(Player players: Main.players){
			if(plugin.bank.get(players) == null){
				plugin.bank.put(players, 0);
			}

			set_boots(players, new ItemStack(Material.LEATHER_BOOTS));
			set_chestplate(players, new ItemStack(Material.LEATHER_CHESTPLATE));
			set_legs(players, new ItemStack(Material.LEATHER_LEGGINGS));
			set_helmer(players, new ItemStack(Material.LEATHER_HELMET));
			set_sword(players, new ItemStack(Material.STONE_SWORD));
		}
		for(Player player: Main.players){SB(player);}

		sched.scheduleSyncRepeatingTask(this.plugin, new Runnable(){
			@Override
			public void run() {
				if (!Main.start_running){
					for(Player player: Main.players){player.setScoreboard(null);}
					p.sendMessage("stopping start command !Main.start_running");
					sched.cancelTasks(plugin);
				}
				if(Main.zombie_count <= 0){
					Main.wave++;
					spawn_zombies();
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "Wave " + Main.wave + " incomming !");
				}
				for(Player p: Main.players){updateSB(p);}
			}}, 0L, 20L);
		
		return true;
	
	}
	public void set_helmer(LivingEntity e, ItemStack helmet){
		EntityEquipment ee = e.getEquipment();
	    ee.setHelmet(helmet);
	}
	
	public void set_chestplate(LivingEntity e, ItemStack chestplate){
		EntityEquipment ee = e.getEquipment();
	    ee.setChestplate(chestplate);
	}

	public void set_legs(LivingEntity e, ItemStack legs){
		EntityEquipment ee = e.getEquipment();
	    ee.setLeggings(legs);
	}

	public void set_boots(LivingEntity e, ItemStack boots){
		EntityEquipment ee = e.getEquipment();
	    ee.setBoots(boots);
	}
	
	public void set_sword(LivingEntity e, ItemStack sword){
		EntityEquipment ee = e.getEquipment();
	    ee.setItemInMainHand(sword);
	}
	

	
	public void SB(Player p){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("title", "dummy", ChatColor.RED + "[CoD] zombies");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        Score money = objective.getScore(ChatColor.GRAY + "» Money");
        money.setScore(13);

        Team moneyCounter = scoreboard.registerNewTeam("moneyCounter");
        moneyCounter.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        moneyCounter.setPrefix(ChatColor.GREEN + "$" + plugin.bank.get(p));
        objective.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(12);
        
        p.setScoreboard(scoreboard);
	}
	
	public void updateSB(Player p){
		Scoreboard board = p.getScoreboard();
		board.getTeam("moneyCounter").setPrefix(ChatColor.GREEN + "$" + plugin.bank.get(p));
	}
	
	
	
	
	public void spawn_zombies(){
		int cap_limit = 24 + 6 * (Main.players.size() - 1);
		double z = 0.15 * Main.wave * cap_limit;
		int spawned = 0;
		while (spawned <= Math.round(z)){
			for (Map.Entry<String, Location> entry: Main.ZombieSpawns.entrySet()){
				if(spawned >= Math.round(z)){
					Main.zombie_count = spawned;
					return;
				}
				Zombie zombie = (Zombie) entry.getValue().getWorld().spawnEntity(entry.getValue(), EntityType.ZOMBIE);
				zombie.setCustomName("[CoD] zombie");
				if(Main.wave < 5){
					set_sword(zombie, new ItemStack(Material.STICK));
					spawned++;
					continue;
				}
				if(Main.wave < 10){
					set_sword(zombie, new ItemStack(Material.WOODEN_SWORD));
					spawned++;
					continue;
				}
				if(Main.wave < 15){
					set_sword(zombie, new ItemStack(Material.GOLDEN_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					spawned++;
					continue;
				}
				if(Main.wave < 20){
					set_sword(zombie, new ItemStack(Material.GOLDEN_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					set_legs(zombie, new ItemStack(Material.LEATHER_LEGGINGS));
					spawned++;
					continue;
					
				}
				if(Main.wave < 25){
					set_sword(zombie, new ItemStack(Material.IRON_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					set_legs(zombie, new ItemStack(Material.LEATHER_LEGGINGS));
					spawned++;
					continue;
				}
				if(Main.wave < 30){
					set_sword(zombie, new ItemStack(Material.IRON_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					set_legs(zombie, new ItemStack(Material.LEATHER_LEGGINGS));
					spawned++;
					continue;
				}
				if(Main.wave < 35){
					set_helmer(zombie, new ItemStack(Material.LEATHER_HELMET));
					set_sword(zombie, new ItemStack(Material.IRON_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					set_legs(zombie, new ItemStack(Material.LEATHER_LEGGINGS));
					spawned++;
					continue;
				}
				if(Main.wave < 40){
					set_helmer(zombie, new ItemStack(Material.IRON_HELMET));
					set_sword(zombie, new ItemStack(Material.IRON_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					set_legs(zombie, new ItemStack(Material.LEATHER_LEGGINGS));
					spawned++;
					continue;
				}
				if(Main.wave < 45){
					set_helmer(zombie, new ItemStack(Material.IRON_HELMET));
					set_sword(zombie, new ItemStack(Material.IRON_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					set_legs(zombie, new ItemStack(Material.IRON_LEGGINGS));
					set_boots(zombie, new ItemStack(Material.IRON_BOOTS));
					spawned++;
					continue;
				}
				if(Main.wave < 50){
					set_helmer(zombie, new ItemStack(Material.IRON_HELMET));
					set_sword(zombie, new ItemStack(Material.DIAMOND_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					set_legs(zombie, new ItemStack(Material.IRON_LEGGINGS));
					set_boots(zombie, new ItemStack(Material.IRON_BOOTS));
					spawned++;
					continue;
				}
				if(Main.wave < 55){
					set_helmer(zombie, new ItemStack(Material.DIAMOND_HELMET));
					set_sword(zombie, new ItemStack(Material.DIAMOND_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					set_legs(zombie, new ItemStack(Material.IRON_LEGGINGS));
					set_boots(zombie, new ItemStack(Material.IRON_BOOTS));
					spawned++;
					continue;
				}
				if(Main.wave < 60){
					set_helmer(zombie, new ItemStack(Material.DIAMOND_HELMET));
					set_sword(zombie, new ItemStack(Material.DIAMOND_SWORD));
					set_chestplate(zombie, new ItemStack(Material.IRON_CHESTPLATE));
					set_legs(zombie, new ItemStack(Material.IRON_LEGGINGS));
					set_boots(zombie, new ItemStack(Material.IRON_BOOTS));
					spawned++;
					continue;
				}
			}
		}
	}
}