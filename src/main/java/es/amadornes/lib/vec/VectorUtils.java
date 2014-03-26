package es.amadornes.lib.vec;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.chunk.Chunk;

public class VectorUtils {
    
    public static void removeTileEntityWithoutInvalidating(Vector3 vector) {
    
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
        vector.getWorld().func_96440_m(vector.getBlockX(), vector.getBlockY(),
                vector.getBlockZ(), 0);
    }
    
}
