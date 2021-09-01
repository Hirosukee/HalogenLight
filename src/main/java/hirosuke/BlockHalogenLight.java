package hirosuke;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockHalogenLight extends Block {

    public BlockHalogenLight() {
        super(Material.circuits);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.1f);
        this.setResistance(1.0f);
        this.setStepSound(Block.soundTypeCloth);
        this.setLightLevel(1f);
        this.setLightOpacity(1);
    }


    public IIcon Side0;
    public IIcon Side02;
    public IIcon Side1;
    public IIcon Side1top;
    public IIcon Side1top2;
    public IIcon Side2;
    public IIcon Side2top;
    public IIcon Side2top2;

    @Override
    public void registerBlockIcons(IIconRegister icon) {
        Side0 = icon.registerIcon(HalogenLight.modid + ":Side0");
        Side02 = icon.registerIcon(HalogenLight.modid + ":Side02");
        Side1 = icon.registerIcon(HalogenLight.modid + ":Side1");
        Side1top = icon.registerIcon(HalogenLight.modid + ":Side1top");
        Side1top2 = icon.registerIcon(HalogenLight.modid + ":Side1top2");
        Side2 = icon.registerIcon(HalogenLight.modid + ":Side2");
        Side2top = icon.registerIcon(HalogenLight.modid + ":Side2top");
        Side2top2 = icon.registerIcon(HalogenLight.modid + ":Side2top2");
    }

    public IIcon getIcon(int side, int meta) {
        switch(side) {
            case 0:
            case 1:
                return Side0;
            case 2:
            case 3:
                if(meta == 1) {
                    return Side1top;
                }
                return Side1;
            case 4:
            case 5:
                if(meta == 1) {
                    return Side2top;
                }
                return Side2;
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return true;
    }

    public boolean renderAsNormalBlock() { return false; }

    public int getRenderType() { return HalogenLight.RenderID; }

    public boolean isOpaqueCube() { return false; }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return super.getItemDropped(p_149650_1_, p_149650_2_, p_149650_3_);
    }

    private boolean isUnderSolid(World p_150107_1_, int p_150107_2_, int p_150107_3_, int p_150107_4_)
    {
        if (isSideSolid(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_, ForgeDirection.UP))
        {
            return true;
        }
        else
        {
            Block block = p_150107_1_.getBlock(p_150107_2_, p_150107_3_, p_150107_4_);
            return block.canPlaceTorchOnTop(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_);
        }
    }

    private boolean isAboveSolid(World p_150107_1_, int p_150107_2_, int p_150107_3_, int p_150107_4_)
    {
        if (isSideSolid(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_, ForgeDirection.DOWN))
        {
            return true;
        }
        else
        {
            Block block = p_150107_1_.getBlock(p_150107_2_, p_150107_3_, p_150107_4_);
            return block.canPlaceTorchOnTop(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_);
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
        if(!isUnderSolid(world, x, y - 1, z)) {
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
            world.notifyBlockChange(x, y, z, world.getBlock(x, y, z));
        } else {
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
            world.notifyBlockChange(x, y, z, world.getBlock(x, y, z));
        }
    }
}
