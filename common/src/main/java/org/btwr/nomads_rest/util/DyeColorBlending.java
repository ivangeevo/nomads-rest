package org.btwr.nomads_rest.util;

import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * Blends dye colors into the closest available dye color.
 */
public final class DyeColorBlending {

    private DyeColorBlending() {}

    /**
     * @return the closest dye color to the average of the given colors (WHITE if none are given)
     */
    public static DyeColor blend(Collection<DyeColor> colors) {
        if (colors.isEmpty()) {
            return DyeColor.WHITE;
        }

        int r = 0;
        int g = 0;
        int b = 0;

        for (DyeColor color : colors) {
            int rgb = color.getFireworkColor();
            r += (rgb >> 16) & 0xFF;
            g += (rgb >> 8) & 0xFF;
            b += rgb & 0xFF;
        }

        int count = colors.size();
        int blended = ((r / count) << 16) | ((g / count) << 8) | (b / count);

        return getClosestDyeColor(blended);
    }

    public static DyeColor getClosestDyeColor(int rgb) {
        return Arrays.stream(DyeColor.values())
                .min(Comparator.comparingInt(color -> colorDistance(color.getFireworkColor(), rgb)))
                .orElse(DyeColor.WHITE);
    }

    public static int colorDistance(int rgb1, int rgb2) {
        int dr = ((rgb1 >> 16) & 0xFF) - ((rgb2 >> 16) & 0xFF);
        int dg = ((rgb1 >> 8) & 0xFF) - ((rgb2 >> 8) & 0xFF);
        int db = (rgb1 & 0xFF) - (rgb2 & 0xFF);
        return dr * dr + dg * dg + db * db;
    }
}