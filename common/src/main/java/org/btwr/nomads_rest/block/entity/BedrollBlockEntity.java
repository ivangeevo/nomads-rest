package org.btwr.nomads_rest.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.btwr.nomads_rest.block.blocks.BedrollBlock;
import org.jetbrains.annotations.Nullable;

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

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public DyeColor getColor() {
        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}
