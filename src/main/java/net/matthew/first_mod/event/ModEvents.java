package net.matthew.first_mod.event;

import net.matthew.first_mod.block.entity.ModEntities;
import net.matthew.first_mod.block.entity.custom.CatEntity;
import net.matthew.first_mod.first_mod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = first_mod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(ModEntities.CAT.get(), CatEntity.setAttributes());
    }

}
