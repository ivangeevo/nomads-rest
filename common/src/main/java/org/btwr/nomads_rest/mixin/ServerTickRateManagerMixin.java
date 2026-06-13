package org.btwr.nomads_rest.mixin;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.ServerTickRateManager;
import org.btwr.nomads_rest.util.SleepFeedback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(ServerTickRateManager.class)
public abstract class ServerTickRateManagerMixin implements SleepFeedback {

    @Unique
    private boolean nomads_rest$noFeedbackForSleep = false;

    public void nomads_rest$setNoFeedbackForSleep(boolean value) {
        this.nomads_rest$noFeedbackForSleep = value;
    }

    @Redirect(
        method = "finishTickSprint",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"
        )
    )
    private void redirectSendSuccess(CommandSourceStack instance, Supplier<Component> messageSupplier, boolean allowLogging) {
        if (!this.nomads_rest$noFeedbackForSleep) {
            instance.sendSuccess(messageSupplier, allowLogging);
        }
        this.nomads_rest$noFeedbackForSleep = false; // always reset
    }
    
}
