package net.doverholm.spmod;

import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.doverholm.spmod.item.custom.BurningBowItem;
import net.doverholm.spmod.network.FireballVolleyPayload;
import net.doverholm.spmod.item.custom.WardensBaneSwordItem;
import net.doverholm.spmod.util.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.Locale;

public class SPModClient implements ClientModInitializer {
    private static int fireballCooldownTicks;
    private static final KeyBinding FIREBALL_VOLLEY_KEY = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("key.spmod.fireball_volley", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "category.spmod.spmod")
    );

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOODWOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOODWOOD_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOODWOOD_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOODWOOD_SAPLING, RenderLayer.getCutout());

        ModModelPredicates.registerModelPredicates();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClientPlayerEntity player = client.player;
            if (player == null) {
                return;
            }

            while (FIREBALL_VOLLEY_KEY.wasPressed()) {
                if (player.getMainHandStack().isOf(ModItems.OATH_OF_THE_BURNING_VEIN)
                        || player.getOffHandStack().isOf(ModItems.OATH_OF_THE_BURNING_VEIN)) {
                    if (fireballCooldownTicks > 0) {
                        continue;
                    }

                    ClientPlayNetworking.send(FireballVolleyPayload.INSTANCE);
                    fireballCooldownTicks = BurningBowItem.getFireballCooldownTicks();
                }
            }

            if (fireballCooldownTicks > 0) {
                fireballCooldownTicks--;
            }

            ItemStack mainHandStack = player.getMainHandStack();
            ItemStack offHandStack = player.getOffHandStack();
            boolean isHoldingBurningBow = mainHandStack.isOf(ModItems.OATH_OF_THE_BURNING_VEIN)
                    || offHandStack.isOf(ModItems.OATH_OF_THE_BURNING_VEIN);
            boolean isHoldingWardensBaneSword = mainHandStack.isOf(ModItems.WARDENS_BANE_SWORD)
                    || offHandStack.isOf(ModItems.WARDENS_BANE_SWORD);

            if (isHoldingBurningBow && fireballCooldownTicks > 0) {
                float remainingSeconds = fireballCooldownTicks / 20.0f;

                player.sendMessage(Text.literal(String.format(Locale.ROOT, "Fireball Cooldown: %.1fs", remainingSeconds)), true);
            }

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
