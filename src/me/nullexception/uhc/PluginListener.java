package me.nullexception.uhc;

import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PluginListener implements Listener {

	public void onJoin(PlayerJoinEvent e){
		e.getPlayer().teleport(new Location(e.getPlayer().getWorld(),0,150,0));
	}
}
