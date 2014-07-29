package com.amadornes.lib.gui;

import java.util.List;

import com.amadornes.lib.gui.component.IComponent;

public interface IGui {
    
    public void renderBackground(int mouseX, int mouseY);
    
    public void renderForeground(int mouseX, int mouseY, float frame);
    
    public void renderComponents(int mouseX, int mouseY, float frame);
    
    public List<IComponent> getComponents();
    
    public void addComponent(IComponent comp);
    
    public void onClick(int x, int y, int button);
    
    public void onKeyPress(int key, char c);
    
    public IComponent getFocusedComponent();
    
    public boolean shouldPauseGame();
    
}
