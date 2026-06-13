package org.btwr.nomads_rest.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.btwr.nomads_rest.Constants;
import org.btwr.nomads_rest.block.blocks.BedrollBlock;

public class ModBlocks {

    public static final Block WHITE_BEDROLL = registerWithoutItem("white_bedroll", createBedrollBlock(DyeColor.WHITE));
    public static final Block ORANGE_BEDROLL = registerWithoutItem("orange_bedroll", createBedrollBlock(DyeColor.ORANGE));
    public static final Block MAGENTA_BEDROLL = registerWithoutItem("magenta_bedroll", createBedrollBlock(DyeColor.MAGENTA));
    public static final Block LIGHT_BLUE_BEDROLL = registerWithoutItem("light_blue_bedroll", createBedrollBlock(DyeColor.LIGHT_BLUE));
    public static final Block YELLOW_BEDROLL = registerWithoutItem("yellow_bedroll", createBedrollBlock(DyeColor.YELLOW));
    public static final Block LIME_BEDROLL = registerWithoutItem("lime_bedroll", createBedrollBlock(DyeColor.LIME));
    public static final Block PINK_BEDROLL = registerWithoutItem("pink_bedroll", createBedrollBlock(DyeColor.PINK));
    public static final Block GRAY_BEDROLL = registerWithoutItem("gray_bedroll", createBedrollBlock(DyeColor.GRAY));
    public static final Block LIGHT_GRAY_BEDROLL = registerWithoutItem("light_gray_bedroll", createBedrollBlock(DyeColor.LIGHT_GRAY));
    public static final Block CYAN_BEDROLL = registerWithoutItem("cyan_bedroll", createBedrollBlock(DyeColor.CYAN));
    public static final Block PURPLE_BEDROLL = registerWithoutItem("purple_bedroll", createBedrollBlock(DyeColor.PURPLE));
    public static final Block BLUE_BEDROLL = registerWithoutItem("blue_bedroll", createBedrollBlock(DyeColor.BLUE));
    public static final Block BROWN_BEDROLL = registerWithoutItem("brown_bedroll", createBedrollBlock(DyeColor.BROWN));
    public static final Block GREEN_BEDROLL = registerWithoutItem("green_bedroll", createBedrollBlock(DyeColor.GREEN));
    public static final Block RED_BEDROLL = registerWithoutItem("red_bedroll", createBedrollBlock(DyeColor.RED));
    public static final Block BLACK_BEDROLL = registerWithoutItem("black_bedroll", createBedrollBlock(DyeColor.BLACK));

    private static Block createBedrollBlock(DyeColor color) {
        return new BedrollBlock(color, BlockBehaviour.Properties.of()
                .mapColor(state -> state.getValue(BedrollBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.SNOW)
                .sound(SoundType.WOOD)
                .strength(0.2f)
                .noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
        );
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block);
    }

    private static Block registerWithoutItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name),
                new BlockItem(block, new Item.Properties())
        );
    }

    public static void initialize() {
        Constants.LOG.debug("Registering ModBlocks for " + Constants.MOD_ID);
    }

}