package org.ivangeevo.nomads_rest.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        /**
        getOrCreateTagBuilder(ModTags.Items.BEDROLLS)
                .add(ModBlocks.WHITE_BEDROLL)
                .add(ModBlocks.ORANGE_BEDROLL)
                .add(ModBlocks.MAGENTA_BEDROLL)
                .add(ModBlocks.LIGHT_BLUE_BEDROLL)
                .add(ModBlocks.YELLOW_BEDROLL)
                .add(ModBlocks.LIME_BEDROLL)
                .add(ModBlocks.PINK_BEDROLL)
                .add(ModBlocks.GRAY_BEDROLL)
                .add(ModBlocks.LIGHT_GRAY_BEDROLL)
                .add(ModBlocks.CYAN_BEDROLL)
                .add(ModBlocks.PURPLE_BEDROLL)
                .add(ModBlocks.BLUE_BEDROLL)
                .add(ModBlocks.BROWN_BEDROLL)
                .add(ModBlocks.GREEN_BEDROLL)
                .add(ModBlocks.RED_BEDROLL)
                .add(ModBlocks.BLACK_BEDROLL);
         **/

    }
}
