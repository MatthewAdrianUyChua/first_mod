package net.matthew.first_mod.block.entity;

import net.matthew.first_mod.block.ModBlocks;
import net.matthew.first_mod.first_mod;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, first_mod.MODID);

    public static final RegistryObject<BlockEntityType<DisplayItemBlockEntity>> DISPLAY_ITEM_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("display_item_block_entity", () ->
                    BlockEntityType.Builder.of(DisplayItemBlockEntity::new,
                            ModBlocks.CUTTING_BOARD_BLOCK.get(),
                            ModBlocks.SKILLET_BLOCK.get(),
                            ModBlocks.PLATE_BLOCK.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<DisplayItemBlockEntityPlus1>> DISPLAY_ITEM_BLOCK_ENTITY_PLUS1 =
            BLOCK_ENTITIES.register("display_item_block_entity_plus1", () ->
                    BlockEntityType.Builder.of(DisplayItemBlockEntityPlus1::new,
                            ModBlocks.JAPANESE_PLATE_BLOCK.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<DisplayItemBlockEntityPlus4>> DISPLAY_ITEM_BLOCK_ENTITY_PLUS4 =
            BLOCK_ENTITIES.register("display_item_block_entity_plus4", () ->
                    BlockEntityType.Builder.of(DisplayItemBlockEntityPlus4::new,
                            ModBlocks.GRILL_BLOCK.get()
                    ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
