package de.iron42.therush;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R3.EntityPlayer;
import net.minecraft.server.v1_7_R3.EnumClientCommand;
import net.minecraft.server.v1_7_R3.PacketPlayInClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class AutoRespawn implements Listener {
	public static void autoRespawn(final Player player) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(TheRush.plugin,
				new Runnable() {
					public void run() {
						try {
							EntityPlayer ePlayer = ((CraftPlayer) player)
									.getHandle();
							PacketPlayInClientCommand packet = new PacketPlayInClientCommand();

							Field field = PacketPlayInClientCommand.class
									.getDeclaredField("a");
							field.setAccessible(true);
							field.set(packet, EnumClientCommand.PERFORM_RESPAWN);
							ePlayer.playerConnection.a(packet);
						} catch (Exception e) {
							TheRush.plugin.getLogger().warning(
									"Eventuell falsche Minecraft Version? "
											+ e.getMessage());
						}
					}
				}, 20L);
	}

	@EventHandler
	public void On(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		final Location green = (new Location(Bukkit.getWorld("world"), -1, 53,
				-60));
		final Location orange = (new Location(Bukkit.getWorld("world"), -1, 53,
				59));

		if (green.getBlock().getType() == Material.STAINED_CLAY) {
			Location loc = (new Location(Bukkit.getWorld("world"), -1, 54.5,
					-60));
			e.setRespawnLocation(loc);
		}

		if (orange.getBlock().getType() == Material.STAINED_CLAY) {
			Location loc = (new Location(Bukkit.getWorld("world"), -1, 54.5, 59));
			e.setRespawnLocation(loc);
		}
		// Location loc = p.getLocation();
		// e.setRespawnLocation(loc);
	}
}
