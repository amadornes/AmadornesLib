package es.amadornes.lib.render;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import es.amadornes.lib.vec.Vector3fMax;

public class RenderHelper {
    
    public static final void vertexWithTexture(double x, double y, double z,
            float tx, float ty) {
    
        GL11.glTexCoord2f(tx, ty);
        GL11.glVertex3d(x, y, z);
    }
    
    public static void drawTexturedCube(Vector3fMax vector) {
    
        // Top side:
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(),
                vector.getZMax(), 0.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(),
                vector.getZMax(), 1.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(),
                vector.getZMin(), 1.0F, 1.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(),
                vector.getZMin(), 0.0F, 1.0F);
        
        // Bottom side:
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(),
                vector.getZMax(), 0.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(),
                vector.getZMax(), 1.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(),
                vector.getZMin(), 1.0F, 1.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(),
                vector.getZMin(), 0.0F, 1.0F);
        
        // Draw west side:
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(),
                vector.getZMax(), 1.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(),
                vector.getZMax(), 1.0F, 1.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(),
                vector.getZMin(), 0.0F, 1.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(),
                vector.getZMin(), 0.0F, 0.0F);
        
        // Draw east side:
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(),
                vector.getZMin(), 1.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(),
                vector.getZMin(), 1.0F, 1.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(),
                vector.getZMax(), 0.0F, 1.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(),
                vector.getZMax(), 0.0F, 0.0F);
        
        // Draw north side
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(),
                vector.getZMin(), 1.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(),
                vector.getZMin(), 1.0F, 1.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(),
                vector.getZMin(), 0.0F, 1.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(),
                vector.getZMin(), 0.0F, 0.0F);
        
        // Draw south side
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(),
                vector.getZMax(), 0.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(),
                vector.getZMax(), 1.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(),
                vector.getZMax(), 1.0F, 1.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(),
                vector.getZMax(), 0.0F, 1.0F);
    }
    
    public static DoubleBuffer planeEquation(double x1, double y1, double z1,
            double x2, double y2, double z2, double x3, double y3, double z3) {
    
        double[] eq = new double[4];
        eq[0] = (y1 * (z2 - z3)) + (y2 * (z3 - z1)) + (y3 * (z1 - z2));
        eq[1] = (z1 * (x2 - x3)) + (z2 * (x3 - x1)) + (z3 * (x1 - x2));
        eq[2] = (x1 * (y2 - y3)) + (x2 * (y3 - y1)) + (x3 * (y1 - y2));
        eq[3] = -((x1 * ((y2 * z3) - (y3 * z2)))
                + (x2 * ((y3 * z1) - (y1 * z3))) + (x3 * ((y1 * z2) - (y2 * z1))));
        DoubleBuffer b = BufferUtils.createDoubleBuffer(8).put(eq);
        b.flip();
        return b;
    }
}
