package org.btwr.nomads_rest.client.block.entity.model;

import com.google.common.collect.Sets;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import org.btwr.nomads_rest.Constants;

import java.util.Set;

public class ModEntityModelLayers {

    private static final String MAIN = "main";
    private static final Set<ModelLayerLocation> LAYERS = Sets.newHashSet();
    public static final ModelLayerLocation BEDROLL_FOOT = registerMain("bedroll_foot");
    public static final ModelLayerLocation BEDROLL_HEAD = registerMain("bedroll_head");


    private static ModelLayerLocation registerMain(String id) {
        return ModEntityModelLayers.register(id, MAIN);
    }

    private static ModelLayerLocation register(String id, String layer) {
        ModelLayerLocation entityModelLayer = create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        }
        return entityModelLayer;
    }

    private static ModelLayerLocation create(String id, String layer) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, id), layer);
    }

}

