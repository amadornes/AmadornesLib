package com.amadornes.lib.packet;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.nbt.NBTTagCompound;

public interface IPacket {

	public void write(NBTTagCompound tag);
	public void read(NBTTagCompound tag);
	
	public void handle(Side side);
	
}
