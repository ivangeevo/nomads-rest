package org.btwr.nomads_rest.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.ServerTickRateManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.SleepStatus;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WritableLevelData;
import org.btwr.nomads_rest.util.SleepFeedback;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin extends Level {

    @Final @Shadow private SleepStatus sleepStatus;
    @Final @Shadow List<ServerPlayer> players;
    @Final @Shadow private ServerLevelData serverLevelData;

    @Invoker("wakeUpAllPlayers") abstract void wakeUpAllPlayers();
    @Invoker("resetWeatherCycle") abstract void resetWeatherCycle();

    protected ServerLevelMixin(WritableLevelData levelData, ResourceKey<Level> dimension, RegistryAccess registryAccess, Holder<DimensionType> dimensionTypeRegistration, Supplier<ProfilerFiller> profiler, boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
        super(levelData, dimension, registryAccess, dimensionTypeRegistration, profiler, isClientSide, isDebug, biomeZoomSeed, maxChainedNeighborUpdates);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/SleepStatus;areEnoughSleeping(I)Z"))
    public boolean canSkipNightRedirect(SleepStatus instance, int percentage) {
        // Turn off normal sleeping
        return false;
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getInt(Lnet/minecraft/world/level/GameRules$Key;)I"))
    public void skipNightTickWarp(BooleanSupplier shouldKeepTicking, CallbackInfo ci, @Local TickRateManager tickRateManager) {
        int i = this.getGameRules().getInt(GameRules.RULE_PLAYERS_SLEEPING_PERCENTAGE);

        if (!(tickRateManager instanceof ServerTickRateManager serverTickRateManager)) {
            return;
        }

        if (this.sleepStatus.areEnoughSleeping(i) && this.sleepStatus.areEnoughDeepSleeping(i, this.players)) {
            long time = this.serverLevelData.getDayTime();
            long sprintTargetTicks = 24000L - ((time + 24000L) % 24000L);
            if (!this.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {
                return;
            }

            if (time % 24000L == 0) {
                serverTickRateManager.stopSprinting();
                this.wakeUpAllPlayers();
                if (this.getGameRules().getBoolean(GameRules.RULE_WEATHER_CYCLE) && this.isRaining()) {
                    this.resetWeatherCycle();
                }
                return;
            }

            if (!serverTickRateManager.isSprinting()) {
                if (serverTickRateManager instanceof SleepFeedback feedback) {
                    feedback.nomads_rest$setNoFeedbackForSleep(true);
                }
                serverTickRateManager.requestGameToSprint((int) sprintTargetTicks);
            }
        }
    }

}