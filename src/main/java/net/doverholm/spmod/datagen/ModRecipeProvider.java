package net.doverholm.spmod.datagen;

import net.doverholm.spmod.block.ModBlocks;
import net.doverholm.spmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
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
                .criterion(hasItem(Items.BLAZE_ROD), conditionsFromItem(Items.BLAZE_ROD))
                .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                .criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE))
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                .criterion(hasItem(Items.MAGMA_CREAM), conditionsFromItem(Items.MAGMA_CREAM))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.OATH_OF_THE_BURNING_VEIN)
                        .pattern(" WS")
                        .pattern("W S")
                        .pattern(" WS")
                        .input('W', ModBlocks.BLOODWOOD_LOG)
                        .input('S', ModItems.SCORCHLINE)
                    .criterion(hasItem(ModItems.SCORCHLINE), conditionsFromItem(ModItems.SCORCHLINE))
                    .criterion(hasItem(ModBlocks.BLOODWOOD_LOG), conditionsFromItem(ModBlocks.BLOODWOOD_LOG))
                    .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.WARDENS_BANE_SWORD)
                .pattern(" E ")
                .pattern("BCB")
                .pattern(" N ")
                .input('E', Items.ECHO_SHARD)
                .input('B', Items.BLAZE_POWDER)
                .input('C', Items.SCULK_CATALYST)
                .input('N', Items.BONE)
                .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODWOOD_PLANKS, 4)
                .input(Ingredient.ofItems(
                        ModBlocks.BLOODWOOD_LOG,
                        ModBlocks.BLOODWOOD_WOOD,
                        ModBlocks.STRIPPED_BLOODWOOD_LOG,
                        ModBlocks.STRIPPED_BLOODWOOD_WOOD
                ))
                .criterion("has_bloodwood_log", conditionsFromItem(ModBlocks.BLOODWOOD_LOG))
                .offerTo(exporter);

        createStairsRecipe(ModBlocks.BLOODWOOD_STAIRS, Ingredient.ofItems(ModBlocks.BLOODWOOD_PLANKS))
                .criterion("has_bloodwood_planks", conditionsFromItem(ModBlocks.BLOODWOOD_PLANKS))
                .offerTo(exporter);

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODWOOD_SLAB, Ingredient.ofItems(ModBlocks.BLOODWOOD_PLANKS))
                .criterion("has_bloodwood_planks", conditionsFromItem(ModBlocks.BLOODWOOD_PLANKS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.BLOODWOOD_BUTTON)
                .input(ModBlocks.BLOODWOOD_PLANKS)
                .criterion("has_bloodwood_log", conditionsFromItem(ModBlocks.BLOODWOOD_PLANKS))
                .offerTo(exporter);

        createPressurePlateRecipe(RecipeCategory.REDSTONE, ModBlocks.BLOODWOOD_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.BLOODWOOD_PLANKS))
                .criterion("has_bloodwood_planks", conditionsFromItem(ModBlocks.BLOODWOOD_PLANKS))
                .offerTo(exporter);

        createFenceRecipe(ModBlocks.BLOODWOOD_FENCE, Ingredient.ofItems(ModBlocks.BLOODWOOD_PLANKS))
                .criterion("has_bloodwood_planks", conditionsFromItem(ModBlocks.BLOODWOOD_PLANKS))
                .offerTo(exporter);

        createFenceGateRecipe(ModBlocks.BLOODWOOD_FENCE_GATE, Ingredient.ofItems(ModBlocks.BLOODWOOD_PLANKS))
                .criterion("has_bloodwood_planks", conditionsFromItem(ModBlocks.BLOODWOOD_PLANKS))
                .offerTo(exporter);

        createDoorRecipe(ModBlocks.BLOODWOOD_DOOR, Ingredient.ofItems(ModBlocks.BLOODWOOD_PLANKS))
                .criterion("has_bloodwood_planks", conditionsFromItem(ModBlocks.BLOODWOOD_PLANKS))
                .offerTo(exporter);

        createTrapdoorRecipe(ModBlocks.BLOODWOOD_TRAPDOOR, Ingredient.ofItems(ModBlocks.BLOODWOOD_PLANKS))
                .criterion("has_bloodwood_planks", conditionsFromItem(ModBlocks.BLOODWOOD_PLANKS))
                .offerTo(exporter);

    }
}
