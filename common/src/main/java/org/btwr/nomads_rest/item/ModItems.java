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
        return registerItem(name, new BedItem(bedrollBlock, new Item.Properties()
                .component(DataComponents.DYED_COLOR, new DyedItemColor(color.getFireworkColor(), false))
                .stacksTo(1))
        );
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), item);
    }

    public static void initialize() {
        Constants.LOG.info("Registering Mod Items for " + Constants.MOD_ID);
    }

}