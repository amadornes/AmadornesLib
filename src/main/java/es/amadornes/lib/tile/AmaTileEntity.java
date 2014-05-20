package es.amadornes.lib.tile;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;

public abstract class AmaTileEntity extends TileEntity {
    
    public void onPlace() {
    
    }
    
    public void onPlaceBy(EntityLivingBase who, ItemStack item) {
    
    }
    
    public void onBreak() {
    
    }
    
    public void onBreak(EntityPlayer player) {
    
    }
    
    public void onBreakByExplosion(Explosion explosion) {
    
    }
    
    public void writeDescription(NBTTagCompound tag) {
    
        writeToNBT(tag);
    }
    
    public void readDescription(NBTTagCompound tag) {
    
        readFromNBT(tag);
    }
    
    @Override
    public Packet getDescriptionPacket() {
    
        NBTTagCompound tag = new NBTTagCompound();
        writeDescription(tag);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
    
        readDescription(pkt.data);
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }
    
    public void sendUpdatePacket(){
        if(FMLCommonHandler.instance().getEffectiveSide().isServer()){
            PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
        }
    }
    
}
