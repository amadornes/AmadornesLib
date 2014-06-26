package com.amadornes.lib;

import com.amadornes.lib.packet.NetworkHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = ALModInfo.MODID, name = ALModInfo.NAME, version = ALModInfo.VERSION, canBeDeactivated = true, acceptableRemoteVersions = "*")
public class AmadornesLib {
    
    @EventHandler
    public void init(FMLInitializationEvent ev) {
    
        NetworkHandler.init();
    }
}
