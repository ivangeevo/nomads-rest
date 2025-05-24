package org.ivangeevo.nomads_rest.mixin;

import net.minecraft.server.ServerTickManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(ServerTickManager.class)
public abstract class ServerTickManagerMixin {

    @Redirect(
        method = "finishSprinting",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/command/ServerCommandSource;sendFeedback(Ljava/util/function/Supplier;Z)V"
        )
    )
    private void redirectSendFeedback(ServerCommandSource instance, Supplier<Text> feedbackSupplier, boolean broadcastToOps) {
        //Do nothing to cancel the feedback
    }

    
}
