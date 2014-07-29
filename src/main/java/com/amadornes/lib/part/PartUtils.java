package com.amadornes.lib.part;

import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;

import com.amadornes.lib.mod.ModReference;
import com.amadornes.lib.part.fmp.MultipartAmaPart;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;

public class PartUtils {
    
    public static void sendUpdatePacket(IPart part) {
    
        PartType type = getPartType(part);
        
        switch (type) {
            case AMAPART:
                sendUpdatePacketAma(part);
                break;
            case FMP:
                sendUpdatePacketFMP(part);
                break;
        }
    }
    
    @Optional.Method(modid = ModReference.FMP_MODID)
    private static void sendUpdatePacketFMP(IPart part) {
    
        for (TMultiPart p : ((TileMultipart) part.getParent()).jPartList()) {
            if (p instanceof MultipartAmaPart) {
                if (((MultipartAmaPart) p).getPart() == part) {
                    p.sendDescUpdate();
                    return;
                }
            }
        }
    }
    
    private static void sendUpdatePacketAma(IPart part) {
    
        // TODO AMALIB: Send update packet (custom multipart system)
    }
    
    public static void removePart(IPart part) {
    
        PartType type = getPartType(part);
        
        switch (type) {
            case AMAPART:
                removePartAma(part);
                break;
            case FMP:
                removePartFMP(part);
                break;
        }
    }
    
    @Optional.Method(modid = ModReference.FMP_MODID)
    private static void removePartFMP(IPart part) {
    
        for (TMultiPart p : ((TileMultipart) part.getParent()).jPartList()) {
            if (p instanceof MultipartAmaPart) {
                if (((MultipartAmaPart) p).getPart() == part) {
                    ((TileMultipart) part.getParent()).remPart(p);
                    return;
                }
            }
        }
    }
    
    private static void removePartAma(IPart part) {
    
        // TODO AMALIB: Remove part (custom multipart system)
    }
    
    public static PartType getPartType(IPart part) {
    
        if (Loader.isModLoaded(ModReference.FMP_MODID) && isFMPPart(part)) return PartType.FMP;
        
        return PartType.AMAPART;
    }
    
    @Optional.Method(modid = ModReference.FMP_MODID)
    public static boolean isFMPPart(IPart part) {
    
        return part.getParent() instanceof TileMultipart;
    }
    
}
