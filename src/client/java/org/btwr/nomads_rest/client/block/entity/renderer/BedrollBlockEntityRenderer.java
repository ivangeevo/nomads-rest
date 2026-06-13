package org.btwr.nomads_rest.client.block.entity.renderer;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.enums.BedPart;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import org.btwr.nomads_rest.NomadsRestMod;
import org.btwr.nomads_rest.block.blocks.BedrollBlock;
import org.btwr.nomads_rest.block.entity.BedrollBlockEntity;
import org.btwr.nomads_rest.block.entity.ModBlockEntities;
import org.btwr.nomads_rest.client.block.entity.model.ModEntityModelLayers;

@Environment(EnvType.CLIENT)
public class BedrollBlockEntityRenderer implements BlockEntityRenderer<BedrollBlockEntity>
{
    private final ModelPart bedHead;

    private final ModelPart bedFoot;

    public BedrollBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.bedHead = ctx.getLayerModelPart(ModEntityModelLayers.BEDROLL_HEAD);
        this.bedFoot = ctx.getLayerModelPart(ModEntityModelLayers.BEDROLL_FOOT);
    }

    public static TexturedModelData getHeadTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData
                .addChild("main",
                        ModelPartBuilder.create()
                                .uv(0, 0)
                                .cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 3.0F), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getFootTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData
                .addChild("main", ModelPartBuilder
                        .create()
                        .uv(0, 19)
                        .cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 3.0F), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }

    public void render(BedrollBlockEntity be, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        final SpriteIdentifier spriteIdentifier = new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE,
                Identifier.of(NomadsRestMod.MOD_ID, "entity/bedroll/bedroll"));

        World world = be.getWorld();

        int rgb = be.getColor().getFireworkColor();
        int argb = 0xFF000000 | rgb;

        if (world != null) {
            BlockState blockState = be.getCachedState();
            DoubleBlockProperties.PropertySource<? extends BedrollBlockEntity> propertySource =
                    DoubleBlockProperties.toPropertySource(
                            ModBlockEntities.BEDROLL,
                            BedrollBlock::getBedPart,
                            BedrollBlock::getOppositePartDirection,
                            ChestBlock.FACING,
                            blockState,
                            world,
                            be.getPos(),
                            (worldx, pos) -> false
                    );
            int k = ((Int2IntFunction)propertySource.apply(new LightmapCoordinatesRetriever())).get(i);
            this.renderPart(matrixStack, vertexConsumerProvider, blockState.get(BedrollBlock.PART) == BedPart.HEAD ? this.bedHead : this.bedFoot, blockState.get(BedBlock.FACING), spriteIdentifier, k, j, false, argb);
        } else {
            this.renderPart(matrixStack, vertexConsumerProvider, this.bedHead, Direction.SOUTH, spriteIdentifier, i, j, false, argb);
            this.renderPart(matrixStack, vertexConsumerProvider, this.bedFoot, Direction.SOUTH, spriteIdentifier, i, j, true, argb);
        }

    }

    private void renderPart(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ModelPart part, Direction direction, SpriteIdentifier sprite, int light, int overlay, boolean isFoot, int color) {
        matrices.push();
        matrices.translate(0.0F, 0.1875F, isFoot ? -1.0F : 0.0F);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
        matrices.translate(0.5F, 0.5F, 0.5F);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0F + direction.asRotation()));
        matrices.translate(-0.5F, -0.5F, -0.5F);
        VertexConsumer vertexConsumer = sprite.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
        part.render(matrices, vertexConsumer, light, overlay, color);
        matrices.pop();
    }

}