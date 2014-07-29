package com.amadornes.lib.gui.component;

import com.amadornes.lib.gui.IGui;

public abstract class Component implements IComponent {
    
    protected int   x, y;
    private boolean hasFocus = false;
    protected IGui  parent;
    
    @Override
    public abstract void render(int mx, int my, float f);
    
    @Override
    public void setX(int x) {
    
        this.x = x;
    }
    
    @Override
    public void setY(int y) {
    
        this.y = y;
    }
    
    @Override
    public int getX() {
    
        return x;
    }
    
    @Override
    public int getY() {
    
        return y;
    }
    
    @Override
    public void onMouseClick(int x, int y, int btn) {
    
    }
    
    @Override
    public void onKeyPress(int key, char c) {
    
    }
    
    @Override
    public boolean hasFocus() {
    
        return hasFocus;
    }
    
    @Override
    public IGui getParent() {
    
        return parent;
    }
    
}
