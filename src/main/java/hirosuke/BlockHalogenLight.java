package hirosuke;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

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
        Side3 = icon.registerIcon(HalogenLight.modid + ":Side3");
        Side4 = icon.registerIcon(HalogenLight.modid + ":Side4");
        Side5 = icon.registerIcon(HalogenLight.modid + ":Side5");
    }

    public IIcon getIcon(int side, int meta) {
        switch(side) {
            case 0: return Side0;
            case 1: return Side1;
            case 2: return Side2;
            case 3: return Side3;
            case 4: return Side4;
            case 5: return Side5;
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

    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, EAST,  true) ||
                p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, WEST,  true) ||
                p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, SOUTH, true) ||
                p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, NORTH, true) ||
                func_150107_m(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_);
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
}
