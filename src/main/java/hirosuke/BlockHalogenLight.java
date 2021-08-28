package hirosuke;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

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

    public int getRenderType() {
        return HalogenLight.RenderID;
    }

    public boolean isOpaqueCube() {
        return false;
    }
}
