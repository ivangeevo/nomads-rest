package org.btwr.nomads_rest;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class NomadsRestMod {
    public NomadsRestMod(IEventBus eventBus) {
        CommonClass.init();
    }
}