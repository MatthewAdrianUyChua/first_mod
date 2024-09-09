package net.matthew.first_mod.datagen;

import net.matthew.first_mod.block.ModBlocks;
import net.matthew.first_mod.block.custom.Eight_Candle_Like_Rotatable_Block;
import net.matthew.first_mod.block.custom.Rotatable_Block;
import net.matthew.first_mod.first_mod;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


import java.nio.file.Path;

public class ModBlockStateProvider extends BlockStateProvider {

    public static final IntegerProperty BITES_FOUR = IntegerProperty.create("bites", 0, 3);
    public static final IntegerProperty BITES_SIX = IntegerProperty.create("bites", 0, 5);

    public static final IntegerProperty BITES_FIVE = IntegerProperty.create("bites", 0, 4);

    public static final IntegerProperty CANDLES_THREE = IntegerProperty.create("candles", 1, 3);
    public static final IntegerProperty CANDLES_EIGHT = IntegerProperty.create("candles", 1, 8);

    public static final IntegerProperty STATES_TWO = IntegerProperty.create("states", 0, 1);

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper){
        super(output, first_mod.MODID, exFileHelper);
    }
    @Override
    protected void registerStatesAndModels() {


        directionalBlockWithItem(ModBlocks.SAPPHIRE_BLOCK, ResourceLocation.tryParse("first_mod:block/sapphire_block"));
        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        directionalBlockWithItem(ModBlocks.BEER_BLOCK, ResourceLocation.tryParse("first_mod:block/beer_block"));
        directionalBlockWithItem(ModBlocks.BEER_PILE_BLOCK, ResourceLocation.tryParse("first_mod:block/beer_pile_block"));
        directionalBlockWithItem(ModBlocks.BEER_SET_BLOCK, ResourceLocation.tryParse("first_mod:block/beer_set_block"));
        directionalBlockWithItem(ModBlocks.CIGARETTE_BOX_BLOCK, ResourceLocation.tryParse("first_mod:block/cigarette_box_block"));
        directionalBlockWithItem(ModBlocks.GIN_BLOCK, ResourceLocation.tryParse("first_mod:block/gin_block"));
        directionalBlockWithItem(ModBlocks.RAMEN_BLOCK, ResourceLocation.tryParse("first_mod:block/ramen_block"));
        directionalBlockWithItem(ModBlocks.TEQUILA_BLOCK, ResourceLocation.tryParse("first_mod:block/tequila_block"));
        directionalBlockWithItem(ModBlocks.WHISKEY_BLOCK, ResourceLocation.tryParse("first_mod:block/whiskey_block"));
        directionalBlockWithItem(ModBlocks.MILK_TEA_BLOCK, ResourceLocation.tryParse("first_mod:block/milk_tea_block"));
        directionalBlockWithItem(ModBlocks.BRANDY_BLOCK, ResourceLocation.tryParse("first_mod:block/brandy_block"));
        directionalBlockWithItem(ModBlocks.RUM_BLOCK, ResourceLocation.tryParse("first_mod:block/rum_block"));
        directionalBlockWithItem(ModBlocks.CHAMPAGNE_BLOCK, ResourceLocation.tryParse("first_mod:block/champagne_block"));
        directionalBlockWithItem(ModBlocks.MOONSHINE_BLOCK, ResourceLocation.tryParse("first_mod:block/moonshine_block"));
        directionalBlockWithItem(ModBlocks.WINE_BLOCK, ResourceLocation.tryParse("first_mod:block/wine_block"));
        directionalBlockWithItem(ModBlocks.COGNAC_BLOCK, ResourceLocation.tryParse("first_mod:block/cognac_block"));
        directionalBlockWithCandlesAndLit(ModBlocks.CHOCOLATE_BLOCK, ResourceLocation.tryParse("first_mod:block/chocolate_block"));
        directionalBlockWithCandlesAndLit(ModBlocks.CANDY_BLOCK, ResourceLocation.tryParse("first_mod:block/candy_block"));
        directionalBlockWithItem(ModBlocks.TEMPURA_BLOCK, ResourceLocation.tryParse("first_mod:block/tempura_block"));
        directionalBlockWithItem(ModBlocks.BURGER_BLOCK, ResourceLocation.tryParse("first_mod:block/burger_block"));
        directionalBlockWithCandlesAndLit(ModBlocks.SODA_BLOCK, ResourceLocation.tryParse("first_mod:block/soda_block"));
        directionalBlockWithCandlesAndLit(ModBlocks.SUGAR_PACK_BLOCK, ResourceLocation.tryParse("first_mod:block/sugar_pack_block"));
        directionalBlockWithCandlesAndLit(ModBlocks.LOLIPOP_PACK_BLOCK, ResourceLocation.tryParse("first_mod:block/lolipop_pack_block"));
        directionalBlockWithCandlesAndLit(ModBlocks.FLOUR_PACK_BLOCK, ResourceLocation.tryParse("first_mod:block/flour_pack_block"));
        directionalBlockWithItem(ModBlocks.BUFFALO_WINGS_BLOCK, ResourceLocation.tryParse("first_mod:block/buffalo_wings_block"));
        directionalBlockWithBites(ModBlocks.STEAK_AND_POTATOES_BLOCK, ResourceLocation.tryParse("first_mod:block/steak_and_potatoes_block"));
        directionalBlockWithBites(ModBlocks.ORANGE_CHICKEN_BLOCK, ResourceLocation.tryParse("first_mod:block/orange_chicken_block"));
        directionalBlockWithCandlesAndLit(ModBlocks.SALT_PACK_BLOCK, ResourceLocation.tryParse("first_mod:block/salt_pack_block"));
        directionalBlockWithCandlesAndLit(ModBlocks.PEPPER_PACK_BLOCK, ResourceLocation.tryParse("first_mod:block/pepper_pack_block"));
        directionalBlockWithFourBites(ModBlocks.PIZZA_BLOCK, ResourceLocation.tryParse("first_mod:block/pizza_block"));
        directionalBlockWithFourBites(ModBlocks.MOJOS_BLOCK, ResourceLocation.tryParse("first_mod:block/mojos_block"));
        directionalBlockWithSixBites(ModBlocks.SUSHI_PLATE_BLOCK, ResourceLocation.tryParse("first_mod:block/sushi_plate_block"));
        directionalBlockWithSixBites(ModBlocks.KATSUDON_BLOCK, ResourceLocation.tryParse("first_mod:block/katsudon_block"));
        eightDirectionalBlockWithCandlesAndLit(ModBlocks.CANNED_GOODS_BLOCK, ResourceLocation.tryParse("first_mod:block/canned_goods_block"));
        directionalBlockWithSixBites(ModBlocks.DONUT_BLOCK, ResourceLocation.tryParse("first_mod:block/donut_block"));
        directionalBlockWithFiveBites(ModBlocks.PANCAKES_BLOCK, ResourceLocation.tryParse("first_mod:block/pancakes_block"));
        directionalBlockWithFiveBites(ModBlocks.DUMPLINGS_BLOCK, ResourceLocation.tryParse("first_mod:block/dumplings_block"));
        directionalBlockWithItem(ModBlocks.TOASTER_BLOCK, ResourceLocation.tryParse("first_mod:block/toaster_block"));
        directionalBlockWithItem(ModBlocks.BLENDER_BLOCK, ResourceLocation.tryParse("first_mod:block/blender_block"));
        directionalBlockWithItem(ModBlocks.COFFEE_MAKER_BLOCK, ResourceLocation.tryParse("first_mod:block/coffee_maker_block"));
        directionalBlockWithItem(ModBlocks.ESPRESSO_MACHINE_BLOCK, ResourceLocation.tryParse("first_mod:block/espresso_machine_block"));
        directionalBlockWithItem(ModBlocks.MICROWAVE_BLOCK, ResourceLocation.tryParse("first_mod:block/microwave_block"));
        threeDirectionalWallBlockWithCandlesAndLit(ModBlocks.SPICE_RACK_BLOCK, ResourceLocation.tryParse("first_mod:block/spice_rack_block"));
        threeDirectionalWallBlockWithCandlesAndLit(ModBlocks.SPICE_RACK_2_BLOCK, ResourceLocation.tryParse("first_mod:block/spice_rack_2_block"));
        directionalBlockWithItem(ModBlocks.STEW_BLOCK, ResourceLocation.tryParse("first_mod:block/stew_block"));
        directionalBlockWithItem(ModBlocks.CUTTING_BOARD_BLOCK, ResourceLocation.tryParse("first_mod:block/cutting_board_block"));
        directionalBlockWithItem(ModBlocks.SKILLET_BLOCK, ResourceLocation.tryParse("first_mod:block/skillet_block"));
        directionalBlockWithItem(ModBlocks.PLATE_BLOCK, ResourceLocation.tryParse("first_mod:block/plate_block"));
        directionalBlockWithItem(ModBlocks.JAPANESE_PLATE_BLOCK, ResourceLocation.tryParse("first_mod:block/japanese_plate_block"));
        directionalBlockWithItem(ModBlocks.PLATE_PILE_BLOCK, ResourceLocation.tryParse("first_mod:block/plate_pile_block"));
        directionalBlockWithItem(ModBlocks.JUICE_PITCHER_BLOCK, ResourceLocation.tryParse("first_mod:block/juice_pitcher_block"));
        directionalBlockWithItem(ModBlocks.PINK_JUICE_PITCHER_BLOCK, ResourceLocation.tryParse("first_mod:block/pink_juice_pitcher_block"));
        directionalBlockWithItem(ModBlocks.BLUE_JUICE_PITCHER_BLOCK, ResourceLocation.tryParse("first_mod:block/blue_juice_pitcher_block"));
        directionalBlockWithItem(ModBlocks.GRILL_BLOCK, ResourceLocation.tryParse("first_mod:block/grill_block"));
        directionalBlockWithCandlesAndLit(ModBlocks.CUPCAKE_BLOCK, ResourceLocation.tryParse("first_mod:block/cupcake_block"));
        directionalBlockWithItem(ModBlocks.POT_BLOCK, ResourceLocation.tryParse("first_mod:block/pot_block"));
        directionalBlockWithItem(ModBlocks.SAUCE_PAN_BLOCK, ResourceLocation.tryParse("first_mod:block/sauce_pan_block"));
        directionalBlockWithItem(ModBlocks.SKEWER, ResourceLocation.tryParse("first_mod:block/skewer"));
        directionalBlockWithItem(ModBlocks.EGG_STEAK_SANDWICH_BLOCK, ResourceLocation.tryParse("first_mod:block/egg_steak_sandwich_block"));
        directionalBlockWithItem(ModBlocks.EGG_SANDWICH_BLOCK, ResourceLocation.tryParse("first_mod:block/egg_sandwich_block"));
        catBowlBlock(ModBlocks.CAT_BOWL, ResourceLocation.tryParse("first_mod:block/cat_bowl"));
        //blockWithItem(ModBlocks.CUTTING_BOARD_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void directionalBlockWithItem(RegistryObject<Block> blockRegistryObject, ResourceLocation path) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRotation = (int) direction.getOpposite().toYRot();

            return ConfiguredModel.builder()
                    .modelFile(itemModels().getExistingFile(path))
                    .rotationY(yRotation)
                    .build();
        });
        ModItemModelProvider.writeJsonFile("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + blockRegistryObject.getId().getPath() + ".json" , "first_mod:block/" + blockRegistryObject.getId().getPath());
        //simpleBlockItem(blockRegistryObject.get(), itemModels().getExistingFile(path));
    }

    private void directionalBlockWithCandlesAndLit(RegistryObject<Block> blockRegistryObject, ResourceLocation pathBase) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRotation = (int) direction.getOpposite().toYRot();
            int candles = state.getValue(BlockStateProperties.CANDLES);
            boolean lit = state.getValue(BlockStateProperties.LIT);

            // Build the path to the model file based on the number of candles
            ResourceLocation path = new ResourceLocation(pathBase.getNamespace(), pathBase.getPath() + candles);

            return ConfiguredModel.builder()
                    .modelFile(itemModels().getExistingFile(path))
                    .rotationY(yRotation)
                    .build();
        });

        // Generate item model

            ModItemModelProvider.writeJsonFile("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + blockRegistryObject.getId().getPath() + ".json" , "first_mod:block/" + blockRegistryObject.getId().getPath() + 4);
            //simpleBlockItem(blockRegistryObject.get(), itemModels().getExistingFile(path));

    }

    private void eightDirectionalBlockWithCandlesAndLit(RegistryObject<Block> blockRegistryObject, ResourceLocation pathBase) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRotation = (int) direction.getOpposite().toYRot();
            int candles = state.getValue(CANDLES_EIGHT);
            boolean lit = state.getValue(BlockStateProperties.LIT);

            // Build the path to the model file based on the number of candles
            ResourceLocation path = new ResourceLocation(pathBase.getNamespace(), pathBase.getPath() + candles);

            return ConfiguredModel.builder()
                    .modelFile(itemModels().getExistingFile(path))
                    .rotationY(yRotation)
                    .build();
        });

        // Generate item model

            ModItemModelProvider.writeJsonFile("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + blockRegistryObject.getId().getPath() + ".json" , "first_mod:block/" + blockRegistryObject.getId().getPath() + 8);
            //simpleBlockItem(blockRegistryObject.get(), itemModels().getExistingFile(path));

    }

    private void directionalBlockWithBites(RegistryObject<Block> blockRegistryObject, ResourceLocation pathBase) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRotation = (int) direction.getOpposite().toYRot();
            int bites = state.getValue(BlockStateProperties.BITES);

            // Build the path to the model file based on the number of bites
            ResourceLocation path = new ResourceLocation(pathBase.getNamespace(), pathBase.getPath() + bites);

            return ConfiguredModel.builder()
                    .modelFile(itemModels().getExistingFile(path))
                    .rotationY(yRotation)
                    .build();
        });

        // Generate item model for each bites state

            ModItemModelProvider.writeJsonFile("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + blockRegistryObject.getId().getPath() + ".json" , "first_mod:block/" + blockRegistryObject.getId().getPath() + "0");
            //simpleBlockItem(blockRegistryObject.get(), itemModels().getExistingFile(path));

    }

    private void directionalBlockWithFourBites(RegistryObject<Block> blockRegistryObject, ResourceLocation pathBase) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRotation = (int) direction.getOpposite().toYRot();
            int bites = state.getValue(BITES_FOUR);

            // Build the path to the model file based on the number of bites
            ResourceLocation path = new ResourceLocation(pathBase.getNamespace(), pathBase.getPath() + bites);

            return ConfiguredModel.builder()
                    .modelFile(itemModels().getExistingFile(path))
                    .rotationY(yRotation)
                    .build();
        });

        // Generate item model for each bites state

            ModItemModelProvider.writeJsonFile("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + blockRegistryObject.getId().getPath() + ".json" , "first_mod:block/" + blockRegistryObject.getId().getPath() + "0");
            //simpleBlockItem(blockRegistryObject.get(), itemModels().getExistingFile(path));

    }

    private void directionalBlockWithSixBites(RegistryObject<Block> blockRegistryObject, ResourceLocation pathBase) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRotation = (int) direction.getOpposite().toYRot();
            int bites = state.getValue(BITES_SIX);

            // Build the path to the model file based on the number of bites
            ResourceLocation path = new ResourceLocation(pathBase.getNamespace(), pathBase.getPath() + bites);

            return ConfiguredModel.builder()
                    .modelFile(itemModels().getExistingFile(path))
                    .rotationY(yRotation)
                    .build();
        });

        // Generate item model for each bites state

            ModItemModelProvider.writeJsonFile("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + blockRegistryObject.getId().getPath() + ".json" , "first_mod:block/" + blockRegistryObject.getId().getPath()+ "0");
            //simpleBlockItem(blockRegistryObject.get(), itemModels().getExistingFile(path));

    }

    private void directionalBlockWithFiveBites(RegistryObject<Block> blockRegistryObject, ResourceLocation pathBase) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRotation = (int) direction.getOpposite().toYRot();
            int bites = state.getValue(BITES_FIVE);

            // Build the path to the model file based on the number of bites
            ResourceLocation path = new ResourceLocation(pathBase.getNamespace(), pathBase.getPath() + bites);

            return ConfiguredModel.builder()
                    .modelFile(itemModels().getExistingFile(path))
                    .rotationY(yRotation)
                    .build();
        });

        // Generate item model for each bites state

            ModItemModelProvider.writeJsonFile("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + blockRegistryObject.getId().getPath() + ".json" , "first_mod:block/" + blockRegistryObject.getId().getPath() + "0");
            //simpleBlockItem(blockRegistryObject.get(), itemModels().getExistingFile(path));

    }

    private void threeDirectionalWallBlockWithCandlesAndLit(RegistryObject<Block> blockRegistryObject, ResourceLocation pathBase) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRotation = (int) direction.getOpposite().toYRot();
            int candles = state.getValue(CANDLES_THREE);
            boolean lit = state.getValue(BlockStateProperties.LIT);

            // Build the path to the model file based on the number of candles
            ResourceLocation path = new ResourceLocation(pathBase.getNamespace(), pathBase.getPath() + candles);

            return ConfiguredModel.builder()
                    .modelFile(itemModels().getExistingFile(path))
                    .rotationY(yRotation)
                    .build();
        });

        // Generate item model

        ModItemModelProvider.writeJsonFile("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + blockRegistryObject.getId().getPath() + ".json" , "first_mod:block/" + blockRegistryObject.getId().getPath() + 3);
        //simpleBlockItem(blockRegistryObject.get(), itemModels().getExistingFile(path));

    }

    private void catBowlBlock(RegistryObject<Block> blockRegistryObject, ResourceLocation pathBase) {
        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRotation = (int) direction.getOpposite().toYRot();
            int states= state.getValue(STATES_TWO);

            // Build the path to the model file based on the number of bites
            ResourceLocation path = new ResourceLocation(pathBase.getNamespace(), pathBase.getPath() + states);

            return ConfiguredModel.builder()
                    .modelFile(itemModels().getExistingFile(path))
                    .rotationY(yRotation)
                    .build();
        });

        // Generate item model for each bites state

        ModItemModelProvider.writeJsonFile("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + blockRegistryObject.getId().getPath() + ".json" , "first_mod:block/" + blockRegistryObject.getId().getPath() + "0");
        //simpleBlockItem(blockRegistryObject.get(), itemModels().getExistingFile(path));

    }



}
