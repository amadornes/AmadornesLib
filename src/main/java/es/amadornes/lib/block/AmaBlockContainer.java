package es.amadornes.lib.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import es.amadornes.lib.tile.AmaTileEntity;

public abstract class AmaBlockContainer extends BlockContainer {
    
    protected AmaBlockContainer(int id, Material mat) {
    
        super(id, mat);
    }
    
    private static AmaTileEntity getTile(IBlockAccess w, int x, int y, int z) {
    
        try {
            return (AmaTileEntity) w.getBlockTileEntity(x, y, z);
        } catch (Exception ex) {
        }
        return null;
    }
    
    @Override
    public int onBlockPlaced(World w, int x, int y, int z, int side, float hx,
            float hy, float hz, int meta) {
    
        AmaTileEntity te = getTile(w, x, y, z);
        if (te != null) {
            te.onPlace();
        }
        
        return meta;
    }
    
    @Override
    public void onBlockPlacedBy(World w, int x, int y, int z,
            EntityLivingBase who, ItemStack item) {
    
        AmaTileEntity te = getTile(w, x, y, z);
        if (te != null) {
            te.onPlaceBy(who, item);
        }
        
    }
    
    @Override
    public void onBlockDestroyedByExplosion(World w, int x, int y, int z,
            Explosion explosion) {
    
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
    public void onBlockHarvested(World w, int x, int y, int z, int side,
            EntityPlayer player) {
    
        AmaTileEntity te = getTile(w, x, y, z);
        if (te != null) {
            te.onBreak();
            te.onBreak(player);
        }
    }
    
}
