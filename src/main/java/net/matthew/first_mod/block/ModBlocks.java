package net.matthew.first_mod.block;

import net.matthew.first_mod.block.custom.*;
import net.matthew.first_mod.first_mod;
import net.matthew.first_mod.item.ModItems;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, first_mod.MODID);

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> BEER_BLOCK = registerBlock("beer_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> MILK_TEA_BLOCK = registerBlock("milk_tea_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> CIGARETTE_BOX_BLOCK = registerBlock("cigarette_box_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> BEER_SET_BLOCK = registerBlock("beer_set_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> RAMEN_BLOCK = registerBlock("ramen_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> BEER_PILE_BLOCK = registerBlock("beer_pile_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> WHISKEY_BLOCK = registerBlock("whiskey_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> SHOT_GLASS_BLOCK = registerBlock("shot_glass_block",
            () -> new Rotatable_Glass_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS).noOcclusion()));

    public static final RegistryObject<Block> GIN_BLOCK = registerBlock("gin_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> TEQUILA_BLOCK = registerBlock("tequila_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> BRANDY_BLOCK = registerBlock("brandy_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> RUM_BLOCK = registerBlock("rum_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> CHAMPAGNE_BLOCK = registerBlock("champagne_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> MOONSHINE_BLOCK = registerBlock("moonshine_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.GLASS)));

    public static final RegistryObject<Block> WINE_BLOCK = registerBlock("wine_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.GLASS)));

    public static final RegistryObject<Block> COGNAC_BLOCK = registerBlock("cognac_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.GLASS)));

    public static final RegistryObject<Block> CHOCOLATE_BLOCK = registerBlock("chocolate_block",
            () -> new Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CANDY_BLOCK = registerBlock("candy_block",
            () -> new Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> TEMPURA_BLOCK = registerBlock("tempura_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> BURGER_BLOCK = registerBlock("burger_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> SODA_BLOCK = registerBlock("soda_block",
            () -> new Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SUGAR_PACK_BLOCK = registerBlock("sugar_pack_block",
            () -> new Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> LOLIPOP_PACK_BLOCK = registerBlock("lolipop_pack_block",
            () -> new Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> FLOUR_PACK_BLOCK = registerBlock("flour_pack_block",
            () -> new Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> BUFFALO_WINGS_BLOCK = registerBlock("buffalo_wings_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> STEAK_AND_POTATOES_BLOCK = registerBlock("steak_and_potatoes_block",
            () -> new Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ORANGE_CHICKEN_BLOCK = registerBlock("orange_chicken_block",
            () -> new Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SALT_PACK_BLOCK = registerBlock("salt_pack_block",
            () -> new Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PEPPER_PACK_BLOCK = registerBlock("pepper_pack_block",
            () -> new Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PIZZA_BLOCK = registerBlock("pizza_block",
            () -> new Four_Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOJOS_BLOCK = registerBlock("mojos_block",
            () -> new Four_Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SUSHI_PLATE_BLOCK = registerBlock("sushi_plate_block",
            () -> new Six_Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> KATSUDON_BLOCK = registerBlock("katsudon_block",
            () -> new Six_Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CANNED_GOODS_BLOCK = registerBlock("canned_goods_block",
            () -> new Eight_Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> DONUT_BLOCK = registerBlock("donut_block",
            () -> new Six_Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PANCAKES_BLOCK = registerBlock("pancakes_block",
            () -> new Five_Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> DUMPLINGS_BLOCK = registerBlock("dumplings_block",
            () -> new Five_Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> TOASTER_BLOCK = registerBlock("toaster_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> BLENDER_BLOCK = registerBlock("blender_block",
            () -> new Rotatable_Glass_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS).noOcclusion()));

    public static final RegistryObject<Block> COFFEE_MAKER_BLOCK = registerBlock("coffee_maker_block",
            () -> new Rotatable_Glass_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS).noOcclusion()));

    public static final RegistryObject<Block> ESPRESSO_MACHINE_BLOCK = registerBlock("espresso_machine_block",
            () -> new Rotatable_Glass_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> MICROWAVE_BLOCK = registerBlock("microwave_block",
            () -> new Rotatable_Glass_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> STEW_BLOCK = registerBlock("stew_block",
            () -> new Rotatable_Block_With_Smoke(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SPICE_RACK_BLOCK = registerBlock("spice_rack_block",
            () -> new Wall__Three_Candle_Like_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SPICE_RACK_2_BLOCK = registerBlock("spice_rack_2_block",
            () -> new Wall__Three_Candle_Like_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CUTTING_BOARD_BLOCK = registerBlock("cutting_board_block",
            () -> new Display_Item_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SKILLET_BLOCK = registerBlock("skillet_block",
            () -> new Display_Item_Block_Smoke(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> PLATE_BLOCK = registerBlock("plate_block",
            () -> new Display_Item_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> JAPANESE_PLATE_BLOCK = registerBlock("japanese_plate_block",
            () -> new Display_Item_Block_Plus1(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PLATE_PILE_BLOCK = registerBlock("plate_pile_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> JUICE_PITCHER_BLOCK = registerBlock("juice_pitcher_block",
            () -> new Rotatable_Glass_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> PINK_JUICE_PITCHER_BLOCK = registerBlock("pink_juice_pitcher_block",
            () -> new Rotatable_Glass_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> BLUE_JUICE_PITCHER_BLOCK = registerBlock("blue_juice_pitcher_block",
            () -> new Rotatable_Glass_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> CUPCAKE_BLOCK = registerBlock("cupcake_block",
            () -> new Candle_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> GRILL_BLOCK = registerBlock("grill_block",
            () -> new Display_Item_Block_Smoke_Plus4(BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion().sound(SoundType.WOOD).lightLevel((state) -> 13)));

    public static final RegistryObject<Block> POT_BLOCK = registerBlock("pot_block",
            () -> new Rotatable_Block_With_Big_Smoke(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> SAUCE_PAN_BLOCK = registerBlock("sauce_pan_block",
            () -> new Rotatable_Block_With_Big_Smoke(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> SKEWER = registerBlock("skewer",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> EGG_STEAK_SANDWICH_BLOCK = registerBlock("egg_steak_sandwich_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> EGG_SANDWICH_BLOCK = registerBlock("egg_sandwich_block",
            () -> new Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> CAT_BOWL = registerBlock("cat_bowl",
            () -> new Cat_Bowl_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> CRISPY_PORK_BELLY_BLOCK = registerBlock("crispy_pork_belly_block",
            () -> new Four_Cake_Like_Rotatable_Block(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3,6))); //this means you will get 3-6 exp per mine of ore

    private static <T extends  Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
