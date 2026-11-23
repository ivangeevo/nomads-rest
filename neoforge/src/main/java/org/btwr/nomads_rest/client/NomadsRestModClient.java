package org.btwr.nomads_rest.client;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.btwr.nomads_rest.block.entity.ModBlockEntities;
import org.btwr.nomads_rest.client.block.entity.model.ModEntityModelLayers;
import org.btwr.nomads_rest.client.block.entity.renderer.BedrollBlockEntityRenderer;
import org.btwr.nomads_rest.item.ModItems;

public class NomadsRestModClient {
    public static void init(IEventBus eventBus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, (IConfigScreenFactory) (mod, parent) -> new NomadsRestConfigScreen(parent));

        eventBus.addListener(NomadsRestModClient::onRegisterRenderers);
        eventBus.addListener(NomadsRestModClient::onRegisterLayerDefinitions);
        eventBus.addListener(NomadsRestModClient::onRegisterItemColors);
    }

    private static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.BEDROLL, BedrollBlockEntityRenderer::new);
    }

    private static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntityModelLayers.BEDROLL_FOOT, BedrollBlockEntityRenderer::getFootTexturedModelData);
        event.registerLayerDefinition(ModEntityModelLayers.BEDROLL_HEAD, BedrollBlockEntityRenderer::getHeadTexturedModelData);
    }

    private static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
                    if (tintIndex == 0) {
                        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
                        return dyedColor != null ? 0xFF000000 | dyedColor.rgb() : 0xFFFFFFFF;
                    }
                    return -1;
                },
                ModItems.WHITE_BEDROLL, ModItems.ORANGE_BEDROLL, ModItems.MAGENTA_BEDROLL,
                ModItems.LIGHT_BLUE_BEDROLL, ModItems.YELLOW_BEDROLL, ModItems.LIME_BEDROLL,
                ModItems.PINK_BEDROLL, ModItems.GRAY_BEDROLL, ModItems.LIGHT_GRAY_BEDROLL,
                ModItems.CYAN_BEDROLL, ModItems.PURPLE_BEDROLL, ModItems.BLUE_BEDROLL,
                ModItems.BROWN_BEDROLL, ModItems.GREEN_BEDROLL, ModItems.RED_BEDROLL,
                ModItems.BLACK_BEDROLL
        );
    }
}