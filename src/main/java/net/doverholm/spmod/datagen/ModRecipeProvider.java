package net.doverholm.spmod.datagen;

import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        List<ItemConvertible> ETHEREUM_SMELTABLES = List.of(ModItems.ETHEREUM_ORE, ModBlocks.ETHEREUM_ORE_BLOCK);
        List<ItemConvertible> CURIOUS_MANA_BEAN_SMELTABLES = List.of(ModItems.CURIOUS_MANA_BEAN);

        offerSmelting(exporter, ETHEREUM_SMELTABLES, RecipeCategory.MISC, ModItems.ETHEREUM, 0.25f, 200, "ethereum");
        offerBlasting(exporter, ETHEREUM_SMELTABLES, RecipeCategory.MISC, ModItems.ETHEREUM, 0.25f, 100, "ethereum");
        offerSmelting(exporter, CURIOUS_MANA_BEAN_SMELTABLES, RecipeCategory.FOOD, ModItems.DRIED_CURIOUS_MANA_BEAN, 0.1f, 100, "ethereum");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.ETHEREUM, RecipeCategory.MISC, ModBlocks.ETHEREUM_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCORCHLINE)
                .pattern(" F ")
                .pattern("BSG")
                .pattern(" M ")
                .input('F', Items.FIRE_CHARGE)
                .input('B', Items.BLAZE_POWDER)
                .input('S', Items.STRING)
                .input('G', Items.GUNPOWDER)
                .input('M', Items.MAGMA_CREAM)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .offerTo(exporter);
    }
}
