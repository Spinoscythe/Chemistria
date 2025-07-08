package io.github.spinoscythe.chemistria.datagen;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.block.ChemistriaBlocks;
import io.github.spinoscythe.chemistria.item.ChemistriaItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ChemistriaEnUsProvider extends LanguageProvider {
    public ChemistriaEnUsProvider(PackOutput output) {
        super(output, Chemistria.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ChemistriaBlocks.ONYX_ORE.get(), "Onyx Ore");
        add(ChemistriaBlocks.DEEPSLATE_ONYX_ORE.get(), "Deepslate Onyx Ore");
        add(ChemistriaBlocks.ONYX_BLOCK.get(), "Block Of Onyx");
        add(ChemistriaBlocks.RAW_ONYX_BLOCK.get(), "Block of Raw Onyx");

        add(ChemistriaItems.ONYX_INGOT.get(), "Onyx Ingot");
        add(ChemistriaItems.RAW_ONYX.get(), "Raw Onyx");

        add(ChemistriaItems.ONYX_PICKAXE.get(), "Onyx Pickaxe");
        add(ChemistriaItems.ONYX_AXE.get(), "Onyx Axe");
        add(ChemistriaItems.ONYX_SWORD.get(), "Onyx Sword");
        add(ChemistriaItems.ONYX_SHOVEL.get(), "Onyx Shovel");
        add(ChemistriaItems.ONYX_HOE.get(), "Onyx Hoe");

        add(ChemistriaItems.ONYX_HELMET.get(), "Onyx Helmet");
        add(ChemistriaItems.ONYX_CHESTPLATE.get(), "Onyx Chestplate");
        add(ChemistriaItems.ONYX_LEGGINGS.get(), "Onyx Leggings");
        add(ChemistriaItems.ONYX_BOOTS.get(), "Onyx Boots");
    }
}
