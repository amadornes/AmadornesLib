package com.amadornes.lib.multipart;

import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.opengl.GL11;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.TMultiPart;

import com.amadornes.lib.tile.ITileContainer;
import com.amadornes.lib.tile.Tile;

public class PartTile extends TMultiPart implements ITileContainer {

	public PartTile(NBTTagCompound tag) {

	}

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

	@Override
	public void onAdded() {
		getContainedTile().onPlace();
	}

	@Override
	public void onRemoved() {
		getContainedTile().onBreak();
	}

	@Override
	public void update() {
		if (getContainedTile().canUpdate())
			getContainedTile().update();
	}

	@Override
	public Cuboid6 getRenderBounds() {
		return new Cuboid6(getContainedTile().getRenderBoundingBox());
	}

	@Override
	public void onChunkUnload() {
		getContainedTile().onChunkUnload();
	}

	@Override
	public boolean renderStatic(Vector3 pos, int pass) {
		if (getContainedTile().shouldRenderInPass(pass)) {
			GL11.glPushMatrix();

			GL11.glTranslated(pos.x, pos.y, pos.z);
			getContainedTile().renderStatic(pass);

			GL11.glPopMatrix();
			return true;
		}
		return false;
	}

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		if (getContainedTile().shouldRenderInPass(pass)) {
			GL11.glPushMatrix();

			GL11.glTranslated(pos.x, pos.y, pos.z);
			getContainedTile().renderDynamic(pass, frame);

			GL11.glPopMatrix();
		}
	}

}
