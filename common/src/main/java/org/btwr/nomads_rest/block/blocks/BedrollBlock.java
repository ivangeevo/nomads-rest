package org.btwr.nomads_rest.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.btwr.nomads_rest.block.entity.BedrollBlockEntity;
import org.btwr.nomads_rest.config.NomadsRestConfig;

public class BedrollBlock extends BedBlock {
    private static final VoxelShape BEDROLL_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);

    public BedrollBlock(DyeColor color, Properties properties) {
        super(color, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BEDROLL_SHAPE;
    }

    // override parent to disallow bounce
    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {}

    // override parent to replace the bed's fall damage halving with the configured reduction
    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        float damagedDistance = fallDistance * (1.0F - (float) NomadsRestConfig.bedrollFallDamageReduction);
        entity.causeFallDamage(damagedDistance, 1.0F, entity.damageSources().fall());
    }

    // override parent to optionally refuse to sleep instead of exploding where beds can't be used
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide && !NomadsRestConfig.bedrollExplodesOutsideOverworld && !BedBlock.canSetSpawn(level)) {
            player.displayClientMessage(Component.translatable("block.nomads_rest.bedroll.no_sleep_here"), true);
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BedrollBlockEntity(pos, state, this.getColor());
    }
}