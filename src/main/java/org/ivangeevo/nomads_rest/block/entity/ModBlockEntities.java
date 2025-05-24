package org.ivangeevo.nomads_rest.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.ivangeevo.nomads_rest.NomadsRestMod;
import org.ivangeevo.nomads_rest.block.ModBlocks;

public class ModBlockEntities {

    public static BlockEntityType<BedrollBlockEntity> BEDROLL;

    public static void registerBlockEntities() {

        BEDROLL = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(NomadsRestMod.MOD_ID, "bedroll"),
                BlockEntityType.Builder.create(BedrollBlockEntity::new,
                        ModBlocks.WHITE_BEDROLL,
                        ModBlocks.ORANGE_BEDROLL,
                        ModBlocks.MAGENTA_BEDROLL,
                        ModBlocks.LIGHT_BLUE_BEDROLL,
                        ModBlocks.YELLOW_BEDROLL,
                        ModBlocks.LIME_BEDROLL,
                        ModBlocks.PINK_BEDROLL,
                        ModBlocks.GRAY_BEDROLL,
                        ModBlocks.LIGHT_GRAY_BEDROLL,
                        ModBlocks.CYAN_BEDROLL,
                        ModBlocks.PURPLE_BEDROLL,
                        ModBlocks.BLUE_BEDROLL,
                        ModBlocks.BROWN_BEDROLL,
                        ModBlocks.GREEN_BEDROLL,
                        ModBlocks.RED_BEDROLL,
                        ModBlocks.BLACK_BEDROLL

                ).build(null)
        );

    }


}
