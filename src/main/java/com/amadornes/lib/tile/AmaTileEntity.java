package com.amadornes.lib.tile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;

import com.amadornes.lib.packet.PacketHandler;
import com.amadornes.lib.packet.PacketTileUpdate;

import cpw.mods.fml.common.FMLCommonHandler;

public abstract class AmaTileEntity extends TileEntity {

	public void onPlace() {

	}

	public void onPlaceBy(EntityLivingBase who, ItemStack item) {

	}

	public void onBreak() {

	}

	public void onBreak(EntityPlayer player) {

	}

	public void onBreakByExplosion(Explosion explosion) {

	}

	public void writeDescription(NBTTagCompound tag) {

		writeToNBT(tag);
	}

	public void readDescription(NBTTagCompound tag) {

		readFromNBT(tag);
	}

	@Override
	public final Packet getDescriptionPacket() {
		return super.getDescriptionPacket();
	}

	@Override
	public final void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
	}

	public void sendUpdatePacket() {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer())
			PacketHandler.sendToAllClients(new PacketTileUpdate(this));
	}

}
