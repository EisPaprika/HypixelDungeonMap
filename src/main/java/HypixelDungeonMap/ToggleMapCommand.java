package HypixelDungeonMap;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class ToggleMapCommand extends CommandBase {
	@Override
	public String getCommandName() {
		return "toggleMap";
	}

	@Override
	public String getCommandUsage(ICommandSender arg0) {
		return null;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
		HypixelDungeonMap.showMap = !HypixelDungeonMap.showMap;
		if(HypixelDungeonMap.showMap){
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("The map is now shown."));
		}else{
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("The map is now hidden."));
		}
	}
}
