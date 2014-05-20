package com.amadornes.lib.vec;

import java.util.Comparator;

public class VectorSorter implements Comparator<Vector3> {
    
    private Vector3 start;
    
    public VectorSorter(Vector3 start) {
    
        this.start = start;
    }
    
    @Override
    public int compare(Vector3 o1, Vector3 o2) {
    
        double d = o1.distanceTo(start) - o2.distanceTo(start);
        
        return d < 0 ? -1 : (d > 0 ? 1 : 0);
    }
    
}
