package com.amadornes.lib.part.fmp;

import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;

import com.amadornes.lib.ALModInfo;
import com.amadornes.lib.part.IPart;
import com.amadornes.lib.part.IPartFace;
import com.amadornes.lib.part.PartRegistry;

public class RegisterMultiparts implements IPartFactory {
    
    private RegisterMultiparts() {
    
    }
    
    public static void register() {
    
        String[] parts = PartRegistry.getRegisteredParts().toArray(new String[0]);
        
        for (int i = 0; i < parts.length; i++)
            parts[i] = ALModInfo.MODID + "_" + parts[i];
        
        MultiPartRegistry.registerParts(new RegisterMultiparts(), parts);
    }
    
    @Override
    public TMultiPart createPart(String id, boolean client) {
    
        return createPart_(id, client, true);
    }
    
    public static TMultiPart createPart_(String id, boolean client, boolean multipartFactory) {
    
        IPart part = PartRegistry.createPart(multipartFactory ? id.substring((ALModInfo.MODID + "_").length()) : id);
        
        return createPart_(part);
    }
    
    public static TMultiPart createPart_(IPart part, boolean unused) {
    
        return createPart_(part);
    }
    
    public static TMultiPart createPart_(IPart part) {
    
        if (part != null) {
            if (part instanceof IPartFace) {
                return new MultipartAmaPartFace((IPartFace) part);
            } else {
                return new MultipartAmaPart(part);
            }
        }
        
        return null;
    }
    
}
