package org.btwr.nomads_rest.mixin.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import org.btwr.nomads_rest.client.util.ClientSleepUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(method = "renderLevel", at = @At("HEAD"), cancellable = true)
    private void skipWorldRenderWhileAsleep(DeltaTracker deltaTracker, CallbackInfo ci) {
        if (ClientSleepUtils.shouldSkipWorldRender()) {
            ci.cancel();
        }
    }
}