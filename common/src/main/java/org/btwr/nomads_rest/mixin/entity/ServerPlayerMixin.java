package org.btwr.nomads_rest.mixin.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.btwr.nomads_rest.util.BedUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "setRespawnPosition", at = @At("HEAD"), cancellable = true)
    public void setRespawnPosition(ResourceKey<Level> dimension, @Nullable BlockPos position, float angle, boolean forced, boolean sendMessage, CallbackInfo ci) {
        if (BedUtils.preventsSettingSpawn((ServerPlayer) (Object) this, position)) {
            ci.cancel();
        }
    }
}