package org.btwr.nomads_rest.mixin.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    // Changes the dim overlay when sleeping to pitch black
    @Inject(method = "renderSleepOverlay", at = @At("HEAD"), cancellable = true)
    private void pitchBlackSleep(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        if (mc.player.getSleepTimer() > 0) {
            float f = mc.player.getSleepTimer();
            float g = Math.min(f / 100.0F, 1.0F - (f - 100.0F) / 10.0F);
            g = Math.max(0, Math.min(g, 1.0F)); // clamp 0-1
            int i = (int) (255.0F * g) << 24;
            guiGraphics.fill(
                    RenderType.guiOverlay(), 0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight(), i
            );
            ci.cancel();
        }
    }

}