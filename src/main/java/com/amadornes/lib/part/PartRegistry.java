package com.amadornes.lib.part;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PartRegistry {
    
    private static Map<String, IPartProvider> parts   = new HashMap<String, IPartProvider>();
    private static Map<String, IPart>         samples = new HashMap<String, IPart>();
    
    public static void registerPart(String part, IPartProvider provider) {
    
        if (part == null) throw new RuntimeException("");
        if (parts.containsKey(part)) throw new RuntimeException("The part \"" + part + "\" has already been registered!");
        parts.put(part, provider);
        samples.put(part, provider.createPart(part));
    }
    
    public static void registerParts(IPartProvider provider, String... parts) {
    
        for (String part : parts)
            registerPart(part, provider);
    }
    
    public static IPart createPart(String type) {
    
        if (parts.containsKey(type)) return parts.get(type).createPart(type);
        
        return null;
    }
    
    public static IPart getSample(String type) {
    
        if (samples.containsKey(type)) return samples.get(type);
        
        return null;
    }
    
    public static ItemStack getItem(String type) {
    
        if (parts.containsKey(type)) return parts.get(type).createItem(type);
        
        return null;
    }
    
    public static String getPartId(ItemStack is) {
    
        for (String p : parts.keySet()) {
            String s = parts.get(p).getPartType(is);
            if (s != null && s.trim().length() > 0) return s;
        }
        
        return null;
    }
    
    public static IPart createPartFromStack(ItemStack is) {
    
        String type = getPartId(is);
        if (type == null) return null;
        
        return createPart(type);
    }
    
    public static IPart getSampleFromStack(ItemStack is) {
    
        String type = getPartId(is);
        if (type == null) return null;
        
        if (samples.containsKey(type)) return samples.get(type);
        
        return null;
    }
    
    public static List<String> getRegisteredParts() {
    
        List<String> parts = new ArrayList<String>();
        parts.addAll(PartRegistry.parts.keySet());
        return parts;
    }
    
    public static List<String> getRegisteredPartsForTab(CreativeTabs tab) {
    
        List<String> partIds = new ArrayList<String>();
        List<IPart> parts = new ArrayList<IPart>();
        
        if (tab != null) {
            for (IPart p : PartRegistry.samples.values())
                if (Arrays.asList(p.getCreativeTabs()).contains(tab)) parts.add(p);
            Collections.sort(parts, new ComparatorCreativeTabIndex(tab));
            for (IPart p : parts)
                partIds.add(p.getType());
        }
        
        return Collections.unmodifiableList(new ArrayList<String>(partIds));
    }
    
}
