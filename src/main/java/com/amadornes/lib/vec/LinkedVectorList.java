package com.amadornes.lib.vec;

import com.amadornes.lib.list.LinkedList;

import net.minecraft.world.World;

public class LinkedVectorList extends LinkedList<Vector3> {
    
    private static final long serialVersionUID = 1L;
    
    public boolean add(double x, double y, double z) {
    
        return super.add(new Vector3(x, y, z));
    }
    
    public boolean add(double x, double y, double z, World w) {
    
        return super.add(new Vector3(x, y, z, w));
    }
    
    public boolean remove(double x, double y, double z) {
    
        return super.remove(new Vector3(x, y, z));
    }
    
    public boolean remove(double x, double y, double z, World w) {
    
        return super.remove(new Vector3(x, y, z, w));
    }
    
}
