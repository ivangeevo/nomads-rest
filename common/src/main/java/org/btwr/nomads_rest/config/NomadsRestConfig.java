package org.btwr.nomads_rest.config;

import org.btwr.nomads_rest.Constants;
import org.btwr.nomads_rest.platform.Services;

import java.nio.file.Path;

public final class NomadsRestConfig {
    public static final String FILE_NAME = "btwr/" + Constants.MOD_ID + ".toml";
    public static final int MAX_WAKE_TIME = 11999;

    public static boolean timeLapseSleep = true;
    public static int wakeTime = 0;
    public static boolean clearWeatherOnWake = true;

    public static boolean bedrollSetsSpawn = false;
    public static double bedrollFallDamageReduction = 0.0D;
    public static boolean bedrollExplodesOutsideOverworld = true;

    public static boolean pitchBlackSleep = true;
    public static boolean hideWorldWhileAsleep = true;

    private NomadsRestConfig() {}

    public static void load() {
        TomlFile toml = TomlFile.read(file());

        timeLapseSleep = toml.getBoolean("timeLapseSleep", timeLapseSleep);
        wakeTime = toml.getInt("wakeTime", wakeTime, 0, MAX_WAKE_TIME);
        clearWeatherOnWake = toml.getBoolean("clearWeatherOnWake", clearWeatherOnWake);

        bedrollSetsSpawn = toml.getBoolean("bedrollSetsSpawn", bedrollSetsSpawn);
        bedrollFallDamageReduction = toml.getDouble("bedrollFallDamageReduction", bedrollFallDamageReduction, 0.0D, 1.0D);
        bedrollExplodesOutsideOverworld = toml.getBoolean("bedrollExplodesOutsideOverworld", bedrollExplodesOutsideOverworld);

        pitchBlackSleep = toml.getBoolean("pitchBlackSleep", pitchBlackSleep);
        hideWorldWhileAsleep = toml.getBoolean("hideWorldWhileAsleep", hideWorldWhileAsleep);

        save();
    }

    public static void save() {
        new TomlFile.Writer("Nomad's Rest settings. Restart the game or server after editing this file.")
                .add("timeLapseSleep", timeLapseSleep,
                        "Sleeping speeds up time until morning. When false, sleeping skips the night instantly like vanilla.",
                        "Default: true"
                )
                .add("wakeTime", wakeTime,
                        "The time of day players wake up at when time-lapse sleeping, in ticks. 0 is sunrise and 6000 is noon.",
                        "Default: 0 | Min: 0 Max: " + MAX_WAKE_TIME
                )
                .add("clearWeatherOnWake", clearWeatherOnWake,
                        "Whether waking up from time-lapse sleeping ends rain and thunderstorms.",
                        "Default: true"
                )
                .add("bedrollSetsSpawn", bedrollSetsSpawn,
                        "Whether sleeping in a bedroll sets your spawn point.",
                        "Default: false"
                )
                .add("bedrollFallDamageReduction", bedrollFallDamageReduction,
                        "How much fall damage landing on a bedroll prevents. 0.0 is none and 1.0 is all of it.",
                        "Default: 0.0 | Min: 0.0 Max: 1.0"
                )
                .add("bedrollExplodesOutsideOverworld", bedrollExplodesOutsideOverworld,
                        "Whether bedrolls explode when used in dimensions where beds do, like the Nether and the End.",
                        "Default: true"
                )
                .add("pitchBlackSleep", pitchBlackSleep,
                        "Client only. Fades the screen to fully black when sleeping, instead of vanilla's dim overlay.",
                        "Default: true"
                )
                .add("hideWorldWhileAsleep", hideWorldWhileAsleep,
                        "Client only. Stops rendering the world once the screen is fully black. Needs pitchBlackSleep.",
                        "Default: true"
                )
                .save(file());
    }

    private static Path file() {
        return Services.PLATFORM.getConfigDirectory().resolve(FILE_NAME);
    }
}