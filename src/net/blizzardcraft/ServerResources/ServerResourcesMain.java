package net.blizzardcraft.ServerResources;

import org.bukkit.plugin.java.JavaPlugin;

public class ServerResourcesMain extends JavaPlugin {
	// When the plugin is enabled
	@Override
	public void onEnable() {
		this.getCommand("sr").setExecutor(new ServerResourcesCommand());
	}
	// When the plugin is disabled
	@Override
	public void onDisable() {
		
	}
}