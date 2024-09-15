package net.matthew.first_mod.item;

import net.matthew.first_mod.block.ModBlocks;
import net.matthew.first_mod.first_mod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, first_mod.MODID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = CREATIVE_MOD_TABS.register("mod_tab" ,
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.mod_tab"))
                    .displayItems(((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SAPPHIRE.get());
                        pOutput.accept(ModItems.RAW_SAPPHIRE.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                        pOutput.accept(ModBlocks.BEER_BLOCK.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
                        pOutput.accept(ModBlocks.MILK_TEA_BLOCK.get());
                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                        pOutput.accept(ModItems.STRAWBERRY.get());
                        pOutput.accept(ModBlocks.CIGARETTE_BOX_BLOCK.get());
                        pOutput.accept(ModBlocks.BEER_SET_BLOCK.get());
                        pOutput.accept(ModBlocks.RAMEN_BLOCK.get());
                        pOutput.accept(ModBlocks.BEER_PILE_BLOCK.get());
                        pOutput.accept(ModBlocks.WHISKEY_BLOCK.get());
                        pOutput.accept(ModBlocks.SHOT_GLASS_BLOCK.get());
                        pOutput.accept(ModBlocks.GIN_BLOCK.get());
                        pOutput.accept(ModBlocks.TEQUILA_BLOCK.get());
                        pOutput.accept(ModBlocks.BRANDY_BLOCK.get());
                        pOutput.accept(ModBlocks.RUM_BLOCK.get());
                        pOutput.accept(ModBlocks.CHAMPAGNE_BLOCK.get());
                        pOutput.accept(ModBlocks.MOONSHINE_BLOCK.get());
                        pOutput.accept(ModBlocks.WINE_BLOCK.get());
                        pOutput.accept(ModBlocks.COGNAC_BLOCK.get());
                        pOutput.accept(ModBlocks.CHOCOLATE_BLOCK.get());
                        pOutput.accept(ModBlocks.CANDY_BLOCK.get());
                        pOutput.accept(ModBlocks.TEMPURA_BLOCK.get());
                        pOutput.accept(ModBlocks.BURGER_BLOCK.get());
                        pOutput.accept(ModBlocks.SODA_BLOCK.get());
                        pOutput.accept(ModBlocks.SUGAR_PACK_BLOCK.get());
                        pOutput.accept(ModBlocks.LOLIPOP_PACK_BLOCK.get());
                        pOutput.accept(ModBlocks.FLOUR_PACK_BLOCK.get());
                        pOutput.accept(ModBlocks.BUFFALO_WINGS_BLOCK.get());
                        pOutput.accept(ModBlocks.STEAK_AND_POTATOES_BLOCK.get());
                        pOutput.accept(ModBlocks.ORANGE_CHICKEN_BLOCK.get());
                        pOutput.accept(ModBlocks.SALT_PACK_BLOCK.get());
                        pOutput.accept(ModBlocks.PEPPER_PACK_BLOCK.get());
                        pOutput.accept(ModBlocks.PIZZA_BLOCK.get());
                        pOutput.accept(ModBlocks.MOJOS_BLOCK.get());
                        pOutput.accept(ModBlocks.SUSHI_PLATE_BLOCK.get());
                        pOutput.accept(ModBlocks.KATSUDON_BLOCK.get());
                        pOutput.accept(ModBlocks.CANNED_GOODS_BLOCK.get());
                        pOutput.accept(ModBlocks.DONUT_BLOCK.get());
                        pOutput.accept(ModBlocks.PANCAKES_BLOCK.get());
                        pOutput.accept(ModBlocks.DUMPLINGS_BLOCK.get());
                        pOutput.accept(ModBlocks.TOASTER_BLOCK.get());
                        pOutput.accept(ModBlocks.BLENDER_BLOCK.get());
                        pOutput.accept(ModBlocks.COFFEE_MAKER_BLOCK.get());
                        pOutput.accept(ModBlocks.ESPRESSO_MACHINE_BLOCK.get());
                        pOutput.accept(ModBlocks.MICROWAVE_BLOCK.get());
                        pOutput.accept(ModBlocks.STEW_BLOCK.get());
                        pOutput.accept(ModBlocks.SPICE_RACK_BLOCK.get());
                        pOutput.accept(ModBlocks.SPICE_RACK_2_BLOCK.get());
                        pOutput.accept(ModBlocks.CUTTING_BOARD_BLOCK.get());
                        pOutput.accept(ModBlocks.SKILLET_BLOCK.get());
                        pOutput.accept(ModBlocks.PLATE_BLOCK.get());
                        pOutput.accept(ModBlocks.JAPANESE_PLATE_BLOCK.get());
                        pOutput.accept(ModBlocks.PLATE_PILE_BLOCK.get());
                        pOutput.accept(ModBlocks.JUICE_PITCHER_BLOCK.get());
                        pOutput.accept(ModBlocks.PINK_JUICE_PITCHER_BLOCK.get());
                        pOutput.accept(ModBlocks.BLUE_JUICE_PITCHER_BLOCK.get());
                        pOutput.accept(ModBlocks.CUPCAKE_BLOCK.get());
                        pOutput.accept(ModBlocks.GRILL_BLOCK.get());
                        pOutput.accept(ModBlocks.POT_BLOCK.get());
                        pOutput.accept(ModBlocks.SAUCE_PAN_BLOCK.get());
                        pOutput.accept(ModBlocks.SKEWER.get());
                        pOutput.accept(ModItems.CHEF_HAT.get());
                        pOutput.accept(ModItems.EGG_STEAK_SANDWICH.get());
                        pOutput.accept(ModItems.EGG_SANDWICH.get());
                        pOutput.accept(ModItems.CAT_SPAWN_EGG.get());
                        pOutput.accept(ModBlocks.CAT_BOWL.get());
                        pOutput.accept(ModItems.DOUGH.get());
                        pOutput.accept(ModItems.KNEADED_DOUGH.get());
                        pOutput.accept(ModBlocks.CRISPY_PORK_BELLY_BLOCK.get());
                    })).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
