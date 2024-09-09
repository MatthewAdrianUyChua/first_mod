package net.matthew.first_mod.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.matthew.first_mod.block.custom.Display_Item_Block_Smoke_Plus4;
import net.matthew.first_mod.block.entity.DisplayItemBlockEntityPlus4;
import net.matthew.first_mod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class Display_Item_Block_Renderer_Plus4 implements BlockEntityRenderer<DisplayItemBlockEntityPlus4> {

    public Display_Item_Block_Renderer_Plus4(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(DisplayItemBlockEntityPlus4 blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        ItemStack itemStack = blockEntity.getDisplayedItem();
        if (!itemStack.isEmpty()) {

            if (!(itemStack.getItem() instanceof BlockItem)) {
                poseStack.pushPose();

                // Adjust the translation to move the item into the correct position
                poseStack.translate(0.5D, 0.4D, 0.5D); // Centered horizontally, lowered vertically

                // Get the block’s facing direction
                Direction facingDirection = blockEntity.getBlockState().getValue(Display_Item_Block_Smoke_Plus4.FACING);

                // Rotate item to face north relative to the block's orientation
                switch (facingDirection) {
                    case NORTH:
                        poseStack.mulPose(Axis.YP.rotationDegrees(0.0F)); // No rotation needed
                        break;
                    case EAST:
                        poseStack.mulPose(Axis.YP.rotationDegrees(270.0F)); // Rotate 90 degrees to face north
                        break;
                    case SOUTH:
                        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F)); // Rotate 180 degrees to face north
                        break;
                    case WEST:
                        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F)); // Rotate 270 degrees to face north
                        break;
                    default:
                        break;
                }

                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F)); // Rotates item to lay flat

                // Adjust the scale if needed
                poseStack.scale(0.5F, 0.5F, 0.5F); // Scales the item down by 50%

                // Render the item with the applied transformations
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

                poseStack.popPose();
            }else{
                poseStack.pushPose();

                // Adjust the translation to move the item into the correct position
                poseStack.translate(0.5D, 0.85D, 0.5D); // Centered horizontally, lowered vertically

                // Get the block’s facing direction
                Direction facingDirection = blockEntity.getBlockState().getValue(Display_Item_Block_Smoke_Plus4.FACING);

                // Rotate item to face north relative to the block's orientation
                switch (facingDirection) {
                    case NORTH:
                        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F)); // No rotation needed
                        break;
                    case EAST:
                        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F)); // Rotate 90 degrees to face north
                        break;
                    case SOUTH:
                        poseStack.mulPose(Axis.YP.rotationDegrees(0.0F)); // Rotate 180 degrees to face north
                        break;
                    case WEST:
                        poseStack.mulPose(Axis.YP.rotationDegrees(270.0F)); // Rotate 270 degrees to face north
                        break;
                    default:
                        break;
                }

                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F)); // Rotates item to lay flat

                // Adjust the scale if needed
                poseStack.scale(2.0F, 2.0F, 2.0F); // Scales the item down by 50%

                // Render the item with the applied transformations
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

                poseStack.popPose();
            }
        }
    }

}

