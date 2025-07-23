package org.ivangeevo.nomads_rest.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.ivangeevo.nomads_rest.NomadsRestMod;
import org.ivangeevo.nomads_rest.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    @Override
    protected Identifier getRecipeIdentifier(Identifier identifier) {
        return identifier;
    }

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {

        this.overrideRecipesForVanillaBeds(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WHITE_BEDROLL)
                .input(ItemTags.WOOL)
                .input(ItemTags.WOOL)
                .input(ConventionalItemTags.STRINGS)
                .criterion("has_wool", conditionsFromTag(ItemTags.WOOL))
                .offerTo(recipeExporter, Identifier.of(NomadsRestMod.MOD_ID, "white_bedroll"));
    }

    private void overrideRecipesForVanillaBeds(RecipeExporter exporter) {
        offerBedRecipe(exporter, Items.WHITE_BED, Items.WHITE_WOOL);
        offerBedRecipe(exporter, Items.ORANGE_BED, Items.ORANGE_WOOL);
        offerBedRecipe(exporter, Items.MAGENTA_BED, Items.MAGENTA_WOOL);
        offerBedRecipe(exporter, Items.LIGHT_BLUE_BED, Items.LIGHT_BLUE_WOOL);
        offerBedRecipe(exporter, Items.YELLOW_BED, Items.YELLOW_WOOL);
        offerBedRecipe(exporter, Items.LIME_BED, Items.LIME_WOOL);
        offerBedRecipe(exporter, Items.PINK_BED, Items.PINK_WOOL);
        offerBedRecipe(exporter, Items.GRAY_BED, Items.GRAY_WOOL);
        offerBedRecipe(exporter, Items.LIGHT_GRAY_BED, Items.LIGHT_GRAY_WOOL);
        offerBedRecipe(exporter, Items.CYAN_BED, Items.CYAN_WOOL);
        offerBedRecipe(exporter, Items.PURPLE_BED, Items.PURPLE_WOOL);
        offerBedRecipe(exporter, Items.BLUE_BED, Items.BLUE_WOOL);
        offerBedRecipe(exporter, Items.BROWN_BED, Items.BROWN_WOOL);
        offerBedRecipe(exporter, Items.GREEN_BED, Items.GREEN_WOOL);
        offerBedRecipe(exporter, Items.RED_BED, Items.RED_WOOL);
        offerBedRecipe(exporter, Items.BLACK_BED, Items.BLACK_WOOL);
    }

    public static void offerBedRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible woolInput) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, output)
                .input('B', ModItems.WHITE_BEDROLL)
                .input('#', woolInput)
                .input('X', ItemTags.WOODEN_SLABS)
                .pattern(" B ")
                .pattern("###")
                .pattern("XXX")
                .group("bed")
                .criterion(RecipeProvider.hasItem(woolInput), RecipeProvider.conditionsFromItem(woolInput))
                .offerTo(exporter);
    }
}
