package es.amadornes.lib.tile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;

public abstract class AmaTileEntity extends TileEntity {
    
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
    
}
