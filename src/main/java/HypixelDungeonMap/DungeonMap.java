package HypixelDungeonMap;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec4b;
import net.minecraft.world.storage.MapData;

public class DungeonMap {
	static ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
	static MapData oldMapData;
	static MapData mapData;

	public static void worldLoad() {
		oldMapData = null;
	}

	public static void renderOverlay() {
		double x = 0;
		double y = 0;
		float scale = 1;
		try {
			List<ItemStack> items = Minecraft.getMinecraft().thePlayer.inventoryContainer.inventoryItemStacks;
			for (ItemStack item : items) {
				if (item != null) {
					if (item.getItem().isMap()) {
						if (item.getItem() instanceof ItemMap) {
							ItemMap mapitem = (ItemMap) item.getItem();
							mapData = mapitem.getMapData(item, Minecraft.getMinecraft().thePlayer.getEntityWorld());
						}
					}
				}
			}
			if (mapData == null)
				return;
			oldMapData = mapData;
		} catch (Error error) {
			Minecraft.getMinecraft().thePlayer
					.addChatMessage(new ChatComponentText("&cError loading map! Check your console!"));
			return;
		}

		try {
			GlStateManager.pushMatrix(); // GlStateManager.push()
			GlStateManager.translate(x, y, 0.0); // GlStateManager.translate()
			GlStateManager.scale(scale, scale, 1); // GlStateManager.scale()
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F); // GlStateManager.color()
			drawMapBackground();
			Minecraft.getMinecraft().entityRenderer.getMapItemRenderer().renderMap(mapData, true);
			drawPlayersOnMap();
			GlStateManager.popMatrix(); // GlStateManager.pop()
		} catch (Error error) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("&cError! Check your console!"));
		}
	}

	// source: net.minecraft.client.gui/MapItemRenderer
	public static void drawPlayersOnMap() {
		int i = 0;
		int j = 0;
		int k = 0;
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		float z = 1.0F;

		for (Vec4b vec4b : mapData.mapDecorations.values()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 0, z);
			GlStateManager.translate(i + vec4b.func_176112_b() / 2.0 + 64.0, j + vec4b.func_176113_c() / 2.0 + 64.0,
					-0.02);
			GlStateManager.rotate((vec4b.func_176111_d() * 360F) / 16.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.scale(4.0, 4.0, 1);
			GlStateManager.translate(-0.125, 0.125, 0.0);
			double b0 = vec4b.func_176110_a();
			double f1 = (b0 % 4) / 4.0;
			double f2 = (Math.floor(b0 / 4)) / 4.0;
			double f3 = (b0 % 4 + 1) / 4.0;
			double f4 = (Math.floor(b0 / 4) + 1) / 4.0;
			worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
			worldrenderer.pos(-1.0D, 1.0D, (double) ((float) k * -0.001F)).tex((double) f1, (double) f2).endVertex();
			worldrenderer.pos(1.0D, 1.0D, (double) ((float) k * -0.001F)).tex((double) f3, (double) f2).endVertex();
			worldrenderer.pos(1.0D, -1.0D, (double) ((float) k * -0.001F)).tex((double) f3, (double) f4).endVertex();
			worldrenderer.pos(-1.0D, -1.0D, (double) ((float) k * -0.001F)).tex((double) f1, (double) f4).endVertex();
			tessellator.draw();
			GlStateManager.popMatrix(); // pop
			k++;
			z++;
		}

		GlStateManager.pushMatrix();
		GlStateManager.scale(0.0F, 0.0F, -0.04F);
		GlStateManager.translate(1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();
	};

	public static void drawMapBackground() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(RES_MAP_BACKGROUND); // Minecraft.getTextureManager().bindTexture()
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		GlStateManager.pushMatrix(); // push
		GlStateManager.enableAlpha();
		GL11.glNormal3f(0.0F, 0.0F, -1.0F);
		GlStateManager.translate(0F, 0F, -1.0F);
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos(-7.0, 135.0, 0.0).tex(0.0, 1.0).endVertex();
		worldrenderer.pos(135.0, 135.0, 0.0).tex(1.0, 1.0).endVertex();
		worldrenderer.pos(135.0, -7.0, 0.0).tex(1.0, 0.0).endVertex();
		worldrenderer.pos(-7.0, -7.0, 0.0).tex(0.0, 0.0).endVertex();
		tessellator.draw();
		GlStateManager.popMatrix(); // pop
	}
}
