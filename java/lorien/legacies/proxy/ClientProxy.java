package lorien.legacies.proxy;

import lorien.legacies.blocks.ModBlocks;
import lorien.legacies.items.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }
	
	
	
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
    	ModBlocks.registerModels();
    	ModItems.registerModels();
    }
	
}