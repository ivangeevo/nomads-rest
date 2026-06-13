package org.btwr.nomads_rest.mixin.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.world.level.block.BedBlock.PART;
import static net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {

    // Make beds and bedrolls(by inheritance) to drop when either of it's supporting blocks below isn't present
    @Inject(method = "handleNeighborChanged", at = @At("HEAD"))
    private void makeBedDropOnUpdateBelow(Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving, CallbackInfo ci) {
        BlockBehaviour.BlockStateBase state = (BlockBehaviour.BlockStateBase)(Object)this;
        if (!(state.getBlock() instanceof BedBlock) || !state.hasProperty(PART) || !state.hasProperty(FACING)) return;

        BedPart part = state.getValue(PART);
        Direction facing = state.getValue(FACING);

        BlockPos otherPartPos = part == BedPart.HEAD ? pos.relative(facing.getOpposite()) : pos.relative(facing);
        BlockState otherPartState = level.getBlockState(otherPartPos);

        if (!(otherPartState.getBlock() instanceof BedBlock) || !otherPartState.hasProperty(PART)) return;

        boolean currentOnSolid = level.getBlockState(pos.below()).isRedstoneConductor(level, pos.below());
        boolean otherOnSolid = level.getBlockState(otherPartPos.below()).isRedstoneConductor(level, otherPartPos.below());

        if (!currentOnSolid || !otherOnSolid) {
            if (part == BedPart.HEAD) {
                level.destroyBlock(pos, true); // Drop loot
                level.destroyBlock(otherPartPos, false); // No loot
            } else {
                level.destroyBlock(pos, false); // No loot
                level.destroyBlock(otherPartPos, true); // Drop loot
            }
        }
    }

}

