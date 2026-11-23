package org.btwr.nomads_rest.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.btwr.nomads_rest.Constants;
import org.btwr.nomads_rest.util.DyeColorBlending;

import java.util.List;
import java.util.Map;

public class MixedBedrollRecipe extends CustomRecipe {
    private final Map<DyeColor, Ingredient> materials;
    private final Ingredient binding;

    public MixedBedrollRecipe(CraftingBookCategory category, Map<DyeColor, Ingredient> materials, Ingredient binding) {
        super(category);
        this.materials = materials;
        this.binding = binding;
    }

    public Map<DyeColor, Ingredient> getMaterials() {
        return this.materials;
    }

    public Ingredient getBinding() {
        return this.binding;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        List<DyeColor> colors = this.getMaterialColors(input);
        return colors != null && colors.get(0) != colors.get(1);
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        List<DyeColor> colors = this.getMaterialColors(input);

        if (colors == null) return ItemStack.EMPTY;

        DyeColor color = DyeColorBlending.blend(colors);
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, color.getName() + "_bedroll");

        return new ItemStack(BuiltInRegistries.ITEM.get(id));
    }

    private List<DyeColor> getMaterialColors(CraftingInput input) {
        DyeColor first = null;
        DyeColor second = null;
        boolean hasBinding = false;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);

            if (stack.isEmpty()) {
                continue;
            }

            DyeColor color = DyeMaterials.colorOf(this.materials, stack);

            if (color != null && second == null) {
                if (first == null) {
                    first = color;
                } else {
                    second = color;
                }
            } else if (color == null && !hasBinding && this.binding.test(stack)) {
                hasBinding = true;
            } else {
                return null;
            }
        }

        return first != null && second != null && hasBinding ? List.of(first, second) : null;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MIXED_BEDROLL;
    }

    public static class Serializer implements RecipeSerializer<MixedBedrollRecipe> {
        private static final MapCodec<MixedBedrollRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(MixedBedrollRecipe::category),
                DyeMaterials.CODEC.fieldOf("materials").forGetter(MixedBedrollRecipe::getMaterials),
                Ingredient.CODEC_NONEMPTY.fieldOf("binding").forGetter(MixedBedrollRecipe::getBinding)
        ).apply(instance, MixedBedrollRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, MixedBedrollRecipe> STREAM_CODEC = StreamCodec.composite(
                CraftingBookCategory.STREAM_CODEC, MixedBedrollRecipe::category,
                DyeMaterials.STREAM_CODEC, MixedBedrollRecipe::getMaterials,
                Ingredient.CONTENTS_STREAM_CODEC, MixedBedrollRecipe::getBinding,
                MixedBedrollRecipe::new
        );

        @Override
        public MapCodec<MixedBedrollRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MixedBedrollRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}