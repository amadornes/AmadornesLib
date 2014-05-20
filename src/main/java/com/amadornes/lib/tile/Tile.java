package com.amadornes.lib.tile;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.amadornes.lib.multipart.PartTile;
import com.amadornes.lib.packet.PacketHandler;
import com.amadornes.lib.packet.PacketTileUpdate;
import com.amadornes.lib.vec.ILocationW;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Tile implements ILocationW {

	protected TileEntityTile tile;
	protected PartTile part;

	public Tile(TileEntityTile tile) {
		this.tile = tile;
	}

	public Tile(PartTile part) {
		this.part = part;
	}

	/**
	 * @return A type that identifies this tile and makes it different from the
	 *         ones in the same mod and others (recommended format:
	 *         modid.tilename)
	 */
	public abstract String getType();

	public boolean isTile() {
		return tile != null;
	}

	public TileEntityTile getTileEntity() {
		return tile;
	}

	public boolean isMultiPart() {
		return part != null;
	}

	public PartTile getMultiPart() {
		return part;
	}

	@Override
	public int getX() {
		return isMultiPart() ? part.x() : tile.xCoord;
	}

	public int getY() {
		return isMultiPart() ? part.y() : tile.yCoord;
	}

	public int getZ() {
		return isMultiPart() ? part.z() : tile.zCoord;
	}

	@Override
	public World getWorld() {
		return isMultiPart() ? part.world() : tile.getWorldObj();
	}

	public boolean hasWorld() {
		return this.getWorld() != null;
	}

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

	public void readFromNBT(NBTTagCompound tag) {
	}

	public void writeToNBT(NBTTagCompound tag) {
	}

	public void writeDescription(NBTTagCompound tag) {

		writeToNBT(tag);
	}

	public void readDescription(NBTTagCompound tag) {

		readFromNBT(tag);
	}

	public void sendUpdatePacket() {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			if (isMultiPart()) {
				getMultiPart().sendDescUpdate();
			} else {
				PacketHandler.sendToAllClients(new PacketTileUpdate(getTileEntity(), this));
			}
		}
	}

	public void update() {
	}

	public double getDistanceFrom(double x, double y, double z) {
		double d3 = (double) getX() + 0.5D - x;
		double d4 = (double) getY() + 0.5D - y;
		double d5 = (double) getZ() + 0.5D - z;
		return d3 * d3 + d4 * d4 + d5 * d5;
	}

	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 4096.0D;
	}

	public Block getBlockType() {
		return this.getWorld().getBlock(getX(), getY(), getZ());
	}

	public boolean canUpdate() {
		return true;
	}

	public void onChunkUnload() {
	}

	public boolean shouldRenderInPass(int pass) {
		return pass == 0;
	}

	public static final AxisAlignedBB INFINITE_EXTENT_AABB = AxisAlignedBB.getBoundingBox(
			Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
			Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		Block type = getBlockType();
		if (type == Blocks.enchanting_table) {
			bb = AxisAlignedBB.getAABBPool().getAABB(getX(), getY(), getZ(), getX() + 1,
					getY() + 1, getZ() + 1);
		} else if (type == Blocks.chest || type == Blocks.trapped_chest) {
			bb = AxisAlignedBB.getAABBPool().getAABB(getX() - 1, getY(), getZ() - 1, getX() + 2,
					getY() + 2, getZ() + 2);
		} else if (type != null && type != Blocks.beacon) {
			AxisAlignedBB cbb = type.getCollisionBoundingBoxFromPool(getWorld(), getX(), getY(),
					getZ());
			if (cbb != null) {
				bb = cbb;
			}
		}
		return bb;
	}

}
