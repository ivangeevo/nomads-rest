package org.ivangeevo.nomads_rest.mixin.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.BedBlock.PART;
import static net.minecraft.block.HorizontalFacingBlock.FACING;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin
{

    // Make beds and bedrolls(by inheritance) to drop when either of it's supporting blocks below isn't present
    @Inject(method = "neighborUpdate", at = @At("HEAD"))
    private void makeBedDropOnUpdateBelow(World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify, CallbackInfo ci) {
        BlockState state = (BlockState)(Object)this;
        if (!(state.getBlock() instanceof BedBlock) || !state.contains(PART) || !state.contains(FACING)) return;

        BedPart part = state.get(PART);
        Direction facing = state.get(FACING);

        BlockPos otherPartPos = part == BedPart.HEAD ? pos.offset(facing.getOpposite()) : pos.offset(facing);
        BlockState otherPartState = world.getBlockState(otherPartPos);

        if (!(otherPartState.getBlock() instanceof BedBlock) || !otherPartState.contains(PART)) return;

        boolean currentOnSolid = world.getBlockState(pos.down()).isSolidBlock(world, pos.down());
        boolean otherOnSolid = world.getBlockState(otherPartPos.down()).isSolidBlock(world, otherPartPos.down());

        if (!currentOnSolid || !otherOnSolid) {
            if (part == BedPart.HEAD) {
                world.breakBlock(pos, true); // Drop loot
                world.breakBlock(otherPartPos, false); // No loot
            } else {
                world.breakBlock(pos, false); // No loot
                world.breakBlock(otherPartPos, true); // Drop loot
            }
        }
    }

}

