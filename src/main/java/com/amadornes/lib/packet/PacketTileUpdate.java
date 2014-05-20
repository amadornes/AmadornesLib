package com.amadornes.lib.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.amadornes.lib.tile.AmaTileEntity;

import cpw.mods.fml.relauncher.Side;

public class PacketTileUpdate implements IPacket {

	private AmaTileEntity te;
	private NBTTagCompound tag;

	public PacketTileUpdate(AmaTileEntity te) {
		this.te = te;
	}

	public PacketTileUpdate() {
	}

	@Override
	public void write(NBTTagCompound tag) {
		tag.setInteger("x", te.xCoord);
		tag.setInteger("y", te.yCoord);
		tag.setInteger("z", te.zCoord);
		tag.setInteger("dim", te.getWorldObj().provider.dimensionId);
		
		NBTTagCompound t = new NBTTagCompound();
		te.writeDescription(t);
		tag.setTag("desc", t);
	}

	@Override
	public void read(NBTTagCompound tag) {
		int dim = tag.getInteger("dim");
		if(Minecraft.getMinecraft().theWorld == null)
			return;
		if(Minecraft.getMinecraft().theWorld.provider.dimensionId != dim)
			return;
		
		World w = Minecraft.getMinecraft().theWorld;

		int x = tag.getInteger("x");
		int y = tag.getInteger("y");
		int z = tag.getInteger("z");
		
		this.te = (AmaTileEntity) w.getTileEntity(x, y, z);
		this.tag = tag.getCompoundTag("desc");
	}

	@Override
	public void handle(Side side) {
		if(side.isClient()){
			te.readDescription(tag);
		}
	}

}
