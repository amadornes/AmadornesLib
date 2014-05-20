package es.amadornes.lib.vec;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class ImmutableVector3 extends Vector3 {
    
    private Block b;
    private int   meta;
    private World w;
    private TileEntity te;
    
    protected ImmutableVector3(Vector3 v) {
    
        super(v.toBlockCoord(), v.getWorld());
        if(v instanceof ImmutableVector3){
            b = ((ImmutableVector3) v).b;
            meta = ((ImmutableVector3) v).meta;
            w = ((ImmutableVector3) v).w;
            te = ((ImmutableVector3) v).te;
        }else{
            b = v.getBlock();
            meta = v.getBlockMeta();
            w = v.getWorld();
            te = v.getTileEntity();
        }
    }
    
    @Override
    public Vector3 add(double x, double y, double z) {
    
        return this;
    }
    
    @Override
    public Vector3 add(ForgeDirection dir) {
    
        return this;
    }
    
    @Override
    public Vector3 add(Vector3 vec) {
    
        return this;
    }
    
    @Override
    public Vector3 divide(double multiplier) {
    
        return this;
    }
    
    @Override
    public Vector3 divide(double x, double y, double z) {
    
        return this;
    }
    
    @Override
    public Vector3 divide(ForgeDirection direction) {
    
        return this;
    }
    
    @Override
    public Block getBlock() {
    
        return b;
    }
    
    @Override
    public int getBlockId() {
    
        try {
            return b.blockID;
        } catch (Exception ex) {
        }
        return -1;
    }
    
    @Override
    public int getBlockMeta() {
    
        return meta;
    }
    
    @Override
    public TileEntity getTileEntity() {
    
        return te;
    }
    
    @Override
    public World getWorld() {
    
        return w;
    }
    
    @Override
    public boolean hasTileEntity() {
    
        return getTileEntity() != null;
    }
    
    @Override
    public boolean hasWorld() {
    
        return getWorld() != null;
    }
    
    @Override
    public boolean isBlock(Block b) {
    
        return super.isBlock(b);
    }
    
    @Override
    public boolean isBlock(Block b, boolean checkAir) {
    
        if (hasWorld()) {
            if (b == null && getBlockId() <= 0) return true;
            if (b == null
                    && checkAir
                    && this.b.blockMaterial == Material.air) return true;
            if (b == null
                    && checkAir
                    && this.b.isAirBlock(w, (int) x, (int) y, (int) z)) return true;
            
            return this.b != null && this.b.getClass().isInstance(b);
        }
        return false;
    }
    
    @Override
    public Vector3 multiply(double multiplier) {
    
        return this;
    }
    
    @Override
    public Vector3 multiply(double x, double y, double z) {
    
        return this;
    }
    
    @Override
    public Vector3 multiply(ForgeDirection direction) {
    
        return this;
    }
    
    @Override
    public Vector3 setWorld(World world) {
    
        return this;
    }
    
    @Override
    public Vector3 subtract(double x, double y, double z) {
    
        return this;
    }
    
    @Override
    public Vector3 subtract(ForgeDirection dir) {
    
        return this;
    }
    
    @Override
    public Vector3 subtract(Vector3 vec) {
    
        return this;
    }
    
}
