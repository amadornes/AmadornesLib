package es.amadornes.lib.vec;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import codechicken.lib.vec.BlockCoord;

public class Vector3Tex extends Vector3 {
    
    private double texX = 0;
    private double texY = 0;
    
    public Vector3Tex(BlockCoord coord, double textureX, double textureY) {
    
        super(coord);
        
        this.texX = textureX;
        this.texY = textureY;
    }
    
    public Vector3Tex(BlockCoord coord, World w, double textureX,
            double textureY) {
    
        super(coord, w);
        
        this.texX = textureX;
        this.texY = textureY;
    }
    
    public Vector3Tex(double x, double y, double z, double textureX,
            double textureY) {
    
        super(x, y, z);
        
        this.texX = textureX;
        this.texY = textureY;
    }
    
    public Vector3Tex(double x, double y, double z, World w, double textureX,
            double textureY) {
    
        super(x, y, z, w);
        
        this.texX = textureX;
        this.texY = textureY;
    }
    
    public Vector3Tex(TileEntity te, double textureX, double textureY) {
    
        super(te);
        
        this.texX = textureX;
        this.texY = textureY;
    }
    
    public Vector3Tex(Vec3 vec, double textureX, double textureY) {
    
        super(vec);
        
        this.texX = textureX;
        this.texY = textureY;
    }
    
    public Vector3Tex(Vec3 vec, World w, double textureX, double textureY) {
    
        super(vec, w);
        
        this.texX = textureX;
        this.texY = textureY;
    }
    
    public Vector3Tex(Vector3 vector, double textureX, double textureY) {
    
        super(vector.x, vector.y, vector.z, vector.w);
        
        this.texX = textureX;
        this.texY = textureY;
    }
    
    public double getTextureX() {
    
        return texX;
    }
    
    public double getTextureY() {
    
        return texY;
    }
    
}
