package com.amadornes.lib.util;

import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.amadornes.lib.block.AmaBlock;
import com.amadornes.lib.render.AmaTESR;
import com.amadornes.lib.tile.AmaTileEntity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class AmaUtils {
    
    public static AmaBlock registerBlock(AmaBlock block) {
    
        GameRegistry.registerBlock(block, block.getBlockId());
        return block;
    }
    
    public static Class<? extends AmaTileEntity> registerTE(Class<? extends AmaTileEntity> te) {
    
        GameRegistry.registerTileEntity(te, formatClass(te.getName()));
        return te;
    }
    
    public static AmaBlock registerBlockAndTE(AmaBlock block, Class<? extends AmaTileEntity> te) {
    
        registerBlock(block);
        registerTE(te);
        return block;
    }
    
    public static void registerTESR(AmaTESR tesr, Class<? extends AmaTileEntity> te) {
    
        ClientRegistry.bindTileEntitySpecialRenderer(te, tesr);
    }
    
    public static void registerISBRH(ISimpleBlockRenderingHandler isbrh) {
    
        RenderingRegistry.registerBlockHandler(isbrh);
    }
    
    public static void registerItemRenderer(IItemRenderer iir, Item item) {
    
        MinecraftForgeClient.registerItemRenderer(item, iir);
    }
    
    public static void registerRenderers(Object o, Class<? extends AmaTileEntity> te, Item item) {
    
        if (o instanceof AmaTESR) registerTESR((AmaTESR) o, te);
        if (o instanceof ISimpleBlockRenderingHandler) registerISBRH((ISimpleBlockRenderingHandler) o);
        if (o instanceof IItemRenderer) registerItemRenderer((IItemRenderer) o, item);
    }
    
    // Helpers
    
    private static String formatClass(String className) {
    
        if (className.contains(".")) className = className.substring(className.lastIndexOf(".") + 1);
        
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        
        return className;
    }
    
}
