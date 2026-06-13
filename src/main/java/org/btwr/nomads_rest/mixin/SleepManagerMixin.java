package org.btwr.nomads_rest.mixin;

import net.minecraft.server.ServerTickManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.SleepManager;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SleepManager.class)
public abstract class SleepManagerMixin {

    @Inject(method = "update", at = @At("TAIL"))
    public void onSleepUpdate(List<ServerPlayerEntity> players, CallbackInfoReturnable<Boolean> cir) {
        if (players.isEmpty()) return;

        SleepManager sleepManager = (SleepManager) (Object) this;
        ServerWorld world = players.getFirst().getServerWorld();
        ServerTickManager tickManager = world.getServer().getTickManager();

        int requiredSleepers = sleepManager.getNightSkippingRequirement(
            world.getGameRules().getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE)
        );

        // Stop sprinting if there are not enough sleeping players
        if (sleepManager.getSleeping() < requiredSleepers) {
            tickManager.stopSprinting();
        }
    }
}
