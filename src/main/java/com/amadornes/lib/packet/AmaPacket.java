package com.amadornes.lib.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public abstract class AmaPacket<REQ> implements IMessage, IMessageHandler<AmaPacket<REQ>, AmaPacket<REQ>> {
    
    public abstract void write(NBTTagCompound tag);
    
    public abstract void read(NBTTagCompound tag);
    
    public abstract void handle(Side side);
    
    @Override
    public void fromBytes(ByteBuf buf) {// Read
    
//        try {
//            byte[] bytes = buf.array();
//            NBTTagCompound tag = CompressedStreamTools.decompress(bytes);
//            read(tag);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    
    @Override
    public void toBytes(ByteBuf buf) {// Write
    
//        try {
//            NBTTagCompound tag = new NBTTagCompound();
//            write(tag);
//            byte[] data = CompressedStreamTools.compress(tag);
//            buf.writeBytes(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    
    @Override
    public AmaPacket<REQ> onMessage(AmaPacket<REQ> message, MessageContext ctx) {
    
        handle(ctx.side);
        
        return null;
    }
    
}
