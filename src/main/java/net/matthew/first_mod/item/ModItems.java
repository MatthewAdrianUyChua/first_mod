package net.matthew.first_mod.item;

import net.matthew.first_mod.block.ModBlocks;
import net.matthew.first_mod.block.custom.Rotatable_Block_Eatable;
import net.matthew.first_mod.block.entity.ModEntities;
import net.matthew.first_mod.item.custom.ChefArmorItem;
import net.matthew.first_mod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.matthew.first_mod.first_mod;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, first_mod.MODID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> SKEWER_ITEM = ITEMS.register("skewer_item",
            () -> new Rotatable_Block_Eatable(ModBlocks.SKEWER.get(), new Item.Properties().food(ModFoods.SKEWER_ITEM)));

    public static final RegistryObject<Item> EGG_STEAK_SANDWICH = ITEMS.register("egg_steak_sandwich",
            () -> new Rotatable_Block_Eatable(ModBlocks.EGG_STEAK_SANDWICH_BLOCK.get(), new Item.Properties().food(ModFoods.EGG_STEAK_SANDWICH)));

    public static final RegistryObject<Item> EGG_SANDWICH = ITEMS.register("egg_sandwich",
            () -> new Rotatable_Block_Eatable(ModBlocks.EGG_SANDWICH_BLOCK.get(), new Item.Properties().food(ModFoods.EGG_SANDWICH)));

    public static final RegistryObject<Item> CHEF_HAT = ITEMS.register("chef_armor",
            () -> new ChefArmorItem(ModArmorMaterials.CHEF, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> CAT_SPAWN_EGG = ITEMS.register("cat_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.CAT, 0xD57E36, 0x1D0D00,
                    new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
