package de.iron42.therush;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener{
	@EventHandler
	public void on(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if ((TheRush.Status == GameStatus.Game) || (TheRush.Status == GameStatus.Restarting)) {
			e.disallow(null, "§8[§6TheRush§8] §7The game is already ingame!");
		}
	}
}
