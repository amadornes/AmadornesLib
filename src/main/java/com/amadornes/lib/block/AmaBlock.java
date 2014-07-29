package com.amadornes.lib.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.Loader;

public class AmaBlock extends Block {
    
    private String blockId;
    
    public AmaBlock(Material material, String blockId) {
    
        super(material);
        setBlockName(this.blockId = Loader.instance().activeModContainer().getModId() + "." + blockId);
    }
    
    public String getBlockId() {
    
        return blockId;
    }
    
}
