package net.doverholm.spmod.item;

import net.doverholm.spmod.SPMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ETHEREUM_ORE = registerItem("ethereum_ore", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SPMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SPMod.LOGGER.info("Registering Mod Items for " + SPMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ETHEREUM_ORE);
        });
    }
}
