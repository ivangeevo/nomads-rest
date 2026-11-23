package org.btwr.nomads_rest.sound;

import net.minecraft.sounds.SoundEvent;
import org.btwr.nomads_rest.platform.Services;

public class ModSoundEvents {
    public static SoundEvent BED_SHATTER() {
        return Services.SOUND_EVENTS.bedShatter();
    }
}