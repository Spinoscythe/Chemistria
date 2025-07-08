package io.github.spinoscythe.chemistria.item.armor;

import io.github.spinoscythe.chemistria.Chemistria;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimPattern;

public class ChemistriaTrimPatterns {
    public static final ResourceKey<TrimPattern> ONYX = registryKey("onyx");

    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        register(context, ONYX);
    }

    private static void register(BootstrapContext<TrimPattern> context, ResourceKey<TrimPattern> key) {
        TrimPattern pattern = new TrimPattern(Chemistria.rl("onyx"), Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false);
        context.register(ONYX, pattern);
    }

    private static ResourceKey<TrimPattern> registryKey(String name) {
        return ResourceKey.create(Registries.TRIM_PATTERN, Chemistria.rl(name));
    }
}
