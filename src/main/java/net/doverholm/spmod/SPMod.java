package net.doverholm.spmod;

import net.doverholm.spmod.item.ModItemGroups;
import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// En kommentar ifrån Martin :)
// En kommentar ifrån Charlie :-)

public class SPMod implements ModInitializer {
	public static final String MOD_ID = "spmod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}