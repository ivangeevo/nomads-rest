package org.btwr.nomads_rest.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.btwr.nomads_rest.Constants;
import org.btwr.nomads_rest.recipe.DyeMaterials;
import org.btwr.nomads_rest.recipe.MixedBedRecipe;
import org.btwr.nomads_rest.recipe.MixedBedrollRecipe;
import org.btwr.nomads_rest.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected ResourceLocation getRecipeIdentifier(ResourceLocation identifier) {
        return identifier;
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        this.overrideVanillaBedRecipes(recipeOutput);
        this.registerBedrollRecipes(recipeOutput);
        this.registerMixedColorRecipes(recipeOutput);
    }

    // Bedrolls and beds from mixed wool colors, colored with the blend of the wools. Other mods can override these
    // (same ids) to use different materials
    private void registerMixedColorRecipes(RecipeOutput recipeOutput) {
        MixedBedrollRecipe bedrollRecipe = new MixedBedrollRecipe(
                CraftingBookCategory.MISC, DyeMaterials.vanillaWool(), Ingredient.of(ConventionalItemTags.STRINGS)
        );

        MixedBedRecipe bedRecipe = new MixedBedRecipe(
                CraftingBookCategory.MISC, Ingredient.of(ModTags.Items.BEDROLLS),
                DyeMaterials.vanillaWool(), Ingredient.of(ItemTags.WOODEN_SLABS)
        );

        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mixed_bedroll"),
                bedrollRecipe,
                null
        );

        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mixed_bed"),
                bedRecipe,
                null
        );
    }

    private void registerBedrollRecipes(RecipeOutput recipeOutput) {
        for (DyeColor color : DyeColor.values()) {
            Item woolItem = BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(color.getName() + "_wool"));
            ResourceLocation bedrollId = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, color.getName() + "_bedroll");
            Item bedrollItem = BuiltInRegistries.ITEM.get(bedrollId);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, bedrollItem)
                    .requires(woolItem)
                    .requires(woolItem)
                    .requires(ConventionalItemTags.STRINGS)
                    .unlockedBy("has_wool", has(ItemTags.WOOL))
                    .save(recipeOutput, bedrollId);
        }
    }

    private void overrideVanillaBedRecipes(RecipeOutput recipeOutput) {
        for (DyeColor color : DyeColor.values()) {
            ItemLike woolItem = BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(color + "_wool"));
            ItemLike bedItem = BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(color + "_bed"));

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, bedItem)
                    .define('B', ModTags.Items.BEDROLLS)
                    .define('#', woolItem)
                    .define('X', ItemTags.WOODEN_SLABS)
                    .pattern(" B ")
                    .pattern("###")
                    .pattern("XXX")
                    .group("bed")
                    .unlockedBy(getHasName(woolItem), has(woolItem))
                    .save(recipeOutput);
        }
    }
}