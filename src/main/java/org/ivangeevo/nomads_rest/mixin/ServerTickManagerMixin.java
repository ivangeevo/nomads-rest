package org.ivangeevo.nomads_rest.mixin;

import net.minecraft.server.ServerTickManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.ivangeevo.nomads_rest.util.ServerTickManagerSleepFeedback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(ServerTickManager.class)
public abstract class ServerTickManagerMixin implements ServerTickManagerSleepFeedback {

    @Unique
    private boolean noFeedbackForSleep = false;

    public void nomads_rest$setNoFeedbackForSleep(boolean value) {
        this.noFeedbackForSleep = value;
    }

    @Redirect(
        method = "finishSprinting",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/command/ServerCommandSource;sendFeedback(Ljava/util/function/Supplier;Z)V"
        )
    )
    private void redirectSendFeedback(ServerCommandSource instance, Supplier<Text> feedbackSupplier, boolean broadcastToOps) {
        if (!this.noFeedbackForSleep) {
            instance.sendFeedback(feedbackSupplier, broadcastToOps);
        }
        this.noFeedbackForSleep = false; // always reset
    }

    
}
