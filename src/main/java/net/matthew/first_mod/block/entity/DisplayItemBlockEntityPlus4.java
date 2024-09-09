package net.matthew.first_mod.block.entity;

import net.matthew.first_mod.util.ModTags;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DisplayItemBlockEntityPlus4 extends BlockEntity{
    private ItemStack displayedItem = ItemStack.EMPTY;
    private int cookTime = 0;
    private static final int MAX_COOK_TIME = 100; // 10 seconds at 20 ticks per second

    public DisplayItemBlockEntityPlus4(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DISPLAY_ITEM_BLOCK_ENTITY_PLUS4.get(), pos, state);
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

    private static final Logger LOGGER = LogManager.getLogger();

    // This method will be called every tick
    public void tick() {

        if(displayedItem.isEmpty() || !displayedItem.is(ModTags.Items.IS_COOKABLE)){
            cookTime = 0;
            return;
        }

        if (!displayedItem.isEmpty() && isValidFood(displayedItem)) {
            cookTime++;
            LOGGER.info("Cooking... Time: " + cookTime);
            if (cookTime >= MAX_COOK_TIME) {
                // Replace item with cooked version
                displayedItem = getCookedItem(displayedItem);
                cookTime = 0; // Reset timer for next cooking
                //LOGGER.info("Item cooked: " + displayedItem);
                setChanged(); // Mark the block entity as changed to update the client
                if (level != null && !level.isClientSide) {
                    level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
                }
            }
        }
    }

    private boolean isValidFood(ItemStack stack) {
        // Check if the item is a raw food item (e.g., raw beef, raw chicken, etc.)
        return stack.is(ModTags.Items.IS_COOKABLE);
    }

    private ItemStack getCookedItem(ItemStack stack) {
        // Return the corresponding cooked item
        if (stack.getItem() == Items.BEEF) {
            return new ItemStack(Items.COOKED_BEEF);
        } else if (stack.getItem() == Items.CHICKEN) {
            return new ItemStack(Items.COOKED_CHICKEN);
        } else if (stack.getItem() == Items.PORKCHOP) {
            return new ItemStack(Items.COOKED_PORKCHOP);
        }else if (stack.getItem() == Items.RABBIT) {
            return new ItemStack(Items.COOKED_RABBIT);
        }else if (stack.getItem() == Items.RABBIT) {
            return new ItemStack(Items.COOKED_RABBIT);
        }else if (stack.getItem() == Items.POTATO) {
            return new ItemStack(Items.BAKED_POTATO);
        }else if (stack.getItem() == Items.COD) {
            return new ItemStack(Items.COOKED_COD);
        }else if (stack.getItem() == Items.MUTTON) {
            return new ItemStack(Items.COOKED_MUTTON);
        }else if (stack.getItem() == Items.SALMON) {
            return new ItemStack(Items.COOKED_SALMON);
        }else if (stack.getItem() == Items.KELP) {
            return new ItemStack(Items.DRIED_KELP);
        }

        return ItemStack.EMPTY; // Default to empty if the item isn't recognized
    }
}


