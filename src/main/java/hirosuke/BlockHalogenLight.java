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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.*;

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

    public IIcon Side0_0;
    public IIcon Side0_1;
    public IIcon Side0_2;
    public IIcon Side0_3;
    public IIcon Side0_4;
    public IIcon Side1_0;
    public IIcon Side1_1;
    public IIcon Side1_2;
    public IIcon Side1_3;
    public IIcon Side1_4;
    public IIcon Side2_0;
    public IIcon Side2_1;
    public IIcon Side2_2;
    public IIcon Side2_3;

    @Override
    public void registerBlockIcons(IIconRegister icon) {
        Side0_0 = icon.registerIcon(HalogenLight.modid + ":Side0_0");
        Side0_1 = icon.registerIcon(HalogenLight.modid + ":Side0_1");
        Side0_2 = icon.registerIcon(HalogenLight.modid + ":Side0_2");
        Side0_3 = icon.registerIcon(HalogenLight.modid + ":Side0_3");
        Side0_4 = icon.registerIcon(HalogenLight.modid + ":Side0_4");
        Side1_0 = icon.registerIcon(HalogenLight.modid + ":Side1_0");
        Side1_1 = icon.registerIcon(HalogenLight.modid + ":Side1_1");
        Side1_2 = icon.registerIcon(HalogenLight.modid + ":Side1_2");
        Side1_3 = icon.registerIcon(HalogenLight.modid + ":Side1_3");
        Side1_4 = icon.registerIcon(HalogenLight.modid + ":Side1_4");
        Side2_0 = icon.registerIcon(HalogenLight.modid + ":Side2_0");
        Side2_1 = icon.registerIcon(HalogenLight.modid + ":Side2_1");
        Side2_2 = icon.registerIcon(HalogenLight.modid + ":Side2_2");
        Side2_3 = icon.registerIcon(HalogenLight.modid + ":Side2_3");
    }

    public IIcon getIcon(int side, int meta) {
        switch(side) {
            case 0:
            case 1:
                if(meta == 4) {
                    return Side0_1;
                } else if (meta == 3) {
                    return Side0_2;
                } else if (meta == 2) {
                    return Side0_3;
                } else if (meta == 1) {
                    return Side0_4;
                } else if (meta == 5 || meta == 7) {
                    return Side1_2;
                }
                return Side0_0;
            case 2:
            case 3:
                if(meta == 0) {
                    return Side1_1;
                } else if (meta == 4 || meta == 3) {
                    return Side1_2;
                } else if (meta == 2) {
                    return Side1_3;
                } else if (meta == 1) {
                    return Side1_4;
                } else if (meta == 5) {
                    return Side2_0;
                } else if (meta == 7) {
                    return Side0_2;
                    a
                }
                return Side1_0;
            case 4:
            case 5:
                if(meta == 0) {
                    return Side2_1;
                } else if(meta == 4) {
                    return Side2_2;
                } else if (meta == 3 || meta == 2 || meta == 1) {
                    return Side2_3;
                } else if (meta == 5) {
                    return Side1_0;
                } else if (meta == 7) {
                    return Side1_1;
                }
                return Side2_0;
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
        return null;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return super.getItemDropped(p_149650_1_, p_149650_2_, p_149650_3_);
    }

    private boolean isUnderSolid(World world, int x, int y, int z)
    {
        if (isSideSolid(world, x, y, z, ForgeDirection.UP)) { return true; }
        else {
            Block block = world.getBlock(x, y, z);
            return block.canPlaceTorchOnTop(world, x, y, z);
        }
    }

    private boolean isAboveSolid(World world, int x, int y, int z)
    {
        if (isSideSolid(world, x, y, z, ForgeDirection.DOWN)) { return true; }
        else
        {
            Block block = world.getBlock(x, y, z);
            return block.canPlaceTorchOnTop(world, x, y, z);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {

        int l = world.getBlockMetadata(x, y, z);
        if (l == 0) {
            this.setBlockBounds(0.4375f, 1 - 0.125f, 0.0f, 0.5625f, 1f, 1.0f);
        } else if (l == 6) {
            this.setBlockBounds(0.4375f, 0.0f, 0.0f, 0.5625f, 0.125f, 1.0f);
        } else if (l == 4) {
            this.setBlockBounds(0f, 0.4375f, 1 - 0.125f, 1f, 0.5625f, 1.0f);
        } else if (l == 3) {
            this.setBlockBounds(0f, 0.4375f, 0f, 1f, 0.5625f, 0.125f);
        } else if (l == 2) {
            this.setBlockBounds(1 - 0.125f, 0.4375f, 0.0f, 1f, 0.5625f, 1.0f);
        } else if (l == 1) {
            this.setBlockBounds(0f, 0.4375f, 0.0f, 0.125f, 0.5625f, 1.0f);
        } else {
            if(l == 5) {
                this.setBlockBounds(0f, 0.0f, 0.4375f, 1f, 0.125f, 0.5625f);
            } else if (l == 7) {
                this.setBlockBounds(0f, 1 - 0.125f, 0.4375f, 1f, 1f, 0.5625f);
            }
        }
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int direction)
    {
        ForgeDirection dir = ForgeDirection.getOrientation(direction);
        return (dir == DOWN  && world.isSideSolid(x, y + 1, z, DOWN )) ||
                (dir == UP    && world.isSideSolid(x, y - 1, z, UP   )) ||
                (dir == NORTH && world.isSideSolid(x, y, z + 1, NORTH)) ||
                (dir == SOUTH && world.isSideSolid(x, y, z - 1, SOUTH)) ||
                (dir == WEST  && world.isSideSolid(x + 1, y, z, WEST )) ||
                (dir == EAST  && world.isSideSolid(x - 1, y, z, EAST ));
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return world.isSideSolid(x - 1, y, z, EAST ) ||
                world.isSideSolid(x + 1, y, z, WEST ) ||
                world.isSideSolid(x, y, z - 1, SOUTH) ||
                world.isSideSolid(x, y, z + 1, NORTH) ||
                world.isSideSolid(x, y - 1, z, UP   ) ||
                world.isSideSolid(x, y + 1, z, DOWN );
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {

        int l = world.getBlockMetadata(x, y, z);
        int i1 = l & 7;
        int j1 = l & 8;

        if (i1 == invertMetadata(1))
        {
            if ((MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 1) == 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, 5 | j1, 2);
            }
            else
            {
                world.setBlockMetadataWithNotify(x, y, z, 6 | j1, 2);
            }
        }
        else if (i1 == invertMetadata(0))
        {
            if ((MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 1) == 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, 7 | j1, 2);
            }
            else
            {
                world.setBlockMetadataWithNotify(x, y, z, 0 | j1, 2);
            }
        }

        Logger.info(world.getBlockMetadata(x, y, z) + "");
    }

    public static int invertMetadata(int p_149819_0_)
    {
        switch (p_149819_0_)
        {
            case 0:
                return 0;
            case 1:
                return 5;
            case 2:
                return 4;
            case 3:
                return 3;
            case 4:
                return 2;
            case 5:
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        int k1 = meta & 8;
        int j1 = meta & 7;
        byte b0 = -1;

        if (side == 0 && world.isSideSolid(x, y + 1, z, DOWN))
        {
            b0 = 0;
        }

        if (side == 1 && world.isSideSolid(x, y - 1, z, UP))
        {
            b0 = 5;
        }

        if (side == 2 && world.isSideSolid(x, y, z + 1, NORTH))
        {
            b0 = 4;
        }

        if (side == 3 && world.isSideSolid(x, y, z - 1, SOUTH))
        {
            b0 = 3;
        }

        if (side == 4 && world.isSideSolid(x + 1, y, z, WEST))
        {
            b0 = 2;
        }

        if (side == 5 && world.isSideSolid(x - 1, y, z, EAST))
        {
            b0 = 1;
        }

        return b0 + k1;
    }

    private boolean func_149820_e(World world, int x, int y, int z)
    {
        if (!this.canPlaceBlockAt(world, x, y, z))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (this.func_149820_e(world, x, y, z))
        {
            int l = world.getBlockMetadata(x, y, z) & 7;
            boolean flag = false;

            if (!world.isSideSolid(x - 1, y, z, EAST) && l == 1)
            {
                flag = true;
            }

            if (!world.isSideSolid(x + 1, y, z, WEST) && l == 2)
            {
                flag = true;
            }

            if (!world.isSideSolid(x, y, z - 1, SOUTH) && l == 3)
            {
                flag = true;
            }

            if (!world.isSideSolid(x, y, z + 1, NORTH) && l == 4)
            {
                flag = true;
            }

            if (!world.isSideSolid(x, y - 1, z, UP) && l == 5)
            {
                flag = true;
            }

            if (!world.isSideSolid(x, y - 1, z, UP) && l == 6)
            {
                flag = true;
            }

            if (!world.isSideSolid(x, y + 1, z, DOWN) && l == 0)
            {
                flag = true;
            }

            if (!world.isSideSolid(x, y + 1, z, DOWN) && l == 7)
            {
                flag = true;
            }

            if (flag)
            {
                this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
                world.setBlockToAir(x, y, z);
            }
        }
    }
}
