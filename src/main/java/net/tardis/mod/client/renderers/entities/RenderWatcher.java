package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.entities.EntityRaider;
import net.tardis.mod.common.entities.EntityWatcher;

import javax.annotation.Nullable;


public class RenderWatcher extends RenderLiving<EntityWatcher> {
	public static final ResourceLocation texture = new ResourceLocation(Tardis.MODID, "textures/entity/mob/watcher.png");

	public static ModelPlayer STEVE = new ModelPlayer(0F, false);

	public RenderWatcher(RenderManager rendermanagerIn) {
		super(rendermanagerIn, STEVE, 0.3F);
		STEVE.isChild = false;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWatcher entity) {
		return texture;
	}


	@Override
	protected void renderModel(EntityWatcher comp, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		this.bindEntityTexture(comp);
		STEVE.render(comp, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	}

}