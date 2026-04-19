package net.doverholm.spmod.item;

import net.doverholm.spmod.SPMod;
import net.doverholm.spmod.item.custom.BurningBowItem;
import net.doverholm.spmod.item.custom.WandItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.BowItem;
import net.minecraft.item.*;
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

    public static final Item WARDENS_BANE_SWORD = registerItem("wardens_bane_sword", new SwordItem(ModToolMaterials.WARDENS_BANE_SWORD, new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.WARDENS_BANE_SWORD, 5, -2.4f))));

    public static final Item WARDENS_BANE_PICKAXE = registerItem("wardens_bane_pickaxe", new SwordItem(ModToolMaterials.WARDENS_BANE_SWORD, new Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.WARDENS_BANE_SWORD, 1, -2.8f))));

    public static final Item WARDENS_BANE_AXE = registerItem("wardens_bane_axe", new SwordItem(ModToolMaterials.WARDENS_BANE_SWORD, new Item.Settings()
            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.WARDENS_BANE_SWORD, 6, -3.2f))));

    public static final Item WARDENS_BANE_SHOVEL = registerItem("wardens_bane_shovel", new SwordItem(ModToolMaterials.WARDENS_BANE_SWORD, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.WARDENS_BANE_SWORD, 1.5f, -3.0f))));

    public static final Item WARDENS_BANE_HOE = registerItem("wardens_bane_sword", new SwordItem(ModToolMaterials.WARDENS_BANE_SWORD, new Item.Settings()
            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.WARDENS_BANE_SWORD, 0, -3.0f))));

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


