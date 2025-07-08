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

public class ChemistriaTrimMaterials {
    public static final ResourceKey<TrimMaterial> ONYX = registryKey("onyx");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, ONYX, Style.EMPTY.withColor(TextColor.parseColor("#031cfc").getOrThrow()), MaterialAssetGroup.create("bismuth"));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Style style, MaterialAssetGroup assets) {
        Component component = Component.translatable(Util.makeDescriptionId("trim_material", key.location())).withStyle(style);
        context.register(key, new TrimMaterial(assets, component));
    }

    private static ResourceKey<TrimMaterial> registryKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Chemistria.rl(name));
    }
}
