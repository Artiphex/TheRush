package de.iron42.therush;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MapReset implements Listener {
	public static ArrayList<Location> Sandstone = new ArrayList<Location>();
	public static ArrayList<Location> Glowstone = new ArrayList<Location>();
	public static ArrayList<Location> Endstone = new ArrayList<Location>();
	public static ArrayList<Location> Brick = new ArrayList<Location>();
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Location loc = e.getBlock().getLocation();
		if (e.getBlock().getType() == Material.SANDSTONE) {
			Sandstone.add(loc);
		}
		
		if (e.getBlock().getType() == Material.GLOWSTONE) {
			Glowstone.add(loc);
		}
		
		if (e.getBlock().getType() == Material.ENDER_STONE) {
			Endstone.add(loc);
		}
		
		if (e.getBlock().getType() == Material.BRICK) {
			Brick.add(loc);
		}
	}
}
