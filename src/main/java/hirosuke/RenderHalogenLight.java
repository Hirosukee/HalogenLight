package hirosuke;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.*;
import java.util.logging.Logger;

public class RenderHalogenLight extends Block implements ISimpleBlockRenderingHandler {

    protected RenderHalogenLight(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) { }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
        if(world.isSideSolid(x, y, z, ForgeDirection.DOWN)) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        super.onBlockPlacedBy(world, x, y, z, entity, item);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (modelId == this.getRenderId())
        {
            maxX = this.getBlockBoundsMaxX();
            minX = this.getBlockBoundsMinX();
            maxY = this.getBlockBoundsMaxY();
            minY = this.getBlockBoundsMinY();
            maxZ = this.getBlockBoundsMaxZ();
            minZ = this.getBlockBoundsMinZ();

            setBlockBoundsBasedOnState(world, x, y, z, renderer);
            renderer.renderStandardBlock(block, x, y, z);
            return true;
        }
        return false;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z, RenderBlocks renderer) {
        int l = world.getBlockMetadata(x, y, z) & 3;
        if(l == 2) {
            this.setBlockBounds(0.4375f, 1 - 0.125f, 0.0f, 0.5625f, 1f, 1.0f);
            renderer.setRenderBounds(0.4375f, 1 - 0.125f, 0.0f, 0.5625f, 1f, 1.0f);
        } else {
            this.setBlockBounds(0.4375f, 0.0f, 0.0f, 0.5625f, 0.125f, 1.0f);
            renderer.setRenderBounds(0.4375f, 0.0f, 0.0f, 0.5625f, 0.125f, 1.0f);
        }
        super.setBlockBoundsBasedOnState(world, x, y, z);
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
