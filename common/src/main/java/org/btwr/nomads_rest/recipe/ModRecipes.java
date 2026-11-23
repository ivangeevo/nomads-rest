package org.btwr.nomads_rest.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.btwr.nomads_rest.Constants;

public class ModRecipes {
    public static final RecipeSerializer<MixedBedrollRecipe> MIXED_BEDROLL = new MixedBedrollRecipe.Serializer();
    public static final RecipeSerializer<MixedBedRecipe> MIXED_BED = new MixedBedRecipe.Serializer();

    public static void initialize() {
        Constants.LOG.debug("Registering ModRecipes for " + Constants.MOD_ID);

        register("mixed_bedroll", MIXED_BEDROLL);
        register("mixed_bed", MIXED_BED);
    }

    private static void register(String name, RecipeSerializer<?> serializer) {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), serializer);
    }
}