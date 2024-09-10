package net.matthew.first_mod.block.entity.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.matthew.first_mod.block.entity.custom.CatEntity;
import net.matthew.first_mod.first_mod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CatRenderer extends GeoEntityRenderer<CatEntity> {
    public CatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CatModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CatEntity animatable) {
        if(animatable.isSleeping() == true){
            return new ResourceLocation(first_mod.MODID, "textures/entities/cat_eyes_closed.png");
        }else{
            return new ResourceLocation(first_mod.MODID, "textures/entities/cat.png");
        }
    }

    @Override
    public void render(CatEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()){
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
