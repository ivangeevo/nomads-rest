package org.btwr.nomads_rest.recipe;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

/**
 * The colored materials of a mixed-color recipe: which ingredient counts as which dye color.
 * Data-driven so other mods and datapacks can swap the material.
 */
public final class DyeMaterials {
    public static final Codec<Map<DyeColor, Ingredient>> CODEC =
            Codec.unboundedMap(DyeColor.CODEC, Ingredient.CODEC_NONEMPTY).xmap(DyeMaterials::copyOf, m -> m);

    public static final StreamCodec<RegistryFriendlyByteBuf, Map<DyeColor, Ingredient>> STREAM_CODEC =
            ByteBufCodecs.map(
                    size -> new EnumMap<>(DyeColor.class), DyeColor.STREAM_CODEC, Ingredient.CONTENTS_STREAM_CODEC
            );

    private DyeMaterials() {}

    /**
     * @return the dye color the stack counts as, or null if it isn't one of the materials
     */
    @Nullable
    public static DyeColor colorOf(Map<DyeColor, Ingredient> materials, ItemStack stack) {
        if (stack.isEmpty()) {
            return null;
        }

        for (Map.Entry<DyeColor, Ingredient> entry : materials.entrySet()) {
            if (entry.getValue().test(stack)) {
                return entry.getKey();
            }
        }

        return null;
    }

    /**
     * @return the 16 vanilla wool blocks, one per dye color
     */
    public static Map<DyeColor, Ingredient> vanillaWool() {
        Map<DyeColor, Ingredient> materials = new EnumMap<>(DyeColor.class);

        for (DyeColor color : DyeColor.values()) {
            ResourceLocation id = ResourceLocation.withDefaultNamespace(color.getName() + "_wool");
            materials.put(color, Ingredient.of(BuiltInRegistries.ITEM.get(id)));
        }

        return materials;
    }

    private static Map<DyeColor, Ingredient> copyOf(Map<DyeColor, Ingredient> map) {
        Map<DyeColor, Ingredient> copy = new EnumMap<>(DyeColor.class);
        copy.putAll(map);
        return copy;
    }
}