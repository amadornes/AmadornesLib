package com.amadornes.lib.multipart;

import net.minecraft.nbt.NBTTagCompound;
import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.multipart.TMultiPart;

import com.amadornes.lib.tile.ITileContainer;
import com.amadornes.lib.tile.Tile;

public class PartTile extends TMultiPart implements ITileContainer {

	private Tile tile;

	public Tile getContainedTile() {
		return tile;
	}

	public void setContainedTile(Tile tile) {
		this.tile = tile;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public void save(NBTTagCompound tag) {

		super.save(tag);
		
		NBTTagCompound t = new NBTTagCompound();
		getTile().writeToNBT(t);
		tag.setTag("data", t);
	}

	@Override
	public void load(NBTTagCompound tag) {

		super.load(tag);
		
		NBTTagCompound t = tag.getCompoundTag("data");
		getTile().writeToNBT(t);
	}

	@Override
	public void writeDesc(MCDataOutput packet) {

		super.writeDesc(packet);
		
		NBTTagCompound t = new NBTTagCompound();
		getContainedTile().writeDescription(t);
		packet.writeNBTTagCompound(t);
	}

	@Override
	public void readDesc(MCDataInput packet) {

		super.readDesc(packet);

		NBTTagCompound t = packet.readNBTTagCompound();
		getContainedTile().readDescription(t);
	}

}
