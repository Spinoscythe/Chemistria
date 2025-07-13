package io.github.spinoscythe.chemistria.datagen;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.enchantment.ChemistriaEnchantments;
import io.github.spinoscythe.chemistria.item.armor.ChemistriaTrimMaterials;
import io.github.spinoscythe.chemistria.item.armor.ChemistriaTrimPatterns;
import io.github.spinoscythe.chemistria.worldgen.ChemistriaOreFeatures;
import io.github.spinoscythe.chemistria.worldgen.ChemistriaOrePlacements;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Chemistria.MOD_ID)
public final class ChemistriaDataGenerators {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ChemistriaOreFeatures::boostrap)
            .add(Registries.PLACED_FEATURE, ChemistriaOrePlacements::bootstrap)
            .add(Registries.TRIM_MATERIAL, ChemistriaTrimMaterials::bootstrap)
            .add(Registries.TRIM_PATTERN, ChemistriaTrimPatterns::bootstrap)
            .add(Registries.ENCHANTMENT, ChemistriaEnchantments::bootstrap);

    private static final List<LootTableProvider.SubProviderEntry> SUB_PROVIDER_ENTRIES = List.of(
            new LootTableProvider.SubProviderEntry(ChemistriaBlockLoot::new, LootContextParamSets.BLOCK)
    );

    @SubscribeEvent
    public static void gatherClient(GatherDataEvent.Client event) {
        event.addProvider(new ChemistriaModelProvider(event.getGenerator().getPackOutput()));
        event.addProvider(new ChemistriaTags.Blocks(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.addProvider(new ChemistriaTags.Items(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.addProvider(new ChemistriaRecipeProvider.Runner(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.addProvider(new LootTableProvider(event.getGenerator().getPackOutput(), Set.of(), SUB_PROVIDER_ENTRIES, event.getLookupProvider()));
        event.addProvider(new ChemistriaEnUsProvider(event.getGenerator().getPackOutput()));
        event.addProvider(new ChemistriaDataMapProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.createDatapackRegistryObjects(BUILDER);
    }

    @SubscribeEvent
    public static void gatherServer(GatherDataEvent.Server event) {
        event.addProvider(new ChemistriaModelProvider(event.getGenerator().getPackOutput()));
        event.addProvider(new ChemistriaTags.Blocks(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.addProvider(new ChemistriaTags.Items(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.addProvider(new ChemistriaRecipeProvider.Runner(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.addProvider(new LootTableProvider(event.getGenerator().getPackOutput(), Set.of(), SUB_PROVIDER_ENTRIES, event.getLookupProvider()));
        event.addProvider(new ChemistriaEnUsProvider(event.getGenerator().getPackOutput()));
        event.addProvider(new ChemistriaDataMapProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.createDatapackRegistryObjects(BUILDER);
    }
}
