package lorien.legacies.legacies.implementations;

import org.lwjgl.input.Keyboard;

import lorien.legacies.legacies.Legacy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class FortemLegacy extends Legacy {

	public FortemLegacy()
	{
		LEGACY_NAME = "Fortem";
		DESCRIPTION = "grants super strength";
	}
	
	@Override
	public void computeLegacyTick(EntityPlayer player)
	{
		if (toggled)
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("strength"), 1, 30, true, false));
	}

}
