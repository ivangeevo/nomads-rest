package org.btwr.nomads_rest.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.BedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.btwr.nomads_rest.NomadsRestMod;
import org.btwr.nomads_rest.block.ModBlocks;

public class ModItems {

    public static final Item WHITE_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL, DyeColor.WHITE);
    public static final Item ORANGE_BEDROLL = registerBedrollItem("orange_bedroll", ModBlocks.ORANGE_BEDROLL, DyeColor.ORANGE);
    public static final Item MAGENTA_BEDROLL = registerBedrollItem("magenta_bedroll", ModBlocks.MAGENTA_BEDROLL, DyeColor.MAGENTA);
    public static final Item LIGHT_BLUE_BEDROLL = registerBedrollItem("light_blue_bedroll", ModBlocks.LIGHT_BLUE_BEDROLL, DyeColor.LIGHT_BLUE);
    public static final Item YELLOW_BEDROLL = registerBedrollItem("yellow_bedroll", ModBlocks.YELLOW_BEDROLL, DyeColor.YELLOW);
    public static final Item LIME_BEDROLL = registerBedrollItem("lime_bedroll", ModBlocks.LIME_BEDROLL, DyeColor.LIME);
    public static final Item PINK_BEDROLL = registerBedrollItem("pink_bedroll", ModBlocks.PINK_BEDROLL, DyeColor.PINK);
    public static final Item GRAY_BEDROLL = registerBedrollItem("gray_bedroll", ModBlocks.GRAY_BEDROLL, DyeColor.GRAY);
    public static final Item LIGHT_GRAY_BEDROLL = registerBedrollItem("light_gray_bedroll", ModBlocks.LIGHT_GRAY_BEDROLL, DyeColor.LIGHT_GRAY);
    public static final Item CYAN_BEDROLL = registerBedrollItem("cyan_bedroll", ModBlocks.CYAN_BEDROLL, DyeColor.CYAN);
    public static final Item PURPLE_BEDROLL = registerBedrollItem("purple_bedroll", ModBlocks.PURPLE_BEDROLL, DyeColor.PURPLE);
    public static final Item BLUE_BEDROLL = registerBedrollItem("blue_bedroll", ModBlocks.BLUE_BEDROLL, DyeColor.BLUE);
    public static final Item BROWN_BEDROLL = registerBedrollItem("brown_bedroll", ModBlocks.BROWN_BEDROLL, DyeColor.BROWN);
    public static final Item GREEN_BEDROLL = registerBedrollItem("green_bedroll", ModBlocks.GREEN_BEDROLL, DyeColor.GREEN);
    public static final Item RED_BEDROLL = registerBedrollItem("red_bedroll", ModBlocks.RED_BEDROLL, DyeColor.RED);
    public static final Item BLACK_BEDROLL = registerBedrollItem("black_bedroll", ModBlocks.BLACK_BEDROLL, DyeColor.BLACK);

    private static Item registerBedrollItem(String name, Block bedrollBlock, DyeColor color) {
        return registerItem(name, new BedItem(bedrollBlock, new Item.Settings()
                .component(DataComponentTypes.DYED_COLOR, new DyedColorComponent(color.getFireworkColor(), false))
                .maxCount(1))
        );
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NomadsRestMod.MOD_ID, name), item);
    }

    public static void register() {
        NomadsRestMod.LOGGER.info("Registering Mod Items for " + NomadsRestMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.add(ModItems.WHITE_BEDROLL);
            entries.add(ModItems.ORANGE_BEDROLL);
            entries.add(ModItems.MAGENTA_BEDROLL);
            entries.add(ModItems.LIGHT_BLUE_BEDROLL);
            entries.add(ModItems.YELLOW_BEDROLL);
            entries.add(ModItems.LIME_BEDROLL);
            entries.add(ModItems.PINK_BEDROLL);
            entries.add(ModItems.GRAY_BEDROLL);
            entries.add(ModItems.LIGHT_GRAY_BEDROLL);
            entries.add(ModItems.CYAN_BEDROLL);
            entries.add(ModItems.PURPLE_BEDROLL);
            entries.add(ModItems.BLUE_BEDROLL);
            entries.add(ModItems.BROWN_BEDROLL);
            entries.add(ModItems.GREEN_BEDROLL);
            entries.add(ModItems.RED_BEDROLL);
            entries.add(ModItems.BLACK_BEDROLL);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModItems.WHITE_BEDROLL);
            entries.add(ModItems.ORANGE_BEDROLL);
            entries.add(ModItems.MAGENTA_BEDROLL);
            entries.add(ModItems.LIGHT_BLUE_BEDROLL);
            entries.add(ModItems.YELLOW_BEDROLL);
            entries.add(ModItems.LIME_BEDROLL);
            entries.add(ModItems.PINK_BEDROLL);
            entries.add(ModItems.GRAY_BEDROLL);
            entries.add(ModItems.LIGHT_GRAY_BEDROLL);
            entries.add(ModItems.CYAN_BEDROLL);
            entries.add(ModItems.PURPLE_BEDROLL);
            entries.add(ModItems.BLUE_BEDROLL);
            entries.add(ModItems.BROWN_BEDROLL);
            entries.add(ModItems.GREEN_BEDROLL);
            entries.add(ModItems.RED_BEDROLL);
            entries.add(ModItems.BLACK_BEDROLL);
        });
    }

}