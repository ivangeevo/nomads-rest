package org.btwr.nomads_rest.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.btwr.nomads_rest.block.entity.BedrollBlockEntity;

public class BedrollBlock extends BedBlock {

    private static final VoxelShape BEDROLL_SHAPE = Block.box(
            0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D
    );

    public BedrollBlock(DyeColor color, Properties properties) {
        super(color, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BEDROLL_SHAPE;
    }

    // override parent to disallow bounce and disable negation of fall damage
    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {}

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BedrollBlockEntity(pos, state, this.getColor());
    }

}