package me.nullexception.uhc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class main extends JavaPlugin {
	public Boolean gameStarted = false;
	public Boolean pvp = false;
	public int pvptimer = 600;
	public Scoreboard board;
	public Objective objective;
	public static main instance;
	public int borderSize = 5000;
	
	public void onEnable() {
		
		instance = this;
		for( World world : getServer().getWorlds()) {
			world.getWorldBorder().setSize(borderSize);
		}
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		objective = board.registerNewObjective("UHC", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.GOLD + "UHC");
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new PluginListener(), this);
		getCommand("uhc").setExecutor(new Commands());
	}

}
