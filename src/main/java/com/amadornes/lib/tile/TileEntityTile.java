package com.amadornes.lib.tile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;

public class TileEntityTile extends AmaTileEntity implements ITileContainer {

	private Tile tile;

	public Tile getContainedTile() {
		return tile;
	}

	public void setContainedTile(Tile tile) {
		this.tile = tile;
	}

	public void onPlace() {
		getContainedTile().onPlace();
	}

	public void onPlaceBy(EntityLivingBase who, ItemStack item) {
		getContainedTile().onPlaceBy(who, item);
	}

	public void onBreak() {
		getContainedTile().onBreak();
	}

	public void onBreak(EntityPlayer player) {
		getContainedTile().onBreak(player);
	}

	public void onBreakByExplosion(Explosion explosion) {
		getContainedTile().onBreakByExplosion(explosion);
	}

	public void readFromNBT(NBTTagCompound tag) {
		getContainedTile().readFromNBT(tag);
	}

	public void writeToNBT(NBTTagCompound tag) {
		getContainedTile().writeToNBT(tag);
	}
	
	@Override
	public void writeDescription(NBTTagCompound tag) {
		getContainedTile().writeDescription(tag);
	}
	
	@Override
	public void readDescription(NBTTagCompound tag) {
		getContainedTile().readDescription(tag);
	}
	
	@Override
	public void updateEntity() {
		getContainedTile().update();
	}
	
	@Override
	public double getMaxRenderDistanceSquared() {
		return getContainedTile().getMaxRenderDistanceSquared();
	}
	
	@Override
	public boolean canUpdate() {
		return getContainedTile().canUpdate();
	}
	
	@Override
	public void onChunkUnload() {
		getContainedTile().onChunkUnload();
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return getContainedTile().shouldRenderInPass(pass);
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return getContainedTile().getRenderBoundingBox();
	}

}
