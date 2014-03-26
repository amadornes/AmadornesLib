package es.amadornes.lib.render;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;


public class RenderUtils {
    
    public static final FloatBuffer asFloatBuffer(float[] values){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
}
