package es.amadornes.lib.raytrace;

import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3Pool;
import es.amadornes.lib.vec.Vector3;

public class Hit {
    
    private int     side = -1;
    private Vector3 hit  = null;
    
    private Vector3 start, end;
    
    public Hit(Vector3 start, Vector3 end, Vector3 hitVec, int sideHit) {
    
        this.start = start;
        this.end = end;
        this.hit = hitVec;
        this.side = sideHit;
    }
    
    public Vector3 getHit() {
    
        return hit;
    }
    
    public int getSide() {
    
        return side;
    }
    
    public Vector3 getStart() {
    
        return start;
    }
    
    public Vector3 getEnd() {
    
        return end;
    }
    
    public MovingObjectPosition toMOP() {
    
        return new MovingObjectPosition(hit.getBlockX(), hit.getBlockY(),
                hit.getBlockZ(), side, new Vec3Pool(-1, -1).getVecFromPool(
                        hit.getX() - hit.getBlockX(),
                        hit.getY() - hit.getBlockY(),
                        hit.getZ() - hit.getBlockZ()));
    }
    
    @Override
    public String toString() {
        
        String s = "{";
        
        /* Hit */
        s += "hit=";
        if(hit != null){
            s += "{";

            s += "x=" + hit.getX() + ",";
            s += "y=" + hit.getY() + ",";
            s += "z=" + hit.getZ();
            
            s += "}";
        }else{
            s += "null";
        }
        /* Comma */
        s += ",";
        /* Side hit */
        s += "side=" + side;
        
        return s + "}";
    }
    
}
