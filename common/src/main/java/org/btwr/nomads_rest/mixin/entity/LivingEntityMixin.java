package org.btwr.nomads_rest.mixin.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.btwr.nomads_rest.block.blocks.BedrollBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    // Doesn't seeem to work properly. Not a priority; leave for later

    // Sets the Y position for the player when sleeping in a bedroll to be lower than normal bed
    //@Inject(method = "setPosToBed", at = @At("HEAD"))
    private void setBedrollSleepPos(BlockPos pos, CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        Level world = self.level();

        if (world.getBlockState(pos).getBlock() instanceof BedrollBlock) {
            //self.setPosition(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);
        }
    }

}
