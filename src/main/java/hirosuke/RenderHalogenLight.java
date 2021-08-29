package hirosuke;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

import java.util.*;

public class RenderHalogenLight extends Block implements ISimpleBlockRenderingHandler {

    protected RenderHalogenLight(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) { }


    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (modelId == this.getRenderId())
        {

            renderer.setRenderBounds(0.4375D, 0.0D, 0.0D, 0.5625D, 0.125D, 1.0D);
            maxX = this.getBlockBoundsMaxX();
            minX = this.getBlockBoundsMinX();
            maxY = this.getBlockBoundsMaxY();
            minY = this.getBlockBoundsMinY();
            maxZ = this.getBlockBoundsMaxZ();
            minZ = this.getBlockBoundsMinZ();
            AxisAlignedBB bound = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
            List<AxisAlignedBB> list = new List<AxisAlignedBB>() {
                public int size() {
                    return 0;
                }

                public boolean isEmpty() {
                    return false;
                }

                public boolean contains(Object o) {
                    return false;
                }

                public Iterator<AxisAlignedBB> iterator() {
                    return null;
                }

                public Object[] toArray() {
                    return new Object[0];
                }

                public <T> T[] toArray(T[] a) {
                    return null;
                }

                public boolean add(AxisAlignedBB axisAlignedBB) {
                    return false;
                }

                public boolean remove(Object o) {
                    return false;
                }

                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                public boolean addAll(Collection<? extends AxisAlignedBB> c) {
                    return false;
                }

                public boolean addAll(int index, Collection<? extends AxisAlignedBB> c) {
                    return false;
                }

                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                public void clear() {

                }

                public boolean equals(Object o) {
                    return false;
                }

                public int hashCode() {
                    return 0;
                }

                public AxisAlignedBB get(int index) {
                    return null;
                }

                public AxisAlignedBB set(int index, AxisAlignedBB element) {
                    return null;
                }

                public void add(int index, AxisAlignedBB element) {

                }

                public AxisAlignedBB remove(int index) {
                    return null;
                }

                public int indexOf(Object o) {
                    return 0;
                }

                public int lastIndexOf(Object o) {
                    return 0;
                }

                public ListIterator<AxisAlignedBB> listIterator() {
                    return null;
                }

                public ListIterator<AxisAlignedBB> listIterator(int index) {
                    return null;
                }

                public List<AxisAlignedBB> subList(int fromIndex, int toIndex) {
                    return null;
                }
            };
            list.add(bound);
            BlockHalogenLight blockHalogen = new BlockHalogenLight();
            blockHalogen.addCollisionBoxesToList(renderer.minecraftRB.theWorld, 0, 0, 0, bound, list, null);
            this.setBlockBounds(0.4375f, 0.0f, 0.0f, 0.5625f, 0.125f, 1.0f);
            EntityLivingBase player = renderer.minecraftRB.thePlayer;
            int dir = MathHelper.floor_double((player.rotationYaw * 4F) / 360F + 0.5D) & 3;
            renderer.renderStandardBlock(block, x, y, z);
            GL11.glPushMatrix();
            if (dir == 0)
            {
                GL11.glRotatef(-180F, 0.0F, 1.0F, 0.0F);
            }

            if (dir % 2 != 0)
            {
                GL11.glRotatef(dir * (-90F), 0.0F, 1.0F, 0.0F);
            }

            if (dir % 2 == 0)
            {
                GL11.glRotatef(dir * (-180F), 0.0F, 1.0F, 0.0F);
            }
            GL11.glPopMatrix();
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return HalogenLight.RenderID;
    }
}
