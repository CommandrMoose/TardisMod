package net.tardis.mod.capability;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageCapabilityDoorOpen;
import net.tardis.mod.network.packets.MessageSetupFlight;
import net.tardis.mod.network.packets.MessageSyncCap;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.PlayerHelper;
import net.tardis.mod.util.common.helpers.TardisHelper;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.UUID;

public class CapabilityTardis implements ITardisCap {
	
	@CapabilityInject(ITardisCap.class)
	public static final Capability<ITardisCap> CAPABILITY = null;
	public static final ResourceLocation CAP_REGEN_ID = new ResourceLocation(Tardis.MODID, "tardis_cap");
	
	private EntityPlayer player;
	private BlockPos posFly = BlockPos.ORIGIN;
	private boolean isInFlight = false;
	private int exterior = Block.getStateId(TBlocks.tardis_top.getDefaultState());
	private boolean hasFuel = true;
	private int timeOnGround = 0;
	private boolean isOpen = false;
	private static AttributeModifier mod = new AttributeModifier(UUID.fromString("42b68862-2bdc-4df4-9fbe-4ad597cda211"), "Flight mode", 0.12F, 0);
	private BlockPos prevPos = new BlockPos(0, 0, 0);
	private Vec2d prevRot = new Vec2d(0, 0);
	private float alpha = 1;
	
	static {
		mod.setSaved(false);
	}
	
	private BlockPos intPos = BlockPos.ORIGIN;
	private TardisFlightState flightState = TardisFlightState.REMAT_FULL;
	
	public CapabilityTardis() {
	
	}
	
	public CapabilityTardis(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public void setFlightTardis(BlockPos pos) {
		this.posFly = pos;
	}
	
	@Override
	public BlockPos getFlightTardis() {
		return posFly;
	}
	
	@Override
	public void setTardis(BlockPos pos) {
		this.intPos = pos;
	}
	
	@Override
	public BlockPos getTardis() {
		return intPos;
	}
	
	@Override
	public boolean isInFlight() {
		return isInFlight;
	}
	
	@Override
	public void setInFlight(boolean inFlight) {
		isInFlight = inFlight;
	}
	
	public static void setSpeeds(EntityPlayer player, boolean reset) {
		if (reset) {
			if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(mod)) {
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(mod.getID());
			}
		} else {
			if (!player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(mod)) {
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(mod);
			}
		}
	}
	
	public static void setupFlight(EntityPlayer player, TileEntityTardis console, boolean move) {
		if (player.world.isRemote) return;
		setSpeeds(player, false);
		ITardisCap cap = get(player);
		cap.setTimeOnGround(0);
		if (console != null && !console.hasPilot() && console.fuel > 0) {
			console.setFlightPilot(player);
			cap.setFlightTardis(console.getPos());
			cap.setFlightState(TardisFlightState.REMAT);
			if (move) {
				console.transferPlayer(player, false);
				cap.setPreviousPos(player.getPosition());
				cap.setPrevRot(new Vec2d((double) player.rotationYaw, (double) player.rotationPitch));
				WorldServer world = DimensionManager.getWorld(console.dimension);
				world.setBlockState(console.getLocation(), Blocks.AIR.getDefaultState());
				world.setBlockState(console.getLocation().up(), Blocks.AIR.getDefaultState());
				NetworkHandler.NETWORK.sendToAll(new MessageSetupFlight(player.getUniqueID().toString()));
			}
			cap.setInFlight(true);
			cap.setExterior(console.getTopBlock());
			cap.setHasFuel(true);
			player.capabilities.allowFlying = true;
			player.capabilities.isFlying = true;
			player.capabilities.allowEdit = false;
			player.velocityChanged = true;
			player.eyeHeight = 2;
			player.noClip = false;
			player.clearActivePotions();
			player.extinguish();
			player.sendPlayerAbilities();
			cap.sync();
		} else {
			PlayerHelper.sendMessage(player, new TextComponentTranslation("tardis.message.has_pilot"), true);
		}
	}
	
	@Override
	public void setExterior(IBlockState exterior) {
		this.exterior = Block.getStateId(exterior);
	}
	
	@Override
	public IBlockState getExterior() {
		return Block.getStateById(exterior);
	}
	
	@Override
	public void sync() {
		NetworkHandler.NETWORK.sendToAll(new MessageSyncCap(player, serializeNBT()));
	}
	
	@Override
	public void setHasFuel(boolean b) {
		hasFuel = b;
	}
	
	@Override
	public boolean hasFuel() {
		return hasFuel;
	}
	
