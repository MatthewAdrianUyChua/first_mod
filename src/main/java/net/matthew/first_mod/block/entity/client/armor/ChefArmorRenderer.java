package net.matthew.first_mod.block.entity.client.armor;

import net.matthew.first_mod.item.custom.ChefArmorItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ChefArmorRenderer extends GeoArmorRenderer<ChefArmorItem> {
    public ChefArmorRenderer() {
        super(new ChefArmorModel());
    }
}
