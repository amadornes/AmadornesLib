package com.amadornes.lib.gui.component;

import com.amadornes.lib.gui.IGui;

public interface IComponent {
    
    public void render(int mx, int my, float f);
    
    public void setX(int x);
    
    public void setY(int y);
    
    public int getX();
    
    public int getY();
    
    public void onMouseClick(int x, int y, int btn);
    
    public void onKeyPress(int key, char c);
    
    public boolean hasFocus();
    
    public boolean canHaveFocus();
    
    public IGui getParent();
    
    public boolean isInsideBounds(int x, int y);
    
}
