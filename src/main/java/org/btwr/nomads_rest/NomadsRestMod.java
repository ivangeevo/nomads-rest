package org.btwr.nomads_rest;

import net.fabricmc.api.ModInitializer;
import org.btwr.nomads_rest.block.ModBlocks;
import org.btwr.nomads_rest.block.entity.ModBlockEntities;
import org.btwr.nomads_rest.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NomadsRestMod implements ModInitializer {

    public static final String MOD_ID = "nomads_rest";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModItems.register();
        ModBlockEntities.register();
    }

}