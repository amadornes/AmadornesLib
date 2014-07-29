package com.amadornes.lib.part.fmp;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.JItemMultiPart;
import codechicken.multipart.TMultiPart;

import com.amadornes.lib.part.IPart;

public class FMPUtils {
    
    private static IPart                part          = null;
    
    private static final JItemMultiPart multipartItem = new JItemMultiPart() {
                                                          
                                                          @Override
                                                          public TMultiPart newPart(ItemStack arg0, EntityPlayer arg1, World world, BlockCoord arg3,
                                                                  int arg4, codechicken.lib.vec.Vector3 arg5) {
                                                          
                                                              return RegisterMultiparts.createPart_(part, false);
                                                          }
                                                      };
    
    public static final JItemMultiPart getItemForPart(IPart part) {
    
        FMPUtils.part = part;
        return multipartItem;
    }
    
}
