package com.amadornes.lib.part;

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
import com.amadornes.lib.vec.Vector3;
import com.amadornes.lib.vec.Vector3Cube;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IPart {
    
    public void setParent(TileEntity te);
    
    public TileEntity getParent();
    
    public int x();
    
    public int y();
    
    public int z();
    
    public World world();
    
    public String getType();
    
    public String getUnlocalizedName();
    
    public void update();
    
    public List<Vector3Cube> getCollisionBoxes();
    
    public List<Vector3Cube> getSelectionBoxes();
    
    public List<Vector3Cube> getOcclusionBoxes();
    
    public AmaMOP rayTrace(Vector3 start, Vector3 end);
    
    @SideOnly(Side.CLIENT)
    public void renderDynamic(Vector3 pos, int pass, float partialTickTime);
    
    @SideOnly(Side.CLIENT)
    public boolean renderStatic(Vector3 pos, int pass);
    
    @SideOnly(Side.CLIENT)
    public boolean shouldRenderDynamicInPass(int pass);
    
    @SideOnly(Side.CLIENT)
    public boolean shouldRenderStaticInPass(int pass);
    
    @SideOnly(Side.CLIENT)
    public void renderItem(ItemStack item, ItemRenderType type, Object... data);
    
    @SideOnly(Side.CLIENT)
    public boolean renderSelectionBoxes(MovingObjectPosition mop, EntityPlayer player, float frame);
    
    @SideOnly(Side.CLIENT)
    public void markPartForRenderUpdate();
    
    public float getHardness(MovingObjectPosition mop, EntityPlayer player);
    
    public float getExplosionResistance();
    
    public int getLightValue();
    
    public ItemStack getPickedItem(MovingObjectPosition mop);
    
    public List<ItemStack> getDrops();
    
    public void onAdded();
    
    public void onRemoved();
    
    public void onPartChanged();
    
    public void onEntityCollide(Entity entity);
    
    public void onNeighborUpdate();
    
    public boolean onActivated(EntityPlayer player, MovingObjectPosition mop, ItemStack item);
    
    public void notifyNeighbors();
    
    public void sendUpdatePacket();
    
    public void save(NBTTagCompound tag);
    
    public void load(NBTTagCompound tag);
    
    public void writePacket(NBTTagCompound tag);
    
    public void readPacket(NBTTagCompound tag);
    
    public CreativeTabs[] getCreativeTabs();
    
    public int[] getCreativeTabIndexes();
    
    public boolean checkOcclusion(IPart part);
    
    public void addWailaInfo(List<String> info);
    
    public boolean hasCustomItemEntity();
    
    public EntityItem createItemEntity(World w, double x, double y, double z, ItemStack item);
    
    public boolean canStay();
    
    public void dropIfCantStay();
    
    public boolean canPlacePart(ItemStack is, EntityPlayer player, Vector3 block, Vector3 hit);
    
}
