package io.github.spinoscythe.chemistria.item;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.block.ChemistriaBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ChemistriaCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Chemistria.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CHEMISTRIA_MAIN = CREATIVE_MODE_TABS
            .register("main", () -> CreativeModeTab.builder()
                    .title(Component.literal("Chemistria"))
                    .icon(ChemistriaItems.RAW_ONYX::toStack)
                    .withSearchBar()
                    .displayItems((itemDisplayParameters, output) -> {
                        ChemistriaItems.ITEMS.getEntries().forEach(h -> output.accept(h.value()));
                        ChemistriaBlocks.BLOCKS.getEntries().forEach(h -> output.accept(h.value()));
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
