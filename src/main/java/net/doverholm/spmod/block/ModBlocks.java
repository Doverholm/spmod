package net.doverholm.spmod.block;

import net.doverholm.spmod.SPMod;
import net.doverholm.spmod.block.custom.MagicBlock;
import net.doverholm.spmod.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block ETHEREUM_ORE_BLOCK = registerBlock("ethereum_ore_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.create().strength(1f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block ETHEREUM_BLOCK = registerBlock("ethereum_block",
            new Block(AbstractBlock.Settings.create().strength(1f).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(AbstractBlock.Settings.create().strength(1f).requiresTool()));

    public static final Block BLOODWOOD_LOG = registerBlock("bloodwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block BLOODWOOD_WOOD = registerBlock("bloodwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_BLOODWOOD_LOG = registerBlock("stripped_bloodwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_BLOODWOOD_WOOD = registerBlock("stripped_bloodwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block BLOODWOOD_LEAVES = registerBlock("bloodwood_leaves",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block BLOODWOOD_PLANKS = registerBlock("bloodwood_planks",
            new Block(AbstractBlock.Settings.create().strength(1f).sounds(BlockSoundGroup.WOOD)));
    public static final Block BLOODWOOD_SAPLING = registerBlock("bloodwood_sapling",
            new SaplingBlock(ModSaplingGenerators.BLOODWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block BLOODWOOD_STAIRS = registerBlock("bloodwood_stairs",
            new StairsBlock(ModBlocks.BLOODWOOD_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.create().strength(1f).sounds(BlockSoundGroup.WOOD)));
    public static final Block BLOODWOOD_SLAB = registerBlock("bloodwood_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(1f).sounds(BlockSoundGroup.WOOD)));
    public static final Block BLOODWOOD_BUTTON = registerBlock("bloodwood_button",
            new ButtonBlock(BlockSetType.ACACIA, 2, AbstractBlock.Settings.create().strength(1f).noCollision().sounds(BlockSoundGroup.WOOD)));
    public static final Block BLOODWOOD_PRESSURE_PLATE = registerBlock("bloodwood_pressure_plate",
            new PressurePlateBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(1f).sounds(BlockSoundGroup.WOOD)));
    public static final Block BLOODWOOD_FENCE = registerBlock("bloodwood_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(1f).sounds(BlockSoundGroup.WOOD)));
    public static final Block BLOODWOOD_FENCE_GATE = registerBlock("bloodwood_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(1f).sounds(BlockSoundGroup.WOOD)));
    public static final Block BLOODWOOD_DOOR = registerBlock("bloodwood_door",
            new DoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(1f).nonOpaque().sounds(BlockSoundGroup.WOOD)));
    public static final Block BLOODWOOD_TRAPDOOR = registerBlock("bloodwood_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(1f).nonOpaque().sounds(BlockSoundGroup.WOOD)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SPMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(SPMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        SPMod.LOGGER.info("Registiring Mod Blocks  " + SPMod.MOD_ID);
    }
}
