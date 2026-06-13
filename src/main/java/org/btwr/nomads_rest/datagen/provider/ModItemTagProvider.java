package org.btwr.nomads_rest.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import org.btwr.nomads_rest.item.ModItems;
import org.btwr.nomads_rest.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.BEDROLLS)
                .add(ModItems.WHITE_BEDROLL)
                .add(ModItems.ORANGE_BEDROLL)
                .add(ModItems.MAGENTA_BEDROLL)
                .add(ModItems.LIGHT_BLUE_BEDROLL)
                .add(ModItems.YELLOW_BEDROLL)
                .add(ModItems.LIME_BEDROLL)
                .add(ModItems.PINK_BEDROLL)
                .add(ModItems.GRAY_BEDROLL)
                .add(ModItems.LIGHT_GRAY_BEDROLL)
                .add(ModItems.CYAN_BEDROLL)
                .add(ModItems.PURPLE_BEDROLL)
                .add(ModItems.BLUE_BEDROLL)
                .add(ModItems.BROWN_BEDROLL)
                .add(ModItems.GREEN_BEDROLL)
                .add(ModItems.RED_BEDROLL)
                .add(ModItems.BLACK_BEDROLL);
    }

}