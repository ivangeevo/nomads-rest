package org.ivangeevo.nomads_rest.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.ivangeevo.nomads_rest.NomadsRestMod;
import org.ivangeevo.nomads_rest.block.blocks.BedrollBlock;

public class ModBlocks {

    public static final Block WHITE_BEDROLL = registerWithoutItem("white_bedroll", createBedrollBlock(DyeColor.WHITE));
    //
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
        return new BedrollBlock(color, AbstractBlock.Settings.create().mapColor(state -> state.get(BedrollBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE_GRAY).sounds(BlockSoundGroup.WOOD).strength(0.2f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY));
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(NomadsRestMod.MOD_ID, name), block);
    }

    private static Block registerWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(NomadsRestMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(NomadsRestMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        NomadsRestMod.LOGGER.debug("Registering ModBlocks for " + NomadsRestMod.MOD_ID);
    }
}
