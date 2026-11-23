package org.btwr.nomads_rest.mixin.accessor;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.SleepStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerLevel.class)
public interface ServerLevelSleepAccessor {
    @Accessor("sleepStatus") SleepStatus nomads_rest$getSleepStatus();
    @Invoker("wakeUpAllPlayers") void nomads_rest$wakeUpAllPlayers();
    @Invoker("resetWeatherCycle") void nomads_rest$resetWeatherCycle();
}