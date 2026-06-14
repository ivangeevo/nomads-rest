package org.btwr.nomads_rest;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.btwr.nomads_rest.block.ModBlocks;
import org.btwr.nomads_rest.block.entity.BedrollBlockEntity;
import org.btwr.nomads_rest.block.entity.ModBlockEntities;
import org.btwr.nomads_rest.client.NomadsRestModClient;
import org.btwr.nomads_rest.item.ModItems;

import static net.minecraft.world.item.CreativeModeTabs.FUNCTIONAL_BLOCKS;

@Mod(Constants.MOD_ID)
public class NomadsRestMod {

    public NomadsRestMod(IEventBus eventBus) {
        CommonClass.init();

        eventBus.addListener(this::onRegister);
        eventBus.addListener(this::registerCreativeTabEntries);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            NomadsRestModClient.init(eventBus);
        }
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.BLOCK) {
            ModBlocks.initialize();
        }
        if (event.getRegistryKey() == Registries.ITEM) {
            ModItems.initialize();
        }
        if (event.getRegistryKey() == Registries.BLOCK_ENTITY_TYPE) {
            registerBlockEntities();
        }
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
                ).build(null)
        );
    }

    private void registerCreativeTabEntries(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == FUNCTIONAL_BLOCKS) {
            event.accept(ModItems.WHITE_BEDROLL);
            event.accept(ModItems.ORANGE_BEDROLL);
            event.accept(ModItems.MAGENTA_BEDROLL);
            event.accept(ModItems.LIGHT_BLUE_BEDROLL);
            event.accept(ModItems.YELLOW_BEDROLL);
            event.accept(ModItems.LIME_BEDROLL);
            event.accept(ModItems.PINK_BEDROLL);
            event.accept(ModItems.GRAY_BEDROLL);
            event.accept(ModItems.LIGHT_GRAY_BEDROLL);
            event.accept(ModItems.CYAN_BEDROLL);
            event.accept(ModItems.PURPLE_BEDROLL);
            event.accept(ModItems.BLUE_BEDROLL);
            event.accept(ModItems.BROWN_BEDROLL);
            event.accept(ModItems.GREEN_BEDROLL);
            event.accept(ModItems.RED_BEDROLL);
            event.accept(ModItems.BLACK_BEDROLL);
        }
    }

}