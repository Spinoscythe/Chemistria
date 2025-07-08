package io.github.spinoscythe.chemistria.datagen;

import io.github.spinoscythe.chemistria.block.ChemistriaBlocks;
import io.github.spinoscythe.chemistria.item.ChemistriaItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ChemistriaBlockLoot extends BlockLootSubProvider {
    protected ChemistriaBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ChemistriaBlocks.RAW_ONYX_BLOCK.get());
        this.dropSelf(ChemistriaBlocks.ONYX_BLOCK.get());

        this.add(ChemistriaBlocks.ONYX_ORE.get(), block -> createOreDrop(block, ChemistriaItems.RAW_ONYX.get()));
        this.add(ChemistriaBlocks.DEEPSLATE_ONYX_ORE.get(), block -> createOreDrop(block, ChemistriaItems.RAW_ONYX.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ChemistriaBlocks.BLOCKS.getEntries().stream().map(Holder::value).toList();
    }
}
