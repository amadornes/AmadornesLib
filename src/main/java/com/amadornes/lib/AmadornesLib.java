package com.amadornes.lib;

import com.amadornes.lib.init.ALItems;
import com.amadornes.lib.mod.ModReference;
import com.amadornes.lib.packet.NetworkHandler;
import com.amadornes.lib.part.fmp.RegisterMultiparts;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = ALModInfo.MODID, name = ALModInfo.NAME, version = ALModInfo.VERSION, canBeDeactivated = true, acceptableRemoteVersions = "*")
public class AmadornesLib {
    
    @EventHandler
    public void init(FMLInitializationEvent ev) {
    
        NetworkHandler.init();
        ALItems.register();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent ev) {
    
        if (Loader.isModLoaded(ModReference.FMP_MODID)) RegisterMultiparts.register();
    }
}
