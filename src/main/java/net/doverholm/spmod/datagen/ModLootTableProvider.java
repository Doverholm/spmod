package net.doverholm.spmod.datagen;

import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.MAGIC_BLOCK);
        addDrop(ModBlocks.ETHEREUM_BLOCK);

        addDrop(ModBlocks.BLOODWOOD_LOG);
        addDrop(ModBlocks.BLOODWOOD_WOOD);
        addDrop(ModBlocks.STRIPPED_BLOODWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
        addDrop(ModBlocks.BLOODWOOD_LEAVES, leavesDrops(ModBlocks.BLOODWOOD_LEAVES, ModBlocks.BLOODWOOD_SAPLING, 0.0625f));
        addDrop(ModBlocks.BLOODWOOD_PLANKS);
        addDrop(ModBlocks.BLOODWOOD_SAPLING);
        addDrop(ModBlocks.BLOODWOOD_STAIRS);
        addDrop(ModBlocks.BLOODWOOD_SLAB, slabDrops(ModBlocks.BLOODWOOD_SLAB));
        addDrop(ModBlocks.BLOODWOOD_BUTTON);
        addDrop(ModBlocks.BLOODWOOD_PRESSURE_PLATE);
        addDrop(ModBlocks.BLOODWOOD_FENCE);
        addDrop(ModBlocks.BLOODWOOD_FENCE_GATE);
        addDrop(ModBlocks.BLOODWOOD_DOOR, doorDrops(ModBlocks.BLOODWOOD_DOOR));
        addDrop(ModBlocks.BLOODWOOD_TRAPDOOR);

        addDrop(ModBlocks.ETHEREUM_ORE_BLOCK, multipleOreDrops(ModBlocks.ETHEREUM_ORE_BLOCK, ModItems.ETHEREUM_ORE, 1, 3));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                        ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                        .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
}
