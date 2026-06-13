/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package org.btwr.nomads_rest.client.block.entity.model;

import com.google.common.collect.Sets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.btwr.nomads_rest.NomadsRestMod;

import java.util.Set;

@Environment(value=EnvType.CLIENT)
public class ModEntityModelLayers {

    private static final String MAIN = "main";
    private static final Set<EntityModelLayer> LAYERS = Sets.newHashSet();
    public static final EntityModelLayer BEDROLL_FOOT = registerMain("bedroll_foot");
    public static final EntityModelLayer BEDROLL_HEAD = registerMain("bedroll_head");


    private static EntityModelLayer registerMain(String id) {
        return ModEntityModelLayers.register(id, MAIN);
    }

    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        }
        return entityModelLayer;
    }

    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(Identifier.of(NomadsRestMod.MOD_ID, id), layer);
    }

}

