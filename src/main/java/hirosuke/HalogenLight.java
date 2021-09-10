package hirosuke;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = HalogenLight.modid, name = HalogenLight.name, version = HalogenLight.version)
public class HalogenLight {
     public static final String modid = "halogelight";
     public static final String name = "Halogen Light";
     public static final String version = "1.1";

     public static Block blockHalogenLight;
     public static Item itemHalogenLight;
     public static int RenderID;

     @Mod.EventHandler
     public void perInit(FMLPreInitializationEvent event) {
          Logger.registry(event);
          itemHalogenLight = new Item()
                  .setCreativeTab(CreativeTabs.tabDecorations)
                  .setUnlocalizedName("halogenlight")
                  .setTextureName("halogenlight:itemhalogenlight");

          blockHalogenLight = new BlockHalogenLight()
                  .setBlockName("halogenlight")
                  .setBlockTextureName("halogenlight:blockhalogenlight");
          GameRegistry.registerBlock(blockHalogenLight, "Halogen Light");
          LanguageRegistry.addName(blockHalogenLight, "Halogen Light");
          Logger.info("preinited.");
     }

     @Mod.EventHandler
     public void init (FMLInitializationEvent event) {
          if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
          {
               this.RenderID = RenderingRegistry.getNextAvailableRenderId();
               RenderingRegistry.registerBlockHandler(new RenderHalogenLight(Material.rock));
          }
          GameRegistry.addRecipe(new ItemStack(blockHalogenLight), "ABA", 'A', Blocks.glass, 'B', Blocks.glowstone);
          Logger.info("inited.");
     }
}
