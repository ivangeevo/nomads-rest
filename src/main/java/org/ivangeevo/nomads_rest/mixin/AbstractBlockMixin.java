package org.ivangeevo.nomads_rest.mixin;

import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.BedBlock.PART;
import static net.minecraft.block.HorizontalFacingBlock.FACING;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin
{

    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    private void requireSolidGround(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!(state.getBlock() instanceof BedBlock)) return;
        if (!state.contains(HorizontalFacingBlock.FACING) || !state.contains(BedBlock.PART)) return;

        Direction facing = state.get(HorizontalFacingBlock.FACING);
        BedPart part = state.get(BedBlock.PART);

        BlockPos footPos = part == BedPart.FOOT ? pos : pos.offset(facing.getOpposite());
        BlockPos headPos = part == BedPart.HEAD ? pos : pos.offset(facing);

        boolean footSolid = world.getBlockState(footPos.down()).isSolidBlock(world, footPos.down());
        boolean headSolid = world.getBlockState(headPos.down()).isSolidBlock(world, headPos.down());

        if (!footSolid || !headSolid) {
            cir.setReturnValue(false);
        }
    }
}

