package com.amadornes.lib.part.fmp;

import net.minecraft.nbt.NBTTagCompound;
import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.multipart.IFaceRedstonePart;
import codechicken.multipart.TFacePart;

import com.amadornes.lib.part.IPartFace;
import com.amadornes.lib.part.Part;

public class MultipartAmaPartFace extends MultipartAmaPart implements TFacePart, IFaceRedstonePart {
    
    private IPartFace facePart;
    
    public MultipartAmaPartFace(IPartFace part) {
    
        super((Part) part);
        facePart = part;
    }
    
    @Override
    public int getSlotMask() {
    
        return 1 << getFace();
    }
    
    @Override
    public int redstoneConductionMap() {
    
        return 0;
    }
    
    @Override
    public boolean solid(int side) {
    
        return false;
    }
    
    @Override
    public int getFace() {
    
        return facePart.getFace() ^ 1;
    }
    
    @Override
    public void writeDesc(MCDataOutput packet) {
    
        super.writeDesc(packet);
        packet.writeInt(facePart.getFace());
        packet.writeInt(facePart.getRotation());
    }
    
    @Override
    public void readDesc(MCDataInput packet) {
    
        super.readDesc(packet);
        facePart.setFace(packet.readInt());
        facePart.setRotation(packet.readInt());
    }
    
    @Override
    public void save(NBTTagCompound tag) {
    
        super.save(tag);
        tag.setInteger("face", facePart.getFace());
        tag.setInteger("rotation", facePart.getRotation());
    }
    
    @Override
    public void load(NBTTagCompound tag) {
    
        super.load(tag);
        facePart.setFace(tag.getInteger("face"));
        facePart.setRotation(tag.getInteger("rotation"));
    }
}
