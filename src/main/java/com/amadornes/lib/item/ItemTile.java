package com.amadornes.lib.item;

import com.amadornes.lib.multipart.PartTile;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.JItemMultiPart;
import codechicken.multipart.TMultiPart;

public class ItemTile extends JItemMultiPart {

	@Override
	public double getHitDepth(Vector3 vec, int unused) {
		return 4;
	}

	@Override
	public TMultiPart newPart(ItemStack is, EntityPlayer player, World w, BlockCoord b, int unused,
			Vector3 unused1) {

		return new PartTile(is.getTagCompound());
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World w, int x, int y, int z,
			int side, float f, float f2, float f3) {

		if (super.onItemUse(stack, player, w, x, y, z, side, f, f2, f3)) {
			w.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, Block.soundTypeStone.soundName,
					Block.soundTypeStone.getVolume(), Block.soundTypeStone.getPitch());
			return true;
		}
		return false;
	}

}
