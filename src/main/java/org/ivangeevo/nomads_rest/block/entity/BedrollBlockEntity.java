package org.ivangeevo.nomads_rest.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import org.ivangeevo.nomads_rest.block.blocks.BedrollBlock;

public class BedrollBlockEntity extends BlockEntity {
    private DyeColor color;

    public BedrollBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BEDROLL, pos, state);
        this.color = ((BedrollBlock)state.getBlock()).getColor();
    }

    public BedrollBlockEntity(BlockPos pos, BlockState state, DyeColor color) {
        super(ModBlockEntities.BEDROLL, pos, state);
        this.color = color;
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public DyeColor getColor() {
        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}
