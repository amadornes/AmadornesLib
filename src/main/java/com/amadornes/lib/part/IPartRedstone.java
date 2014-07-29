package com.amadornes.lib.part;

import net.minecraftforge.common.util.ForgeDirection;

public interface IPartRedstone {
    
    public boolean canConnect(ForgeDirection side);
    
    public int getStrongOutput(ForgeDirection side);
    
    public int getWeakOutput(ForgeDirection side);
    
}
