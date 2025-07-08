package io.github.spinoscythe.chemistria.block;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.item.ChemistriaItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public final class ChemistriaBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Chemistria.MOD_ID);

    public static final DeferredBlock<DropExperienceBlock> ONYX_ORE = registerBlock("onyx_ore", properties -> new DropExperienceBlock(UniformInt.of(5, 14), properties.mapColor(DyeColor.GRAY).requiresCorrectToolForDrops()));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_ONYX_ORE = registerBlock("deepslate_onyx_ore", properties -> new DropExperienceBlock(UniformInt.of(5, 14), properties.mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ONYX_BLOCK = registerSimpleBlock("onyx_block", properties -> properties.mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.IRON));

    public static final DeferredBlock<Block> RAW_ONYX_BLOCK = registerSimpleBlock("raw_onyx_block", properties -> properties.mapColor(MapColor.RAW_IRON)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F));

    private static DeferredBlock<DropExperienceBlock> registerOre(String name, int min, int max, BlockBehaviour.Properties properties) {
        return registerBlock(name, p -> new DropExperienceBlock(UniformInt.of(min, max), properties));
    }

    private static DeferredBlock<Block> registerSimpleBlock(String name, UnaryOperator<BlockBehaviour.Properties> properties) {
        return registerBlock(name, p -> new Block(properties.apply(p)));
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        var toRegister = BLOCKS.registerBlock(name, function);
        ChemistriaItems.ITEMS.registerSimpleBlockItem(toRegister);
        return toRegister;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
