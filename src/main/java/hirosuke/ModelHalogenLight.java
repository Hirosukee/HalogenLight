package hirosuke;
//Exported java file
//Keep in mind that you still need to fill in some blanks
// - ZeuX

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class ModelHalogenLight extends ModelBase implements ISimpleBlockRenderingHandler
{

	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(modelId == this.getRenderId()) {
			renderer.setRenderBounds(0.4375D, 0.0D, 0.0D, 0.5625D, 0.125D, 1.0D);
			renderer.setOverrideBlockTexture(null);
			renderer.renderStandardBlock(block, x, y, z);
		}
		return false;
	}

	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	public int getRenderId() {
		return HalogenLight.RenderID;
	}
}
