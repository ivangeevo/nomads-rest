package org.ivangeevo;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.ivangeevo.nomads_rest.datagen.ModBlockTagProvider;
import org.ivangeevo.nomads_rest.datagen.ModItemTagProvider;
import org.ivangeevo.nomads_rest.datagen.ModLootTableProvider;
import org.ivangeevo.nomads_rest.datagen.ModRecipeProvider;

public class ModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModLootTableProvider::new);
    }
}