	@Override
	public int timeOnGround() {
		return timeOnGround;
	}
	
	@Override
	public void setTimeOnGround(int time) {
		timeOnGround = time;
	}
	
	@Override
	public void setDoorsOpen(boolean open) {
		isOpen = open;
	}
	
	@Override
	public boolean isOpen() {
		return isOpen;
	}
	
	public static void endFlight(EntityPlayer player, boolean placeExterior) {
		if (player.world.isRemote) return;
		
		ITardisCap cap = get(player); //Get the Players Tardis Data
		TileEntityTardis console = TardisHelper.getConsole(cap.getFlightTardis()); //Get the Console
		BlockPos bPos = player.getPosition(); //Back up the players position before we do anything
		
		
		if (console != null) {
			
			if (player.dimension != console.getWorld().provider.getDimension()) {
				console.enterTARDIS(player);
				player.attemptTeleport(console.getPos().getX(), console.getPos().getY(), console.getPos().getZ() - 1.5F);
			}
			
			
			WorldServer exteriorWorld = DimensionManager.getWorld(console.dimension);
			
			//Set Exterior of asked to
			if (placeExterior && cap.isInFlight()) {
				exteriorWorld.getChunkProvider().loadChunk(bPos.getX() * 16, bPos.getZ() * 16);
				exteriorWorld.setBlockState(bPos, TBlocks.tardis.getDefaultState());
				exteriorWorld.setBlockState(bPos.up(), console.getTopBlock().withProperty(BlockTardisTop.FACING, player.getHorizontalFacing()));
				BlockPos consolePos = cap.getFlightTardis();
				exteriorWorld.addScheduledTask(() -> {
					TileEntity door = exteriorWorld.getTileEntity(bPos.up());
					if (door instanceof TileEntityDoor) {
						((TileEntityDoor) door).setConsolePos(consolePos);
						((TileEntityDoor) Objects.requireNonNull(exteriorWorld.getTileEntity(bPos.up()))).forceVisible();
					}
				});
			}
			
			//Set some new the console data
			console.setLocation(bPos);
			console.dimension = exteriorWorld.provider.getDimension();
			console.setFlightPilot(null);
			console.markDirty();
			
			
			//Set player Capabilities/Speeds and eye height back to normal
			player.velocityChanged = true;
			player.eyeHeight = player.getDefaultEyeHeight();
			PlayerHelper.resetCapabilities(player);
			player.noClip = false;
			player.sendPlayerAbilities();
			setSpeeds(player, true);
			
			//Reset Cap stuff
			cap.setTardis(BlockPos.ORIGIN);
			cap.setInFlight(false);
			cap.setTimeOnGround(0);
			cap.sync();
		}
	}
	
	@Nonnull
	public static ITardisCap get(EntityPlayer player) {
		if (player.hasCapability(CAPABILITY, null)) {
			return player.getCapability(CAPABILITY, null);
		}
		throw new IllegalStateException("Missing Tardis capability: " + player + ", please report this to the issue tracker");
	}
	
