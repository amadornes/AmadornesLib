package com.amadornes.lib.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.amadornes.lib.tile.ITileContainer;
import com.amadornes.lib.tile.Tile;
import com.amadornes.lib.tile.TileEntityTile;

import cpw.mods.fml.relauncher.Side;

public class PacketTileUpdate implements IPacket {

	private Tile tile;
	private TileEntityTile parent;
	private NBTTagCompound tag;

	public PacketTileUpdate(TileEntityTile parent, Tile tile) {
		this.parent = parent;
		this.tile = tile;
	}

	public PacketTileUpdate() {
	}

	@Override
	public void write(NBTTagCompound tag) {
		tag.setInteger("x", tile.getX());
		tag.setInteger("y", tile.getY());
		tag.setInteger("z", tile.getZ());
		tag.setInteger("dim", tile.getWorld().provider.dimensionId);

		NBTTagCompound t = new NBTTagCompound();
		tile.writeDescription(t);
		tag.setTag("desc", t);
	}

	@Override
	public void read(NBTTagCompound tag) {
		int dim = tag.getInteger("dim");
		if (Minecraft.getMinecraft().theWorld == null)
			return;
		if (Minecraft.getMinecraft().theWorld.provider.dimensionId != dim)
			return;

		World w = Minecraft.getMinecraft().theWorld;

		int x = tag.getInteger("x");
		int y = tag.getInteger("y");
		int z = tag.getInteger("z");

		this.parent = (TileEntityTile) w.getTileEntity(x, y, z);
		this.tile = this.parent.getContainedTile();
		this.tag = tag.getCompoundTag("desc");
	}

	@Override
	public void handle(Side side) {
		if (side.isClient())
			tile.readDescription(tag);
	}

}
