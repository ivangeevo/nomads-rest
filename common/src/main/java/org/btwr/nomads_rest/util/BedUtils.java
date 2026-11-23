package org.btwr.nomads_rest.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import org.btwr.nomads_rest.block.blocks.BedrollBlock;
import org.btwr.nomads_rest.config.NomadsRestConfig;
import org.btwr.nomads_rest.sound.ModSoundEvents;
import org.jetbrains.annotations.Nullable;

// These all apply to beds and bedrolls (by inheritance)
public class BedUtils {
    // Beds need solid ground under both of their halves
    public static boolean isMissingSupport(BlockState state, LevelReader level, BlockPos pos) {
        if (!isBedWithParts(state)) return false;
        return !isOnSolidGround(level, pos) || !isOnSolidGround(level, getOtherPartPos(state, pos));
    }

    // Breaks the whole bed when either of its supporting blocks below isn't present. Only the head drops loot.
    public static void breakIfUnsupported(BlockState state, Level level, BlockPos pos) {
        if (!isBedWithParts(state)) return;

        BlockPos otherPartPos = getOtherPartPos(state, pos);
        BlockState otherPartState = level.getBlockState(otherPartPos);
        if (!(otherPartState.getBlock() instanceof BedBlock) || !otherPartState.hasProperty(BedBlock.PART)) return;

        if (!isOnSolidGround(level, pos) || !isOnSolidGround(level, otherPartPos)) {
            boolean isHead = state.getValue(BedBlock.PART) == BedPart.HEAD;
            level.destroyBlock(pos, isHead);
            level.destroyBlock(otherPartPos, !isHead);
        }
    }

    public static void playShatterSound(Level level, BlockPos pos, BlockState state, Player player) {
        if (!state.is(BlockTags.BEDS) || player.isCreative()) return;
        level.playSound(null, pos, ModSoundEvents.BED_SHATTER(), SoundSource.BLOCKS,
                0.25F, 1.0F + (level.getRandom().nextFloat() * 0.25F)
        );
    }

    public static boolean preventsSettingSpawn(ServerPlayer player, @Nullable BlockPos position) {
        return !NomadsRestConfig.bedrollSetsSpawn
                && position != null
                && player.level().getBlockState(position).getBlock() instanceof BedrollBlock;
    }

    private static boolean isBedWithParts(BlockState state) {
        return state.getBlock() instanceof BedBlock
                && state.hasProperty(BedBlock.PART)
                && state.hasProperty(HorizontalDirectionalBlock.FACING);
    }

    private static BlockPos getOtherPartPos(BlockState state, BlockPos pos) {
        Direction facing = state.getValue(HorizontalDirectionalBlock.FACING);
        return state.getValue(BedBlock.PART) == BedPart.HEAD ? pos.relative(facing.getOpposite()) : pos.relative(facing);
    }

    private static boolean isOnSolidGround(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isRedstoneConductor(level, pos.below());
    }
}