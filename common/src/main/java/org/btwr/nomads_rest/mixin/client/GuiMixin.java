package org.btwr.nomads_rest.mixin.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.btwr.nomads_rest.client.util.ClientSleepUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Inject(method = "renderSleepOverlay", at = @At("HEAD"), cancellable = true)
    private void pitchBlackSleep(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (ClientSleepUtils.renderPitchBlackOverlay(guiGraphics)) {
            ci.cancel();
        }
    }
}