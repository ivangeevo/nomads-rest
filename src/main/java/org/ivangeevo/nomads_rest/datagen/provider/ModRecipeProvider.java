package org.ivangeevo.nomads_rest.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.ivangeevo.nomads_rest.NomadsRestMod;
import org.ivangeevo.nomads_rest.item.ModItems;
import org.ivangeevo.nomads_rest.tag.ModTags;

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
        this.overrideVanillaBedRecipes(recipeExporter);
        this.registerBedrollRecipes(recipeExporter);
    }

    private void registerBedrollRecipes(RecipeExporter exporter) {
        for (DyeColor color : DyeColor.values()) {
            Item woolItem = Registries.ITEM.get(Identifier.ofVanilla(color.getName() + "_wool"));
            Identifier bedrollId = Identifier.of(NomadsRestMod.MOD_ID, color.getName() + "_bedroll");
            Item bedrollItem = Registries.ITEM.get(bedrollId);

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, bedrollItem)
                    .input(woolItem)
                    .input(woolItem)
                    .input(ConventionalItemTags.STRINGS)
                    .criterion("has_wool", conditionsFromTag(ItemTags.WOOL))
                    .offerTo(exporter, bedrollId);
        }
    }

    private void overrideVanillaBedRecipes(RecipeExporter exporter) {
        offerBedRecipe(exporter, Items.WHITE_BED, Items.WHITE_WOOL, ModItems.WHITE_BEDROLL);
        offerBedRecipe(exporter, Items.ORANGE_BED, Items.ORANGE_WOOL, ModItems.ORANGE_BEDROLL);
        offerBedRecipe(exporter, Items.MAGENTA_BED, Items.MAGENTA_WOOL, ModItems.MAGENTA_BEDROLL);
        offerBedRecipe(exporter, Items.LIGHT_BLUE_BED, Items.LIGHT_BLUE_WOOL, ModItems.LIGHT_BLUE_BEDROLL);
        offerBedRecipe(exporter, Items.YELLOW_BED, Items.YELLOW_WOOL, ModItems.YELLOW_BEDROLL);
        offerBedRecipe(exporter, Items.LIME_BED, Items.LIME_WOOL, ModItems.LIME_BEDROLL);
        offerBedRecipe(exporter, Items.PINK_BED, Items.PINK_WOOL, ModItems.PINK_BEDROLL);
        offerBedRecipe(exporter, Items.GRAY_BED, Items.GRAY_WOOL, ModItems.GRAY_BEDROLL);
        offerBedRecipe(exporter, Items.LIGHT_GRAY_BED, Items.LIGHT_GRAY_WOOL, ModItems.LIGHT_GRAY_BEDROLL);
        offerBedRecipe(exporter, Items.CYAN_BED, Items.CYAN_WOOL, ModItems.CYAN_BEDROLL);
        offerBedRecipe(exporter, Items.PURPLE_BED, Items.PURPLE_WOOL, ModItems.PURPLE_BEDROLL);
        offerBedRecipe(exporter, Items.BLUE_BED, Items.BLUE_WOOL, ModItems.BLUE_BEDROLL);
        offerBedRecipe(exporter, Items.BROWN_BED, Items.BROWN_WOOL, ModItems.BROWN_BEDROLL);
        offerBedRecipe(exporter, Items.GREEN_BED, Items.GREEN_WOOL, ModItems.GREEN_BEDROLL);
        offerBedRecipe(exporter, Items.RED_BED, Items.RED_WOOL, ModItems.RED_BEDROLL);
        offerBedRecipe(exporter, Items.BLACK_BED, Items.BLACK_WOOL, ModItems.BLACK_BEDROLL);
    }

    public static void offerBedRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible woolInput, ItemConvertible bedrollItem) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, output)
                .input('B', ModTags.Items.BEDROLLS)
                .input('#', woolInput)
                .input('X', ItemTags.WOODEN_SLABS)
                .pattern(" B ")
                .pattern("###")
                .pattern("XXX")
                .group("bed")
                .criterion(hasItem(woolInput), conditionsFromItem(woolInput))
                .offerTo(exporter);
    }

}