package org.btwr.nomads_rest.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import org.btwr.nomads_rest.block.ModBlocks;
import org.btwr.nomads_rest.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {
    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder tb) {
        this.addBlockTranslations(tb);
        this.addTagName(ModTags.Items.BEDROLLS, "Bedrolls", tb);
        this.addTagName(ModTags.Blocks.BEDROLLS, "Bedrolls", tb);
        tb.add("block.nomads_rest.bedroll.no_sleep_here", "You can't sleep here");
        tb.add("subtitles.nomads_rest.bed_shatter", "Bed Shatters");
        this.addConfigScreenTranslations(tb);
    }

    private void addConfigScreenTranslations(TranslationBuilder tb) {
        tb.add("screen.nomads_rest.title", "Nomad's Rest");
        tb.add("screen.nomads_rest.server_note", "On dedicated servers, edit config/btwr/%s");
        tb.add("screen.nomads_rest.remote_server", "This server's settings can only be changed in its config file");

        this.addSetting("time_lapse_sleep", "Time-Lapse Sleep", "Sleeping speeds up time. Off skips the night instantly", tb);
        this.addSetting("wake_time", "Wake Time", "When a time-lapse ends, in ticks. 0 is sunrise, 6000 is noon", tb);
        this.addSetting("clear_weather_on_wake", "Clear Weather on Wake", "Waking from a time-lapse ends rain and storms", tb);
        this.addSetting("bedroll_sets_spawn", "Bedrolls Set Spawn", "Sleeping in a bedroll sets your spawn point", tb);
        this.addSetting("bedroll_fall_damage_reduction", "Bedroll Fall Cushion", "How much fall damage landing on a bedroll prevents", tb);
        this.addSetting("bedroll_explodes_outside_overworld", "Bedrolls Explode", "Bedrolls explode in the Nether and the End, like beds", tb);
        this.addSetting("pitch_black_sleep", "Pitch Black Sleep", "Fades to fully black instead of vanilla's dim overlay", tb);
        this.addSetting("hide_world_while_asleep", "Hide World While Asleep", "Stops rendering the world once the screen is black. Needs Pitch Black Sleep to be on", tb);
    }

    private void addSetting(String key, String name, String description, TranslationBuilder tb) {
        tb.add("screen.nomads_rest." + key, name);
        tb.add("screen.nomads_rest." + key + ".description", description);
    }

    private void addBlockTranslations(TranslationBuilder tb) {
        tb.add(ModBlocks.WHITE_BEDROLL, "White Bedroll");
        tb.add(ModBlocks.ORANGE_BEDROLL, "Orange Bedroll");
        tb.add(ModBlocks.MAGENTA_BEDROLL, "Magenta Bedroll");
        tb.add(ModBlocks.LIGHT_BLUE_BEDROLL, "Light Blue Bedroll");
        tb.add(ModBlocks.YELLOW_BEDROLL, "Yellow Bedroll");
        tb.add(ModBlocks.LIME_BEDROLL, "Lime Bedroll");
        tb.add(ModBlocks.PINK_BEDROLL, "Pink Bedroll");
        tb.add(ModBlocks.GRAY_BEDROLL, "Gray Bedroll");
        tb.add(ModBlocks.LIGHT_GRAY_BEDROLL, "Light Gray Bedroll");
        tb.add(ModBlocks.CYAN_BEDROLL, "Cyan Bedroll");
        tb.add(ModBlocks.PURPLE_BEDROLL, "Purple Bedroll");
        tb.add(ModBlocks.BLUE_BEDROLL, "Blue Bedroll");
        tb.add(ModBlocks.BROWN_BEDROLL, "Brown Bedroll");
        tb.add(ModBlocks.GREEN_BEDROLL, "Green Bedroll");
        tb.add(ModBlocks.RED_BEDROLL, "Red Bedroll");
        tb.add(ModBlocks.BLACK_BEDROLL, "Black Bedroll");
    }

    protected void addTagName(TagKey<?> tagKey, String value, TranslationBuilder tb) {
        tb.add(tagKey, value);
    }
}