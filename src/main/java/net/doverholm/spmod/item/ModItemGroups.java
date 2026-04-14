package net.doverholm.spmod.item;

import net.doverholm.spmod.SPMod;
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


                    }).build());
    public static final ItemGroup ETHEREUM = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SPMod.MOD_ID, "ethereum"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ETHEREUM))
                    .displayName(Text.translatable("itemgroup.spmod.sp_mod.ethereum"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ETHEREUM);
                        entries.add(ModItems.ETHEREUM_ORE);

                    }).build());


    public static void registerItemGroups() {
        SPMod.LOGGER.info("Registering Item Groups for " + SPMod.MOD_ID);
    }
}
