package org.btwr.nomads_rest.mixin;

import net.minecraft.server.ServerTickRateManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.SleepStatus;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SleepStatus.class)
public abstract class SleepStatusMixin {

    @Inject(method = "update", at = @At("TAIL"))
    public void onSleepUpdate(List<ServerPlayer> players, CallbackInfoReturnable<Boolean> cir) {
        if (players.isEmpty()) return;

        SleepStatus self = (SleepStatus) (Object) this;
        ServerLevel world = players.getFirst().serverLevel();
        ServerTickRateManager tickManager = world.getServer().tickRateManager();

        int requiredSleepers = self.sleepersNeeded(
            world.getGameRules().getInt(GameRules.RULE_PLAYERS_SLEEPING_PERCENTAGE)
        );

        // Stop sprinting if there are not enough sleeping players
        if (self.amountSleeping() < requiredSleepers) {
            tickManager.stopSprinting();
        }
    }
}
