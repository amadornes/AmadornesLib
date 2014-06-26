package com.amadornes.lib.effect;

import net.minecraft.world.World;

public class ParticleUtils {
    
    public static void spawnParticle(World w, double x, double y, double z, Particle particle) {
    
        w.spawnEntityInWorld(new EntityFXParticle(w, x, y, z, particle));
    }
    
}
