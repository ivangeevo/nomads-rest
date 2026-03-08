package org.ivangeevo.nomads_rest.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    // Changes the dim overlay when sleeping to pitch black
    @Inject(method = "renderSleepOverlay", at = @At("HEAD"), cancellable = true)
    private void pitchBlackSleep(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.player != null;
        if (client.player.getSleepTimer() > 0) {
            float f = client.player.getSleepTimer();
            float g = Math.min(f / 100.0F, 1.0F - (f - 100.0F) / 10.0F);
            g = Math.max(0, Math.min(g, 1.0F)); // clamp 0-1
            int i = (int) (255.0F * g) << 24;
            context.fill(RenderLayer.getGuiOverlay(), 0, 0,
                    context.getScaledWindowWidth(), context.getScaledWindowHeight(), i
            );
            ci.cancel();
        }
    }
}