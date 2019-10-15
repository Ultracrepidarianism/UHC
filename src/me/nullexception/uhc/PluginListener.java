package me.nullexception.uhc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Score;

public class PluginListener implements Listener{
public Boolean spawnBuilt = false;
public main instance = main.instance;
	
public List<Player> listPly = new ArrayList<Player>();
@EventHandler
public void PlayerJoin(PlayerJoinEvent e){
	if(!spawnBuilt)
	{
		Block spawnBarrier = null;
		for(int i = 0; i <= 50; i++)
		{
			for (int j = 0; j <= 50; j++) {
				spawnBarrier = e.getPlayer().getWorld().getBlockAt(new Location(e.getPlayer().getWorld(),(-25 + i),150,(-25 + j)));
				spawnBarrier.setType(Material.BARRIER);
				spawnBarrier = e.getPlayer().getWorld().getBlockAt(new Location(e.getPlayer().getWorld(),(-25 + i),154,(-25 + j)));
				spawnBarrier.setType(Material.BARRIER);
			}
		}
		
		for (int i = 0; i <= 50; i++) {
			for(int y=0;y <= 3; y++) {
				spawnBarrier = e.getPlayer().getWorld().getBlockAt(new Location(e.getPlayer().getWorld(),(-25 + i),(151 + y),-25));
				spawnBarrier.setType(Material.BARRIER);
				spawnBarrier = e.getPlayer().getWorld().getBlockAt(new Location(e.getPlayer().getWorld(),-25,(151 + y),(-25 + i)));
				spawnBarrier.setType(Material.BARRIER);
				spawnBarrier = e.getPlayer().getWorld().getBlockAt(new Location(e.getPlayer().getWorld(),(-25 + i),(151 + y),25));
				spawnBarrier.setType(Material.BARRIER);
				spawnBarrier = e.getPlayer().getWorld().getBlockAt(new Location(e.getPlayer().getWorld(),25,(151 + y),(-25 + i)));
				spawnBarrier.setType(Material.BARRIER);
			}
		}
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder center 0 0");
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 5000");
		spawnBuilt = true;
	}
	if(!instance.gameStarted && !listPly.contains(e.getPlayer()))
	{
		e.getPlayer().setGameMode(GameMode.SURVIVAL);
		e.getPlayer().teleport(new Location(e.getPlayer().getWorld(),0,151,0));
		listPly.add(e.getPlayer());
	}
	if(instance.gameStarted && !listPly.contains(e.getPlayer())) {
		e.getPlayer().setGameMode(GameMode.SPECTATOR);
	}
Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {		
		@Override
		public void run() {
			Score score4;
			Score score3;
			int dayTimer = 0;
			if(!instance.gameStarted) {
				score3 = instance.objective.getScore(ChatColor.BLUE + "PvP: " + ChatColor.WHITE + "10:00" );
				score3.setScore(2);
				score4 = instance.objective.getScore(ChatColor.BLUE + "Day 1");
				score4.setScore(4);
			}
			else {
				instance.board.resetScores(ChatColor.BLUE + "Day 1");
				instance.board.resetScores(ChatColor.BLUE + "PvP: " + ChatColor.WHITE + "10:00" );
				instance.board.resetScores(ChatColor.BLUE + "Day " + (dayTimer/24000 + 1));
				dayTimer++;
				score4 = instance.objective.getScore(ChatColor.BLUE + "Day " + (dayTimer/24000 + 1));
				score4.setScore(4);
			}
			if(instance.gameStarted && !instance.pvp) {
				instance.board.resetScores(ChatColor.BLUE + "PvP: " + instance.pvptimer/60 + ":" + instance.pvptimer%60);
				instance.pvptimer--;
				if(instance.pvptimer == 0) {
					score3 = instance.objective.getScore(ChatColor.BLUE + "PvP: " + ChatColor.GREEN + "Enabled" );
					score3.setScore(2);
					instance.pvp = true;
				}
				score3 = instance.objective.getScore(ChatColor.BLUE + "PvP: " + ChatColor.WHITE + instance.pvptimer%60 + ":" + instance.pvptimer/60);
				score3.setScore(2);						
			}		
			Score score = instance.objective.getScore(ChatColor.BLUE + "Border: " + ChatColor.WHITE + e.getPlayer().getWorld().getWorldBorder().getSize()/2 );
			score.setScore(1);
			Score score2 = instance.objective.getScore( ChatColor.BLUE + "Players:" + ChatColor.WHITE + Bukkit.getServer().getOnlinePlayers().size());
			score2.setScore(3);
			e.getPlayer().setScoreboard(instance.board);
			e.getPlayer().sendMessage("Is this every second ?");
		}
	}, 0, 20);



}

@EventHandler
public void CheckStarve(FoodLevelChangeEvent e) {
	if(!instance.gameStarted) {
		e.setCancelled(true);
	}
}

@EventHandler
public void onPvP(EntityDamageByEntityEvent e){
	if(!instance.pvp) {
		if(e.getEntity() instanceof Player){
			if(e.getDamager() instanceof Player){
				e.setCancelled(true);
			}
		}		
	}
}

}
