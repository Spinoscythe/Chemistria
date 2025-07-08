package io.github.spinoscythe.chemistria.datagen;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.item.ChemistriaItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ChemistriaTags {
    public static class Items extends ItemTagsProvider {
        public static final TagKey<Item> ONYX_TOOL_MATERIALS = ItemTags.create(Chemistria.rl("onyx_tool_materials"));

        public Items(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider, Chemistria.MOD_ID);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(ONYX_TOOL_MATERIALS).add(ChemistriaItems.ONYX_INGOT.get());
        }
    }

    public static class Blocks extends BlockTagsProvider {
        public static final TagKey<Block> INCORRECT_FOR_ONYX_TOOL = BlockTags.create(Chemistria.rl("onyx_tool_materials"));

        public Blocks(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider, Chemistria.MOD_ID);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(INCORRECT_FOR_ONYX_TOOL).addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        }
    }
}
