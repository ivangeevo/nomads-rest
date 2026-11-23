package org.btwr.nomads_rest.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.SleepStatus;
import org.btwr.nomads_rest.util.SleepUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SleepStatus.class)
public abstract class SleepStatusMixin {
    @Inject(method = "update", at = @At("TAIL"))
    public void onSleepUpdate(List<ServerPlayer> players, CallbackInfoReturnable<Boolean> cir) {
        SleepUtils.stopTimeLapseIfTooFewSleeping((SleepStatus) (Object) this, players);
    }
}