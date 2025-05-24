package org.ivangeevo.nomads_rest.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.ivangeevo.nomads_rest.NomadsRestMod;
import org.ivangeevo.nomads_rest.block.ModBlocks;

public class ModItems {

    // Nomad's Rest group
    public static final Item NR_GROUP = registerItem( "nr_group", new Item(new Item.Settings()));

    public static final Item WHITE_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    //
    public static final Item ORANGE_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item MAGENTA_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item LIGHT_BLUE_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item YELLOW_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item LIME_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item PINK_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item GRAY_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item LIGHT_GRAY_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item CYAN_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item PURPLE_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item BLUE_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item BROWN_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item GREEN_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item RED_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);
    public static final Item BLACK_BEDROLL = registerBedrollItem("white_bedroll", ModBlocks.WHITE_BEDROLL);

    private static Item registerBedrollItem(String name, Block bedrollBlock) {
        return new BedItem(bedrollBlock, new Item.Settings().maxCount(1));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NomadsRestMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NomadsRestMod.LOGGER.info("Registering Mod Items for " + NomadsRestMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
            content.add(ModItems.WHITE_BEDROLL);
        });
    }
}
