package net.matthew.first_mod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DisplayItemBlockEntityPlus1 extends BlockEntity {
    private ItemStack displayedItem = ItemStack.EMPTY;

    public DisplayItemBlockEntityPlus1(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DISPLAY_ITEM_BLOCK_ENTITY_PLUS1.get(), pos, state);
    }

    public ItemStack getDisplayedItem() {
        return displayedItem;
    }

    public void setDisplayedItem(ItemStack item) {
        this.displayedItem = item;
        setChanged(); // Mark the block entity as changed to update the client
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("DisplayedItem", displayedItem.save(new CompoundTag()));
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.displayedItem = ItemStack.of(compound.getCompound("DisplayedItem"));
        this.setDisplayedItem(ItemStack.of(compound.getCompound("DisplayedItem")));
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }
}


