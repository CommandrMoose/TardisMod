package net.tardis.mod.client.worldshell;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class RenderWorldShell extends Render {

	public RenderWorldShell(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		if (entity instanceof IContainsWorldShell) {
			BufferBuilder bb = Tessellator.getInstance().getBuffer();

			IContainsWorldShell container = (IContainsWorldShell) entity;

			GlStateManager.pushMatrix();
			bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

			BlockPos offset = container.getWorldShell().getOffset();
			GlStateManager.translate(x - offset.getX(), y - offset.getY(), z - offset.getZ());
			
			if (container.getWorldShell().bufferstate == null || container.getWorldShell().updateRequired) {
				for (BlockPos bp : container.getWorldShell().blockMap.keySet()) {
					if (bp == null)
						continue;
					Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlock(
							container.getWorldShell().getBlockState(bp), bp, entity.getEntityWorld(), bb);
				}
				container.getWorldShell().bufferstate = bb.getVertexState();
			} else {
				bb.setVertexState(container.getWorldShell().bufferstate);
			}

			Tessellator.getInstance().draw();

			for (TileEntity t : container.getWorldShell().getTESRs()) {
				if (t != null) {
					TileEntityRendererDispatcher.instance.render(t, t.getPos().getX(), t.getPos().getY(),
							t.getPos().getZ(), partialTicks);
				}
			}

			GlStateManager.popMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
