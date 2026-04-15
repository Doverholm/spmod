package net.doverholm.spmod;

import net.doverholm.spmod.item.ModItemGroups;
import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
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

		FuelRegistry.INSTANCE.add(ModItems.DRIED_CURIOUS_MANA_BEAN, 40);

		ServerPlayerEvents.JOIN.register((player -> {
			String playerName = player.getName().getString();
			String message = "Welcome " + playerName + "!";

			player.sendMessage(Text.literal(message), true);
		}));

		ServerTickEvents.END_SERVER_TICK.register((server) -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
				if (player.getMainHandStack().getItem() == ModItems.SCORCHLINE ||
					player.getOffHandStack().getItem() == ModItems.SCORCHLINE) {
					player.setOnFireFor(1);

					break;
				}

			}
		});
	}
}