package org.btwr.nomads_rest.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.DyedItemColor;
import org.btwr.nomads_rest.Constants;
import org.btwr.nomads_rest.block.ModBlocks;
import org.btwr.nomads_rest.block.entity.ModBlockEntities;
import org.btwr.nomads_rest.client.block.entity.model.ModEntityModelLayers;
import org.btwr.nomads_rest.client.block.entity.renderer.BedrollBlockEntityRenderer;
import org.btwr.nomads_rest.item.ModItems;

public class NomadsRestModClient implements ClientModInitializer {

    public static final ResourceLocation BEDROLLS_ATLAS_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID, "textures/atlas/bedrolls.png"
    );

    private static final RenderType BEDROLLS_RENDER_LAYER = RenderType.entitySolid(BEDROLLS_ATLAS_TEXTURE);

    @Override
    public void onInitializeClient() {

        // Register the RenderLayer for the Bedrolls
        BlockRenderLayerMap.INSTANCE.putBlocks(BEDROLLS_RENDER_LAYER,
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
        );

        BlockEntityRenderers.register(ModBlockEntities.BEDROLL, BedrollBlockEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(
                ModEntityModelLayers.BEDROLL_FOOT,
                BedrollBlockEntityRenderer::getFootTexturedModelData
        );

        EntityModelLayerRegistry.registerModelLayer(
                ModEntityModelLayers.BEDROLL_HEAD,
                BedrollBlockEntityRenderer::getHeadTexturedModelData
        );

        ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> {
            if (tintIndex == 0) {
                DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
                return dyedColor != null ? 0xFF000000 | dyedColor.rgb() : 0xFFFFFFFF;
            }
            return -1;
        }),
                ModItems.WHITE_BEDROLL,
                ModItems.ORANGE_BEDROLL,
                ModItems.MAGENTA_BEDROLL,
                ModItems.LIGHT_BLUE_BEDROLL,
                ModItems.YELLOW_BEDROLL,
                ModItems.LIME_BEDROLL,
                ModItems.PINK_BEDROLL,
                ModItems.GRAY_BEDROLL,
                ModItems.LIGHT_GRAY_BEDROLL,
                ModItems.CYAN_BEDROLL,
                ModItems.PURPLE_BEDROLL,
                ModItems.BLUE_BEDROLL,
                ModItems.BROWN_BEDROLL,
                ModItems.GREEN_BEDROLL,
                ModItems.RED_BEDROLL,
                ModItems.BLACK_BEDROLL
        );

    }

}