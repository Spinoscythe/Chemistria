package io.github.spinoscythe.chemistria.datagen;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.block.ChemistriaBlocks;
import io.github.spinoscythe.chemistria.item.ChemistriaItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

public class ChemistriaModelProvider extends ModelProvider {

    public static final ResourceKey<EquipmentAsset> ONYX = createId("onyx");

    public ChemistriaModelProvider(PackOutput output) {
        super(output, Chemistria.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // ITEMS
        itemModels.generateFlatItem(ChemistriaItems.RAW_ONYX.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ChemistriaItems.ONYX_INGOT.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ChemistriaItems.PEAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ChemistriaItems.ONYX_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ChemistriaItems.ONYX_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ChemistriaItems.ONYX_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ChemistriaItems.ONYX_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ChemistriaItems.ONYX_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateTrimmableItem(ChemistriaItems.ONYX_HELMET.get(), ONYX, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ChemistriaItems.ONYX_CHESTPLATE.get(), ONYX, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ChemistriaItems.ONYX_LEGGINGS.get(), ONYX, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ChemistriaItems.ONYX_BOOTS.get(), ONYX, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        // BLOCKS
        blockModels.createTrivialCube(ChemistriaBlocks.ONYX_ORE.get());
        blockModels.createTrivialCube(ChemistriaBlocks.DEEPSLATE_ONYX_ORE.get());
        blockModels.createTrivialCube(ChemistriaBlocks.ONYX_BLOCK.get());
        blockModels.createTrivialCube(ChemistriaBlocks.RAW_ONYX_BLOCK.get());
        blockModels.createTrivialCube(ChemistriaBlocks.PEAT_BLOCK.get());
    }

    private static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Chemistria.rl(name));
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ChemistriaBlocks.BLOCKS.getEntries().stream().filter(h -> h != ChemistriaBlocks.BOILER_TANK);
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ChemistriaItems.ITEMS.getEntries().stream();
    }
}
