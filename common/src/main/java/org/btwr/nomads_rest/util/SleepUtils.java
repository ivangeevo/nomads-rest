package org.btwr.nomads_rest.util;

import net.minecraft.server.ServerTickRateManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.SleepStatus;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.btwr.nomads_rest.config.NomadsRestConfig;
import org.btwr.nomads_rest.mixin.accessor.ServerLevelSleepAccessor;

import java.util.List;

public class SleepUtils {
    private static final long TICKS_PER_DAY = 24000L;

    // Normal sleeping is turned off, unless the config wants the vanilla night skip
    public static boolean shouldSkipNightInstantly(SleepStatus sleepStatus, int percentage) {
        return !NomadsRestConfig.timeLapseSleep && sleepStatus.areEnoughSleeping(percentage);
    }

    // Speeds up time while enough players are asleep, then wakes everyone up once it reaches the wake time
    public static void tickTimeLapse(ServerLevel level, TickRateManager tickRateManager) {
        if (!NomadsRestConfig.timeLapseSleep) return;
        if (!(tickRateManager instanceof ServerTickRateManager serverTickRateManager)) return;

        ServerLevelSleepAccessor accessor = (ServerLevelSleepAccessor) level;
        SleepStatus sleepStatus = accessor.nomads_rest$getSleepStatus();
        GameRules gameRules = level.getGameRules();
        int percentage = gameRules.getInt(GameRules.RULE_PLAYERS_SLEEPING_PERCENTAGE);

        if (!sleepStatus.areEnoughSleeping(percentage) || !sleepStatus.areEnoughDeepSleeping(percentage, level.players())) return;
        if (!gameRules.getBoolean(GameRules.RULE_DAYLIGHT)) return;

        long ticksUntilWake = Math.floorMod(NomadsRestConfig.wakeTime - level.getDayTime(), TICKS_PER_DAY);

        if (ticksUntilWake == 0) {
            serverTickRateManager.stopSprinting();
            accessor.nomads_rest$wakeUpAllPlayers();

            if (NomadsRestConfig.clearWeatherOnWake && gameRules.getBoolean(GameRules.RULE_WEATHER_CYCLE) && level.isRaining()) {
                accessor.nomads_rest$resetWeatherCycle();
            }
            return;
        }

        if (!serverTickRateManager.isSprinting()) {
            if (serverTickRateManager instanceof SleepFeedback feedback) {
                feedback.nomads_rest$setNoFeedbackForSleep(true);
            }

            serverTickRateManager.requestGameToSprint((int) ticksUntilWake);
        }
    }

    // Stops the time-lapse if there are no longer enough sleeping players
    public static void stopTimeLapseIfTooFewSleeping(SleepStatus sleepStatus, List<ServerPlayer> players) {
        if (!NomadsRestConfig.timeLapseSleep || players.isEmpty()) return;

        ServerLevel level = players.getFirst().serverLevel();
        int requiredSleepers = sleepStatus.sleepersNeeded(level.getGameRules().getInt(GameRules.RULE_PLAYERS_SLEEPING_PERCENTAGE));

        if (sleepStatus.amountSleeping() < requiredSleepers) {
            level.getServer().tickRateManager().stopSprinting();
        }
    }

    // Vanilla wakes sleepers as soon as it's bright enough, which is before the time-lapse reaches the wake time.
    // tickTimeLapse wakes everyone up instead once it gets there.
    public static boolean canWakeInDaylight(Player player) {
        return !(NomadsRestConfig.timeLapseSleep
                && player.level() instanceof ServerLevel serverLevel
                && serverLevel.getServer().tickRateManager().isSprinting());
    }
}