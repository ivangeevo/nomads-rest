package org.ivangeevo.nomads_rest.mixin;

import net.minecraft.server.ServerTickManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerTickManager.class)
public abstract class ServerTickManagerMixin {

    //@Redirect(
        //method = "finishSprinting",
        //at = @At(
            //value = "INVOKE",
            //target = "Lnet/minecraft/server/command/ServerCommandSource;sendFeedback(Ljava/util/function/Supplier;Z)V"
        //)
    //)
    //private void redirectSendFeedback(ServerCommandSource instance, Supplier<Text> feedbackSupplier, boolean broadcastToOps) {
        // Do nothing to cancel the feedback
    //}

    
}
