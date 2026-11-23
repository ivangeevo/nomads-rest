package org.btwr.nomads_rest.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.SleepStatus;
import net.minecraft.world.TickRateManager;
import org.btwr.nomads_rest.util.SleepUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/SleepStatus;areEnoughSleeping(I)Z"))
    public boolean canSkipNightRedirect(SleepStatus instance, int percentage) {
        return SleepUtils.shouldSkipNightInstantly(instance, percentage);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getInt(Lnet/minecraft/world/level/GameRules$Key;)I"))
    public void skipNightTickWarp(BooleanSupplier shouldKeepTicking, CallbackInfo ci, @Local TickRateManager tickRateManager) {
        SleepUtils.tickTimeLapse((ServerLevel) (Object) this, tickRateManager);
    }
}