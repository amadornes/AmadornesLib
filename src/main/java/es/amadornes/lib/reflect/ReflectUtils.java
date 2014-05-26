package es.amadornes.lib.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {
    
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object object, String name) {
    
        if (object == null) return null;
        
        Field f = getField(object, name);
        if (f == null) return null;
        
        try {
            return (T) f.get(object);
        } catch (Exception e) {
        }
        return null;
    }
    
    public static Method getMethod(Object object, String name, Class<?>[] params) {
    
        if (object == null) return null;
        
        return getMethod(object, object.getClass(), name, params);
    }
    
    private static Method getMethod(Object object, Class<?> c, String name, Class<?>[] params) {
    
        try {
            return object.getClass().getDeclaredMethod(name, params);
        } catch (Exception ex) {
        }
        
        try {
            return getMethod(object, c.getSuperclass(), name, params);
        } catch (Exception ex) {
        }
        return null;
    }
    
    public static Field getField(Object object, String name) {
    
        if (object == null) return null;
        
        return getField(object, object.getClass(), name);
    }
    
    private static Field getField(Object object, Class<?> c, String name) {
    
        try {
            return object.getClass().getDeclaredField(name);
        } catch (Exception ex) {
        }
        
        try {
            return getField(object, c.getSuperclass(), name);
        } catch (Exception ex) {
        }
        return null;
    }
    
}