	@Override
	public void update() {
		
		//Interior Handling
		if (this.getTardis().equals(BlockPos.ORIGIN) && player.dimension == TDimensions.TARDIS_ID) {
			this.setTardis(TardisHelper.getTardisForPosition(player.getPosition()));
		} else if (!this.getTardis().equals(BlockPos.ORIGIN) && player.dimension != TDimensions.TARDIS_ID) {
			this.setTardis(BlockPos.ORIGIN);
		}
		
		if (player.dimension == TDimensions.TARDIS_ID && !this.getTardis().equals(BlockPos.ORIGIN)) {
			if (player.getPosition().distanceSq(this.getTardis()) > 16384) {
				player.setPositionAndUpdate(this.getTardis().getX(), this.getTardis().getY() + 1, this.getTardis().getZ());
				PlayerHelper.sendMessage(player, new TextComponentTranslation("tardis.message.confines"), true);
			}
		}
		
		
		if (player.dimension != TDimensions.TARDIS_ID) {
			if (!getFlightTardis().equals(BlockPos.ORIGIN)) {
				if (isInFlight()) {
					
					if (player.isEntityInsideOpaqueBlock()) {
						double d0 = player.posX + (player.world.rand.nextDouble() - 0.5D) * 64.0D;
						double d1 = player.posY + (double) (player.world.rand.nextInt(64) - 32);
						double d2 = player.posZ + (player.world.rand.nextDouble() - 0.5D) * 64.0D;
						player.attemptTeleport(d0, d1, d2);
					}
					
					//Alpha "Animation"
					if (getFlightState().equals(TardisFlightState.DEMAT)) {
						alpha -= 0.005F;
						player.noClip = true;
						sync();
					}
					
					if (getFlightState().equals(TardisFlightState.REMAT)) {
						alpha += 0.005F;
						player.noClip = true;
						sync();
					}
					
					if (alpha >= 1F && flightState != TardisFlightState.REMAT_FULL) {
						setFlightState(TardisFlightState.REMAT_FULL);
						player.noClip = false;
						alpha = 1F;
						sync();
					}
					
					if (alpha <= 0F && flightState != TardisFlightState.DEMAT_FULL) {
						setFlightState(TardisFlightState.DEMAT_FULL);
						player.noClip = true;
						alpha = 0F;
						
						if (!player.world.isRemote) {
							TileEntityTardis console = TardisHelper.getConsole(getFlightTardis());
							if (console != null) {
								console.setFlightPilot(null);
								BlockPos backUpPos = prevPos;
								Vec2d bacupRot = prevRot;
								//	if (console.destDim != player.dimension) {
								Helper.transferToWorld((EntityPlayerMP) player, (WorldServer) player.world, console.getDestination(), console.destDim);
								NetworkHandler.NETWORK.sendToAll(new MessageSetupFlight(player.getUniqueID().toString()));
								//	} else {
								//		player.setLocationAndAngles(console.getDestination().getX(), console.getDestination().getY(), console.getDestination().getZ(), player.rotationYaw, player.rotationPitch);
								//	}
								setPreviousPos(backUpPos);
								setPrevRot(bacupRot);
							}
							setupFlight(player, console, false);
						}
						
					}
					
					//Ground ticking
					BlockPos blockPosToCheck = player.getPosition().down();
					if (!player.world.isAirBlock(blockPosToCheck) && !player.world.getBlockState(blockPosToCheck).getMaterial().isLiquid()) {
						timeOnGround++;
					} else {
						timeOnGround = 0;
					}
					
					
					//Return to interior after a few seconds of shifting on ground
					if (timeOnGround >= 50 && player.isSneaking()) {
						endFlight(player, true);
					}
					
					
					if (hasFuel) {
						
						if (!player.capabilities.allowFlying || !player.capabilities.isFlying) {
							player.capabilities.allowFlying = true;
							player.capabilities.isFlying = true;
							setSpeeds(player, false);
							player.velocityChanged = true;
						}
					} else {
						setSpeeds(player, true);
						player.capabilities.isFlying = false;
						player.capabilities.allowFlying = false;
						player.velocityChanged = true;
					}
				} else {
					endFlight(player, true);
				}
			}
		} else {
			//If the player is in the Tardis dimension and are still in a state of flight, we reset them
			if (isInFlight) {
				endFlight(player, true);
			}
		}
	}
	
	
	@SubscribeEvent
	public static void onPlayerRespawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
		get(event.player).sync();
	}
	
	@SubscribeEvent
	public static void onPlayerChangedDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
		get(event.player).sync();
	}
	
	
	@SubscribeEvent
	public static void onDeathEvent(LivingDeathEvent e) {
		if (e.getEntityLiving() instanceof EntityPlayer) {
			get((EntityPlayer) e.getEntityLiving()).sync();
		}
	}
	
	//===== HELPERS =====
	
	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("exterior", exterior);
		nbt.setBoolean("inFlight", isInFlight);
		nbt.setBoolean("hasFuel", hasFuel());
		nbt.setInteger("groundTime", timeOnGround);
		nbt.setBoolean("open", isOpen);
		nbt.setString("flightState", flightState.name());
		nbt.setFloat("alpha", alpha);
		nbt.setLong("prev_pos", prevPos.toLong());
		nbt.setDouble("rotx", prevRot.x);
		nbt.setDouble("rotz", prevRot.y);
		return nbt;
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		exterior = nbt.getInteger("exterior");
		isInFlight = nbt.getBoolean("inFlight");
		hasFuel = nbt.getBoolean("hasFuel");
		timeOnGround = nbt.getInteger("groundTime");
		isOpen = nbt.getBoolean("open");
		flightState = TardisFlightState.valueOf(nbt.getString("flightState"));
		alpha = nbt.getFloat("alpha");
		prevPos = BlockPos.fromLong(nbt.getLong("prev_pos"));
		prevRot = new Vec2d(nbt.getDouble("rotx"), nbt.getDouble("roty"));
	}
	
	@Override
	public BlockPos getPreviousPos() {
		return prevPos;
	}
	
	@Override
	public void setPrevRot(Vec2d vec) {
		this.prevRot = vec;
	}
	
	@Override
	public Vec2d getPrevRot() {
		return prevRot;
	}
	
	@Override
	public TardisFlightState getFlightState() {
		return flightState;
	}
	
	@Override
	public void setFlightState(TardisFlightState state) {
		flightState = state;
	}
	
	@Override
	public float getAlpha() {
		return alpha;
	}
	
	@Override
	public void setPreviousPos(BlockPos pos) {
		prevPos = pos;
	}
	
	//===== CAPABILITY EVENTS =====
	@Mod.EventBusSubscriber(modid = Tardis.MODID)
	public static class Events {
		
		@SubscribeEvent
		public static void onJoin(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
			get(event.player).sync();
			if (get(event.player).isInFlight()) {
				CapabilityTardis.endFlight(event.player, true);
			}
		}
		
		@SubscribeEvent
		public static void attach(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof EntityPlayer)
				event.addCapability(new ResourceLocation(Tardis.MODID, "tardis_cap"), new TardisCapProvider(new CapabilityTardis((EntityPlayer) event.getObject())));
		}
		
		
		@SubscribeEvent
		public static void update(LivingUpdateEvent event) {
			if (event.getEntityLiving() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				CapabilityTardis.get(player).update();
			}
		}
		
		@SubscribeEvent
		public static void onPlayerClone(PlayerEvent.Clone event) {
			Capability.IStorage<ITardisCap> storage = CAPABILITY.getStorage();
			
			ITardisCap oldCap = get(event.getOriginal());
			ITardisCap newCap = get(event.getEntityPlayer());
			
			NBTTagCompound nbt = (NBTTagCompound) storage.writeNBT(CAPABILITY, oldCap, null);
			storage.readNBT(CAPABILITY, newCap, null, nbt);
		}
		
		//End flight safely when the player logs out
		@SubscribeEvent
		public static void onPlayerLogout(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent e) {
			if (CapabilityTardis.get(e.player).isInFlight()) {
				CapabilityTardis.endFlight(e.player, true);
			}
		}
		
		//SYNCING - When player tracked
		@SubscribeEvent
		public static void onPlayerTracked(PlayerEvent.StartTracking event) {
			get(event.getEntityPlayer()).sync();
		}
		
		//Open and close doors when inflight
		@SubscribeEvent
		public static void onPunch(PlayerInteractEvent.LeftClickEmpty empty) {
			EntityPlayer pilot = empty.getEntityPlayer();
			ITardisCap data = get(pilot);
			if (data.isInFlight()) {
				NetworkHandler.NETWORK.sendToServer(new MessageCapabilityDoorOpen());
			}
		}
		
		//Stop the player from breaking blocks in flight
		@SubscribeEvent
		public static void onBreakBlock(BlockEvent.BreakEvent event) {
			EntityPlayer breaker = event.getPlayer();
			ITardisCap data = get(breaker);
			event.setCanceled(data.isInFlight());
		}
		
		//Stop players being knockedback in flight
		@SubscribeEvent
		public static void onKnockBack(LivingKnockBackEvent event) {
			if (event.getEntity() instanceof EntityPlayer) {
				EntityPlayer victim = (EntityPlayer) event.getEntity();
				ITardisCap data = get(victim);
				event.setCanceled(data.isInFlight() && victim.onGround);
			}
		}
		
		//Stop players dealing damage
		@SubscribeEvent
		public static void onPlayerAttack(LivingAttackEvent event) {
			if (event.getEntity() instanceof EntityPlayer) {
				EntityPlayer attacker = (EntityPlayer) event.getEntity();
				ITardisCap data = get(attacker);
				event.setCanceled(data.isInFlight());
			}
		}
		
		@SubscribeEvent
		public static void onHurtPilot(LivingHurtEvent event) {
			if (event.getEntity().world.isRemote) return;
			if (event.getEntity() instanceof EntityPlayer) {
				EntityPlayer victim = (EntityPlayer) event.getEntity();
				ITardisCap data = get(victim);
				if (!data.isInFlight()) return;
				event.setAmount(0.0F);
				TileEntityTardis tardis = TardisHelper.getConsole(data.getFlightTardis());
				if (tardis == null) return;
				for (TardisSystems.BaseSystem s : tardis.systems) {
					s.wear();
				}
			}
		}
	}
	
	public enum TardisFlightState {
		REMAT, REMAT_FULL, DEMAT, DEMAT_FULL
	}
	
}
