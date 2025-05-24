package org.ivangeevo.nomads_rest.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.Identifier;
import org.ivangeevo.nomads_rest.NomadsRestMod;
import org.ivangeevo.nomads_rest.block.ModBlocks;
import org.ivangeevo.nomads_rest.block.entity.ModBlockEntities;
import org.ivangeevo.nomads_rest.client.block.entity.model.ModEntityModelLayers;
import org.ivangeevo.nomads_rest.client.block.entity.renderer.BedrollBlockEntityRenderer;

public class NomadsRestModClient implements ClientModInitializer
{

    public static final Identifier BEDROLLS_ATLAS_TEXTURE = Identifier.of(
            NomadsRestMod.MOD_ID, "textures/atlas/bedrolls.png"
    );

    private static final RenderLayer BEDROLLS_RENDER_LAYER = RenderLayer.getEntitySolid(BEDROLLS_ATLAS_TEXTURE);


    @Override
    public void onInitializeClient() {

        // Register the RenderLayer for the Bedroll Block
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

        BlockEntityRendererFactories.register(ModBlockEntities.BEDROLL, BedrollBlockEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(
                ModEntityModelLayers.BEDROLL_FOOT,
                BedrollBlockEntityRenderer::getFootTexturedModelData
        );

        EntityModelLayerRegistry.registerModelLayer(
                ModEntityModelLayers.BEDROLL_HEAD,
                BedrollBlockEntityRenderer::getHeadTexturedModelData
        );

    }



}
