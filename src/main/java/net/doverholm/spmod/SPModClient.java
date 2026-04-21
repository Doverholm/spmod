package net.doverholm.spmod;

import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.doverholm.spmod.item.custom.WardensBaneSwordItem;
import net.doverholm.spmod.util.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.Locale;

public class SPModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOODWOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOODWOOD_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOODWOOD_LEAVES, RenderLayer.getCutout());

        ModModelPredicates.registerModelPredicates();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClientPlayerEntity player = client.player;
            if (player == null) {
                return;
            }

            ItemStack mainHandStack = player.getMainHandStack();
            ItemStack offHandStack = player.getOffHandStack();
            boolean isHoldingWardensBaneSword = mainHandStack.isOf(ModItems.WARDENS_BANE_SWORD)
                    || offHandStack.isOf(ModItems.WARDENS_BANE_SWORD);

            if (!isHoldingWardensBaneSword || !player.getItemCooldownManager().isCoolingDown(ModItems.WARDENS_BANE_SWORD)) {
                return;
            }

            float cooldownProgress = player.getItemCooldownManager().getCooldownProgress(
                    ModItems.WARDENS_BANE_SWORD,
                    0.0f
            );
            float remainingSeconds = (cooldownProgress * WardensBaneSwordItem.getSonicBoomCooldownTicks()) / 20.0f;

            player.sendMessage(Text.literal(String.format(Locale.ROOT, "Sonic Boom Cooldown: %.1fs", remainingSeconds)), true);
        });
    }
}
