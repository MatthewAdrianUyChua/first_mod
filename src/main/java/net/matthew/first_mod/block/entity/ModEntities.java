package net.matthew.first_mod.block.entity;

import net.matthew.first_mod.block.entity.custom.CatEntity;
import net.matthew.first_mod.first_mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, first_mod.MODID);

    public static final RegistryObject<EntityType<CatEntity>> CAT = ENTITY_TYPES.register("cat",
            () -> EntityType.Builder.of(CatEntity:: new, MobCategory.CREATURE)
                    .sized(0.6f, 0.7f)
                    .build(new ResourceLocation(first_mod.MODID, "cat").toString()));

    public static void register (IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
