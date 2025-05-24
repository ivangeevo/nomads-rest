package org.ivangeevo.nomads_rest.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BedPart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.BedBlock.PART;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin
{

    //@Inject(method = "neighborUpdate", at = @At("HEAD"))
    private void makeBedDropOnUpdateBelow(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify, CallbackInfo ci) {
        if (!((AbstractBlock)(Object)this instanceof BedBlock bedBlock)) {
            return;
        }

        if (!world.getBlockState(pos.down()).isSolidBlock(world, pos.down()) && state.contains(PART)) {
            if (state.get(PART) == BedPart.HEAD) {
                Block.dropStacks(state, world, pos);
            }
        }


    }



}
