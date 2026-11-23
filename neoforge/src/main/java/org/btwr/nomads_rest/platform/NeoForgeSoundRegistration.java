package org.btwr.nomads_rest.platform;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.btwr.nomads_rest.Constants;
import org.btwr.nomads_rest.platform.services.SoundRegistration;

public class NeoForgeSoundRegistration implements SoundRegistration {
    // Registries are frozen while mods construct, so unlike on Fabric the sounds have to be registered later through the event bus
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Constants.MOD_ID);

    private static final DeferredHolder<SoundEvent, SoundEvent> BED_SHATTER = SOUND_EVENTS.register("bed_shatter", SoundEvent::createVariableRangeEvent);

    @Override
    public SoundEvent bedShatter() {
        return BED_SHATTER.value();
    }
}