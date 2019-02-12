package net.tardis.mod.client.worldshell;

import java.util.Map.Entry;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncWorldShell implements IMessage {
	//Sync the world shell from client to server

	public WorldShell worldShell;
	public BlockPos tilePos = BlockPos.ORIGIN;
	public int id = -1;
	public EnumType type;

	public MessageSyncWorldShell(WorldShell ws, int id, EnumType type) {
		this.id = id;
		worldShell = ws;
		this.type = type;
	}

	public MessageSyncWorldShell(WorldShell ws, BlockPos pos, EnumType type) {
		this.tilePos = pos.toImmutable();
		this.worldShell = ws;
		this.id = -1;
		this.type = type;
	}

	public MessageSyncWorldShell() {}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.tilePos = BlockPos.fromLong(buf.readLong());
		this.worldShell = new WorldShell(BlockPos.fromLong(buf.readLong()));
		type = EnumType.values()[buf.readInt()];
		if(type == EnumType.BLOCKS) {
			int size = buf.readInt();
			for(int i = 0; i < size; ++i) {
				this.worldShell.blockMap.put(BlockPos.fromLong(buf.readLong()), new BlockStorage(buf));
			}
		}
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeLong(tilePos.toLong());
		buf.writeLong(worldShell.getOffset().toLong());
		buf.writeInt(type.ordinal());
		if(type == EnumType.BLOCKS) {
			buf.writeInt(this.worldShell.blockMap.size());
			for(Entry<BlockPos, BlockStorage> stor : this.worldShell.blockMap.entrySet()) {
				buf.writeLong(stor.getKey().toLong());
				stor.getValue().toBuf(buf);
				
			}
		}
	}

	public static class Handler implements IMessageHandler<MessageSyncWorldShell, IMessage> {

		public Handler() {}

		@Override
		public IMessage onMessage(MessageSyncWorldShell mes, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					World world = Minecraft.getMinecraft().world;
					if (mes == null || world == null) return;
					if (mes.id == -1) {
						TileEntity te = world.getTileEntity(mes.tilePos);
						if (te != null && te instanceof IContainsWorldShell) {
							IContainsWorldShell cont = (IContainsWorldShell)te;
							if(!cont.getWorldShell().getOffset().equals(mes.worldShell.getOffset()))
								cont.setWorldShell(mes.worldShell);
							else{
								if(mes.type == EnumType.BLOCKS) {
									cont.getWorldShell().blockMap.putAll(mes.worldShell.blockMap);
								}
							}
						}
					} else {
						Entity entity = world.getEntityByID(mes.id);
						if (entity != null && entity instanceof IContainsWorldShell) {
							IContainsWorldShell cont = (IContainsWorldShell) entity;
							if(!cont.getWorldShell().getOffset().equals(mes.worldShell.getOffset())){
								cont.setWorldShell(mes.worldShell);
							}
							else {
								if(mes.type == EnumType.BLOCKS) {
									cont.getWorldShell().blockMap.putAll(mes.worldShell.blockMap);
								}
							}
						}
					}
				}
			});
			return null;
		}

	}
	
	public static enum EnumType{
		BLOCKS,
		ENTITITES,
		PLAYERS
	}

}
