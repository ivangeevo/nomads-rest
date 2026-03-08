package org.ivangeevo.nomads_rest.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import org.ivangeevo.nomads_rest.NomadsRestMod;
import org.ivangeevo.nomads_rest.block.ModBlocks;
import org.ivangeevo.nomads_rest.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {

    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder tb) {
        this.addBlockTranslations(tb);
        this.addTagName(ModTags.Items.BEDROLLS, "Bedrolls", tb);
        this.addTagName(ModTags.Blocks.BEDROLLS, "Bedrolls", tb);
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


    private void addItemTranslations(TranslationBuilder tb) {
    }

    protected void addEmiCategory(String key, String name, TranslationBuilder tb) {
        tb.add("emi.category.self_sustainable." + key, name);
    }

    protected void addEmiTooltip(String key, String name, TranslationBuilder tb) {
        tb.add("emi." + key + ".tooltip", name);
    }

    private void addItemGroup(String entryPath, String translation, TranslationBuilder tb) {
        tb.add("itemgroup." + entryPath, translation);
    }

    private void addConfigMenuTitle(String translation, TranslationBuilder tb) {
        tb.add("title." + NomadsRestMod.MOD_ID + ".config", translation);
    }

    private void addConfigCategory(String categoryPath, String translation, TranslationBuilder tb) {
        tb.add("config." + NomadsRestMod.MOD_ID + ".category." + categoryPath, translation);
    }

    private void addConfig(String configPath, String translation, TranslationBuilder tb) {
        tb.add("config." + NomadsRestMod.MOD_ID + "." + configPath, translation);
    }

    private void addConfigTooltip(String configPath, String translation, TranslationBuilder tb) {
        tb.add("config." + NomadsRestMod.MOD_ID + ".tooltip." + configPath, translation);
    }

    protected void addTagName(TagKey<?> tagKey, String value, TranslationBuilder tb) {
        tb.add(tagKey, value);
    }

}