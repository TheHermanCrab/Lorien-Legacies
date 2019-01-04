package lorien.legacies.entities;

import lorien.legacies.core.LorienLegacies;
import lorien.legacies.entities.Chimaera.Chimaera;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModEntities {
	
	private static World worldIn = Minecraft.getMinecraft().world;
	
	public static Chimaera chimaera = new Chimaera(worldIn);
	
	
	public static void register(IForgeRegistry<EntityEntry> registry)
	{
		int entityID = 420;
		//registry.register(new EntityEntry(chimaera.getClass(), "bob").setRegistryName(chimaera.getName()));
		
		Object mod = LorienLegacies.instance;
		
		EntityRegistry.registerModEntity(new ResourceLocation(LorienLegacies.MODID + ":" + "chimaera"), Chimaera.class, "Chimaera", ++entityID, LorienLegacies.instance, 244, 1, false);
	}
	
}
