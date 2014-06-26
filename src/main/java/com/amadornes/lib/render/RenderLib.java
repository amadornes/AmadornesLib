package com.amadornes.lib.render;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.OpenGlHelper;

import org.lwjgl.opengl.GL11;

import com.amadornes.lib.vec.Rotation;

public class RenderLib {
    
    private RenderLib() {
    
    }
    
    private static TranslateMode        mode      = TranslateMode.RELATIVE;
    private static final List<Rotation> rotations = new ArrayList<Rotation>();
    private static double               scaleX    = 1;
    private static double               scaleY    = 1;
    private static double               scaleZ    = 1;
    private static float                lightmapX = -1, lightmapY = -1;
    
    public static void setMode(TranslateMode mode) {
    
        RenderLib.mode = mode;
    }
    
    public static TranslateMode getMode() {
    
        return mode;
    }
    
    private static void rotateAll(boolean positive) {
    
        if (positive) {
            for (int i = 0; i < rotations.size(); i++) {
                
                Rotation r = rotations.get(i);
                
                GL11.glRotated(r.getX(), 1, 0, 0);
                GL11.glRotated(r.getY(), 0, 1, 0);
                GL11.glRotated(r.getZ(), 0, 0, 1);
                
            }
        } else {
            for (int i = (rotations.size() - 1); i >= 0; i--) {
                
                Rotation r = rotations.get(i);
                
                GL11.glRotated(-r.getX(), 1, 0, 0);
                GL11.glRotated(-r.getY(), 0, 1, 0);
                GL11.glRotated(-r.getZ(), 0, 0, 1);
            }
        }
    }
    
    public static void resetRotation() {
    
        rotateAll(false);
        
        rotations.clear();
    }
    
    public static void rr() {
    
        resetRotation();
    }
    
    public static void resetScale() {
    
        GL11.glScaled(1 / scaleX, 1 / scaleY, 1 / scaleZ);
        
        scaleX = 1;
        scaleY = 1;
        scaleZ = 1;
    }
    
    public static void rs() {
    
        resetScale();
    }
    
    public static void translate(double x, double y, double z) {
    
        if (mode == TranslateMode.ABSOLUTE) {
            rotateAll(false);
            GL11.glScaled(1 / scaleX, 1 / scaleY, 1 / scaleZ);
        }
        
        GL11.glTranslated(x, y, z);
        
        if (mode == TranslateMode.ABSOLUTE) {
            rotateAll(true);
            GL11.glScaled(scaleX, scaleY, scaleZ);
        }
    }
    
    public static void t(double x, double y, double z) {
    
        translate(x, y, z);
    }
    
    public static void tx(double amt) {
    
        translate(amt, 0, 0);
    }
    
    public static void ty(double amt) {
    
        translate(0, amt, 0);
    }
    
    public static void tz(double amt) {
    
        translate(0, 0, amt);
    }
    
    public static void rotate(double x, double y, double z) {
    
        rotations.add(new Rotation(x, y, z));
        
        GL11.glRotated(x, 1, 0, 0);
        GL11.glRotated(y, 0, 1, 0);
        GL11.glRotated(z, 0, 0, 1);
    }
    
    public static void r(double x, double y, double z) {
    
        rotate(x, y, z);
    }
    
    public static void rx(double amt) {
    
        rotate(amt, 0, 0);
    }
    
    public static void ry(double amt) {
    
        rotate(0, amt, 0);
    }
    
    public static void rz(double amt) {
    
        rotate(0, 0, amt);
    }
    
    public static void scale(double x, double y, double z) {
    
        scaleX *= x;
        scaleY *= y;
        scaleZ *= z;
        
        GL11.glScaled(x, y, z);
    }
    
    public static void scale(double amt) {
    
        scale(amt, amt, amt);
    }
    
    public static void s(double x, double y, double z) {
    
        scale(x, y, z);
    }
    
    public static void s(double amt) {
    
        scale(amt, amt, amt);
    }
    
    public static void sX(double amt) {
    
        scale(amt, 1, 1);
    }
    
    public static void sY(double amt) {
    
        scale(1, amt, 1);
    }
    
    public static void sZ(double amt) {
    
        scale(1, 1, amt);
    }
    
    public static void enableBlend() {
    
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
    
    public static void disableBlend() {
    
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public static void enableCullFace() {
    
        GL11.glEnable(GL11.GL_CULL_FACE);
    }
    
    public static void disableCullFace() {
    
        GL11.glDisable(GL11.GL_CULL_FACE);
    }
    
    public static void enableDepth() {
    
        GL11.glEnable(GL11.GL_DEPTH);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    
    public static void disableDepth() {
    
        GL11.glDisable(GL11.GL_DEPTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
    }
    
    public static void disableLightingCheck() {
    
        setLightmapPosition(1);
    }
    
    public static void setColor(double r, double g, double b, double a) {
    
        if (a >= 0) {
            GL11.glColor4d(r, g, b, a);
        } else {
            GL11.glColor3d(r, g, b);
        }
    }
    
    public static void setColor(int rgb) {
    
        int red = (rgb & 0xFF000000) >> 24;
        int green = (rgb & 0x00FF0000) >> 16;
        int blue = (rgb & 0x0000FF00) >> 8;
        
        setColor(red / 255D, green / 255D, blue / 255D, -1);
    }
    
    public static void c(double r, double g, double b, double a) {
    
        setColor(r, g, b, a);
    }
    
    public static void c(int rgb) {
    
        setColor(rgb);
    }
    
    public static void setLightmapPosition(double pos) {
    
        if (lightmapX == -1 || lightmapY == -1) {
            lightmapX = OpenGlHelper.lastBrightnessX;
            lightmapY = OpenGlHelper.lastBrightnessY;
        }
        
        float j = (float) (235F * pos);
        float k = j % 65536;
        float l = j / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k, l);
    }
    
    public static void resetLightmap() {
    
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightmapY, lightmapY);
        lightmapX = -1;
        lightmapY = -1;
    }
    
}
