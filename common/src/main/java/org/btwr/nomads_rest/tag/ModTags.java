package org.btwr.nomads_rest.tag;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.btwr.nomads_rest.Constants;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> BEDROLLS = createTag("bedrolls");

        private static TagKey<Block> createTag(String path) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, path));
        }
    }

    public static class Items {

        public static final TagKey<Item> BEDROLLS = createTag("bedrolls");

        private static TagKey<Item> createTag(String path) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, path));
        }
    }

}