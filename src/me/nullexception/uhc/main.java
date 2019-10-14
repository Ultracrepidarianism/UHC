package me.nullexception.uhc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new PluginListener(), this);
		for( World world : getServer().getWorlds()) {
			for(int i = 0; i <= 50; i++)
			{
				for (int j = 0; j <= 50; j++) {
					Block spawnBarrier = world.getBlockAt(new Location(world,(-25 + i),150,(-25 + j)));
					spawnBarrier.setType(Material.BARRIER);
				}
			}
		}
	}

}
