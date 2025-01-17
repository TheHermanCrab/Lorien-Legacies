package lorien.legacies.legacies;

import org.lwjgl.input.Keyboard;

import lorien.legacies.core.LorienLegacies;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public abstract class Legacy {
	
	public String LEGACY_NAME = "[legacy name not set]";
	public String DESCRIPTION = "[description not set]";
	
	public boolean toggled = false;
	
	// Called every tick
	public abstract void computeLegacyTick(EntityPlayer player);
	
	public void blessedMessage(EntityPlayer player)
	{
		player.sendMessage(new TextComponentString(LEGACY_NAME + " - " + DESCRIPTION).setStyle(new Style().setColor(TextFormatting.YELLOW)));
	}
	
	public void toggle(EntityPlayer player)
	{
		player.sendMessage(new TextComponentString(LEGACY_NAME + " legacy toggled - set to " + !toggled).setStyle(new Style().setColor(TextFormatting.RED)));
		toggled = !toggled;
	}
	
	
}
