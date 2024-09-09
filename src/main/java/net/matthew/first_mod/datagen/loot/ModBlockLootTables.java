package net.matthew.first_mod.datagen.loot;

import net.matthew.first_mod.block.ModBlocks;
import net.matthew.first_mod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBlocks.BEER_BLOCK.get());
        this.dropSelf(ModBlocks.MILK_TEA_BLOCK.get());
        this.dropSelf(ModBlocks.CIGARETTE_BOX_BLOCK.get());
        this.dropSelf(ModBlocks.BEER_SET_BLOCK.get());
        this.dropSelf(ModBlocks.RAMEN_BLOCK.get());
        this.dropSelf(ModBlocks.BEER_PILE_BLOCK.get());
        this.dropSelf(ModBlocks.WHISKEY_BLOCK.get());
        this.dropSelf(ModBlocks.SHOT_GLASS_BLOCK.get());
        this.dropSelf(ModBlocks.GIN_BLOCK.get());
        this.dropSelf(ModBlocks.TEQUILA_BLOCK.get());
        this.dropSelf(ModBlocks.BRANDY_BLOCK.get());
        this.dropSelf(ModBlocks.RUM_BLOCK.get());
        this.dropSelf(ModBlocks.CHAMPAGNE_BLOCK.get());
        this.dropSelf(ModBlocks.MOONSHINE_BLOCK.get());
        this.dropSelf(ModBlocks.WINE_BLOCK.get());
        this.dropSelf(ModBlocks.COGNAC_BLOCK.get());
        this.dropSelf(ModBlocks.CHOCOLATE_BLOCK.get());
        this.dropSelf(ModBlocks.CANDY_BLOCK.get());
        this.dropSelf(ModBlocks.TEMPURA_BLOCK.get());
        this.dropSelf(ModBlocks.BURGER_BLOCK.get());
        this.dropSelf(ModBlocks.SODA_BLOCK.get());
        this.dropSelf(ModBlocks.SUGAR_PACK_BLOCK.get());
        this.dropSelf(ModBlocks.LOLIPOP_PACK_BLOCK.get());
        this.dropSelf(ModBlocks.FLOUR_PACK_BLOCK.get());
        this.dropSelf(ModBlocks.BUFFALO_WINGS_BLOCK.get());
        this.dropSelf(ModBlocks.STEAK_AND_POTATOES_BLOCK.get());
        this.dropSelf(ModBlocks.ORANGE_CHICKEN_BLOCK.get());
        this.dropSelf(ModBlocks.SALT_PACK_BLOCK.get());
        this.dropSelf(ModBlocks.PEPPER_PACK_BLOCK.get());
        this.dropSelf(ModBlocks.PIZZA_BLOCK.get());
        this.dropSelf(ModBlocks.MOJOS_BLOCK.get());
        this.dropSelf(ModBlocks.SUSHI_PLATE_BLOCK.get());
        this.dropSelf(ModBlocks.KATSUDON_BLOCK.get());
        this.dropSelf(ModBlocks.CANNED_GOODS_BLOCK.get());
        this.dropSelf(ModBlocks.DONUT_BLOCK.get());
        this.dropSelf(ModBlocks.PANCAKES_BLOCK.get());
        this.dropSelf(ModBlocks.DUMPLINGS_BLOCK.get());
        this.dropSelf(ModBlocks.TOASTER_BLOCK.get());
        this.dropSelf(ModBlocks.BLENDER_BLOCK.get());
        this.dropSelf(ModBlocks.COFFEE_MAKER_BLOCK.get());
        this.dropSelf(ModBlocks.ESPRESSO_MACHINE_BLOCK.get());
        this.dropSelf(ModBlocks.MICROWAVE_BLOCK.get());
        this.dropSelf(ModBlocks.STEW_BLOCK.get());
        this.dropSelf(ModBlocks.SPICE_RACK_BLOCK.get());
        this.dropSelf(ModBlocks.SPICE_RACK_2_BLOCK.get());
        this.dropSelf(ModBlocks.CUTTING_BOARD_BLOCK.get());
        this.dropSelf(ModBlocks.SKILLET_BLOCK.get());
        this.dropSelf(ModBlocks.PLATE_BLOCK.get());
        this.dropSelf(ModBlocks.JAPANESE_PLATE_BLOCK.get());
        this.dropSelf(ModBlocks.PLATE_PILE_BLOCK.get());
        this.dropSelf(ModBlocks.JUICE_PITCHER_BLOCK.get());
        this.dropSelf(ModBlocks.PINK_JUICE_PITCHER_BLOCK.get());
        this.dropSelf(ModBlocks.BLUE_JUICE_PITCHER_BLOCK.get());
        this.dropSelf(ModBlocks.CUPCAKE_BLOCK.get());
        this.dropSelf(ModBlocks.GRILL_BLOCK.get());
        this.dropSelf(ModBlocks.POT_BLOCK.get());
        this.dropSelf(ModBlocks.SAUCE_PAN_BLOCK.get());
        this.dropSelf(ModBlocks.SKEWER.get());
        this.dropSelf(ModBlocks.EGG_STEAK_SANDWICH_BLOCK.get());
        this.dropSelf(ModBlocks.EGG_SANDWICH_BLOCK.get());
        this.dropSelf(ModBlocks.CAT_BOWL.get());


        this.add(ModBlocks.SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
