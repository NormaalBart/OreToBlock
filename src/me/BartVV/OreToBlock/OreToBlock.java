package me.BartVV.OreToBlock;

import org.bukkit.plugin.java.JavaPlugin;

public class OreToBlock extends JavaPlugin{
	
	protected static Boolean start = true;
	protected static String convert = "§aYou succesfully converted {AMOUNT}!";
	protected static String no_minirals = "§cTheir weren't any minirals to convert!";
	protected static String no_permission = "§cAcces denied!";
	
	public void onEnable(){
		saveDefaultConfig();
		getCommand("block").setExecutor(new OreToBlockCMD());
		convert = getConfig().getString("convert").replace("&", "§");
		no_minirals = getConfig().getString("no_minirals").replace("&", "§");
		no_permission = getConfig().getString("no_permission").replace("&", "§");
	}
	
	
}
