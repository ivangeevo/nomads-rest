package org.btwr.nomads_rest.mixin.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviourMixin {

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    private void requireSolidGround(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!(state.getBlock() instanceof BedBlock)) return;
        if (!state.hasProperty(HorizontalDirectionalBlock.FACING) || !state.hasProperty(BedBlock.PART)) return;

        Direction facing = state.getValue(HorizontalDirectionalBlock.FACING);
        BedPart part = state.getValue(BedBlock.PART);

        BlockPos footPos = part == BedPart.FOOT ? pos : pos.relative(facing.getOpposite());
        BlockPos headPos = part == BedPart.HEAD ? pos : pos.relative(facing);

        boolean footSolid = level.getBlockState(footPos.below()).isRedstoneConductor(level, footPos.below());
        boolean headSolid = level.getBlockState(headPos.below()).isRedstoneConductor(level, headPos.below());

        if (!footSolid || !headSolid) {
            cir.setReturnValue(false);
        }
    }
}

