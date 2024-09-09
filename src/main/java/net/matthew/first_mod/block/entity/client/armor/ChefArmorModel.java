package net.matthew.first_mod.block.entity.client.armor;

import net.matthew.first_mod.first_mod;
import net.matthew.first_mod.item.custom.ChefArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChefArmorModel extends GeoModel<ChefArmorItem> {
    @Override
    public ResourceLocation getModelResource(ChefArmorItem animatable) {
        return new ResourceLocation(first_mod.MODID, "geo/chef_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChefArmorItem animatable) {
        return new ResourceLocation(first_mod.MODID, "textures/armor/chef_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChefArmorItem animatable) {
        return new ResourceLocation(first_mod.MODID, "animations/chef_armor.animation.json");
    }
}
