package org.btwr.nomads_rest.client.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import org.btwr.nomads_rest.Constants;
import org.btwr.nomads_rest.block.blocks.BedrollBlock;
import org.btwr.nomads_rest.block.entity.BedrollBlockEntity;
import org.btwr.nomads_rest.block.entity.ModBlockEntities;
import org.btwr.nomads_rest.client.block.entity.model.ModEntityModelLayers;

public class BedrollBlockEntityRenderer implements BlockEntityRenderer<BedrollBlockEntity> {
    private final ModelPart bedHead;

    private final ModelPart bedFoot;

    public BedrollBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.bedHead = ctx.bakeLayer(ModEntityModelLayers.BEDROLL_HEAD);
        this.bedFoot = ctx.bakeLayer(ModEntityModelLayers.BEDROLL_FOOT);
    }

    public static LayerDefinition getHeadTexturedModelData() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition partData = mesh.getRoot();
        partData
                .addOrReplaceChild("main",
                        CubeListBuilder.create()
                                .texOffs(0, 0)
                                .addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 3.0F), PartPose.ZERO);
        return LayerDefinition.create(mesh, 64, 64);
    }

    public static LayerDefinition getFootTexturedModelData() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition partData = mesh.getRoot();
        partData
                .addOrReplaceChild("main",
                        CubeListBuilder.create()
                                .texOffs(0, 19)
                                .addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 3.0F), PartPose.ZERO);
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void render(BedrollBlockEntity be, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        final Material material = new Material(InventoryMenu.BLOCK_ATLAS,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entity/bedroll/bedroll")
        );

        Level level = be.getLevel();

        int rgb = be.getColor().getFireworkColor();
        int argb = 0xFF000000 | rgb;

        if (level != null) {
            BlockState state = be.getBlockState();
            DoubleBlockCombiner.NeighborCombineResult<? extends BedrollBlockEntity> combineResult =
                    DoubleBlockCombiner.combineWithNeigbour(
                            ModBlockEntities.BEDROLL,
                            BedrollBlock::getBlockType,
                            BedrollBlock::getConnectedDirection,
                            BedBlock.FACING,
                            state,
                            level,
                            be.getBlockPos(),
                            (worldx, pos) -> false
                    );
            int k = combineResult.apply(new BrightnessCombiner<>()).get(i);
            this.renderPart(poseStack, multiBufferSource, state.getValue(BedrollBlock.PART) == BedPart.HEAD ? this.bedHead : this.bedFoot, state.getValue(BedBlock.FACING), material, k, i1, false, argb);
        } else {
            this.renderPart(poseStack, multiBufferSource, this.bedHead, Direction.SOUTH, material, i, i1, false, argb);
            this.renderPart(poseStack, multiBufferSource, this.bedFoot, Direction.SOUTH, material, i, i1, true, argb);
        }
    }

    private void renderPart(PoseStack poseStack, MultiBufferSource multiBufferSource, ModelPart part, Direction direction, Material material, int light, int overlay, boolean isFoot, int color) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.1875F, isFoot ? -1.0F : 0.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F + direction.toYRot()));
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        VertexConsumer vertexConsumer = material.buffer(multiBufferSource, RenderType::entitySolid);
        part.render(poseStack, vertexConsumer, light, overlay, color);
        poseStack.popPose();
    }

}