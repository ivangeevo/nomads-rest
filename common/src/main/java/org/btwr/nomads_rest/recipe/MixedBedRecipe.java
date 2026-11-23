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
import org.btwr.nomads_rest.util.DyeColorBlending;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Crafts a vanilla bed from a bedroll, three colored materials that aren't all the same color, and three base blocks:
 * <pre>
 *  " B "
 *  "###"
 *  "XXX"
 * </pre>
 * The bed's color is the blend of the three materials' colors (the bedroll's color doesn't matter). Same-color beds are
 * regular shaped recipes, so this only matches mixed colors.
 */
public class MixedBedRecipe extends CustomRecipe {
    private static final int SIZE = 3;

    private final Ingredient bedroll;
    private final Map<DyeColor, Ingredient> materials;
    private final Ingredient base;

    public MixedBedRecipe(CraftingBookCategory category, Ingredient bedroll, Map<DyeColor, Ingredient> materials, Ingredient base) {
        super(category);
        this.bedroll = bedroll;
        this.materials = materials;
        this.base = base;
    }

    public Ingredient getBedroll() {
        return this.bedroll;
    }

    public Map<DyeColor, Ingredient> getMaterials() {
        return this.materials;
    }

    public Ingredient getBase() {
        return this.base;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        List<DyeColor> colors = this.getMaterialColors(input);
        return colors != null && !(colors.get(0) == colors.get(1) && colors.get(1) == colors.get(2));
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        List<DyeColor> colors = this.getMaterialColors(input);

        if (colors == null) {
            return ItemStack.EMPTY;
        }

        DyeColor color = DyeColorBlending.blend(colors);
        return new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(color.getName() + "_bed")));
    }

    /**
     * @return the colors of the three materials in the middle row, or null if the grid doesn't fit the pattern
     */
    private List<DyeColor> getMaterialColors(CraftingInput input) {
        // the input is trimmed to the filled area, so the pattern has to fill exactly 3x3
        if (input.width() != SIZE || input.height() != SIZE) {
            return null;
        }

        // top row: only the bedroll, in the middle
        if (!input.getItem(0).isEmpty() || !this.bedroll.test(input.getItem(1)) || !input.getItem(2).isEmpty()) {
            return null;
        }

        List<DyeColor> colors = new ArrayList<>(SIZE);

        for (int column = 0; column < SIZE; column++) {
            // middle row: materials
            DyeColor color = DyeMaterials.colorOf(this.materials, input.getItem(SIZE + column));

            // bottom row: base blocks
            if (color == null || !this.base.test(input.getItem(2 * SIZE + column))) {
                return null;
            }

            colors.add(color);
        }

        return colors;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= SIZE && height >= SIZE;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MIXED_BED;
    }

    public static class Serializer implements RecipeSerializer<MixedBedRecipe> {
        private static final MapCodec<MixedBedRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(MixedBedRecipe::category),
                Ingredient.CODEC_NONEMPTY.fieldOf("bedroll").forGetter(MixedBedRecipe::getBedroll),
                DyeMaterials.CODEC.fieldOf("materials").forGetter(MixedBedRecipe::getMaterials),
                Ingredient.CODEC_NONEMPTY.fieldOf("base").forGetter(MixedBedRecipe::getBase)
        ).apply(instance, MixedBedRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, MixedBedRecipe> STREAM_CODEC = StreamCodec.composite(
                CraftingBookCategory.STREAM_CODEC, MixedBedRecipe::category,
                Ingredient.CONTENTS_STREAM_CODEC, MixedBedRecipe::getBedroll,
                DyeMaterials.STREAM_CODEC, MixedBedRecipe::getMaterials,
                Ingredient.CONTENTS_STREAM_CODEC, MixedBedRecipe::getBase,
                MixedBedRecipe::new
        );

        @Override
        public MapCodec<MixedBedRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MixedBedRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}