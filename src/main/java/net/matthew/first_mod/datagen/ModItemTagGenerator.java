package net.matthew.first_mod.datagen;

import net.matthew.first_mod.first_mod;
import net.matthew.first_mod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, first_mod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ModTags.Items.IS_COOKABLE)
                .add(Items.RABBIT)
                .add(Items.POTATO)
                .add(Items.CHICKEN)
                .add(Items.COD)
                .add(Items.MUTTON)
                .add(Items.PORKCHOP)
                .add(Items.SALMON)
                .add(Items.KELP)
                .add(Items.BEEF);

    }
}
