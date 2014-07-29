package com.amadornes.lib.part;

import net.minecraft.item.ItemStack;

public abstract class IPartProvider {
    
    public abstract IPart createPart(String id);
    
    public ItemStack createItem(String id) {
    
        return null;
    }
    
    public String getPartType(ItemStack item) {
    
        return null;
    }
    
}
