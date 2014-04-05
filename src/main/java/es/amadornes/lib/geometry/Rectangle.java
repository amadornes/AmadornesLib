package es.amadornes.lib.geometry;

import es.amadornes.lib.vec.Vector3;

public class Rectangle {
    
    private Vector3 min, max;
    
    public Rectangle(Vector3 a, Vector3 b) {
    
        double x = Math.min(a.getX(), b.getX());
        double y = Math.min(a.getY(), b.getY());
        double z = Math.min(a.getZ(), b.getZ());
        double x1 = Math.max(a.getX(), b.getX());
        double y1 = Math.max(a.getY(), b.getY());
        double z1 = Math.max(a.getZ(), b.getZ());
        
        min = new Vector3(x, y, z);
        max = new Vector3(x1, y1, z1);
    }
    
    public Rectangle(double minx, double miny, double minz, double maxx, double maxy, double maxz){
        min = new Vector3(minx, miny, minz);
        max = new Vector3(maxx, maxy, maxz);
    }
    
    public Vector3 getMin() {
    
        return min;
    }
    
    public Vector3 getMax() {
    
        return max;
    }
    
}
