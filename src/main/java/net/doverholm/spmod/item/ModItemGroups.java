package net.doverholm.spmod.item;

import net.doverholm.spmod.SPMod;
import net.doverholm.spmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup SP_MODS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SPMod.MOD_ID, "sp_mod_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.WARDENS_BANE))
                    .displayName(Text.translatable("itemgroup.spmod.sp_mod.items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.WARDENS_BANE);

                        entries.add(ModItems.OATH_OF_THE_BURNING_VEIN);
                        entries.add(ModItems.SCORCHLINE);

                        entries.add(ModItems.CURIOUS_MANA_BEAN);
                        entries.add(ModItems.DRIED_CURIOUS_MANA_BEAN);

                        entries.add(ModBlocks.BLOODWOOD_LOG);
                        entries.add(ModBlocks.BLOODWOOD_WOOD);
                        entries.add(ModBlocks.STRIPPED_BLOODWOOD_LOG);
                        entries.add(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
                        entries.add(ModBlocks.BLOODWOOD_LEAVES);
                        entries.add(ModBlocks.BLOODWOOD_PLANKS);
                        entries.add(ModBlocks.BLOODWOOD_STAIRS);
                        entries.add(ModBlocks.BLOODWOOD_SLAB);
                        entries.add(ModBlocks.BLOODWOOD_BUTTON);
                        entries.add(ModBlocks.BLOODWOOD_PRESSURE_PLATE);
                        entries.add(ModBlocks.BLOODWOOD_FENCE);
                        entries.add(ModBlocks.BLOODWOOD_FENCE_GATE);
                        entries.add(ModBlocks.BLOODWOOD_DOOR);
                        entries.add(ModBlocks.BLOODWOOD_TRAPDOOR);


                    }).build());
    public static final ItemGroup ETHEREUM = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SPMod.MOD_ID, "ethereum"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ETHEREUM))
                    .displayName(Text.translatable("itemgroup.spmod.sp_mod.ethereum"))
                    .entries((displayContext, entries) -> {

                        // items
                        entries.add(ModItems.ETHEREUM);
                        entries.add(ModItems.ETHEREUM_ORE);

                        // blocks
                        entries.add(ModBlocks.ETHEREUM_BLOCK);
                        entries.add(ModBlocks.ETHEREUM_ORE_BLOCK);
                        entries.add(ModBlocks.MAGIC_BLOCK);

                        //tools
                        entries.add(ModItems.WAND);

                    }).build());


    public static void registerItemGroups() {
        SPMod.LOGGER.info("Registering Item Groups for " + SPMod.MOD_ID);
    }
}
