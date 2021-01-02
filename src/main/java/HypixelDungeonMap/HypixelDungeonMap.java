package HypixelDungeonMap;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HypixelDungeonMap.MODID, version = HypixelDungeonMap.VERSION, name = HypixelDungeonMap.NAME, clientSideOnly = true)

// This modification is based on the map feature of DungeonUtilities

public class HypixelDungeonMap {


	public static final String MODID = "HypixelDungeonMap";
	public static final String VERSION = "1.0";
	public static final String NAME = "HypixelDungeonMap";
	public static boolean showMap = true;
	@EventHandler
	public static void preInit(final FMLPreInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new ToggleMapCommand());
		MinecraftForge.EVENT_BUS.register(new DungeonMap());
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
	}
}
