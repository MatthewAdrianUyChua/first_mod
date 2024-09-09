package net.matthew.first_mod.block.custom;

import net.matthew.first_mod.block.entity.DisplayItemBlockEntityPlus1;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Display_Item_Block_Plus1 extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(2.0, 0.0, 2.0, 14.0, 5.0, 14.0); // Adjust this for a custom shape
    }

    public Display_Item_Block_Plus1(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DisplayItemBlockEntityPlus1(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL; // Change this to MODEL if you want the block itself to render
    }

    public BlockState getStateForPlacement(BlockPlaceContext context){
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof DisplayItemBlockEntityPlus1 displayBlockEntity) {
            if (!displayBlockEntity.getDisplayedItem().isEmpty()) {
                // Drop the currently displayed item
                player.drop(displayBlockEntity.getDisplayedItem().split(1), false);
                displayBlockEntity.setDisplayedItem(ItemStack.EMPTY);
            } else if (!heldItem.isEmpty()) {
                // Set the held item as the displayed item
                displayBlockEntity.setDisplayedItem(heldItem.copy());
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof DisplayItemBlockEntityPlus1) {
                DisplayItemBlockEntityPlus1 displayBlockEntity = (DisplayItemBlockEntityPlus1) blockEntity;
                ItemStack itemStack = displayBlockEntity.getDisplayedItem();

                if (!itemStack.isEmpty()) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), itemStack);
                    displayBlockEntity.setDisplayedItem(ItemStack.EMPTY); // Clear the item
                }
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return true; // Allows skylight to pass through
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0; // Prevents the block from blocking any light
    }

}



