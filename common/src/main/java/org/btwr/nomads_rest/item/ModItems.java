package org.btwr.nomads_rest.item;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.block.Block;
import org.btwr.nomads_rest.Constants;
import org.btwr.nomads_rest.block.ModBlocks;

public class ModItems {

    public static Item WHITE_BEDROLL;
    public static Item ORANGE_BEDROLL;
    public static Item MAGENTA_BEDROLL;
    public static Item LIGHT_BLUE_BEDROLL;
    public static Item YELLOW_BEDROLL;
    public static Item LIME_BEDROLL;
    public static Item PINK_BEDROLL;
    public static Item GRAY_BEDROLL;
    public static Item LIGHT_GRAY_BEDROLL;
    public static Item CYAN_BEDROLL;
    public static Item PURPLE_BEDROLL;
    public static Item BLUE_BEDROLL;
    public static Item BROWN_BEDROLL;
    public static Item GREEN_BEDROLL;
    public static Item RED_BEDROLL;
    public static Item BLACK_BEDROLL;

    public static void initialize() {
        Constants.LOG.info("Registering Mod Items for " + Constants.MOD_ID);

        WHITE_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL, DyeColor.WHITE);
        ORANGE_BEDROLL = registerBedrollItem("orange_bedroll", ModBlocks.ORANGE_BEDROLL, DyeColor.ORANGE);
        MAGENTA_BEDROLL = registerBedrollItem("magenta_bedroll", ModBlocks.MAGENTA_BEDROLL, DyeColor.MAGENTA);
        LIGHT_BLUE_BEDROLL = registerBedrollItem("light_blue_bedroll", ModBlocks.LIGHT_BLUE_BEDROLL, DyeColor.LIGHT_BLUE);
        YELLOW_BEDROLL = registerBedrollItem("yellow_bedroll", ModBlocks.YELLOW_BEDROLL, DyeColor.YELLOW);
        LIME_BEDROLL = registerBedrollItem("lime_bedroll", ModBlocks.LIME_BEDROLL, DyeColor.LIME);
        PINK_BEDROLL = registerBedrollItem("pink_bedroll", ModBlocks.PINK_BEDROLL, DyeColor.PINK);
        GRAY_BEDROLL = registerBedrollItem("gray_bedroll", ModBlocks.GRAY_BEDROLL, DyeColor.GRAY);
        LIGHT_GRAY_BEDROLL = registerBedrollItem("light_gray_bedroll", ModBlocks.LIGHT_GRAY_BEDROLL, DyeColor.LIGHT_GRAY);
        CYAN_BEDROLL = registerBedrollItem("cyan_bedroll", ModBlocks.CYAN_BEDROLL, DyeColor.CYAN);
        PURPLE_BEDROLL = registerBedrollItem("purple_bedroll", ModBlocks.PURPLE_BEDROLL, DyeColor.PURPLE);
        BLUE_BEDROLL = registerBedrollItem("blue_bedroll", ModBlocks.BLUE_BEDROLL, DyeColor.BLUE);
        BROWN_BEDROLL = registerBedrollItem("brown_bedroll", ModBlocks.BROWN_BEDROLL, DyeColor.BROWN);
        GREEN_BEDROLL = registerBedrollItem("green_bedroll", ModBlocks.GREEN_BEDROLL, DyeColor.GREEN);
        RED_BEDROLL = registerBedrollItem("red_bedroll", ModBlocks.RED_BEDROLL, DyeColor.RED);
        BLACK_BEDROLL = registerBedrollItem("black_bedroll", ModBlocks.BLACK_BEDROLL, DyeColor.BLACK);
    }

    private static Item registerBedrollItem(String name, Block bedrollBlock, DyeColor color) {
        return registerItem(name, new BedItem(bedrollBlock, new Item.Properties()
                .component(DataComponents.DYED_COLOR, new DyedItemColor(color.getFireworkColor(), false))
                .stacksTo(1))
        );
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), item);
    }

}