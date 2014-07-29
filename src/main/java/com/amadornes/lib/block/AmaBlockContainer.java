package com.amadornes.lib.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.amadornes.lib.tile.AmaTileEntity;

public abstract class AmaBlockContainer extends AmaBlock implements ITileEntityProvider {
    
    protected AmaBlockContainer(Material material, String blockId) {
    
        super(material, blockId);
        isBlockContainer = true;
    }
    
    @Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
    
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
        p_149749_1_.removeTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    }
    
    @Override
    public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_) {
    
        super.onBlockEventReceived(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
        TileEntity tileentity = p_149696_1_.getTileEntity(p_149696_2_, p_149696_3_, p_149696_4_);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }
    
    private static AmaTileEntity getTile(IBlockAccess w, int x, int y, int z) {
    
        try {
            return (AmaTileEntity) w.getTileEntity(x, y, z);
        } catch (Exception ex) {
        }
        return null;
    }
    
    @Override
    public int onBlockPlaced(World w, int x, int y, int z, int side, float hx, float hy, float hz, int meta) {
    
        AmaTileEntity te = getTile(w, x, y, z);
        if (te != null) {
            te.onPlace();
        }
        
        return meta;
    }
    
    @Override
    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase who, ItemStack item) {
    
        AmaTileEntity te = getTile(w, x, y, z);
        if (te != null) {
            te.onPlaceBy(who, item);
        }
        
    }
    
    @Override
    public void onBlockDestroyedByExplosion(World w, int x, int y, int z, Explosion explosion) {
    
        AmaTileEntity te = getTile(w, x, y, z);
        if (te != null) {
            te.onBreak();
            te.onBreakByExplosion(explosion);
        }
    }
    
    @Override
    public void onBlockDestroyedByPlayer(World w, int x, int y, int z, int side) {
    
        AmaTileEntity te = getTile(w, x, y, z);
        if (te != null) {
            te.onBreak();
        }
    }
    
    @Override
    public void onBlockHarvested(World w, int x, int y, int z, int side, EntityPlayer player) {
    
        AmaTileEntity te = getTile(w, x, y, z);
        if (te != null) {
            te.onBreak();
            te.onBreak(player);
        }
    }
    
}
