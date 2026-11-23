package org.btwr.nomads_rest.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import org.btwr.nomads_rest.config.NomadsRestConfig;

public class ClientSleepUtils {
    // Draws the sleep overlay in pitch black instead of vanilla's dim one. Returns whether it was drawn.
    public static boolean renderPitchBlackOverlay(GuiGraphics guiGraphics) {
        if (!NomadsRestConfig.pitchBlackSleep) return false;

        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null || player.getSleepTimer() <= 0) {
            return false;
        }

        float timeAsleep = player.getSleepTimer();

        float opacity = Math.min(timeAsleep / 100.0F, 1.0F - (timeAsleep - 100.0F) / 10.0F);

        opacity = Math.max(0, Math.min(opacity, 1.0F));

        int color = (int) (255.0F * opacity) << 24;
        guiGraphics.fill(RenderType.guiOverlay(), 0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight(), color);

        return true;
    }

    // The world can't be seen while the pitch black overlay is fully opaque, so there's no need to render it.
    // Vanilla's overlay is see-through, so the world has to keep rendering without the pitch black one.
    public static boolean shouldSkipWorldRender() {
        if (!NomadsRestConfig.hideWorldWhileAsleep || !NomadsRestConfig.pitchBlackSleep) return false;

        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && player.isSleepingLongEnough();
    }
}