package com.amadornes.lib.raytrace;

import net.minecraft.util.MovingObjectPosition;

import com.amadornes.lib.part.IPart;
import com.amadornes.lib.vec.Vector3;
import com.amadornes.lib.vec.Vector3Cube;

public class AmaMOP extends MovingObjectPosition {
    
    private IPart       partHit = null;
    private Vector3Cube cubeHit = null;
    
    public AmaMOP(int x, int y, int z, int side, Vector3 subHit, IPart partHit, Vector3Cube cubeHit) {
    
        super(x, y, z, side, subHit.toVec3());
        
        this.partHit = partHit;
        this.cubeHit = cubeHit;
    }
    
    public AmaMOP(int x, int y, int z, int side, Vector3 subHit, Vector3Cube cubeHit) {
    
        this(x, y, z, side, subHit, null, cubeHit);
    }
    
    public AmaMOP(int x, int y, int z, int side, Vector3 subHit) {
    
        this(x, y, z, side, subHit, null);
    }
    
    public AmaMOP(MovingObjectPosition mop, IPart partHit, Vector3Cube cubeHit) {
    
        this(mop.blockX, mop.blockY, mop.blockZ, mop.sideHit, new Vector3(mop.hitVec), partHit, cubeHit);
    }
    
    public AmaMOP(MovingObjectPosition mop, Vector3Cube cubeHit) {
    
        this(mop.blockX, mop.blockY, mop.blockZ, mop.sideHit, new Vector3(mop.hitVec), cubeHit);
    }
    
    public AmaMOP(MovingObjectPosition mop) {
    
        this(mop.blockX, mop.blockY, mop.blockZ, mop.sideHit, new Vector3(mop.hitVec));
    }
    
    public Vector3Cube getCubeHit() {
    
        return cubeHit;
    }
    
    public IPart getPartHit() {
    
        return partHit;
    }
    
    public void setPartHit(IPart partHit) {
    
        this.partHit = partHit;
    }
    
}
