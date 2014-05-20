package com.amadornes.lib.vec;

import java.util.List;

import com.amadornes.lib.list.LinkedList;

import net.minecraft.world.World;

public class VectorList extends LinkedList<Vector3> {
    
    public VectorList(List<Vector3> copied) {
        this();
        addAll(copied);
    }
    
    public VectorList() {
    
        super();
    }
    
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
