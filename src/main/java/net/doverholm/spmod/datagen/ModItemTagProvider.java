package net.doverholm.spmod.datagen;

import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.doverholm.spmod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider  extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.ETHEREUM_ORE);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLOODWOOD_LOG.asItem())
                .add(ModBlocks.BLOODWOOD_WOOD.asItem())
                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.BLOODWOOD_PLANKS.asItem());
    }
}
