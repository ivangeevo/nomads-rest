package org.ivangeevo.nomads_rest.block.blocks;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.ivangeevo.nomads_rest.block.entity.BedrollBlockEntity;

public class BedrollBlock extends BedBlock {

    private static final VoxelShape BEDROLL_SHAPE = Block.createCuboidShape(
            0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D
    );

    public BedrollBlock(DyeColor color, Settings settings) {
        super(color, settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BEDROLL_SHAPE;
    }

    // override parent to disallow bounce and disable negation of fall damage
    @Override
    public void onEntityLand(BlockView world, Entity entity) {}

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BedrollBlockEntity(pos, state, this.getColor());
    }

}