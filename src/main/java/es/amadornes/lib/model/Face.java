package es.amadornes.lib.model;

import org.lwjgl.opengl.GL11;

import es.amadornes.lib.vec.Vector3;
import es.amadornes.lib.vec.Vector3Tex;

public class Face {
    
    private Vector3[] vertices = new Vector3[0];
    
    public Face(Vector3[] vertices) {
    
        if (vertices != null) this.vertices = vertices;
    }
    
    public Face() {
    
    }
    
    public Vector3[] getVertices() {
    
        return vertices;
    }
    
    public Vector3 getVertex(int id) {
    
        try {
            return vertices[id];
        } catch (Exception ex) {
        }
        return null;
    }
    
    public int getVertexCount() {
    
        return vertices.length;
    }
    
    public void addVertex(Vector3 vertex) {
    
        Vector3[] vertices = new Vector3[this.vertices.length + 1];
        for(int i = 0; i < this.vertices.length; i++){
            vertices[i] = this.vertices[i];
        }
        vertices[this.vertices.length] = vertex;
        this.vertices = vertices;
    }
    
    public void addVertex(double x, double y, double z) {
    
        addVertex(new Vector3(x, y, z));
    }
    
    public void addVertex(double x, double y, double z, double u, double v) {
    
        addVertex(new Vector3Tex(x, y, z, u, v));
    }
    
    public void clear(){
        vertices = new Vector3[0];
    }
    
    public void render() {
    
        GL11.glBegin(GL11.GL_POLYGON);
        {
            for (Vector3 v : vertices) {
                if(v != null){
                    if (v instanceof Vector3Tex) {
                        Vector3Tex vt = (Vector3Tex) v;
                        GL11.glTexCoord2d(vt.getTextureX(), vt.getTextureY());
                        GL11.glVertex3d(vt.getX(), vt.getY(), vt.getZ());
                    } else {
                        GL11.glVertex3d(v.getX(), v.getY(), v.getZ());
                    }
                }
            }
        }
        GL11.glEnd();
    }
    
}
