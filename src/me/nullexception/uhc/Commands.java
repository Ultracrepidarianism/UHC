package me.nullexception.uhc;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
	public main instance = main.instance;
	@Override
	public boolean onCommand(CommandSender sender, Command command , String label, String[] args) {
		if(label.equalsIgnoreCase("uhc")) {
			if(args[0].equalsIgnoreCase("start")){
				instance.gameStarted = true;
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers 0 0 300 " + instance.borderSize + " true @a");
				return true;
			}
			if(args[0].equalsIgnoreCase("setbordersize")) {
				if(args.length == 2) {
					instance.borderSize = Integer.parseInt(args[1]);
				return true;					
				}
			}
			if(args[0].equalsIgnoreCase("setborderdamage")) {
				return true;
			}
			if(args[0].equalsIgnoreCase("reducebordersize")) {
				return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

}
