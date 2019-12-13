package net.blizzardcraft.ServerResources;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.MinecraftServer;

public class ServerResourcesCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			//Check if command sender has required permission(s) to run this command.
			if (!player.hasPermission("*") || !player.hasPermission("ServerResources.sr")) {
				//Execute if command sender does not have required permission(s)
				player.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
				return true;
			}
			//Continue if command sender has required permission(s)
			
			//Send 3 lines to the command sender about RAM usage.
			player.sendMessage("Max Ram: " + Runtime.getRuntime().maxMemory() / 1048576 + "MB");
			player.sendMessage("Used Ram: " + (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1048576 + "MB");
			player.sendMessage("Free Ram: " + Runtime.getRuntime().freeMemory() / 1048576 + "MB");
			
			//TPS copied from Spigot's TicksPerSecondCommand.java
			StringBuilder sb = new StringBuilder( ChatColor.GOLD + "TPS from last 1m, 5m, 15m: " );
			for (double tps : MinecraftServer.getServer().recentTps) {
				sb.append(format(tps));
				sb.append(", ");
			}
			//Send the command sender Spigot TPS results (same as doing /tps on spigot)
			player.sendMessage(sb.substring(0, sb.length() - 2));
			
			return true; //Return true to signify that the command was used successfully
		}
		return false; //Return false to signify that the command was used unsuccessfully
	}
	
	//Spigot's format method from TicksPerSecondCommand.java
	private String format(double tps)
    {
        return ( ( tps > 18.0 ) ? ChatColor.GREEN : ( tps > 16.0 ) ? ChatColor.YELLOW : ChatColor.RED ).toString()
                + ( ( tps > 20.0 ) ? "*" : "" ) + Math.min( Math.round( tps * 100.0 ) / 100.0, 20.0 );
    }
}