package net.doverholm.spmod;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.doverholm.spmod.component.ModDataComponentTypes;
import net.doverholm.spmod.item.ModItemGroups;
import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.doverholm.spmod.world.gen.ModWorldGeneration;
import net.doverholm.spmod.rank.Rank;
import net.doverholm.spmod.rank.RankManager;
import net.doverholm.spmod.util.NameTagUtil;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;

import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SPMod implements ModInitializer {
	public static final String MOD_ID = "spmod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModDataComponentTypes.registerDataComponentTypes();

		ModWorldGeneration.generateModWorldGen();

		FuelRegistry.INSTANCE.add(ModItems.DRIED_CURIOUS_MANA_BEAN, 40);


		StrippableBlockRegistry.register(ModBlocks.BLOODWOOD_LOG, ModBlocks.STRIPPED_BLOODWOOD_LOG);
		StrippableBlockRegistry.register(ModBlocks.BLOODWOOD_WOOD, ModBlocks.STRIPPED_BLOODWOOD_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLOODWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLOODWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLOODWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLOODWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLOODWOOD_LEAVES, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLOODWOOD_PLANKS, 5, 20);

		ServerPlayerEvents.JOIN.register((player -> {
			String playerName = player.getName().getString();
			String message = "Welcome " + playerName + "!";

			player.sendMessage(Text.literal(message), true);
		}));


		ServerTickEvents.END_SERVER_TICK.register(server -> {
		ServerTickEvents.END_SERVER_TICK.register((server) -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

				boolean shouldBurn = false;

				if (player.getMainHandStack().isOf(ModItems.SCORCHLINE) ||
						player.getOffHandStack().isOf(ModItems.SCORCHLINE) ||
						player.getOffHandStack().isOf(ModItems.OATH_OF_THE_BURNING_VEIN)) {
					shouldBurn = true;
				}

				for (int i = 0; i < 9; i++) {
					if (player.getInventory().getStack(i).isOf(ModItems.OATH_OF_THE_BURNING_VEIN)) {
						shouldBurn = true;
						break;
					}
				}

				if (shouldBurn) {
					player.setOnFireFor(1);
				}
			}
		});

			}


		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("setrank")
					.requires(src -> src.hasPermissionLevel(2))
					.then(CommandManager.argument("player", EntityArgumentType.player())
							.then(CommandManager.argument("rank", StringArgumentType.word())
									.executes(context -> {

										ServerPlayerEntity target = EntityArgumentType.getPlayer(context, "player");
										String rankStr = StringArgumentType.getString(context, "rank");

										Rank rank = Rank.valueOf(rankStr.toUpperCase());

										RankManager.setRank(target.getUuid(), rank);
										NameTagUtil.updateName(target);

										context.getSource().sendFeedback(
												() -> Text.literal("Rank satt!"), false
										);

										return 1;
									}))));
		});


		ServerMessageEvents.ALLOW_CHAT_MESSAGE.register((message, sender, params) -> {

			Rank rank = RankManager.getRank(sender.getUuid());

			Text prefix = switch (rank) {
				case ADMIN -> Text.literal("[ADMIN] ").formatted(Formatting.RED);
				case MODERATOR -> Text.literal("[MOD] ").formatted(Formatting.BLUE);
				default -> Text.literal("");
			};

			Text newMessage = Text.literal("")
					.append(prefix)
					.append(sender.getName())
					.append(Text.literal(": "))
					.append(message.getContent());

			sender.getServer().getPlayerManager().broadcast(newMessage, false);

			return false;
		});


		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			NameTagUtil.updateName(handler.getPlayer());
		});
	}
}