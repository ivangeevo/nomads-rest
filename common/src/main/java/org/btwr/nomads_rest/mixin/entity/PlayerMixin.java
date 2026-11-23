package org.btwr.nomads_rest.mixin.entity;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.world.entity.player.Player;
import org.btwr.nomads_rest.util.SleepUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixin {
    // Wraps the call to wake up rather than the isDay check, since Fabric API already redirects that one
    @WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;stopSleepInBed(ZZ)V"))
    private boolean keepSleepingDuringTimeLapse(Player player, boolean wakeImmediately, boolean updateLevelForSleepingPlayers) {
        return SleepUtils.canWakeInDaylight(player);
    }
}