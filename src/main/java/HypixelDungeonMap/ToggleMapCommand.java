package HypixelDungeonMap;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class ToggleMapCommand extends CommandBase {
	@Override
	public String getCommandName() {
		return "hdm";
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
		DungeonMap.renderOverlay();
	}
}
