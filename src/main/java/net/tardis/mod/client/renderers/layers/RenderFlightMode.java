package net.tardis.mod.client.renderers.layers;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.tardis.mod.Tardis;
import net.tardis.mod.capability.CapabilityTardis;
import net.tardis.mod.capability.ITardisCap;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.client.models.exteriors.IExteriorModel;
import net.tardis.mod.util.common.helpers.PlayerHelper;

import java.util.HashMap;
import java.util.Map;

public class RenderFlightMode {
	
	//This is horrible, I know it is, just bare with me
	public static HashMap<EnumExterior, IExteriorModel> EXTERIOR_CACHE = new HashMap<>();
	
	public static void cacheFlightModels() {
		for (EnumExterior value : EnumExterior.values()) {
			EXTERIOR_CACHE.put(value, value.model);
		}
	}
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/cape/cape.png");
	private static final ResourceLocation SUFF_TEX = new ResourceLocation(Tardis.MODID, "textures/entity/mob/cape/suff_cape.png");
	
	
	public static void renderFlightMode(RenderPlayerEvent.Pre e) {
		EntityPlayer player = e.getEntityPlayer();
		ITardisCap data = CapabilityTardis.get(player);
		
		//Just shove this in here because yeah
		if (player.getUniqueID().toString().equals("3f4f2acd-9427-4917-81c3-7b8a3031fffb") || player.getUniqueID().toString().equals("bc8b891e-5c25-4c9f-ae61-cdfb270f1cc1")) {
			AbstractClientPlayer ap = (AbstractClientPlayer) player;
			if (ap.getLocationCape() != null && !ap.getLocationCape().equals(TEXTURE)) {
				setStupidCape(ap, TEXTURE, player.getUniqueID().toString().equals("bc8b891e-5c25-4c9f-ae61-cdfb270f1cc1") ? SUFF_TEX : TEXTURE);
			}
			
			if (ap.getLocationCape() == null) {
				setStupidCape(ap, TEXTURE, player.getUniqueID().toString().equals("bc8b891e-5c25-4c9f-ae61-cdfb270f1cc1") ? SUFF_TEX : TEXTURE);
			}
			
		}
		
		
		if (data.isInFlight()) {
			
			IBlockState exteriorState = data.getExterior();
			
			//Retrieve the correct exterior based on the blockstate
			EnumExterior exterior = EnumExterior.getExteriorFromBlock(exteriorState.getBlock());
			
			//Persistently keep the pilot in Third person
			if (player.getUniqueID() == Minecraft.getMinecraft().player.getUniqueID()) {
				Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
			}
			
			//Render Particles based on whether there is no fuel, if the player is hurt/collided
			if (player.collidedHorizontally || !data.hasFuel() || player.hurtTime > 0) {
				for (int x = 0; x <= 13; x++) {
					player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.posX + (player.world.rand.nextDouble() - 0.5D) * (double) player.width, player.posY + player.world.rand.nextDouble() * (double) player.height, player.posZ + (player.world.rand.nextDouble() - 0.5D) * (double) player.width, 0.0D, 0.0D, 0.0D);
					player.world.spawnParticle(EnumParticleTypes.FLAME, player.posX + (player.world.rand.nextDouble() - 0.5D) * (double) player.width, player.posY + player.world.rand.nextDouble() * (double) player.height, player.posZ + (player.world.rand.nextDouble() - 0.5D) * (double) player.width, 0.0D, 0.0D, 0.0D);
				}
			}
			
			e.setCanceled(true); //Cancel Event so that player doesn't render
			
			//Render
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			
			//Render alpha colours for Demat/Remat
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, data.getAlpha());
			
			//Render the players name, since we cancel the render player event, this won't render without us calling it
			e.getRenderer().renderName((AbstractClientPlayer) e.getEntityPlayer(), e.getX(), e.getY() + 1, e.getZ());
			
			//Translate the rendering position based on the viewer, else the Tardis will render really badly
			double x2 = ((player.prevPosX + (player.posX - player.prevPosX) * e.getPartialRenderTick()) - TileEntityRendererDispatcher.staticPlayerX);
			double y2 = ((player.prevPosY + (player.posY - player.prevPosY) * e.getPartialRenderTick()) - TileEntityRendererDispatcher.staticPlayerY);
			double z2 = ((player.prevPosZ + (player.posZ - player.prevPosZ) * e.getPartialRenderTick()) - TileEntityRendererDispatcher.staticPlayerZ);
			GlStateManager.translate(x2, y2, z2);
			
			
			//Correct Tardis Rotation and postion (Without, the Tardis will render upside down and slightly off camera)
			GlStateManager.rotate(-180, 1, 0, 0);
			GlStateManager.translate(0, -1.5, 0);
			
			//If the Tardis is flying
			if (!player.onGround) {
				GlStateManager.rotate((float) (player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * e.getPartialRenderTick() + player.motionX + player.motionZ), 0, 1, 0);
				
				int amplifier = PlayerHelper.isPlayerMoving(player) ? 1 : 4;
				GlStateManager.rotate((float) (player.ticksExisted * 3.0f * Math.PI / amplifier), 0, 1, 0);
				
				//If the player is falling
				if (player.world.isAirBlock(player.getPosition().down())) {
					if (player.fallDistance > 0 || !data.hasFuel()) {
						float f = (float) (player.ticksExisted * 3.0f * Math.PI) + e.getPartialRenderTick();
						float f1 = MathHelper.clamp(f * f / 100.0F, 0.0F, 1.0F);
						GlStateManager.rotate(-f1 * (-90.0F - player.rotationPitch), 1.0F, 0.0F, 0.0F);
					}
				}
				
				//Bob the Tardis up and down slightly in flight
				float offset = MathHelper.cos(player.ticksExisted * 0.1F) * -0.67F;
				GlStateManager.translate(0, -offset, 0);
				
				//Rotate Tardis on Z axis based on player motion
				double motion = (Math.abs(e.getEntity().motionX) + Math.abs(e.getEntityPlayer().motionZ)) / 8.0D;
				GlStateManager.rotate((float) (motion * 150D), 0, 0, 1);
			}
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(exterior.tex);
			if (!data.isOpen()) {
				EXTERIOR_CACHE.get(exterior).renderClosed(0.0625F);
			} else {
				EXTERIOR_CACHE.get(exterior).renderOpen(0.0625F);
			}
			
			//Set things back
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1F);
			//GlStateManager.disableAlpha();
			GlStateManager.disableBlend();
			
			GlStateManager.popMatrix();
			
		}
	}
	
	
	public static void setStupidCape(AbstractClientPlayer player, ResourceLocation cape, ResourceLocation elytra) {
		NetworkPlayerInfo playerInfo = ObfuscationReflectionHelper.getPrivateValue(AbstractClientPlayer.class, player, 0);
		if (playerInfo == null)
			return;
		Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, playerInfo, 1);
		playerTextures.put(MinecraftProfileTexture.Type.CAPE, cape);
		playerTextures.put(MinecraftProfileTexture.Type.ELYTRA, elytra);
	}
	
}
