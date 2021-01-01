package HypixelDungeonMap;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MapRenderer {
	    @SubscribeEvent
	    public void onRenderGui(RenderGameOverlayEvent.Post event){
	        if(event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;
	        new DungeonMap();
	}
}
