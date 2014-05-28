package de.iron42.therush;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.iron42.therush.GameStatus;
import de.iron42.therush.PlayerListener;

public class TheRush extends JavaPlugin {
	public int Timer = 0;
	public static GameStatus Status;

	public static ArrayList<String> green = new ArrayList<String>();
	public static ArrayList<String> orange = new ArrayList<String>();

	public void onDisable() {
		System.out.println("[TheRush] disabled");
	}

	public void onEnable() {
		System.out.println("[TheRush] enabled");
		System.out.println("[TheRush] Coded by iron42");

		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new LoginListener(), this);
		pm.registerEvents(new AutoRespawn(), this);
		pm.registerEvents(new Listeners(), this);
		pm.registerEvents(new LoginListener(), this);
		pm.registerEvents(new PlayerListener(), this);

		this.Timer = 91;
		this.Status = GameStatus.Lobby;
		StartCounter();

		plugin = this;

	}

	public void StartCounter() {
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(this, new Runnable() {
					public void run() {
						Timer -= 1;
						Bukkit.clearRecipes();
						if (Status == GameStatus.Lobby) {
							WorldPvP(false);
							if ((Timer == 90) || (Timer == 80) || (Timer == 70)
									|| (Timer == 60) || (Timer == 50)
									|| (Timer == 40) || (Timer == 30)
									|| (Timer == 20) || (Timer == 10)
									|| (Timer == 5) || (Timer == 4)
									|| (Timer == 3) || (Timer == 2)) {
								Bukkit.broadcastMessage("§8[§6TheRush§8] §7The lobby ends in §e"
										+ Timer + " §7seconds.");
							} else if (Timer == 1) {
								Bukkit.broadcastMessage("§8[§6TheRush§8] §7The lobby ends in §e"
										+ Timer + " §7second.");
							} else if (Timer == 0) {
								if (Bukkit.getOnlinePlayers().length <= 2) {
									Bukkit.broadcastMessage("§8[§6TheRush§8] §cThere are not enough players online!");
									Bukkit.broadcastMessage("§8[§6TheRush§8] §cThe timer restarts...");
									Timer = 91;
									Status = GameStatus.Lobby;
								} else {
									for (Player p : Bukkit.getOnlinePlayers()) {
										onZuordung();
										if (TheRush.orange.contains(p.getName())) {
											p.teleport(new Location(Bukkit
													.getWorld("world"), -1.5,
													55.5, -60.5));

											ItemStack o = new ItemStack(
													Material.LEATHER_CHESTPLATE,
													1);
											LeatherArmorMeta d = (LeatherArmorMeta) o
													.getItemMeta();
											d.setColor(Color.ORANGE);
											o.setItemMeta(d);
											p.getInventory().setChestplate(o);

										} else if (TheRush.green.contains(p
												.getName())) {
											p.teleport(new Location(Bukkit
													.getWorld("world"), -1.5,
													55.5, 59.5));

											ItemStack g = new ItemStack(
													Material.LEATHER_CHESTPLATE,
													1);
											LeatherArmorMeta dg = (LeatherArmorMeta) g
													.getItemMeta();
											dg.setColor(Color.GREEN);
											g.setItemMeta(dg);
											p.getInventory().setChestplate(g);

										}
									}
									Bukkit.broadcastMessage("§8[§6TheRush§8] §cThe game starts.");
									Timer = 1801;
									Status = GameStatus.Game;
								}
							}

						} else if (Status == GameStatus.Game) {
							WorldPvP(true);
							if ((Timer == 1800)
									|| ((Timer == 1700) || (Timer == 900)
											|| (Timer == 600) || (Timer == 300) || (Timer == 180))) {
								Bukkit.broadcastMessage("§8[§6TheRush§8] §7The game ends in §c"
										+ Timer / 60 + " §7minutes.");
							} else if ((Timer == 60) || (Timer == 50)
									|| (Timer == 40) || (Timer == 30)
									|| (Timer == 20) || (Timer == 10)
									|| (Timer == 5) || (Timer == 4)
									|| (Timer == 3) || (Timer == 2)) {
								Bukkit.broadcastMessage("§8[§6ThRush§8] §7The game ends in §c"
										+ Timer + " §7seconds.");
							} else if (Timer == 1) {
								Bukkit.broadcastMessage("§8[§6ThRush§8] §7The game ends in §c"
										+ Timer + " §7second.");
							} else if (Timer == 0) {
								Bukkit.broadcastMessage("§8[§6ThRush§8] §7The game is finish.");
								// Ende
								Status = GameStatus.Restarting;
							}

						} else if (Status == GameStatus.Restarting) {
							WorldPvP(false);
							if ((Timer == 60) || (Timer == 50) || (Timer == 40)
									|| (Timer == 30) || (Timer == 20)
									|| (Timer == 10) || (Timer == 5)
									|| (Timer == 4) || (Timer == 3)
									|| (Timer == 2)) {
								Bukkit.broadcastMessage("§8[§6TheRush§8] §7The server restarts in §c"
										+ Timer + " §7seconds.");
							} else if ((Timer == 1)) {
								Bukkit.broadcastMessage("§8[§6TheRush§8] §7The server restarts in §c"
										+ Timer + " §7second.");
							} else if ((Timer == 0)) {
								Bukkit.shutdown();
							}
						}

					}
				}, 0L, 5L);
	}

	public void onZuordung() {
		for (Player p : PlayerListener.Spieler) {

			if (green.size() == 0) {
				green.add(p.getName());
				p.sendMessage("§8[§6TheRush§8] §7You are in the team §2green§7.");
			} else if (green.size() > orange.size()) {
				orange.add(p.getName());
				p.sendMessage("§8[§6TheRush§8] §7You are in the team §6orange§7.");
			} else if (green.size() < orange.size()) {
				green.add(p.getName());
				p.sendMessage("§8[§6TheRush§8] §7You are in the team §2green§7.");
			}

		}
	}

	public void WorldPvP(boolean pvp) {
		Bukkit.getWorld("world").setPVP(pvp);
	}

	public static TheRush plugin;

	public void onChestReset() {
		for (Location loc : MapReset.Sandstone) {
			loc.getBlock().setType(Material.AIR);
		}
		for (Location loc : MapReset.Glowstone) {
			loc.getBlock().setType(Material.AIR);
		}
		for (Location loc : MapReset.Endstone) {
			loc.getBlock().setType(Material.AIR);
		}
		for (Location loc : MapReset.Brick) {
			loc.getBlock().setType(Material.AIR);
		}
	}
}
