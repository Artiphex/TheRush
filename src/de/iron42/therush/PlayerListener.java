package de.iron42.therush;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener{
	public static ArrayList<Player> Spieler = new ArrayList<Player>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p= e.getPlayer();
		p.getInventory().clear();
		e.setJoinMessage("§8[§6TheRush§8] " +p.getName()+" §7joined the game. §8>§a" + Bukkit.getOnlinePlayers().length + "/24§8<" );
		Spieler.add(p);
		p.teleport(new Location(Bukkit.getWorld("world"), 0.5, 58.5, 0.5));
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p= e.getPlayer();
		e.setQuitMessage("§8[§6TheRush§8] " +p.getName() + "§7left the game. §8>§a" + (Bukkit.getOnlinePlayers().length - 1) + "/24§8<");
		Spieler.remove(p);

	}

}
