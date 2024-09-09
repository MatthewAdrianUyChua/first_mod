package net.matthew.first_mod.block.custom;

import net.matthew.first_mod.block.entity.DisplayItemBlockEntity;
import net.matthew.first_mod.block.entity.DisplayItemBlockEntityPlus4;
import net.matthew.first_mod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Display_Item_Block_Smoke_Plus4 extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(.0, 0.0, 1.0, 15.0, 7.0, 15.0); // Adjust this for a custom shape
    }

    public Display_Item_Block_Smoke_Plus4(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DisplayItemBlockEntityPlus4(pos, state);
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

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        DisplayItemBlockEntityPlus4 blockEntity = (DisplayItemBlockEntityPlus4) world.getBlockEntity(pos);

        if (blockEntity != null) {
            if (!blockEntity.getDisplayedItem().isEmpty()) {
                // Drop the currently displayed item
                player.drop(blockEntity.getDisplayedItem().split(1), false);
                blockEntity.setDisplayedItem(ItemStack.EMPTY);
            } else if (!heldItem.isEmpty()) {
                // Set the held item as the displayed item
                blockEntity.setDisplayedItem(heldItem.copy());
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return world.isClientSide ? null : (level, pos, blockState, t) -> {
            if (t instanceof DisplayItemBlockEntityPlus4) {
                ((DisplayItemBlockEntityPlus4) t).tick();
            }
        };
    }


    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(state, world, pos, random);

        // Adjust these coordinates for where you want the smoke to appear
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.2; // Emit from above the block
        double z = pos.getZ() + 0.5;

        // Spawn smoke particles
        world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 0.0, 0.1, 0.0);

        if (random.nextInt(10) == 0) {
            world.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof DisplayItemBlockEntityPlus4) {
                DisplayItemBlockEntityPlus4 displayBlockEntity = (DisplayItemBlockEntityPlus4) blockEntity;
                ItemStack itemStack = displayBlockEntity.getDisplayedItem();

                if (!itemStack.isEmpty()) {
                    ItemStack drop = itemStack.copy();
                    drop.setCount(1);
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), drop);
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



