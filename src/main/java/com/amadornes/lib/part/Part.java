package com.amadornes.lib.part;

import java.util.Arrays;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import com.amadornes.lib.raytrace.AmaMOP;
import com.amadornes.lib.raytrace.RayTracer;
import com.amadornes.lib.vec.Vector3;
import com.amadornes.lib.vec.Vector3Cube;

public abstract class Part implements IPart {
    
    private TileEntity parent;
    
    @Override
    public void setParent(TileEntity te) {
    
        parent = te;
    }
    
    @Override
    public TileEntity getParent() {
    
        return parent;
    }
    
    @Override
    public int x() {
    
        return getParent().xCoord;
    }
    
    @Override
    public int y() {
    
        return getParent().yCoord;
    }
    
    @Override
    public int z() {
    
        return getParent().zCoord;
    }
    
    @Override
    public World world() {
    
        if (getParent() == null) return null;
        
        return getParent().getWorldObj();
    }
    
    @Override
    public void update() {
    
    }
    
    @Override
    public List<Vector3Cube> getCollisionBoxes() {
    
        return null;
    }
    
    @Override
    public List<Vector3Cube> getSelectionBoxes() {
    
        return null;
    }
    
    @Override
    public List<Vector3Cube> getOcclusionBoxes() {
    
        return null;
    }
    
    @Override
    public AmaMOP rayTrace(Vector3 start, Vector3 end) {
    
        return RayTracer.rayTrace(start, end, getSelectionBoxes());
    }
    
    @Override
    public void renderDynamic(Vector3 pos, int pass, float partialTickTime) {
    
    }
    
    @Override
    public boolean renderStatic(Vector3 pos, int pass) {
    
        return false;
    }
    
    @Override
    public boolean shouldRenderDynamicInPass(int pass) {
    
        return pass == 0;
    }
    
    @Override
    public boolean shouldRenderStaticInPass(int pass) {
    
        return pass == 0;
    }
    
    @Override
    public void renderItem(ItemStack item, ItemRenderType type, Object... data) {
    
    }
    
    @Override
    public boolean renderSelectionBoxes(MovingObjectPosition mop, EntityPlayer player, float frame) {
    
        return false;
    }
    
    @Override
    public void markPartForRenderUpdate() {
    
        if (world() == null) return;
        
        world().markBlockRangeForRenderUpdate(x(), y(), z(), x(), y(), z());
    }
    
    @Override
    public float getHardness(MovingObjectPosition mop, EntityPlayer player) {
    
        return 0;
    }
    
    @Override
    public float getExplosionResistance() {
    
        return 0;
    }
    
    @Override
    public int getLightValue() {
    
        return 0;
    }
    
    @Override
    public ItemStack getPickedItem(MovingObjectPosition mop) {
    
        return null;
    }
    
    @Override
    public List<ItemStack> getDrops() {
    
        return null;
    }
    
    @Override
    public void onAdded() {
    
    }
    
    @Override
    public void onRemoved() {
    
    }
    
    @Override
    public void onPartChanged() {
    
    }
    
    @Override
    public void onEntityCollide(Entity entity) {
    
    }
    
    @Override
    public void onNeighborUpdate() {
    
    }
    
    @Override
    public boolean onActivated(EntityPlayer player, MovingObjectPosition mop, ItemStack item) {
    
        return false;
    }
    
    @Override
    public final void notifyNeighbors() {
    
        if (world() == null) return;
        
        world().notifyBlockChange(x(), y(), z(), world().getBlock(x(), y(), z()));
        markPartForRenderUpdate();
        
    }
    
    @Override
    public void sendUpdatePacket() {
    
        if (world() == null) return;
        PartUtils.sendUpdatePacket(this);
    }
    
    @Override
    public void save(NBTTagCompound tag) {
    
    }
    
    @Override
    public void load(NBTTagCompound tag) {
    
    }
    
    @Override
    public void writePacket(NBTTagCompound tag) {
    
    }
    
    @Override
    public void readPacket(NBTTagCompound tag) {
    
    }
    
    public CreativeTabs getCreativeTab() {
    
        return null;
    }
    
    public int getCreativeTabIndex() {
    
        return 0;
    }
    
    @Override
    public CreativeTabs[] getCreativeTabs() {
    
        return new CreativeTabs[] { getCreativeTab() };
    }
    
    @Override
    public int[] getCreativeTabIndexes() {
    
        int[] indexes = new int[getCreativeTabs().length];
        Arrays.fill(indexes, getCreativeTabIndex());
        return indexes;
    }
    
    @Override
    public boolean checkOcclusion(IPart part) {
    
        return false;
    }
    
    @Override
    public void addWailaInfo(List<String> info) {
    
    }
    
    @Override
    public boolean hasCustomItemEntity() {
    
        return false;
    }
    
    @Override
    public EntityItem createItemEntity(World w, double x, double y, double z, ItemStack item) {
    
        return null;
    }
    
    @Override
    public boolean canStay() {
    
        return true;
    }
    
    @Override
    public void dropIfCantStay() {
    
        if (!canStay()) {
            for (ItemStack is : getDrops()) {
                EntityItem item = new EntityItem(world(), x() + 0.5, y() + 0.5, z() + 0.5, is);
                item.motionX = world().rand.nextGaussian() * 0.05;
                item.motionY = world().rand.nextGaussian() * 0.05 + 0.2;
                item.motionZ = world().rand.nextGaussian() * 0.05;
                item.delayBeforeCanPickup = 10;
                world().spawnEntityInWorld(item);
            }
            PartUtils.removePart(this);
            return;
        }
    }
    
    @Override
    public boolean canPlacePart(ItemStack is, EntityPlayer player, Vector3 block, Vector3 hit) {
    
        return true;
    }
}
