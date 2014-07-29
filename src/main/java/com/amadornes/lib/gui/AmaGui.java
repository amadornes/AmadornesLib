package com.amadornes.lib.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.amadornes.lib.gui.component.IComponent;

public abstract class AmaGui implements IGui {
    
    private List<IComponent> components = new ArrayList<IComponent>();
    private IComponent       focused    = null;
    
    @Override
    public List<IComponent> getComponents() {
    
        return components;
    }
    
    @Override
    public void addComponent(IComponent comp) {
    
        if (comp == null) return;
        
        components.add(comp);
    }
    
    @Override
    public void renderBackground(int mouseX, int mouseY) {
    
        ResourceLocation loc = getBackgroundTexture();
        if (loc == null) return;
        
        Minecraft.getMinecraft().renderEngine.bindTexture(loc);
    }
    
    public ResourceLocation getBackgroundTexture() {
    
        return null;
    }
    
    @Override
    public void renderForeground(int mouseX, int mouseY, float frame) {
    
    }
    
    @Override
    public void renderComponents(int mouseX, int mouseY, float frame) {
    
        for (IComponent c : getComponents()) {
            GL11.glPushMatrix();
            
            GL11.glTranslated(c.getX(), c.getY(), 0);
            c.render(mouseX, mouseY, frame);
            
            GL11.glPopMatrix();
        }
    }
    
    @Override
    public void onClick(int x, int y, int button) {
    
        IComponent clicked = null;
        
        for (IComponent c : getComponents())
            if (c.isInsideBounds(x, y)) clicked = c;
        
        if (clicked == null) return;
        
        if (clicked.canHaveFocus()) focused = clicked;
        
        clicked.onMouseClick(x - clicked.getX(), y - clicked.getY(), button);
    }
    
    @Override
    public void onKeyPress(int key, char c) {
    
        IComponent comp = getFocusedComponent();
        if (comp == null) return;
        
        comp.onKeyPress(key, c);
    }
    
    @Override
    public IComponent getFocusedComponent() {
    
        return focused;
    }
    
    @Override
    public boolean shouldPauseGame() {
    
        return false;
    }
    
}
