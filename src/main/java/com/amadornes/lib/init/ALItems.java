package com.amadornes.lib.init;

import net.minecraft.item.Item;

import com.amadornes.lib.ALModInfo;
import com.amadornes.lib.part.ItemPart;

import cpw.mods.fml.common.registry.GameRegistry;

public class ALItems {
    
    public static Item itemPart;
    
    public static void register() {
    
        itemPart = new ItemPart();
        GameRegistry.registerItem(itemPart, ALModInfo.MODID + ".part");
    }
    
}
