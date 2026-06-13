package org.btwr.nomads_rest.datagen.provider;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.btwr.nomads_rest.block.ModBlocks;
import org.btwr.nomads_rest.tag.ModTags;

public class ModBlockTagLogic {

    public static void apply(BlockTagConsumer consumer) {
        consumer.add(ModTags.Blocks.BEDROLLS,
                ModBlocks.WHITE_BEDROLL,
                ModBlocks.ORANGE_BEDROLL,
                ModBlocks.MAGENTA_BEDROLL,
                ModBlocks.LIGHT_BLUE_BEDROLL,
                ModBlocks.YELLOW_BEDROLL,
                ModBlocks.LIME_BEDROLL,
                ModBlocks.PINK_BEDROLL,
                ModBlocks.GRAY_BEDROLL,
                ModBlocks.LIGHT_GRAY_BEDROLL,
                ModBlocks.CYAN_BEDROLL,
                ModBlocks.PURPLE_BEDROLL,
                ModBlocks.BLUE_BEDROLL,
                ModBlocks.BROWN_BEDROLL,
                ModBlocks.GREEN_BEDROLL,
                ModBlocks.RED_BEDROLL,
                ModBlocks.BLACK_BEDROLL
        );
    }

    @FunctionalInterface
    public interface BlockTagConsumer {
        void add(TagKey<Block> tag, Object... blocks);
    }
}