package net.doverholm.spmod.item;

import net.doverholm.spmod.SPMod;
import net.doverholm.spmod.item.custom.BurningBowItem;
import net.doverholm.spmod.item.custom.WandItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

public class ModItems {

    public static final Item ETHEREUM_ORE = registerItem("ethereum_ore", new Item(new Item.Settings()));
    public static final Item ETHEREUM = registerItem("ethereum", new Item(new Item.Settings()));
    public static final Item SCORCHLINE = registerItem("scorchline", new Item(new Item.Settings()) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if(Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("tooltip.spmod.scorchline"));
            }
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item WARDENS_BANE = registerItem("wardens_bane", new Item(new Item.Settings()));

    public static final Item WAND = registerItem("wand", new WandItem(new Item.Settings().maxDamage(32)));

    public static final Item CURIOUS_MANA_BEAN = registerItem("curious_mana_bean", new Item(new Item.Settings().food(ModFoodComponents.CURIOUS_MANA_BEAN)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if(Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("tooltip.spmod.curious_mana_bean"));
            }
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item DRIED_CURIOUS_MANA_BEAN = registerItem("dried_curious_mana_bean", new Item(new Item.Settings()));

    public static final Item OATH_OF_THE_BURNING_VEIN = registerItem("oath_of_the_burning_vein",
            new BurningBowItem(new Item.Settings().maxDamage(500).fireproof().rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SPMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SPMod.LOGGER.info("Registering Mod Items for " + SPMod.MOD_ID);
    }
}
