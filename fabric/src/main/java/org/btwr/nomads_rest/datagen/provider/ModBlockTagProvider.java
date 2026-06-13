package org.btwr.nomads_rest.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import org.btwr.nomads_rest.block.ModBlocks;
import org.btwr.nomads_rest.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ModBlockTagLogic.apply((tag, blocks) -> {
            for (Object block : blocks) {
                getOrCreateTagBuilder(tag).add((Block) block);
            }
        });

        //getOrCreateTagBuilder(ModTags.Blocks.BEDROLLS).add(ModBlocks.WHITE_BEDROLL);

        /**
         getOrCreateTagBuilder(ModTags.Blocks.BEDROLLS)
         .add(ModBlocks.WHITE_BEDROLL)
         .add(ModBlocks.ORANGE_BEDROLL)
         .add(ModBlocks.MAGENTA_BEDROLL)
         .add(ModBlocks.LIGHT_BLUE_BEDROLL)
         .add(ModBlocks.YELLOW_BEDROLL)
         .add(ModBlocks.LIME_BEDROLL)
         .add(ModBlocks.PINK_BEDROLL)
         .add(ModBlocks.GRAY_BEDROLL)
         .add(ModBlocks.LIGHT_GRAY_BEDROLL)
         .add(ModBlocks.CYAN_BEDROLL)
         .add(ModBlocks.PURPLE_BEDROLL)
         .add(ModBlocks.BLUE_BEDROLL)
         .add(ModBlocks.BROWN_BEDROLL)
         .add(ModBlocks.GREEN_BEDROLL)
         .add(ModBlocks.RED_BEDROLL)
         .add(ModBlocks.BLACK_BEDROLL);
         **/
    }

}