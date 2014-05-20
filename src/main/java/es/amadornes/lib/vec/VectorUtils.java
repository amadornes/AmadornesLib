package es.amadornes.lib.vec;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.chunk.Chunk;

public class VectorUtils {
    
    public static void removeTileEntityWithoutInvalidating(Vector3 vector,
            boolean notifySurroundingBlocks) {
    
        Chunk chunk = vector.getWorld().getChunkFromChunkCoords(
                vector.getBlockX() >> 4, vector.getBlockZ() >> 4);
        if (chunk != null) {
            ChunkPosition chunkposition = new ChunkPosition(
                    vector.getBlockX() & 15, vector.getBlockY(),
                    vector.getBlockZ() & 15);
            
            if (chunk.isChunkLoaded) {
                chunk.chunkTileEntityMap.remove(chunkposition);
            }
        }
        // notify tile changes
        if (notifySurroundingBlocks) {
            vector.getWorld().func_96440_m(vector.getBlockX(),
                    vector.getBlockY(), vector.getBlockZ(), 0);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void setTileEntity(Vector3 vector, TileEntity te,
            boolean notifySurroundingBlocks) {
    
        if (te == null || te.isInvalid()) { return; }
        
        if (te.canUpdate()) {
            vector.getWorld().loadedTileEntityList.add(te);
        }
        
        Chunk chunk = vector.getWorld().getChunkFromChunkCoords(
                vector.getBlockX() >> 4, vector.getBlockZ() >> 4);
        if (chunk != null) {
            chunk.setChunkBlockTileEntity(vector.getBlockX() & 15,
                    vector.getBlockY(), vector.getBlockZ() & 15, te);
        }
        // notify tile changes
        if (notifySurroundingBlocks) {
            vector.getWorld().func_96440_m(vector.getBlockX(),
                    vector.getBlockY(), vector.getBlockZ(), 0);
        }
    }
    
}
