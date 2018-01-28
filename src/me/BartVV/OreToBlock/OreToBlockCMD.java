package me.BartVV.OreToBlock;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class OreToBlockCMD implements CommandExecutor {
	
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args){
	  if(cmd.getName().equalsIgnoreCase("block")){
		  if(!(cs instanceof Player)){
			  cs.sendMessage("Sender has to be a player!");
			  return true;
		  }
		  Player p = (Player)cs;
		  if(!p.hasPermission("orestoblock.true")){
			  p.sendMessage(OreToBlock.no_permission);
			  return true;
		  }
		  int coal = 0;
		  int diamond = 0;
		  int gold = 0;
		  int gold_nugget = 0;
		  int redstone = 0;
		  int iron = 0;
		  int quartz = 0;
		  int emerald = 0;
		  int lapis = 0;
		  int bonemeal = 0;
		  
		  for(int i = 0; i != p.getInventory().getSize(); i++){
			  try{
				  ItemStack is = p.getInventory().getItem(i);
				  if(is != null){
					  int amount = is.getAmount();
					  if(is.getType() == Material.COAL){
						  if(is.getData().getData() == (byte)0){
							  coal+= amount;
						  }
					  }
					  else if(is.getType() == Material.DIAMOND){
						  diamond+= amount;
					  }else if(is.getType() == Material.GOLD_INGOT){
						  gold+= amount;
					  }else if(is.getType() == Material.GOLD_NUGGET){
						  gold_nugget+= amount;
					  }else if(is.getType() == Material.REDSTONE){
						  redstone+= amount;
					  }else if(is.getType() == Material.IRON_INGOT){
						  iron+= amount;
					  }else if(is.getType() == Material.QUARTZ){
						  quartz+= amount;
					  }else if(is.getType() == Material.EMERALD){
						  emerald+= amount;
					  }else if(is.getType() == Material.INK_SACK){
						  if(is.getData().getData() == (byte)4){
							  lapis+= amount;  
						  }else if(is.getData().getData() == (byte)15){
							  bonemeal+= amount;
						  }
					  }
				  }
			  }catch(NullPointerException e){
				  continue;
			  }
		  } 
		  int total = 0;
		  boolean executed = false;
		  PlayerInventory inv = p.getInventory();

		  if(coal >= 9){
			  int block = coal / 9;
			  inv.removeItem(new ItemStack(Material.COAL, (block * 9)));
			  inv.addItem(new ItemStack(Material.COAL_BLOCK, block));
			  total= total + block;
		  }
		  if(diamond >= 9){
			  int block = diamond / 9;
			  inv.removeItem(new ItemStack(Material.DIAMOND, (block * 9)));
			  inv.addItem(new ItemStack(Material.DIAMOND_BLOCK, block));
			  total= total + block;
		  }
		  if(gold_nugget >= 9){
			  int amount = gold_nugget / 9;
			  inv.removeItem(new ItemStack(Material.GOLD_NUGGET, (amount * 9)));
			  inv.addItem(new ItemStack(Material.GOLD_INGOT, amount));
			  int ngold = gold + amount;
			  if(ngold >= 9){
				  int block = ngold / 9;
				  inv.removeItem(new ItemStack(Material.GOLD_INGOT, (block * 9)));
				  inv.addItem(new ItemStack(Material.GOLD_BLOCK, block));
				  total= total + block;
				  executed = true;
			  }else{
				  total= total + amount;
			  }
		  }
		  if(redstone >= 9){
			  int block = redstone / 9;
			  inv.removeItem(new ItemStack(Material.REDSTONE, (block * 9)));
			  inv.addItem(new ItemStack(Material.REDSTONE_BLOCK, block));
			  total= total + block;
		  }
		  if(gold >= 9 && executed == false){
			  int block = gold / 9;
			  inv.removeItem(new ItemStack(Material.GOLD_INGOT, (block * 9)));
			  inv.addItem(new ItemStack(Material.GOLD_BLOCK, block));
			  total= total + block;
		  }
		  if (iron >= 9){
			  int block = iron / 9;
			  inv.removeItem(new ItemStack(Material.IRON_INGOT, (block * 9)));
			  inv.addItem(new ItemStack(Material.IRON_BLOCK, block));
			  total= total +  block;
		  }if (quartz >= 9){
			  int block = quartz / 9;
			  inv.removeItem(new ItemStack(Material.QUARTZ, (block * 9)));
			  inv.addItem(new ItemStack(Material.QUARTZ_BLOCK, block));
			  total= total +  block;
		  }if (emerald >= 9){
			  int block = emerald / 9;
			  inv.removeItem(new ItemStack(Material.EMERALD, (block * 9)));
			  inv.addItem(new ItemStack(Material.EMERALD_BLOCK, block));
			  total= total +  block;
		  }if (lapis >= 9){
			  int block = lapis / 9;
			  inv.removeItem(new ItemStack(Material.INK_SACK, (block * 9), (byte)4));
			  inv.addItem(new ItemStack(Material.LAPIS_BLOCK, block));
			  total= total +  block;
		  }
		  try{
			  if(bonemeal >= 9){
				  int block = bonemeal / 9;
				  inv.removeItem(new ItemStack(Material.INK_SACK, (block * 9), (byte)15));
				  inv.addItem(new ItemStack(Material.BONE_BLOCK, block));
				  total= total + block;
			  }
		  }catch(NoSuchFieldError e){ /* No Stacktrace because it is to prevent erros to occure in 1.9 or lower because bone blocks weren't added*/}
		  if(total == 0){
			  p.sendMessage(OreToBlock.no_minirals);
			  executed = false;
			  return true;
		  }else{
			  p.sendMessage(OreToBlock.convert.replace("{AMOUNT}", "" + total));
			  executed = false;
			  return true;
		  }
	  }
	return false;
  }
}
