package hirosuke;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.*;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;

public class BlockHalogenLight extends Block {

    public BlockHalogenLight() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.1f);
        this.setResistance(1.0f);
        this.setStepSound(Block.soundTypeCloth);
        this.setLightLevel(1f);
        this.setLightOpacity(1);
        this.disableStats();
    }


    public IIcon Side0;
    public IIcon Side1;
    public IIcon Side2;
    public IIcon Side3;
    public IIcon Side4;
    public IIcon Side5;

    @Override
    public void registerBlockIcons(IIconRegister icon) {
        Side0 = icon.registerIcon(HalogenLight.modid + ":Side0");
        Side1 = icon.registerIcon(HalogenLight.modid + ":Side1");
        Side2 = icon.registerIcon(HalogenLight.modid + ":Side2");
    }

    public IIcon getIcon(int side, int meta) {
        switch(side) {
            case 0:
            case 1:
                return Side0;
            case 2:
            case 3:
                return Side1;
            case 4:
            case 5:
                return Side2;
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return true;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType() {
        return HalogenLight.RenderID;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
        this.setBlockBoundsBasedOnState(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
    }

    private boolean func_150107_m(World p_150107_1_, int p_150107_2_, int p_150107_3_, int p_150107_4_)
    {
        if (World.doesBlockHaveSolidTopSurface(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_))
        {
            return true;
        }
        else
        {
            Block block = p_150107_1_.getBlock(p_150107_2_, p_150107_3_, p_150107_4_);
            return block.canPlaceTorchOnTop(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return AxisAlignedBB.getBoundingBox(0.4375D, 0.0D, 0.0D, 0.5625D, 0.125D, 1.0D);
    }

    @Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        Random random = new Random();
        ItemStack item = new ItemStack(HalogenLight.blockHalogenLight);
        this.dropBlockAsItem(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, item);
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        int j1 = p_149660_9_;

        if (p_149660_5_ == 1 && this.func_150107_m(p_149660_1_, p_149660_2_, p_149660_3_ - 1, p_149660_4_)) {
            j1 = 5;
        }

        if (p_149660_5_ == 2 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ + 1, NORTH, true)) {
            j1 = 4;
        }

        if (p_149660_5_ == 3 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ - 1, SOUTH, true)) {
            j1 = 3;
        }

        if (p_149660_5_ == 4 && p_149660_1_.isSideSolid(p_149660_2_ + 1, p_149660_3_, p_149660_4_, WEST, true)) {
            j1 = 2;
        }

        if (p_149660_5_ == 5 && p_149660_1_.isSideSolid(p_149660_2_ - 1, p_149660_3_, p_149660_4_, EAST, true)) {
            j1 = 1;
        }

        return j1;
    }
}
