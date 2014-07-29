package com.amadornes.lib.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public abstract class AmaTESR extends TileEntitySpecialRenderer {
    
    public TextureManager renderEngine() {
    
        return Minecraft.getMinecraft().renderEngine;
    }
    
    public FontRenderer fontRenderer() {
    
        return Minecraft.getMinecraft().fontRenderer;
    }
    
}
