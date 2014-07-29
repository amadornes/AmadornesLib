package com.amadornes.lib.part.fmp;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.render.RenderUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.IRedstonePart;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.TMultiPart;

import com.amadornes.lib.ALModInfo;
import com.amadornes.lib.part.IPart;
import com.amadornes.lib.part.IPartRedstone;
import com.amadornes.lib.part.PartRegistry;
import com.amadornes.lib.raytrace.AmaMOP;
import com.amadornes.lib.raytrace.RayTracer;
import com.amadornes.lib.vec.Vector3Cube;

public class MultipartAmaPart extends TMultiPart implements IRedstonePart, JNormalOcclusion {
    
    private IPart part;
    
    public MultipartAmaPart(IPart part) {
    
        setPart(part);
    }
    
    public MultipartAmaPart() {
    
    }
    
    public IPart getPart() {
    
        return part;
    }
    
    protected void setPart(IPart part) {
    
        this.part = part;
    }
    
    @Override
    public String getType() {
    
        return ALModInfo.MODID + "_" + getPart().getType();
    }
    
    @Override
    public Iterable<IndexedCuboid6> getSubParts() {
    
        List<IndexedCuboid6> cubes = new ArrayList<IndexedCuboid6>();
        List<Vector3Cube> boxes = getPart().getSelectionBoxes();
        if (boxes == null) return cubes;
        for (Vector3Cube c : boxes)
            cubes.add(new IndexedCuboid6(0, new Cuboid6(c.toAABB())));
        return cubes;
    }
    
    @Override
    public Iterable<Cuboid6> getCollisionBoxes() {
    
        List<Cuboid6> cubes = new ArrayList<Cuboid6>();
        List<Vector3Cube> boxes = getPart().getCollisionBoxes();
        if (boxes == null) return cubes;
        for (Vector3Cube c : boxes)
            cubes.add(new Cuboid6(c.toAABB()));
        return cubes;
    }
    
    @Override
    public Iterable<Cuboid6> getOcclusionBoxes() {
    
        List<Cuboid6> cubes = new ArrayList<Cuboid6>();
        List<Vector3Cube> boxes = getPart().getOcclusionBoxes();
        if (boxes == null) return cubes;
        for (Vector3Cube c : boxes)
            cubes.add(new Cuboid6(c.toAABB()));
        return cubes;
    }
    
    @Override
    public boolean occlusionTest(TMultiPart part) {
    
        return NormalOcclusionTest.apply(this, part);
    }
    
    @Override
    public void writeDesc(MCDataOutput packet) {
    
        super.writeDesc(packet);
        
        packet.writeString(getPart().getType());
        
        NBTTagCompound tag = new NBTTagCompound();
        getPart().save(tag);
        packet.writeNBTTagCompound(tag);
    }
    
    @Override
    public void readDesc(MCDataInput packet) {
    
        super.readDesc(packet);
        
        String type = packet.readString();
        if (getPart() == null) setPart(PartRegistry.createPart(type));
        
        getPart().load(packet.readNBTTagCompound());
    }
    
    @Override
    public void load(NBTTagCompound tag) {
    
        super.load(tag);
        String type = tag.getString("part_id");
        if (getPart() == null) setPart(PartRegistry.createPart(type));
        
        NBTTagCompound t = tag.getCompoundTag("partData");
        getPart().load(t);
    }
    
    @Override
    public void save(NBTTagCompound tag) {
    
        super.save(tag);
        tag.setString("part_id", getPart().getType());
        
        NBTTagCompound t = new NBTTagCompound();
        getPart().save(t);
        tag.setTag("partData", t);
    }
    
    @Override
    public int getLightValue() {
    
        return getPart().getLightValue();
    }
    
    @Override
    public Iterable<ItemStack> getDrops() {
    
        return getPart().getDrops();
    }
    
    @Override
    public ItemStack pickItem(MovingObjectPosition hit) {
    
        return getPart().getPickedItem(hit);
    }
    
    @Override
    public float getStrength(MovingObjectPosition hit, EntityPlayer player) {
    
        float s = 50;
        float h = getPart().getHardness(hit, player) * (s / 2F);
        if (h == 0) return s;
        
        return s / h;
    }
    
    // Redstone
    
    @Override
    public int strongPowerLevel(int side) {
    
        return weakPowerLevel(side);
    }
    
    @Override
    public int weakPowerLevel(int side) {
    
        if (getPart() instanceof IPartRedstone) return ((IPartRedstone) getPart()).getWeakOutput(ForgeDirection.getOrientation(side));
        
        return 0;
    }
    
    @Override
    public boolean canConnectRedstone(int side) {
    
        if (getPart() instanceof IPartRedstone) return ((IPartRedstone) getPart()).canConnect(ForgeDirection.getOrientation(side));
        
        return false;
    }
    
    // Events
    
    @Override
    public void onAdded() {
    
        getPart().onAdded();
    }
    
    @Override
    public void onRemoved() {
    
        getPart().onRemoved();
    }
    
    @Override
    public void onPartChanged(TMultiPart part) {
    
        getPart().onPartChanged();
    }
    
    @Override
    public void onEntityCollision(Entity entity) {
    
        getPart().onEntityCollide(entity);
    }
    
    @Override
    public void onNeighborChanged() {
    
        getPart().onNeighborUpdate();
    }
    
    @Override
    public boolean activate(EntityPlayer player, MovingObjectPosition hit, ItemStack item) {
    
        return getPart().onActivated(player, hit, item);
    }
    
    // Rendering
    
    @Override
    public boolean renderStatic(Vector3 pos, int pass) {
    
        return getPart().renderStatic(new com.amadornes.lib.vec.Vector3(pos.x, pos.y, pos.z), pass);
    }
    
    @Override
    public void renderDynamic(Vector3 pos, float frame, int pass) {
    
        getPart().renderDynamic(new com.amadornes.lib.vec.Vector3(pos.x, pos.y, pos.z), pass, frame);
    }
    
    private static int highlight = -1;
    
    @Override
    public boolean drawHighlight(MovingObjectPosition mop, EntityPlayer player, float frame) {
    
        GL11.glPushMatrix();
        
        GL11.glTranslated(x() - TileEntityRendererDispatcher.staticPlayerX, y() - TileEntityRendererDispatcher.staticPlayerY, z()
                - TileEntityRendererDispatcher.staticPlayerZ);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4d(0, 0, 0, 0);
        
        if (highlight == -1) highlight = GL11.glGenLists(1);
        
        GL11.glNewList(highlight, GL11.GL_COMPILE);
        boolean rendered = getPart().renderSelectionBoxes(mop, player, frame);
        GL11.glEndList();
        
        GL11.glColor4d(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
        GL11.glPopMatrix();
        
        if (rendered) {
            GL11.glCallList(highlight);
        } else {
            AmaMOP amamop = RayTracer.rayTrace(player, getPart().getSelectionBoxes());
            if (amamop != null && amamop.getCubeHit() != null) {
                RenderUtils.drawCuboidOutline(new Cuboid6(amamop.getCubeHit().toAABB()));
            } else {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean firstTick = true;
    
    @Override
    public void update() {
    
        if (firstTick) {
            getPart().setParent(tile());
            firstTick = false;
        }
        
        getPart().update();
    }
    
}
