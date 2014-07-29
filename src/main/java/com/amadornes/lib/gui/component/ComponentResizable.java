package com.amadornes.lib.gui.component;

public abstract class ComponentResizable extends Component implements IComponentSize {
    
    private int width = 16, height = 16;
    
    @Override
    public void setWidth(int width) {
    
        this.width = width;
    }
    
    @Override
    public void setHeight(int height) {
    
        this.height = height;
    }
    
    @Override
    public int getWidth() {
    
        return width;
    }
    
    @Override
    public int getHeight() {
    
        return height;
    }
    
}
