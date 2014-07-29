package com.amadornes.lib.part;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import codechicken.multipart.JItemMultiPart;

import com.amadornes.lib.ALModInfo;
import com.amadornes.lib.mod.ModReference;
import com.amadornes.lib.part.fmp.FMPUtils;
import com.amadornes.lib.vec.Vector3;

import cpw.mods.fml.common.Optional;

public class ItemPart extends Item {
    
    public static IPart part = null;
    
    public ItemPart() {
    
        setUnlocalizedName(ALModInfo.MODID + ".part");
    }
    
    /**
     * This method is based on ChickenBones' code for Forge MultiPart
     * 
     * @author ChickenBones
     */
    public double getHitDepth(Vector3 hit, int side) {
    
        return hit.clone().scalarProject(Vector3.rotationAxes[side]) + (side % 2 ^ 1);
    }
    
    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    
        String id = "awesometorch";// PartRegistry.getPartId(item);
        // if (id == null) return false;
        
        IPart part = PartRegistry.createPart(id);
        if (part == null) return false;
        
        try {
            switch (PartType.FMP) {
                case AMAPART:
                    return placeAma(item, player, w, x, y, z, side, hitX, hitY, hitZ, part);
                case FMP:
                    return placeFMP(item, player, w, x, y, z, side, hitX, hitY, hitZ, part);
            }
        } catch (Exception ex) {
        }
        return false;
    }
    
    @Optional.Method(modid = ModReference.FMP_MODID)
    public boolean placeFMP(ItemStack item, EntityPlayer player, World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ,
            IPart part) {
    
        JItemMultiPart i = FMPUtils.getItemForPart(part);
        boolean oiu = i.onItemUse(item, player, w, x, y, z, side, hitX, hitY, hitZ);
        
        return oiu;
    }
    
    public boolean placeAma(ItemStack item, EntityPlayer player, World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ,
            IPart part) {
    
        return false;
    }
    
    @Override
    public boolean getHasSubtypes() {
    
        return false;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack item) {
    
        return ALModInfo.MODID + ".part." + PartRegistry.getPartId(item);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(Item unused, CreativeTabs tab, List l) {
    
        // NEI
        if (tab == null) for (CreativeTabs t : CreativeTabs.creativeTabArray)
            for (String s : PartRegistry.getRegisteredPartsForTab(t))
                l.add(PartRegistry.getItem(s));
    }
    
    @Override
    public int getDamage(ItemStack stack) {
    
        return super.getDamage(stack);// PartRegistry.getStackMetadata(stack);
    }
    
    @Override
    public boolean hasCustomEntity(ItemStack stack) {
    
        return PartRegistry.getSampleFromStack(stack).hasCustomItemEntity();
    }
    
    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
    
        if (PartRegistry.getSampleFromStack(itemstack).hasCustomItemEntity()) {
            EntityItem e = PartRegistry.getSampleFromStack(itemstack).createItemEntity(world, location.posX, location.posY, location.posZ, itemstack);
            e.delayBeforeCanPickup = 10;
            return e;
        }
        
        return super.createEntity(world, location, itemstack);
    }
    
}
