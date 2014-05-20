package com.amadornes.lib;

import com.amadornes.lib.packet.PacketHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ALModInfo.MODID, name = ALModInfo.NAME, version = ALModInfo.VERSION, canBeDeactivated = true, acceptableRemoteVersions = "*")
public class AmadornesLib {

	@SubscribeEvent
	public void init(FMLInitializationEvent ev) {
		PacketHandler.instance();
	}
}
