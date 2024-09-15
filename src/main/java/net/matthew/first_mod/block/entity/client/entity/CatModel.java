package net.matthew.first_mod.block.entity.client.entity;

import net.matthew.first_mod.block.entity.custom.CatEntity;
import net.matthew.first_mod.first_mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Sheep;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.Objects;

public class CatModel extends GeoModel<CatEntity> {
    @Override
    public ResourceLocation getModelResource(CatEntity animatable) {
        return new ResourceLocation(first_mod.MODID, "geo/cat.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CatEntity animatable) {
        return new ResourceLocation(first_mod.MODID, "textures/entities/cat.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CatEntity animatable) {
        return new ResourceLocation(first_mod.MODID, "animations/cat.animation.json");
    }

    @Override
    public void setCustomAnimations(CatEntity animatable, long instanceId, AnimationState<CatEntity> animationState) {



        CoreGeoBone head = getAnimationProcessor().getBone("head");

        //System.out.println(animationState.getController().getCurrentAnimation().animation().name());
        //System.out.println((Objects.equals(animationState.getController().getCurrentAnimation().animation().name(), "animation.cat.layingDown")));

        if(head != null && (!(Objects.equals(animationState.getController().getCurrentAnimation().animation().name(), "animation.cat.layingDown")) && !(Objects.equals(animationState.getController().getCurrentAnimation().animation().name(), "animation.cat.eat"))
        && !(Objects.equals(animationState.getController().getCurrentAnimation().animation().name(), "animation.cat.sharpen")) && (animatable.isSleeping() == false)
                && !(Objects.equals(animationState.getController().getCurrentAnimation().animation().name(), "animation.cat.knead")) && (animatable.isKneading() == false))){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
