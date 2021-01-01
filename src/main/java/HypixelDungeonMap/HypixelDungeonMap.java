package HypixelDungeonMap;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HypixelDungeonMap.MODID, version = HypixelDungeonMap.VERSION, name = HypixelDungeonMap.NAME, clientSideOnly = true)

// This modification is based on the map feature of DungeonUtilities which is not around in any free Java
// based modification yet

public class HypixelDungeonMap {


	public static final String MODID = "hdm";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "Hypixel Dungeon Map";
	public static boolean showMap = true;
	@EventHandler
	public static void preInit(final FMLPreInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new ToggleMapCommand());
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new MapRenderer());
	}
}
