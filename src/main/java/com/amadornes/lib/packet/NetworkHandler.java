package com.amadornes.lib.packet;

import net.minecraft.entity.player.EntityPlayerMP;

import com.amadornes.lib.ALModInfo;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * This code was made by MineMaarten for BluePower (https://github.com/Quetzi/BluePower)
 * @author MineMaarten
 * 
 */
@SuppressWarnings("rawtypes")
public class NetworkHandler {
    
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ALModInfo.MODID);
    
    public static void init() {
    
        registerPacket(0, PacketTileEntityUpdate.class);
        registerPacket(1, PacketTileUpdate.class);
    }
    
    @SuppressWarnings("unchecked")
    public static void registerPacket(int id, Class packet) {
    
        INSTANCE.registerMessage(packet, packet, id, Side.SERVER);
    }
    
    public static void sendToAll(AmaPacket message) {
    
        INSTANCE.sendToAll(message);
    }
    
    public static void sendTo(AmaPacket message, EntityPlayerMP player) {
    
        INSTANCE.sendTo(message, player);
    }
    
    public static void sendToAllAround(AmaPacket message, NetworkRegistry.TargetPoint point) {
    
        INSTANCE.sendToAllAround(message, point);
    }
    
    public static void sendToDimension(AmaPacket message, int dimensionId) {
    
        INSTANCE.sendToDimension(message, dimensionId);
    }
    
    public static void sendToServer(AmaPacket message) {
    
        INSTANCE.sendToServer(message);
    }
}
