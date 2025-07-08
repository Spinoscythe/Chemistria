package io.github.spinoscythe.chemistria.worldgen;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.block.ChemistriaBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public final class ChemistriaOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ONYX = createKey("ore_onyx");

    public static void boostrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneOreReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        FeatureUtils.register(context,
                ORE_ONYX, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneOreReplaceables, ChemistriaBlocks.ONYX_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateOreReplaceables, ChemistriaBlocks.DEEPSLATE_ONYX_ORE.get().defaultBlockState())
                        ), 3
                )
        );
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Chemistria.rl(name));
    }
}
