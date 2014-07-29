package com.amadornes.lib.raytrace;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;

import com.amadornes.lib.misc.MathHelper;
import com.amadornes.lib.vec.Vector3;
import com.amadornes.lib.vec.Vector3Cube;

public class RayTracer {
    
    /*
     * The following methods are from CodeChickenLib, credits to ChickenBones for this. CodeChickenLib can be found here:
     * http://files.minecraftforge.net/CodeChickenLib/
     */
    public static Vec3 getCorrectedHeadVec(EntityPlayer player) {
    
        Vec3 v = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
        if (player.worldObj.isRemote) {
            // compatibility with eye height changing mods
            v.yCoord += player.getEyeHeight() - player.getDefaultEyeHeight();
        } else {
            v.yCoord += player.getEyeHeight();
            if (player instanceof EntityPlayerMP && player.isSneaking()) v.yCoord -= 0.08;
        }
        return v;
    }
    
    public static Vector3 getStartVec(EntityPlayer player) {
    
        return new Vector3(getCorrectedHeadVec(player));
    }
    
    public static Vector3 getEndVec(EntityPlayer player) {
    
        Vec3 headVec = getCorrectedHeadVec(player);
        Vec3 lookVec = player.getLook(1.0F);
        double reach = player.capabilities.isCreativeMode ? 5 : 4.5;
        return new Vector3(headVec.addVector(lookVec.xCoord * reach, lookVec.yCoord * reach, lookVec.zCoord * reach));
    }
    
    public static List<AmaMOP> rayTraceMulti(Vector3 start, Vector3 end, List<Vector3Cube> boxes) {
    
        List<AmaMOP> rayTraces = new ArrayList<AmaMOP>();
        
        for (Vector3Cube box : boxes) {
            AmaMOP mop = rayTraceBox(start, end, box);
            if (mop != null) rayTraces.add(mop);
        }
        
        return rayTraces;
    }
    
    public static AmaMOP rayTrace(EntityPlayer player, List<Vector3Cube> boxes) {
    
        return rayTrace(getStartVec(player), getEndVec(player), boxes);
    }
    
    public static AmaMOP rayTrace(Vector3 start, Vector3 end, List<Vector3Cube> boxes) {
    
        List<AmaMOP> rayTraces = rayTraceMulti(start, end, boxes);
        AmaMOP mop = null;
        
        for (AmaMOP m : rayTraces)
            if (mop == null || m.hitVec.distanceTo(start.toVec3()) < mop.hitVec.distanceTo(start.toVec3())) mop = m;
        
        rayTraces.clear();
        
        return mop;
    }
    
    public static Vector3 rayTraceSide(Vector3 start, Vector3 end, Vector3Cube box, int side) {
    
        Vector3 hit = null;
        switch (side) {
            case 0:
                hit = start.XZintercept(end, box.getMinY());
                break;
            case 1:
                hit = start.XZintercept(end, box.getMaxY());
                break;
            case 2:
                hit = start.XYintercept(end, box.getMinZ());
                break;
            case 3:
                hit = start.XYintercept(end, box.getMaxZ());
                break;
            case 4:
                hit = start.YZintercept(end, box.getMinX());
                break;
            case 5:
                hit = start.YZintercept(end, box.getMaxX());
        }
        
        if (hit == null) return null;
        if ((side == 0 || side == 1)
                && ((!MathHelper.isBetween(box.getMinX(), hit.getX(), box.getMaxX())) || (!MathHelper.isBetween(box.getMinZ(), hit.getZ(),
                        box.getMaxZ())))) return null;
        if ((side == 2 || side == 3) && (!MathHelper.isBetween(box.getMinX(), hit.getX(), box.getMaxX()))
                || (!MathHelper.isBetween(box.getMinY(), hit.getY(), box.getMaxY()))) return null;
        if ((side == 4 || side == 5) && (!MathHelper.isBetween(box.getMinY(), hit.getY(), box.getMaxY()))
                || (!MathHelper.isBetween(box.getMinZ(), hit.getZ(), box.getMaxZ()))) return null;
        
        return hit;
    }
    
    public static AmaMOP rayTraceBox(Vector3 start, Vector3 end, Vector3Cube box) {
    
        Vector3 vec = null;
        int side = -1;
        double dist = Double.MAX_VALUE;
        
        for (int i = 0; i < 6; i++) {
            Vector3 v = rayTraceSide(start, end, box, i);
            if (v == null) continue;
            
            double d = v.distanceTo(start);
            
            if (vec == null && dist > d) {
                vec = v;
                side = i;
                dist = d;
            }
        }
        
        if (vec == null) return null;
        
        return new AmaMOP(vec.getBlockX(), vec.getBlockY(), vec.getBlockZ(), side, vec);
    }
    
}
