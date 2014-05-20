package com.amadornes.lib.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.EnumMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import com.amadornes.lib.ALModInfo;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler extends FMLIndexedMessageToMessageCodec<IPacket> {

	private static EnumMap<Side, FMLEmbeddedChannel> channels;

	private PacketHandler() {
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, IPacket msg, ByteBuf target) throws Exception {
		NBTTagCompound tag = new NBTTagCompound();
		msg.write(tag);
		target.writeBytes(CompressedStreamTools.compress(tag));
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, IPacket msg) {
		NBTTagCompound tag;
		try {
			tag = CompressedStreamTools.decompress(source.array());
			msg.read(tag);
			msg.handle(FMLCommonHandler.instance().getEffectiveSide());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendToServer(IPacket p) {
		channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET)
				.set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		channels.get(Side.CLIENT).writeOutbound(p);
	}

	public static void sendToClients(IPacket p, EntityPlayer player) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
				.set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		channels.get(Side.SERVER).writeOutbound(p);
	}

	public static void sendToAllClients(IPacket p) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
				.set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeOutbound(p);
	}

	private int packetId = 0;

	public int getNextAvailablePacketId() {
		return packetId++;
	}

	public void registerPacket(int packetId, Class<? extends IPacket> packet) {
		instance().addDiscriminator(packetId, packet);
	}

	private static PacketHandler inst;

	public static PacketHandler instance() {
		if (inst == null) {
			channels = NetworkRegistry.INSTANCE.newChannel(ALModInfo.MODID, PacketHandler.instance());
			inst = new PacketHandler();
			setup();
		}
		return inst;
	}

	private static void setup() {
		instance().registerPacket(0, PacketTileEntityUpdate.class);
	}
}
