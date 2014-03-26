package es.amadornes.lib.render;

import org.lwjgl.opengl.GL11;

public class RenderedObject {
    
    private RenderedObject() {
    
    }
    
    private int renderId = -1;
    
    protected void render() {
    
        if (hasBeenSaved()) {
            GL11.glCallList(renderId);
        }
    }
    
    public boolean hasBeenSaved() {
    
        return GL11.glIsList(renderId);
    }
    
    public void startSaving() {
    
        renderId = GL11.glGenLists(1);
        GL11.glNewList(GL11.GL_LIST_MODE, GL11.GL_COMPILE);
    }
    
    public void save() {
    
        GL11.glEndList();
    }
    
}
