package net.doverholm.spmod;

import net.doverholm.spmod.component.ModDataComponentTypes;
import net.doverholm.spmod.item.ModItemGroups;
import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
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
		ModDataComponentTypes.registerDataComponentTypes();

		FuelRegistry.INSTANCE.add(ModItems.DRIED_CURIOUS_MANA_BEAN, 40);

		StrippableBlockRegistry.register(ModBlocks.BLOODWOOD_LOG, ModBlocks.STRIPPED_BLOODWOOD_LOG);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLOODWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLOODWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLOODWOOD_LEAVES, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLOODWOOD_PLANKS, 5, 20);

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