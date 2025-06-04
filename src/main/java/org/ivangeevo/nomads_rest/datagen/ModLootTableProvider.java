package org.ivangeevo.nomads_rest.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BedPart;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;
import org.ivangeevo.nomads_rest.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.forVanilla();
        this.forMod();
    }

    private void forVanilla() {
        // override bed recipes
        addDrop(Blocks.WHITE_BED, this.bedDrops(Items.WHITE_WOOL));
        addDrop(Blocks.ORANGE_BED, this.bedDrops(Items.ORANGE_WOOL));
        addDrop(Blocks.MAGENTA_BED, this.bedDrops(Items.MAGENTA_WOOL));
        addDrop(Blocks.LIGHT_BLUE_BED, this.bedDrops(Items.LIGHT_BLUE_WOOL));
        addDrop(Blocks.YELLOW_BED, this.bedDrops(Items.YELLOW_WOOL));
        addDrop(Blocks.LIME_BED, this.bedDrops(Items.LIME_WOOL));
        addDrop(Blocks.PINK_BED, this.bedDrops(Items.PINK_WOOL));
        addDrop(Blocks.GRAY_BED, this.bedDrops(Items.GRAY_WOOL));
        addDrop(Blocks.LIGHT_GRAY_BED, this.bedDrops(Items.LIGHT_GRAY_WOOL));
        addDrop(Blocks.CYAN_BED, this.bedDrops(Items.CYAN_WOOL));
        addDrop(Blocks.PURPLE_BED, this.bedDrops(Items.PURPLE_WOOL));
        addDrop(Blocks.BLUE_BED, this.bedDrops(Items.BLUE_WOOL));
        addDrop(Blocks.BROWN_BED, this.bedDrops(Items.BROWN_WOOL));
        addDrop(Blocks.GREEN_BED, this.bedDrops(Items.GREEN_WOOL));
        addDrop(Blocks.RED_BED, this.bedDrops(Items.RED_WOOL));
        addDrop(Blocks.BLACK_BED, this.bedDrops(Items.BLACK_WOOL));

    }

    public LootTable.Builder bedDrops(Item woolItem) {
        return LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(woolItem).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f)))))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.STRING).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f)))))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(5.0f))))
                );

    }

    private void forMod() {
        this.addDrop(ModBlocks.WHITE_BEDROLL, (block) -> this.dropsWithProperty(block, BedBlock.PART, BedPart.HEAD));
    }

}
