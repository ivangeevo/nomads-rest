package org.btwr.nomads_rest.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.btwr.nomads_rest.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.forVanilla();
        this.forMod();
    }

    private void forVanilla() {
        // override bed recipes
        add(Blocks.WHITE_BED, this.bedDrops(Blocks.WHITE_BED, Items.WHITE_WOOL));
        add(Blocks.ORANGE_BED, this.bedDrops(Blocks.ORANGE_BED, Items.ORANGE_WOOL));
        add(Blocks.MAGENTA_BED, this.bedDrops(Blocks.MAGENTA_BED, Items.MAGENTA_WOOL));
        add(Blocks.LIGHT_BLUE_BED, this.bedDrops(Blocks.LIGHT_BLUE_BED, Items.LIGHT_BLUE_WOOL));
        add(Blocks.YELLOW_BED, this.bedDrops(Blocks.YELLOW_BED, Items.YELLOW_WOOL));
        add(Blocks.LIME_BED, this.bedDrops(Blocks.LIME_BED, Items.LIME_WOOL));
        add(Blocks.PINK_BED, this.bedDrops(Blocks.PINK_BED, Items.PINK_WOOL));
        add(Blocks.GRAY_BED, this.bedDrops(Blocks.GRAY_BED, Items.GRAY_WOOL));
        add(Blocks.LIGHT_GRAY_BED, this.bedDrops(Blocks.LIGHT_GRAY_BED, Items.LIGHT_GRAY_WOOL));
        add(Blocks.CYAN_BED, this.bedDrops(Blocks.CYAN_BED, Items.CYAN_WOOL));
        add(Blocks.PURPLE_BED, this.bedDrops(Blocks.PURPLE_BED, Items.PURPLE_WOOL));
        add(Blocks.BLUE_BED, this.bedDrops(Blocks.BLUE_BED, Items.BLUE_WOOL));
        add(Blocks.BROWN_BED, this.bedDrops(Blocks.BROWN_BED, Items.BROWN_WOOL));
        add(Blocks.GREEN_BED, this.bedDrops(Blocks.GREEN_BED, Items.GREEN_WOOL));
        add(Blocks.RED_BED, this.bedDrops(Blocks.RED_BED, Items.RED_WOOL));
        add(Blocks.BLACK_BED, this.bedDrops(Blocks.BLACK_BED, Items.BLACK_WOOL));
    }

    public LootTable.Builder bedDrops(Block bed, Item woolItem) {
        // Only the head rolls loot, otherwise the other half drops a second set when it pops off (and drops in creative)
        LootItemCondition.Builder isHead = LootItemBlockStatePropertyCondition.hasBlockStateProperties(bed)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BedBlock.PART, BedPart.HEAD));

        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(isHead)
                        .add(LootItem.lootTableItem(woolItem).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(isHead)
                        .add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0f)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(isHead)
                        .add(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5.0f)))));
    }

    private void forMod() {
        this.add(ModBlocks.WHITE_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.ORANGE_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.MAGENTA_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.LIGHT_BLUE_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.YELLOW_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.LIME_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.PINK_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.GRAY_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.LIGHT_GRAY_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.CYAN_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.PURPLE_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.BLUE_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.BROWN_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.GREEN_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.RED_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        this.add(ModBlocks.BLACK_BEDROLL, (block) -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
    }
}