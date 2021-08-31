package hirosuke;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
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
    public IIcon Side1top;
    public IIcon Side2;
    public IIcon Side2top;

    @Override
    public void registerBlockIcons(IIconRegister icon) {
        Side0 = icon.registerIcon(HalogenLight.modid + ":Side0");
        Side1 = icon.registerIcon(HalogenLight.modid + ":Side1");
        Side1top = icon.registerIcon(HalogenLight.modid + ":Side1top");
        Side2 = icon.registerIcon(HalogenLight.modid + ":Side2");
        Side2top = icon.registerIcon(HalogenLight.modid + ":Side2top");
    }

    public IIcon getIcon(int side, int meta) {
        switch(side) {
            case 0:
            case 1:
                return Side0;
            case 2:
            case 3:
                if(meta == 2) {
                    return Side1;
                }
                return Side1;
            case 4:
            case 5:
                if(meta == 2) {
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
