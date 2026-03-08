package org.ivangeevo.nomads_rest.tag;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.ivangeevo.nomads_rest.NomadsRestMod;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> BEDROLLS = createTag("bedrolls");

        private static TagKey<Block> createTag(String path) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(NomadsRestMod.MOD_ID, path));
        }
    }

    public static class Items {

        public static final TagKey<Item> BEDROLLS = createTag("bedrolls");

        private static TagKey<Item> createTag(String path) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(NomadsRestMod.MOD_ID, path));
        }
    }

}