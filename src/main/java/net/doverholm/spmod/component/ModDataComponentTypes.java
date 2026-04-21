package net.doverholm.spmod.component;

import com.mojang.serialization.Codec;
import net.doverholm.spmod.SPMod;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<BlockPos> COORDINATES = register("coordinates",
            builder -> builder.codec(BlockPos.CODEC).packetCodec(BlockPos.PACKET_CODEC));
    public static final ComponentType<Integer> BURNING_STACKS = register("burning_stacks",
            builder -> builder.codec(Codec.INT).packetCodec(PacketCodecs.INTEGER));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(SPMod.MOD_ID,name),
            builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        SPMod.LOGGER.info("Registering Data Component Types for " + SPMod.MOD_ID);
    }
}
