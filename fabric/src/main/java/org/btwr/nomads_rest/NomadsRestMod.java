package org.btwr.nomads_rest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.btwr.nomads_rest.block.ModBlocks;
import org.btwr.nomads_rest.block.entity.BedrollBlockEntity;
import org.btwr.nomads_rest.block.entity.ModBlockEntities;
import org.btwr.nomads_rest.item.ModItems;

public class NomadsRestMod implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();

        ModBlocks.initialize();
        ModItems.initialize();

        this.registerBlockEntities();
        this.registerItemGroups();
    }

    private void registerBlockEntities() {
        ModBlockEntities.BEDROLL = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "bedroll"),
                BlockEntityType.Builder.of(BedrollBlockEntity::new,
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
                ).build()
        );
    }

    private void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register(entries -> {
            entries.accept(ModItems.WHITE_BEDROLL);
            entries.accept(ModItems.ORANGE_BEDROLL);
            entries.accept(ModItems.MAGENTA_BEDROLL);
            entries.accept(ModItems.LIGHT_BLUE_BEDROLL);
            entries.accept(ModItems.YELLOW_BEDROLL);
            entries.accept(ModItems.LIME_BEDROLL);
            entries.accept(ModItems.PINK_BEDROLL);
            entries.accept(ModItems.GRAY_BEDROLL);
            entries.accept(ModItems.LIGHT_GRAY_BEDROLL);
            entries.accept(ModItems.CYAN_BEDROLL);
            entries.accept(ModItems.PURPLE_BEDROLL);
            entries.accept(ModItems.BLUE_BEDROLL);
            entries.accept(ModItems.BROWN_BEDROLL);
            entries.accept(ModItems.GREEN_BEDROLL);
            entries.accept(ModItems.RED_BEDROLL);
            entries.accept(ModItems.BLACK_BEDROLL);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.accept(ModItems.WHITE_BEDROLL);
            entries.accept(ModItems.ORANGE_BEDROLL);
            entries.accept(ModItems.MAGENTA_BEDROLL);
            entries.accept(ModItems.LIGHT_BLUE_BEDROLL);
            entries.accept(ModItems.YELLOW_BEDROLL);
            entries.accept(ModItems.LIME_BEDROLL);
            entries.accept(ModItems.PINK_BEDROLL);
            entries.accept(ModItems.GRAY_BEDROLL);
            entries.accept(ModItems.LIGHT_GRAY_BEDROLL);
            entries.accept(ModItems.CYAN_BEDROLL);
            entries.accept(ModItems.PURPLE_BEDROLL);
            entries.accept(ModItems.BLUE_BEDROLL);
            entries.accept(ModItems.BROWN_BEDROLL);
            entries.accept(ModItems.GREEN_BEDROLL);
            entries.accept(ModItems.RED_BEDROLL);
            entries.accept(ModItems.BLACK_BEDROLL);
        });

    }
}
