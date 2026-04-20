package net.doverholm.spmod.datagen;

import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ETHEREUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ETHEREUM_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);


        blockStateModelGenerator.registerLog(ModBlocks.BLOODWOOD_LOG).log(ModBlocks.BLOODWOOD_LOG).wood(ModBlocks.BLOODWOOD_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_BLOODWOOD_LOG).log(ModBlocks.STRIPPED_BLOODWOOD_LOG).wood(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
        blockStateModelGenerator.registerSingleton(ModBlocks.BLOODWOOD_LEAVES, TexturedModel.LEAVES);
        BlockStateModelGenerator.BlockTexturePool bloodwoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BLOODWOOD_PLANKS);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.BLOODWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        bloodwoodPool.stairs(ModBlocks.BLOODWOOD_STAIRS);
        bloodwoodPool.slab(ModBlocks.BLOODWOOD_SLAB);
        bloodwoodPool.button(ModBlocks.BLOODWOOD_BUTTON);
        bloodwoodPool.pressurePlate(ModBlocks.BLOODWOOD_PRESSURE_PLATE);
        bloodwoodPool.fence(ModBlocks.BLOODWOOD_FENCE);
        bloodwoodPool.fenceGate(ModBlocks.BLOODWOOD_FENCE_GATE);
        blockStateModelGenerator.registerDoor(ModBlocks.BLOODWOOD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.BLOODWOOD_TRAPDOOR);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ETHEREUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ETHEREUM_ORE, Models.GENERATED);

        itemModelGenerator.register(ModItems.CURIOUS_MANA_BEAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRIED_CURIOUS_MANA_BEAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARDENS_BANE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCORCHLINE, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.BLOODWOOD_SAPLING.asItem(), Models.GENERATED);

    }
}
