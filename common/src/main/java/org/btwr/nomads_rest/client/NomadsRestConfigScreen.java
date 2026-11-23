package org.btwr.nomads_rest.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.btwr.nomads_rest.config.NomadsRestConfig;

import java.util.function.Consumer;

/**
 * A plain vanilla options list. Mod Menu has no settings list of its own to reuse, and it doesn't exist on NeoForge.
 */
public class NomadsRestConfigScreen extends OptionsSubScreen {
    private static final String KEY = "screen.nomads_rest.";
    private static final int WAKE_TIME_STEP = 100;

    public NomadsRestConfigScreen(Screen parent) {
        super(parent, Minecraft.getInstance().options, Component.translatable(KEY + "title"));
    }

    @Override
    protected void addOptions() {
        OptionInstance<?>[] serverOptions = {
                serverToggle("time_lapse_sleep", NomadsRestConfig.timeLapseSleep, value -> NomadsRestConfig.timeLapseSleep = value),
                wakeTime(),
                serverToggle("clear_weather_on_wake", NomadsRestConfig.clearWeatherOnWake, value -> NomadsRestConfig.clearWeatherOnWake = value),
                serverToggle("bedroll_sets_spawn", NomadsRestConfig.bedrollSetsSpawn, value -> NomadsRestConfig.bedrollSetsSpawn = value),
                bedrollFallDamageReduction(),
                serverToggle("bedroll_explodes_outside_overworld", NomadsRestConfig.bedrollExplodesOutsideOverworld, value -> NomadsRestConfig.bedrollExplodesOutsideOverworld = value)
        };
        this.list.addSmall(serverOptions);

        OptionInstance<Boolean> hideWorld = toggle("hide_world_while_asleep", description("hide_world_while_asleep"), NomadsRestConfig.hideWorldWhileAsleep, value -> NomadsRestConfig.hideWorldWhileAsleep = value);
        OptionInstance<Boolean> pitchBlack = toggle("pitch_black_sleep", description("pitch_black_sleep"), NomadsRestConfig.pitchBlackSleep, value -> {
            NomadsRestConfig.pitchBlackSleep = value;
            this.list.findOption(hideWorld).active = value;
        });
        this.list.addSmall(pitchBlack, hideWorld);
        this.list.findOption(hideWorld).active = NomadsRestConfig.pitchBlackSleep;

        if (this.onRemoteServer()) {
            this.lock(serverOptions);
        }
    }

    // Server settings only do anything on the server running the world, which is out of reach when connected to another one
    private boolean onRemoteServer() {
        return this.minecraft.level != null && !this.minecraft.hasSingleplayerServer();
    }

    private void lock(OptionInstance<?>[] options) {
        Tooltip tooltip = Tooltip.create(Component.translatable(KEY + "remote_server"));
        for (OptionInstance<?> option : options) {
            AbstractWidget widget = this.list.findOption(option);
            widget.active = false;
            widget.setTooltip(tooltip);
        }
    }

    private static OptionInstance<Integer> wakeTime() {
        return new OptionInstance<>(
                KEY + "wake_time",
                OptionInstance.cachedConstantTooltip(serverDescription("wake_time")),
                (caption, value) -> Options.genericValueLabel(caption, value * WAKE_TIME_STEP),
                new OptionInstance.IntRange(0, NomadsRestConfig.MAX_WAKE_TIME / WAKE_TIME_STEP),
                NomadsRestConfig.wakeTime / WAKE_TIME_STEP,
                value -> NomadsRestConfig.wakeTime = value * WAKE_TIME_STEP
        );
    }

    private static OptionInstance<Double> bedrollFallDamageReduction() {
        return new OptionInstance<>(
                KEY + "bedroll_fall_damage_reduction",
                OptionInstance.cachedConstantTooltip(serverDescription("bedroll_fall_damage_reduction")),
                (caption, value) -> Options.genericValueLabel(caption, Component.literal(Math.round(value * 100.0D) + "%")),
                OptionInstance.UnitDouble.INSTANCE,
                NomadsRestConfig.bedrollFallDamageReduction,
                value -> NomadsRestConfig.bedrollFallDamageReduction = Math.round(value * 100.0D) / 100.0D
        );
    }

    private static OptionInstance<Boolean> serverToggle(String key, boolean value, Consumer<Boolean> setter) {
        return toggle(key, serverDescription(key), value, setter);
    }

    private static OptionInstance<Boolean> toggle(String key, Component tooltip, boolean value, Consumer<Boolean> setter) {
        return OptionInstance.createBoolean(KEY + key, OptionInstance.cachedConstantTooltip(tooltip), value, setter);
    }

    private static Component description(String key) {
        return Component.translatable(KEY + key + ".description");
    }

    private static Component serverDescription(String key) {
        return description(key).copy()
                .append(CommonComponents.NEW_LINE)
                .append(Component.translatable(KEY + "server_note", NomadsRestConfig.FILE_NAME).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void removed() {
        super.removed();
        NomadsRestConfig.save();
    }
}