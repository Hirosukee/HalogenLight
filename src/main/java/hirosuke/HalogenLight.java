package hirosuke;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

@Mod(modid = HalogenLight.modid, name = HalogenLight.name, version = HalogenLight.version)
public class HalogenLight {
     public static final String modid = "halogenlight";
     public static final String name = "Halogen Name";
     public static final String version = "1.0";

     public static Block blockHalogenLight;
     public static Item ItemHalogenLight;
     public static int RenderID;

     @Mod.EventHandler
     public void perInit(FMLPreInitializationEvent event) {

          ItemHalogenLight = new Item()
                  .setCreativeTab(CreativeTabs.tabDecorations)
                  .setUnlocalizedName("HalogenLight")
                  .setTextureName("halogenlight:textures/block/halogenlight.png");

          blockHalogenLight = new BlockHalogenLight()
                  .setBlockName("HalogenLight");
          GameRegistry.registerBlock(blockHalogenLight, "Halogen Light");
     }

     @Mod.EventHandler
     public void init(FMLInitializationEvent event) {
          if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
          {
               this.RenderID = RenderingRegistry.getNextAvailableRenderId();
               RenderingRegistry.registerBlockHandler(new ModelHalogenLight());
          }
     }
}
