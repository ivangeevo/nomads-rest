package org.btwr.nomads_rest.platform;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.btwr.nomads_rest.Constants;
import org.btwr.nomads_rest.platform.services.SoundRegistration;

public class FabricSoundRegistration implements SoundRegistration {
    public static final SoundEvent BED_SHATTER = register("bed_shatter");

    private static SoundEvent register(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    @Override
    public SoundEvent bedShatter() {
        return BED_SHATTER;
    }
}