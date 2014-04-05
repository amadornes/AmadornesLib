package es.amadornes.lib.raytrace;

import es.amadornes.lib.geometry.Cube;
import es.amadornes.lib.geometry.Rectangle;
import es.amadornes.lib.vec.Vector3;

public class RayTracer {
    
    public static Hit rayTrace(Vector3 start, Vector3 end, Cube cube, int side) {
    
        /* System 1 */
        {
            Rectangle face = cube.getFace(side);
            
            double proportion = 0.75;
            
            Vector3 intersection = new Vector3((start.getX() * proportion)
                    + (end.getX() * (1 - proportion)),
                    (start.getY() * proportion)
                            + (end.getY() * (1 - proportion)),
                    (start.getZ() * proportion)
                            + (end.getZ() * (1 - proportion)));
            
            if (intersection.getX() < face.getMin().getX()) return null;
            if (intersection.getY() < face.getMin().getY()) return null;
            if (intersection.getZ() < face.getMin().getZ()) return null;
            if (intersection.getX() > face.getMax().getX()) return null;
            if (intersection.getY() > face.getMax().getY()) return null;
            if (intersection.getZ() > face.getMax().getZ()) return null;
            
            return new Hit(start, end, intersection, side);
        }
    }
    
    public static Hit rayTrace(Vector3 start, Vector3 end, Cube cube) {
    
        Hit[] hits = new Hit[6];
        
        for (int i = 0; i < 6; i++)
            hits[i] = rayTrace(start, end, cube, i);
        
        Hit closest = null;
        double dist = 0;
        
        for (int i = 0; i < 6; i++) {
            Hit h = hits[i];
            
            if (h == null) continue;
            
            if (closest == null) {
                closest = h;
                dist = start.distanceTo(start);
            } else {
                if (h.getHit().distanceTo(start) >= dist) continue;
                closest = h;
                dist = start.distanceTo(start);
            }
        }
        
        return closest;
    }
    
}
