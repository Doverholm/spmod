package net.doverholm.spmod.datagen;

import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ETHEREUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ETHEREUM_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ETHEREUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ETHEREUM_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CURIOUS_MANA_BEAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRIED_CURIOUS_MANA_BEAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.WAND, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARDENS_BANE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCORCHLINE, Models.GENERATED);

    }
}
